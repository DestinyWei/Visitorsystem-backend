package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-30-16:18
 * @version:
 */
@TableName("sys_oper_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysOperLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 日志主键 */
    private Long id;

    /** 操作模块 */
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer businessType;

    /** 请求方法 */
    private String method;

    /** 错误消息 */
    private String errorMsg;

    private Integer status;

    /** 操作时间 */
    private Date operTime;
}
