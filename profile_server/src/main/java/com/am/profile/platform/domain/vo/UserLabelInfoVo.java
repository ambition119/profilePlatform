package com.am.profile.platform.domain.vo;


import com.am.profile.platform.comm.Utils;
import com.am.profile.platform.domain.dao.UserLabelInfo;
import lombok.Data;

import java.util.Date;

@Data
public class UserLabelInfoVo {
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

    private String updateTime;

    private String labelDesc;

    private String labelWorkFun;

    private String labelValueDesc;

    private String labelEffect;

    private String labelExample;

    public static UserLabelInfo getUserLabelInfo(UserLabelInfoVo vo){
        UserLabelInfo info = new UserLabelInfo();
        info.setId(vo.getId());
        info.setLabelId(vo.getLabelId());
        info.setLabelRootId(vo.getLabelRootId());
        info.setLabelParentId(vo.getLabelParentId());
        info.setLabelName(vo.getLabelName());
        info.setLabelOwnAppNum(vo.getLabelOwnAppNum());
        info.setLabelVersion(vo.getLabelVersion());
        info.setLabelLevel(vo.getLabelLevel());
        info.setLabelDesc(vo.getLabelDesc());
        info.setLabelOnlineTime(vo.getLabelOnlineTime());
        info.setState(vo.getState());

        info.setCreateTime(vo.getCreateTime());
        info.setUpdateTime(Utils.getStringToDate(vo.getUpdateTime()));

        info.setLabelWorkFun(vo.getLabelWorkFun());
        info.setLabelEffect(vo.getLabelEffect());
        info.setLabelEffect(vo.getLabelExample());
        return info;
    }
}
