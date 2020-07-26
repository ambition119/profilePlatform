package com.am.profile.platform.domain.vo;


import com.am.profile.platform.domain.dao.UserLabelInfo;
import lombok.Data;

import java.util.Date;

/**
 * @author wpl
 */
@Data
public class AddUserLabelInfoVo {
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

    public static UserLabelInfo getUserLabelInfo(AddUserLabelInfoVo vo){
        UserLabelInfo info = new UserLabelInfo();
        info.setId(vo.getId());
        info.setLabelId(vo.getLabelId());
        info.setLabelRootId(vo.getLabelRootId());
        info.setLabelParentId(vo.getLabelParentId());
        info.setLabelEnName(vo.getLabelEnName());
        info.setLabelName(vo.getLabelName());
        info.setLabelOwnAppNum(vo.getLabelOwnAppNum());
        info.setLabelVersion(vo.getLabelVersion());
        info.setLabelLevel(vo.getLabelLevel());
        info.setLabelDesc(vo.getLabelDesc());
        info.setLabelOnlineTime(vo.getLabelOnlineTime());

        //用户和邮箱 是否vo传入
        info.setLabelOwner(vo.getLabelOwner());
        info.setLabelOwnerEmail(vo.getLabelOwnerEmail());

        info.setLabelValueDesc(vo.getLabelValueDesc());
        info.setLabelWorkFun(vo.getLabelWorkFun());
        info.setLabelEffect(vo.getLabelEffect());
        info.setLabelExample(vo.getLabelExample());

        info.setCreateTime(new Date());
        return info;
    }
}
