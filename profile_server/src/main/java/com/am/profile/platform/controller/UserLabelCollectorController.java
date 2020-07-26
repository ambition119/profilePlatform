package com.am.profile.platform.controller;

import com.am.profile.platform.comm.Result;
import com.am.profile.platform.domain.vo.UserLabelCollectorVo;
import com.am.profile.platform.service.UserLabelCollectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collector")
@Api(value = "标签集合", description = "标签集合", tags = {"label collector manager controller"})
public class UserLabelCollectorController {
    @Autowired
    UserLabelCollectorService service;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加集合", notes = "添加集合", response = Result.class)
    public ResponseEntity<?> addInfo(@RequestBody UserLabelCollectorVo vo) {
        Result result = service.addInfo(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改集合", notes = "修改集合", response = Result.class)
    public ResponseEntity<?> update(@RequestBody UserLabelCollectorVo vo) {

        Result result = service.updateInfoById(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据条件获取集合", notes = "根据条件获取集合", response = Result.class)
    public ResponseEntity<?> getMetas(
            @ApiParam(value = "集合名称") @RequestParam(value = "labelCollectorName", required = false) String labelCollectorName,
            @ApiParam(value = "状态") @RequestParam(value = "state", required = false) Integer state,
            @ApiParam(value = "去重") @RequestParam(value = "distinct", required = false) Boolean distinct,
            @ApiParam(value = "排序") @RequestParam(value = "orderByClause", required = false) String orderByClause,
            @ApiParam(value = "分页角标") @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
            @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        Result result = service.getList(
                labelCollectorName,
                state,
                distinct,
                orderByClause,
                pageIndex,
                pageSize
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
