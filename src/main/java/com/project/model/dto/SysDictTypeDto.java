package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.SysDictTypeEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-01-10:40
 * @version:
 */
@Data
public class SysDictTypeDto extends Page<SysDictTypeEntity> {

    private Long dictId;
    private String dictName;
    private String dictType;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
}
