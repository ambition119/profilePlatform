package com.am.profile.platform.controller;

import com.am.profile.platform.comm.Result;
import com.am.profile.platform.domain.vo.AddUserLabelInfoVo;
import com.am.profile.platform.service.UserLabelInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/label")
@Api(value = "标签管理", description = "标签管理", tags = {"label manager controller"})
public class UserLabelInfoController {
    @Autowired
    UserLabelInfoService userLabelInfoService;

    @GetMapping(value = "/getLabelId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据所属产品对应编号,标签版本,标签层级,自动生成标签id", notes = "根据所属产品对应编号,标签版本,标签层级,自动生成标签id", response = Result.class)
    public ResponseEntity<?> getLabelId(
            @ApiParam(value = "标签层级") @RequestParam(value = "labelLevel") String labelLevel,
            @ApiParam(value = "所属产品编号") @RequestParam(value = "labelOwnAppNum", required = false) String labelOwnAppNum,
            @ApiParam(value = "标签版本") @RequestParam(value = "labelVersion", required = false) String labelVersion
    ) {
        Result result = userLabelInfoService.getLabelId(labelOwnAppNum, labelVersion, labelLevel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 子级别的标签先选择父标签
     * @param labelParentId
     * @param labelLevel
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getLabelByParentId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据父标签获取子标签信息", notes = "根据条件获取标签", response = Result.class)
    public ResponseEntity<?> getLabelByParentId(
            @ApiParam(value = "标签父id") @RequestParam(value = "labelParentId", required = false) Long labelParentId,
            @ApiParam(value = "标签状态") @RequestParam(value = "state", required = false) Integer state,
            @ApiParam(value = "标签层级") @RequestParam(value = "labelLevel", required = false) String labelLevel,
            @ApiParam(value = "分页角标") @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
            @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        Result result = userLabelInfoService.getLabelByParentId(
                labelParentId,
                state,
                labelLevel,
                pageIndex,
                pageSize
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addLabel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加标签", notes = "添加标签", response = Result.class)
    public ResponseEntity<?> addUserLabelInfo(@RequestBody AddUserLabelInfoVo vo) {
        Result result = userLabelInfoService.addInfo(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getLabelById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据主键id获取标签详情", notes = "根据主键id获取标签详情", response = Result.class)
    public ResponseEntity<?> getLabelById(
            @ApiParam(value = "主键id") @RequestParam(value = "id") Long id
    ) {
        Result result = userLabelInfoService.getLabelById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/updateLabel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改标签", notes = "修改标签", response = Result.class)
    public ResponseEntity<?> updateLabelById(@RequestBody AddUserLabelInfoVo vo) {

        Result result = userLabelInfoService.updateInfoById(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getLabels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据条件获取标签", notes = "根据条件获取标签", response = Result.class)
    public ResponseEntity<?> getMetas(
            @ApiParam(value = "标签层级") @RequestParam(value = "labelLevel", required = false) String labelLevel,
            @ApiParam(value = "标签版本") @RequestParam(value = "labelVersion", required = false) String labelVersion,
            @ApiParam(value = "分组id") @RequestParam(value = "labelRootId", required = false) Long labelRootId,
            @ApiParam(value = "标签名称") @RequestParam(value = "labelName", required = false) String labelName,
            @ApiParam(value = "状态") @RequestParam(value = "state", required = false) Integer state,
            @ApiParam(value = "去重") @RequestParam(value = "distinct", required = false) Boolean distinct,
            @ApiParam(value = "排序") @RequestParam(value = "orderByClause", required = false) String orderByClause,
            @ApiParam(value = "分页角标") @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
            @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        Result result = userLabelInfoService.getLabels(
                labelRootId,
                labelLevel,
                labelVersion,
                labelName,
                state,
                distinct,
                orderByClause,
                pageIndex,
                pageSize
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
