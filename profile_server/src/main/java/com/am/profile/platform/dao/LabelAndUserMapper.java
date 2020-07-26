package com.am.profile.platform.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelAndUserMapper {
    //用户360的标签ids
    String getUserProfile(@Param("userId") Long userId);

    //通过集合获取标签ids
    String getLabelIds(@Param("collectorId") Long collectorId);

    //输入的标签id列表获取用户列表
    List<String> getUserIds(@Param("labelIds") List<Long> labelIds);
}
