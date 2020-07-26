package com.am.profile.platform.domain.vo;

import com.am.profile.platform.comm.AppCode;
import com.am.profile.platform.comm.Utils;
import com.am.profile.platform.domain.dao.UserLabelInfo;
import lombok.Data;

/**
 * @Author: wpl
 * @Date: 2019/9/27
 */
@Data
public class DetailUserLabelInfoVo {
    private Long id;

    private Long labelId;

    private Long labelRootId;

    private String labelRootName;

    private String labelEnName;

    private String labelName;

    private Integer labelOwnAppNum;

    private String labelOwnAppName;

    private Integer labelVersion;

    private String labelLevel;

    private String labelDesc;

    private String labelParentIds;

    private String labelParentNames;

    private String labelOwner;

    private String labelOwnerEmail;

    private Integer state;

    private String updateTime;

    private String labelOnlineTime;

    private String labelWorkFun;

    private String labelValueDesc;

    private String labelEffect;

    private String labelExample;
}
