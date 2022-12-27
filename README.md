# 企业访客管理系统
- 本项目已部署到服务器上,但在访问申请新增时因遇到跨域问题仍未解决,故生产环境的访问申请新增无法使用,其余功能均正常
- 使用完整功能需本地启动服务即可
- 最终版sql文件及数据为 ./doc/visitor.sql 创建名为visitor的数据库运行即可

# 模板功能 
- Spring Boot 2.7.0（贼新）
- Spring MVC
- MySQL 驱动
- MyBatis
- MyBatis Plus
- Spring Session Redis 分布式登录
- Spring AOP
- Apache Commons Lang3 工具类
- Lombok 注解
- Swagger + Knife4j 接口文档
- Spring Boot 调试工具和项目处理器
- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- JWT登录令牌

访问 
- localhost:7000/api/doc.html 生产环境
- localhost:39000/api/doc.html 测试环境
就能在线调试接口了

后端编写规范:
- 类/接口名理论上按照表名+类别(如service类就在末尾加上Service...)进行创建
- Service层写业务逻辑
- Controller层加上一些业务上的判断,如用户登录判断有没有填写账户密码等等，同时使用通用返回类BaseResponse类进行返回
- Controller中Get请求一般只有search方法使用,同时加上@RequestParam注解来设置分页器,可参考SysUserController的search方法
- Post请求传入的参数一般都加上@RequestBody注解---使用Json格式
- 增删改查,详情分别使用: insert、remove、update、search、detail 进行命名
- 同时必要的注释都要加上,推荐使用AlibabaFormat插件

一些问题记录:
- 前端最开始联调时无法请求到接口 ---- 因为前后端分离的缘故,故需添加跨域配置
- 前端发送请求时会先有一个OPTIONS请求,该请求无法通过 ---- JWT不应该用在OPTIONS请求上,故需排除OPTIONS的校验
- 前端访问服务器后端时需要携带cookie,使用set-cookie方法时会被浏览器自动拦截无法使用 ---- 