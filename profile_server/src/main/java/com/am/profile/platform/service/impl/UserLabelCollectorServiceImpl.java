package com.am.profile.platform.service.impl;

import com.am.profile.platform.comm.ErrorCode;
import com.am.profile.platform.comm.Result;
import com.am.profile.platform.comm.StateCode;
import com.am.profile.platform.dao.UserLabelCollectorMapper;
import com.am.profile.platform.domain.dao.UserLabelCollector;
import com.am.profile.platform.domain.vo.*;
import com.am.profile.platform.service.UserLabelCollectorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserLabelCollectorServiceImpl implements UserLabelCollectorService {
    private static final Logger LOG = LoggerFactory.getLogger(UserLabelCollectorServiceImpl.class);

    @Resource
    private UserLabelCollectorMapper mapper;

    @Override
    public Result addInfo(UserLabelCollectorVo vo) {
        LOG.info("addInfo: vo {}",  vo);
        if (StringUtils.isEmpty(vo.getLabelCollectorName()) || StringUtils.isEmpty(vo.getLabelCollectorIds())) {
            LOG.error("提交参数异常 " + vo);
            return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg("提交参数异常，注意非空字段 " + vo);
        }

        try {
            UserLabelCollectorVo result = mapper.getInfoByName(vo.getLabelCollectorName());
            if (result != null) {
                return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg( "数据库已经有id是: " + result.getId() + "的规则集合");
            }

            UserLabelCollector info = UserLabelCollectorVo.getUserLabelCollector(vo);
            info.setState(StateCode.IS_ADD.getCode());

            mapper.addInfo(info);
            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("id");
            responseVo.setValue(info.getId());

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("插入数据库失败 " + vo, e);
            return Result.fail(ErrorCode.FAIL_INSERT).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result updateInfoById(UserLabelCollectorVo vo) {
        LOG.info("updateMetaById: vo {}",  vo);
        try {
            UserLabelCollector info = UserLabelCollectorVo.getUserLabelCollector(vo);
            info.setState(StateCode.IS_UPDATE.getCode());
            mapper.updateInfoById(info);
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
    public Result getList(String labelCollectorName, Integer state, Boolean distinct, String orderByClause, Integer pageIndex, Integer pageSize) {
        boolean pageEnabled = false;
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
            pageEnabled = true;
        }

        try {
            List<UserLabelCollectorVo> vos = mapper.getList(labelCollectorName, state, distinct, orderByClause);
            Long total = pageEnabled ? new PageInfo(vos).getTotal() : vos.size();

            ListResponseVo<UserLabelCollectorVo> responseVo = new ListResponseVo();
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
}
