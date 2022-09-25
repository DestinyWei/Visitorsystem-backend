package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.InfoReviewDto;
import com.project.model.dto.InfoScoreDto;
import com.project.model.entity.InfoReviewEntity;
import com.project.model.entity.InfoScoreEntity;
import com.project.model.enums.BusinessType;
import com.project.service.InfoScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 评分表接口
 * @author: dsl
 * @create: 2022-09-25-17:54
 * @version:
 */
@Api(tags = "信息评分接口")
@RestController
@RequestMapping("/infoScore")
public class InfoScoreController {

    @Resource
    private InfoScoreService infoScoreService;

    @ApiOperation(value = "信息评分新增")
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody InfoScoreEntity infoScoreEntity, HttpServletRequest request){
        return infoScoreService.insert(infoScoreEntity,request);
    }

    @ApiOperation(value = "信息评分删除")
    @Log(title = "信息评分管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(@ApiParam(value = "信息评分Id") Long id){
        return infoScoreService.remove(id);
    }

    @ApiOperation(value = "信息评分根据申请ID批量删除")
    @Log(title = "信息评分管理", businessType = BusinessType.DELETE)
    @PostMapping("/removes")
    @ResponseBody
    public BaseResponse removes(@ApiParam(value = "信息评分Ids") Long[] visitIds){
        return infoScoreService.removes(visitIds);
    }

    @ApiOperation(value = "信息评分修改")
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@Validated @RequestBody InfoScoreEntity infoScoreEntity){
        return infoScoreService.update(infoScoreEntity);
    }

    @ApiOperation(value = "根据评分Id查询评分表详情")
    @GetMapping("/selectById/{id}")
    @ResponseBody
    public BaseResponse selectById(@PathVariable("id") @ApiParam(value = "评分ID") Long id){
        return infoScoreService.selectById(id);
    }

    @ApiOperation(value = "根据访问申请Id查询评分表详情")
    @GetMapping("/selectByVisitId/{visitId}")
    @ResponseBody
    public BaseResponse selectByVisitId(@PathVariable("visitId") @ApiParam(value = "访问申请ID") Long visitId){
        return infoScoreService.selectByVisitId(visitId);
    }

    @ApiOperation(value = "信息评分模糊查询")
    @PostMapping("/search")
    @ResponseBody
    public BaseResponse search(@RequestBody InfoScoreDto infoScoreDto){
        return infoScoreService.search(infoScoreDto);
    }
}
