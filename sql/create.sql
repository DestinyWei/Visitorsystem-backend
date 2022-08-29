# 系统用户表
CREATE TABLE sys_user(
                        id                     		BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        department_id               BIGINT COMMENT '部门ID',
                        user_name                   VARCHAR(16) DEFAULT '' COMMENT '姓名',
                        sex                         VARCHAR(3) DEFAULT '' COMMENT '性别 0-未知 1-男 2-女',
                        id_number                   VARCHAR(18) DEFAULT '' COMMENT '身份证号',
                        phone                       VARCHAR(11) DEFAULT '' COMMENT '电话',
                        email                       VARCHAR(32) DEFAULT '' COMMENT '邮箱',
                        user_account                VARCHAR(16) DEFAULT '' COMMENT '账号',
                        user_password               VARCHAR(255) DEFAULT '' COMMENT '密码',
                        type                        VARCHAR(8) DEFAULT '' COMMENT '用户类型 0-默认 1-管理员 2-公司高管',
                        create_time                 DATETIME DEFAULT NULL COMMENT '创建时间',
                        update_time                 DATETIME DEFAULT NULL COMMENT '修改时间',
                        user_status                 VARCHAR(8) DEFAULT NULL COMMENT '状态 0-正常 1-停用',
                        is_delete                   TINYINT DEFAULT 0 COMMENT '是否删除(逻辑删除) 0-否 1-是',
                        PRIMARY KEY (id)
)ENGINE=MYISAM
;


# 系统角色表
CREATE TABLE sys_role(
                         id               BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                         role_name        VARCHAR(16) DEFAULT '' COMMENT '角色名称',
                         role_desc        VARCHAR(255) DEFAULT '' COMMENT '角色描述',
                         role_sort        VARCHAR(8) DEFAULT '' COMMENT '角色排序',
                         role_type        VARCHAR(8) DEFAULT '' COMMENT '角色类型 0-系统管理员 1-部门经理 2-普通角色',
                         create_user_id   BIGINT COMMENT '创建者ID',
                         create_time      DATETIME DEFAULT NULL COMMENT '创建时间',
                         PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 角色用户表
CREATE TABLE sys_user_role (
                           id		  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           user_id    BIGINT COMMENT '用户ID',
                           role_id    BIGINT COMMENT '角色ID',
                           PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 角色部门表
CREATE TABLE sys_role_dept (
                           id		  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           role_id    BIGINT COMMENT '角色ID',
                           dept_id    BIGINT COMMENT '部门ID',
                           PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 角色资源表
CREATE TABLE sys_role_resource(
                              id				 BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              resource_id        BIGINT COMMENT '资源ID',
                              role_id            BIGINT COMMENT '角色ID',
                              permission_type    VARCHAR(8) DEFAULT '' COMMENT '权限类型 1-可读可写 2-只读',
                              PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 系统资源表
CREATE TABLE sys_resource(
                             id             		 BIGINT NOT NULL AUTO_INCREMENT COMMENT '资源ID',
                             data_type               VARCHAR(8) DEFAULT '' COMMENT '数据类型',
                             parent_resource_id      BIGINT COMMENT '上级资源ID',
                             resource_type           VARCHAR(8) DEFAULT '' COMMENT '资源类型 1-系统资源 2-普通资源',
                             resource_name           VARCHAR(64) DEFAULT '' COMMENT '资源名称',
                             sort                    INT COMMENT '排序',
                             resource_url            VARCHAR(1000) DEFAULT '' COMMENT '资源URL',
                             photo_url               VARCHAR(500) DEFAULT '' COMMENT '图片URL',
                             resource_code           VARCHAR(64) DEFAULT '' COMMENT '资源编码',
                             authorization_status    INT DEFAULT NULL COMMENT '授权状态 0-未授权 1-已授权',
                             `status`                INT DEFAULT NULL COMMENT '状态 0-正常 1-停用',
                             create_user_id          BIGINT COMMENT '创建者ID',
                             create_time             DATETIME DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 部门信息表
CREATE TABLE sys_dept(
                          id          		     BIGINT NOT NULL AUTO_INCREMENT COMMENT '部门信息ID',
                          department_name        VARCHAR(64) DEFAULT '' COMMENT '部门名称',
                          department_code        VARCHAR(32) DEFAULT '' COMMENT '部门编码',
                          parent_department_id   BIGINT COMMENT '上级部门Id',
                          department_type        VARCHAR(8) DEFAULT '' COMMENT '部门类型',
                          unit                   VARCHAR(32) DEFAULT '' COMMENT '所属单位',
                          `level`                VARCHAR(8) DEFAULT '' COMMENT '级别 1-最高级别 以此类推',
                          department_note        VARCHAR(500) DEFAULT '' COMMENT '备注',
                          create_user_id         BIGINT COMMENT '创建者ID',
                          create_time            DATETIME DEFAULT NULL COMMENT '创建时间',
                          PRIMARY KEY (id)
)ENGINE=MYISAM
;


# 公司信息表
CREATE TABLE company_info(
                             id              		 BIGINT NOT NULL AUTO_INCREMENT COMMENT '公司信息ID',
                             company_name            VARCHAR(16) DEFAULT '' COMMENT '公司名称',
                             company_region          VARCHAR(16) DEFAULT '' COMMENT '地区 如:四川省/成都市/双流区',
                             company_address         VARCHAR(64) DEFAULT '' COMMENT '详细地址',
                             company_phone           VARCHAR(11) DEFAULT '' COMMENT '联系电话',
                             company_info            VARCHAR(255) DEFAULT '' COMMENT '企业信息',
                             company_introduction    VARCHAR(255) DEFAULT '' COMMENT '介绍',
                             create_time             DATETIME DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 访问申请信息表
CREATE TABLE apply_info(
                                 id                		   BIGINT NOT NULL AUTO_INCREMENT COMMENT '访问申请ID',
                                 start_time                DATETIME DEFAULT NULL COMMENT '访问申请开始时间',
                                 end_time                  DATETIME DEFAULT NULL COMMENT '访问申请结束时间',
                                 department_id             BIGINT COMMENT '访问申请的部门',
                                 apply_info                VARCHAR(100) DEFAULT '' COMMENT '申请详情',
                                 create_time               DATETIME DEFAULT NULL COMMENT '申请时间',
                                 applicant_id              BIGINT COMMENT '申请人ID',
                                 principal_id              BIGINT COMMENT '负责人ID',
                                 apply_status              VARCHAR(8) DEFAULT '' COMMENT '申请状态 0-待审核 1-审核中 2-已通过 3-驳回',
                                 company_id                BIGINT COMMENT '企业ID(申请的)',
                                 PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 信息收集表
CREATE TABLE info_collect(
                             id     		  BIGINT NOT NULL AUTO_INCREMENT COMMENT '信息采集ID',
                             visit_id         BIGINT COMMENT '访问申请ID',
                             user_id          BIGINT COMMENT '用户ID',
                             department_id    BIGINT COMMENT '部门ID',
                             create_time      DATETIME DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 人员信息审核表
CREATE TABLE info_review(
                          id			  BIGINT NOT NULL AUTO_INCREMENT COMMENT '审核ID',
                          reviewer_id     BIGINT COMMENT '审核人ID',
                          visit_id        BIGINT COMMENT '访问申请ID',
                          status          VARCHAR(8) DEFAULT '' COMMENT '状态 1-进行中 2-已完成 3-驳回',
                          remark          VARCHAR(255) DEFAULT '' COMMENT '备注',
                          create_time     DATETIME DEFAULT NULL COMMENT '创建时间',
                          PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 信息处理表
CREATE TABLE info_process(
                            id					BIGINT NOT NULL AUTO_INCREMENT COMMENT '处理ID',
                            collect_id          BIGINT COMMENT '信息采集ID',
                            reviewer_id         BIGINT COMMENT '审核ID',
                            score_id            BIGINT COMMENT '评分ID',
                            notice_id           BIGINT COMMENT '通知公告ID',
                            create_time         DATETIME DEFAULT NULL COMMENT '编辑时间',
                            PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 信息评分表
CREATE TABLE info_score(
                          id			  BIGINT NOT NULL AUTO_INCREMENT COMMENT '评分ID',
                          score_rule      VARCHAR(255) DEFAULT  '' COMMENT '评分细则',
                          is_use          VARCHAR(8) DEFAULT '' COMMENT '是否采用',
                          score           INT COMMENT '分数',
                          remark          VARCHAR(500) DEFAULT  '' COMMENT '备注',
                          collect_id      BIGINT COMMENT '采集ID',
                          create_user_id  BIGINT COMMENT '创建者ID',
                          create_time     DATETIME DEFAULT NULL COMMENT '创建时间',
                          PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 公告通知表
CREATE TABLE notice(
                       id               BIGINT NOT NULL AUTO_INCREMENT COMMENT '通知公告ID',
                       notice_title     VARCHAR(16) DEFAULT  '' COMMENT '标题',
                       notice_detail    VARCHAR(255) DEFAULT  '' COMMENT '内容',
                       notice_level     VARCHAR(8) DEFAULT  '' COMMENT '优先级',
                       notice_sorted    INT COMMENT '排序',
                       editor_id        BIGINT COMMENT '编辑者ID',
                       remark           VARCHAR(255) DEFAULT  '' COMMENT '备注',
                       post_range       VARCHAR(255) DEFAULT  '' COMMENT '发布范围',
                       post_time        DATETIME DEFAULT NULL COMMENT '发布时间',
                       create_time      DATETIME DEFAULT NULL COMMENT '创建时间',
                       PRIMARY KEY (id)
)ENGINE=MYISAM
;

# 字典数据表
CREATE TABLE `sys_dict_data` (
                                 `id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` INT ( 11 ) NULL DEFAULT 0 COMMENT '字典排序',
                                 `dict_label` VARCHAR ( 100 )
                                     DEFAULT '' COMMENT '字典标签',
                                 `dict_value` VARCHAR ( 100 )
                                     DEFAULT '' COMMENT '字典键值',
                                 `dict_type` VARCHAR ( 100 )
                                     DEFAULT '' COMMENT '字典类型',
                                 `css_class` VARCHAR ( 100 )
                                     DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` VARCHAR ( 100 )
                                     DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` CHAR ( 1 )
                                     DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` CHAR ( 1 )
                                     DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` VARCHAR ( 64 )
                                     DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime ( 0 ) DEFAULT NULL COMMENT '创建时间',
                                 `update_by` VARCHAR ( 64 )
                                     DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime ( 0 ) DEFAULT NULL COMMENT '更新时间',
                                 `remark` VARCHAR ( 500 )
                                     DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY ( `id` )
)ENGINE=MYISAM
;

# 字典类型表
CREATE TABLE `sys_dict_type` (
                                 `id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` VARCHAR ( 100 )
                                     DEFAULT '' COMMENT '字典名称',
                                 `dict_type` VARCHAR ( 100 )
                                     DEFAULT '' COMMENT '字典类型',
                                 `status` CHAR ( 1 )
                                     DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` VARCHAR ( 64 )
                                     DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime ( 0 ) DEFAULT NULL COMMENT '创建时间',
                                 `update_by` VARCHAR ( 64 )
                                     DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime ( 0 ) DEFAULT NULL COMMENT '更新时间',
                                 `remark` VARCHAR ( 500 )
                                     DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY ( `id` ),
                                 UNIQUE INDEX `dict_type` ( `dict_type` )
)ENGINE=MYISAM
;

CREATE TABLE `sys_oper_log` (
                                `id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                `title` VARCHAR ( 50 )
                                    DEFAULT '' COMMENT '模块标题',
                                `business_type` INT ( 11 ) DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                `method` VARCHAR ( 100 )
                                    DEFAULT '' COMMENT '方法名称',
                                `request_method` VARCHAR ( 10 )
                                    DEFAULT '' COMMENT '请求方式',
                                `operator_type` INT ( 11 ) DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                `oper_name` VARCHAR ( 50 )
                                    DEFAULT '' COMMENT '操作人员',
                                `dept_name` VARCHAR ( 50 )
                                    DEFAULT '' COMMENT '部门名称',
                                `oper_url` VARCHAR ( 255 )
                                    DEFAULT '' COMMENT '请求URL',
                                `oper_ip` VARCHAR ( 128 )
                                    DEFAULT '' COMMENT '主机地址',
                                `oper_location` VARCHAR ( 255 )
                                    DEFAULT '' COMMENT '操作地点',
                                `oper_param` VARCHAR ( 2000 )
                                    DEFAULT '' COMMENT '请求参数',
                                `json_result` VARCHAR ( 2000 )
                                    DEFAULT '' COMMENT '返回参数',
                                `status` INT ( 11 ) DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
                                `error_msg` VARCHAR ( 2000 )
                                    DEFAULT '' COMMENT '错误消息',
                                `oper_time` datetime ( 0 ) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ( 0 ) COMMENT '操作时间',
                                PRIMARY KEY ( `id` )
)ENGINE=MYISAM
;