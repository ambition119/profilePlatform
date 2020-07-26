package com.am.profile.platform.domain.vo;

import com.am.profile.platform.comm.Utils;
import com.am.profile.platform.domain.dao.UserLabelCollector;
import lombok.Data;

import java.util.Date;

@Data
public class UserLabelCollectorVo {
    private Long id;
    private String labelCollectorName;
    private String labelCollectorIds;
    private String labelCollectorOwner;
    private String labelCollectorOwnerEmail;
    private Integer state;
    private String createTime;
    private String updateTime;

    public static UserLabelCollector getUserLabelCollector(UserLabelCollectorVo vo){
        UserLabelCollector info = new UserLabelCollector();
        info.setId(vo.getId());
        info.setLabelCollectorName(vo.getLabelCollectorName());
        info.setLabelCollectorIds(vo.getLabelCollectorIds());
        info.setLabelCollectorOwner(vo.getLabelCollectorOwner());
        info.setLabelCollectorOwnerEmail(vo.getLabelCollectorOwnerEmail());
        info.setState(vo.getState());
        info.setCreateTime(new Date());
        if (null != vo.getUpdateTime()) {
            info.setUpdateTime(Utils.getStringToDate(vo.getUpdateTime()));
        }
        return info;
    }
}
