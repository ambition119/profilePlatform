package com.am.profile.platform.domain.vo;


import java.util.List;

import lombok.Data;

/**
 * @Author: wpl
 */
@Data
public class ListResponseVo<T> {
    private Long total;
    private Integer pageSize;
    private Integer pageIndex;
    private List<T> vos;
}

