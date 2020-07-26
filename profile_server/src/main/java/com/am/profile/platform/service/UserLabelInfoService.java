package com.am.profile.platform.service;

import com.am.profile.platform.comm.Result;
import com.am.profile.platform.domain.vo.AddUserLabelInfoVo;

/**
 * @author wpl
 */
public interface UserLabelInfoService {
    Result getLabelId(String labelOwnAppNum, String labelVersion, String labelLevel);

    Result addInfo(AddUserLabelInfoVo vo);

    Result getLabelByParentId(
            Long labelParentId,
            Integer state,
            String labelLevel,
            Integer pageIndex,
            Integer pageSize
    );

    Result getLabelById(Long id);

    Result updateInfoById(AddUserLabelInfoVo vo);

    Result getLabels(
            Long labelRootId,
            String labelLevel,
            String labelVersion,
            String labelName,
            Integer state,
            Boolean distinct,
            String orderByClause,
            Integer pageIndex,
            Integer pageSize
    );

}
