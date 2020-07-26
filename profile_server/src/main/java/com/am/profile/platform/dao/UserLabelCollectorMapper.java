package com.am.profile.platform.dao;

import com.am.profile.platform.domain.dao.UserLabelCollector;
import com.am.profile.platform.domain.vo.UserLabelCollectorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLabelCollectorMapper {
    /**
     *  添加对象
     * @param info
     * @return 返回插入数量
     */
    int addInfo(UserLabelCollector info) throws Exception;

    /**
     * 根据标签信息记录主键id修改信息
     * @param info  修改对象
     * @return 返回插入数量
     */
    int updateInfoById(UserLabelCollector info) throws Exception;

    UserLabelCollectorVo getInfoByName( @Param("labelCollectorName")String labelCollectorName) throws Exception;

    /**
     * 根据条件查询所有记录
     * @return 查询所有记录
     */
    List<UserLabelCollectorVo> getList(
            @Param("labelCollectorName")String labelCollectorName,
            @Param("state")Integer state,
            @Param("distinct")Boolean distinct,
            @Param("orderByClause")String orderByClause
    ) throws Exception;


}
