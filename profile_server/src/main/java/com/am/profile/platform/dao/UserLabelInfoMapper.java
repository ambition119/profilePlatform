package com.am.profile.platform.dao;

import com.am.profile.platform.domain.dao.UserLabelInfo;
import com.am.profile.platform.domain.vo.ListUserLabelInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wpl
 */
public interface UserLabelInfoMapper {
    /**
     * 根据所属产品对应编号,标签版本,标签层级,获取目前最大的labelId
     * @param labelOwnAppNum
     * @param labelVersion
     * @param labelLevel
     * @return
     * @throws Exception
     */
    Long getMaxLabelId(
            @Param("labelOwnAppNum")Long labelOwnAppNum,
            @Param("labelVersion")Integer labelVersion,
            @Param("labelLevel")String labelLevel
    ) throws Exception;

    /**
     * 通过父标签id获取子标签信息
     */
    List<ListUserLabelInfoVo> getLabelByFirstParentId(
            @Param("state")Integer state,
            @Param("labelLevel")String labelLevel
    ) throws Exception;


    /**
     * 通过父标签id获取子标签信息
     */
    List<ListUserLabelInfoVo> getLabelByOtherParentId(
            @Param("state")Integer state,
            @Param("labelParentId")Long labelParentId,
            @Param("labelLevel")String labelLevel
    ) throws Exception;


    /**
     *  添加对象
     * @param info
     * @return 返回插入数量
     */
    int addInfo(UserLabelInfo info) throws Exception;


    /**
     * 主键id获取标签详细信息
     */
    ListUserLabelInfoVo getLabelById(@Param("id")Long id);

    /**
     * 根据标签信息记录主键id修改信息
     * @param info  修改对象
     * @return 返回插入数量
     */
    int updateInfoById(UserLabelInfo info) throws Exception;

    /**
     * labelId获取标签详细信息
     */
    ListUserLabelInfoVo getInfoByLabelId(@Param("labelId")Long labelId, @Param("labelVersion")Integer labelVersion);

    /**
     * 根据条件查询所有记录
     * @return 查询所有记录
     */
    List<ListUserLabelInfoVo> getLabels(
            @Param("labelRootId") Long labelRootId,
            @Param("labelLevel") String labelLevel,
            @Param("labelVersion")String labelVersion,
            @Param("labelName")String labelName,
            @Param("state")Integer state,
            @Param("distinct")Boolean distinct,
            @Param("orderByClause")String orderByClause
    ) throws Exception;

}
