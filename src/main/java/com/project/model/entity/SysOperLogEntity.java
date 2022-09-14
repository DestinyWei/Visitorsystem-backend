package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录表 oper_log
 */
@ApiModel(value = "系统操作日志实体类")
@TableName("sys_oper_log")
@Data
public class SysOperLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 日志主键 */
    @ApiModelProperty(value = "日志Id")
    @TableId(type = IdType.AUTO)
    private Long operId;

    /** 操作模块 */
    @ApiModelProperty(value = "操作模块")
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    /** 业务类型数组 */
    @ApiModelProperty(value = "业务类型数组")
    private Integer[] businessTypes;

    /** 请求方法 */
    @ApiModelProperty(value = "请求方法")
    private String method;

    /** 请求方式 */
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    @ApiModelProperty(value = "操作类别（0其它 1后台用户 2手机端用户）")
    private Integer operatorType;

    /** 操作人员 */
    @ApiModelProperty(value = "操作人员")
    private String operName;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /** 请求url */
    @ApiModelProperty(value = "请求URL")
    private String operUrl;

    /** 操作地址 */
    @ApiModelProperty(value = "操作地址")
    private String operIp;

    /** 操作地点 */
    @ApiModelProperty(value = "操作地点")
    private String operLocation;

    /** 请求参数 */
    @ApiModelProperty(value = "请求参数")
    private String operParam;

    /** 返回参数 */
    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    /** 操作状态（0正常 1异常） */
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private Integer status;

    /** 错误消息 */
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    /** 操作时间 */
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operTime;
}
