package com.am.profile.platform.domain.dao;

import lombok.Data;

import java.util.Date;

@Data
public class UserLabelCollector {
    private Long id;
    private String labelCollectorName;
    private String labelCollectorIds;
    private String labelCollectorOwner;
    private String labelCollectorOwnerEmail;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}
