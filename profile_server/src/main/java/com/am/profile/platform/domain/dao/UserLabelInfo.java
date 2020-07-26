package com.am.profile.platform.domain.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wpl
 */
@Data
public class UserLabelInfo {
    private Long id;

    private Long labelId;

    private Long labelRootId;

    private Long labelParentId;

    private String labelEnName;

    private String labelName;

    private Integer labelOwnAppNum;

    private Integer labelVersion;

    private String labelLevel;

    private String labelOnlineTime;

    private String labelOwner;

    private String labelOwnerEmail;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String labelDesc;

    private String labelWorkFun;

    private String labelValueDesc;

    private String labelEffect;

    private String labelExample;
}
