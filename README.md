# wechat-spring-boot
微信公众号开发
主体框架：
spring boot spring data jpa 持久层 freemarker试图 shiro安全  druid连接池




功能介绍：
1.shiro 登录（账号 admin 密码123456  数据库里是MD5加密后的）
2.shiro 权限（目前只添加了公众号关注账号的查询、个人信息同步）
3.swagger-ui restful api （登录成功后 访问http://localhost/yin/swagger-ui.html）
4 druid 日志监控 （登录成功后访问http://localhost/yin/druid/index.html（druid账号密码 admin）
5微信公众号配置 （关注消息回复 授权 关键字回复 ）
6 微信、支付宝支付（配置自己填写 方法完成 自己在controller 进行业务处理（也可以使用wexin java tools 可以自己查资料）
7.微信ACCESS_TOKEN 定时刷新  jsapiticket（WeixinJsapiTicketController）



