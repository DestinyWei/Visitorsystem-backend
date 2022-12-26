package com.project.model.request;

import lombok.Data;

/**
 * @description: 通用删除/批量删除请求体
 * @author: weihaoming
 * @create: 2022-12-26-11:36
 * @version:
 */
@Data
public class DeleteRequest {

    private Long id;

    private Long[] ids;
}
