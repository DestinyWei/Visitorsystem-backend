package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.InfoReviewDto;
import com.project.model.entity.InfoReviewEntity;
import com.project.model.enums.BusinessType;
import com.project.service.InfoReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 审核表接口
 * @author: dsl
 * @create: 2022-09-25-17:54
 * @version:
 */
@Api(tags = "信息审核接口")
@RestController
@RequestMapping("/infoReview")
public class InfoReviewController {

    @Resource
    private InfoReviewService infoReviewService;

    @ApiOperation(value = "信息审核新增")
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody InfoReviewEntity infoReviewEntity, HttpServletRequest request){
        return infoReviewService.insert(infoReviewEntity,request);
    }

    @ApiOperation(value = "信息审核删除")
    @Log(title = "信息审核管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(@ApiParam(value = "信息审核Id") Long id){
        return infoReviewService.remove(id);
    }

    @ApiOperation(value = "信息审核批量删除")
    @Log(title = "信息审核管理", businessType = BusinessType.DELETE)
    @PostMapping("/removes")
    @ResponseBody
    public BaseResponse removes(@ApiParam(value = "访问申请Ids") Long[] visitIds){
        return infoReviewService.removes(visitIds);
    }


    @ApiOperation(value = "信息审核修改")
    @Log(title = "信息审核管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@Validated @RequestBody InfoReviewEntity infoReviewEntity){
        return infoReviewService.update(infoReviewEntity);
    }

    @ApiOperation(value = "根据审核Id查询审核表详情")
    @GetMapping("/selectById/{id}")
    @ResponseBody
    public BaseResponse selectById(@PathVariable("id") @ApiParam(value = "审核ID") Long id){
        return infoReviewService.selectById(id);
    }

    @ApiOperation(value = "根据访问申请Id查询审核表详情")
    @GetMapping("/selectByVisitId/{visitId}")
    @ResponseBody
    public BaseResponse selectByVisitId(@PathVariable("visitId") @ApiParam(value = "访问申请ID") Long visitId){
        return infoReviewService.selectByVisitId(visitId);
    }

    @ApiOperation(value = "信息审核查询")
    @PostMapping("/search")
    @ResponseBody
    public BaseResponse search(@RequestBody InfoReviewDto infoReviewDto){
        return infoReviewService.search(infoReviewDto);
    }
}
