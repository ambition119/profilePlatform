package com.am.profile.platform.domain.vo;

import com.am.profile.platform.comm.AppCode;
import lombok.Data;

@Data
public class ListUserLabelInfoVo {
    private Long id;

    private Long labelId;

    private Long labelRootId;

    private String labelRootName;

    private String labelName;

    private String labelEnName;

    private Integer labelOwnAppNum;

    private String labelOwnAppName;

    private Integer labelVersion;

    private String labelLevel;

    private String labelDesc;

    private Long labelParentId;

    private String labelParentName;

    private String labelOwner;

    private String labelOwnerEmail;

    private Integer state;

    private String updateTime;

    private String labelOnlineTime;

    private String labelWorkFun;

    private String labelValueDesc;

    private String labelEffect;

    private String labelExample;

    public static DetailUserLabelInfoVo getDetailUserLabelInfoVo(ListUserLabelInfoVo info) {
        DetailUserLabelInfoVo vo = new DetailUserLabelInfoVo();

        vo.setId(info.getId());
        vo.setLabelId(info.getLabelId());
        vo.setLabelRootId(info.getLabelRootId());
        vo.setLabelRootName(info.getLabelRootName());
        vo.setLabelEnName(info.getLabelEnName());
        vo.setLabelName(info.getLabelName());
        vo.setLabelOwnAppNum(info.getLabelOwnAppNum());
        vo.setLabelOwnAppName(AppCode.getAppName(info.getLabelOwnAppNum()));

        vo.setLabelVersion(info.getLabelVersion());
        vo.setLabelLevel(info.getLabelLevel());
        vo.setLabelDesc(info.getLabelDesc());
        vo.setLabelOwner(info.getLabelOwner());
        vo.setLabelOwner(info.getLabelOwnerEmail());
        vo.setState(info.getState());

        vo.setLabelOnlineTime(info.getLabelOnlineTime());
        vo.setLabelWorkFun(info.getLabelWorkFun());
        vo.setLabelValueDesc(info.getLabelValueDesc());
        vo.setLabelEffect(info.getLabelEffect());
        vo.setLabelExample(info.getLabelExample());

        vo.setUpdateTime(info.getUpdateTime());
        return vo;
    }
}

