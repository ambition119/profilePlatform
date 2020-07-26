package com.am.profile.platform.service.impl;

import com.am.profile.platform.comm.*;
import com.am.profile.platform.dao.UserLabelInfoMapper;
import com.am.profile.platform.domain.dao.UserLabelInfo;
import com.am.profile.platform.domain.vo.*;
import com.am.profile.platform.service.UserLabelInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserLabelInfoServiceImpl implements UserLabelInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(UserLabelInfoServiceImpl.class);
    private static final String DEFAULT_DELIMITER = ",";
    private static final Integer DEFAULT_VERSION = 1;

    @Resource
    UserLabelInfoMapper userLabelInfoMapper;

    @Override
    public Result getLabelId(String labelOwnAppNum, String labelVersion, String labelLevel) {
        try {
            LOG.info("getLabelId: labelOwnAppNum {}, labelVersion {}, labelLevel {}",  labelOwnAppNum, labelVersion, labelLevel);
            Long maxId = userLabelInfoMapper.getMaxLabelId(
                    labelOwnAppNum != null ? Long.valueOf(labelOwnAppNum) : AppCode.VSKIT.getCode(),
                    labelVersion != null ? Integer.valueOf(labelVersion) : null,
                    labelLevel
            );
            LOG.info("根据条件查询最大标签主键是: {}",  maxId);
            ResponseVo<String> vo = new ResponseVo();
            String labelId = Utils.getLabelId(labelOwnAppNum, labelVersion, labelLevel,maxId);
            vo.setName("labelId");
            vo.setValue(labelId);
            return Result.success().withResponse(vo);
        } catch (Exception e) {
            LOG.error("根据条件查询最大标签主键失败", e);
            return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result getLabelByParentId(Long labelParentId, Integer state, String labelLevel, Integer pageIndex, Integer pageSize) {
        LOG.info("getLabelByParentInfo: labelParentId {}, state {}, labelLevel {}, pageIndex {}, pageSize {}", labelParentId, state, labelLevel, pageIndex, pageSize);
        boolean pageEnabled = false;
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
            pageEnabled = true;
        }

        try {
            List<ListUserLabelInfoVo> result = new ArrayList<>();
            //第一级遍历
            if (labelParentId == null) {
                List<ListUserLabelInfoVo> firstParentInfo = userLabelInfoMapper.getLabelByFirstParentId(state, labelLevel);
                result.addAll(firstParentInfo);
                //其他级别获取子标签
            } else {
                List<ListUserLabelInfoVo> otherParentInfo = userLabelInfoMapper.getLabelByOtherParentId(state,labelParentId,labelLevel);
                recursiveLabelId(state, result, otherParentInfo);
            }

            if (! CollectionUtils.isEmpty(result)) {
                Long total = pageEnabled ? new PageInfo(result).getTotal() : result.size();

                ListResponseVo responseVo = new ListResponseVo();
                responseVo.setVos(result);
                responseVo.setPageIndex(pageIndex);
                responseVo.setPageSize(pageSize);
                responseVo.setTotal(total);
                return Result.success().withResponse(responseVo);
            } else {
                ListResponseVo responseVo = new ListResponseVo();
                return Result.success().withResponse(responseVo);
            }
        } catch (Exception e) {
            LOG.error("根据父标签查询失败", e);
            return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result addInfo(AddUserLabelInfoVo vo) {
        LOG.info("addInfo: vo {}",  vo);
        if (StringUtils.isEmpty(vo.getLabelLevel())) {
            LOG.error("提交参数异常 " + vo);
            return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg("提交参数异常，注意非空字段 " + vo);
        }
        if (!vo.getLabelLevel().trim().equals(LabelLevel.Level_1.getLevel())) {
            if (null == vo.getLabelParentId() || vo.getLabelParentId() <= 0) {
                LOG.error("提交参数异常 " + vo);
                return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg("提交参数异常，注意字段要求 " + vo);
            }
        }

        try {
            Integer labelVersion = vo.getLabelVersion() != null ? vo.getLabelVersion() : DEFAULT_VERSION;
            ListUserLabelInfoVo result = userLabelInfoMapper.getInfoByLabelId(vo.getLabelId(),
                    labelVersion
            );

            if (result != null) {
                return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg( "数据库已经有标签id是: " + vo.getLabelId() + " ,版本是: " + labelVersion);
            }

            UserLabelInfo info = AddUserLabelInfoVo.getUserLabelInfo(vo);
            info.setState(StateCode.IS_ADD.getCode());

            userLabelInfoMapper.addInfo(info);
            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("id");
            responseVo.setValue(info.getId());

            if (info.getLabelParentId() == null) {
                info.setLabelRootId(info.getId());

                UserLabelInfo in = new UserLabelInfo();
                in.setLabelRootId(info.getId());
                in.setId(info.getId());
                userLabelInfoMapper.updateInfoById(in);
            }

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("插入数据库失败 " + vo, e);
            return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result getLabelById(Long id) {
        LOG.info("getInfoById: id {}",  id);
        try {
            ListUserLabelInfoVo info = userLabelInfoMapper.getLabelById(id);
            DetailUserLabelInfoVo vo = ListUserLabelInfoVo.getDetailUserLabelInfoVo(info);

            if (!info.getLabelVersion().equals(LabelLevel.Level_1.getLevel())) {
                StringBuilder parentNames = new StringBuilder();
                StringBuilder parentLabelIds = new StringBuilder();
                downToUpLabelIds(info, parentNames, parentLabelIds);

                if (parentNames.toString().endsWith(DEFAULT_DELIMITER)) {
                    parentNames.deleteCharAt(parentNames.length() - 1);
                }
                if (parentLabelIds.toString().endsWith(DEFAULT_DELIMITER)) {
                    parentLabelIds.deleteCharAt(parentLabelIds.length() - 1);
                }

                vo.setLabelParentNames(parentNames.toString());
                vo.setLabelParentIds(parentLabelIds.toString());
            }

            return Result.success().withResponse(vo);
        } catch (Exception e) {
            LOG.error("根据条件查询最大标签主键失败", e);
            return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result updateInfoById(AddUserLabelInfoVo vo) {
        LOG.info("updateMetaById: vo {}",  vo);
        try {
            UserLabelInfo info = AddUserLabelInfoVo.getUserLabelInfo(vo);
            info.setState(StateCode.IS_UPDATE.getCode());
            userLabelInfoMapper.updateInfoById(info);
            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("id");
            responseVo.setValue(info.getId());

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("根据主键id修改信息失败" + vo, e);
            return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result getLabels(Long labelRootId, String labelLevel, String labelVersion, String labelName, Integer state, Boolean distinct, String orderByClause, Integer pageIndex, Integer pageSize) {
        LOG.info("getLabels: labelRootId {}, labelLevel {}, labelVersion {}, labelName {}, state {}, distinct {}, orderByClause {}, pageIndex {}, pageSize {}",
                labelRootId, labelLevel, labelVersion, labelName, state, distinct, orderByClause, pageIndex, pageSize);
        boolean pageEnabled = false;
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
            pageEnabled = true;
        }

        try {
            List<ListUserLabelInfoVo> vos = userLabelInfoMapper.getLabels(labelRootId,
                    labelLevel, labelVersion, labelName,
                    state, distinct, orderByClause);

            Long total = pageEnabled ? new PageInfo(vos).getTotal() : vos.size();

            ListResponseVo responseVo = new ListResponseVo();
            responseVo.setVos(vos);

            responseVo.setPageIndex(pageIndex);
            responseVo.setPageSize(pageSize);
            responseVo.setTotal(total);

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("根据条件查询记录失败", e);
            return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(e.getMessage());
        }
    }

    private void downToUpLabelIds(ListUserLabelInfoVo info, StringBuilder parentNames,
                                  StringBuilder parentLabelIds) {
        if (null == info) {
            return;
        }

        if (!info.getLabelLevel().equals(LabelLevel.Level_1.getLevel())) {
            ListUserLabelInfoVo parentLabelInfoVo =userLabelInfoMapper.getLabelById(info.getLabelParentId());
            parentNames.append(parentLabelInfoVo.getLabelName()).append(DEFAULT_DELIMITER);
            parentLabelIds.append(parentLabelInfoVo.getId()).append(DEFAULT_DELIMITER);
            downToUpLabelIds(parentLabelInfoVo, parentNames, parentLabelIds);
        }
    }

    private void recursiveLabelId(Integer state, List<ListUserLabelInfoVo> result, List<ListUserLabelInfoVo> labelByParentInfoVos) throws Exception{
        if (CollectionUtils.isEmpty(labelByParentInfoVos)) {
            return;
        }
        for (ListUserLabelInfoVo vo : labelByParentInfoVos) {
            result.add(vo);
            if (vo.getState() != StateCode.IS_DEL.getCode() && !vo.getLabelLevel().equals(LabelLevel.Level_5.getLevel())) {

                List<ListUserLabelInfoVo> parentInfo = userLabelInfoMapper.getLabelByOtherParentId(state, vo.getLabelId(),vo.getLabelLevel());
                recursiveLabelId(state, result, parentInfo);
            }
        }
    }

}
