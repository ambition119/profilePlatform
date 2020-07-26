package com.am.profile.platform.service;

import com.am.profile.platform.comm.Result;
import com.am.profile.platform.domain.vo.UserLabelCollectorVo;

public interface UserLabelCollectorService {
    Result addInfo(UserLabelCollectorVo vo);

    Result updateInfoById(UserLabelCollectorVo vo);

    Result getList(
            String labelCollectorName,
            Integer state,
            Boolean distinct,
            String orderByClause,
            Integer pageIndex,
            Integer pageSize
    );
}
