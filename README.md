# 本仓库存放的是开始学习JAVA-WEB开发的一些练手项目，这些也适合初学者进行练习
* [BOS物流系统](https://github.com/ruanwenjun/firstproject/tree/master/BOS物流系统)
* [SSH-CRM项目](https://github.com/ruanwenjun/firstproject/tree/master/SSH-CRM项目)
* [网络商城项目](https://github.com/ruanwenjun/firstproject/tree/master/网络商城项目)
* [SSM简单整合](https://github.com/ruanwenjun/firstproject/tree/master/SSM简单整合)
* [SSM简单练习项目--CRM](https://github.com/ruanwenjun/firstproject/tree/master/SSM简单练习项目--CRM)

如果你想练习可以采用本项目，fork之后clone即可，觉得对你有帮助可以给一颗星>ㅂ< :sparkling_heart:

## 网络商城项目是第一个JAVAWEB项目，适合初学者练手。
- 没有使用框架，采用servlet和JDBC进行开发
- sql文件夹里存放的是mysql建表，src文件夹里的是java代码，WEBCONTENT里的是页面。
- 实现了用户的注册、邮件激活、登陆、支付、商品的添加搜索和显示等功能

---

## SSH_CRM 是一个简单的spring+struts2+Hibernate整合项目
是一个crm人员关系管理系统。

管理员登陆系统可以对录入系统内的人员进行增删查改。

是一个练习ssh框架整合的入门项目，同时可以熟悉hibernate进行增删查改的操作。

技术上的提升：
- 在web层和dao层都提取了公共代码封装到BaseAction和BaseDao中，提高了代码的复用，同时按照OO设计原则，针对接口编程，而不是针对实现编程。
- 使用拦截器的方法对系统权限进行初步控制（后续可以采用Shiro框架进行更新)
- 对用户的密码采用MD5加密方法进行加密（可以采取多次MD5加密方式，和其他加密方式）

---

## BOS物流是一个物流管理系统

- 采用Struts2与前台页面进行数据的交互
- 使用sping注解的方式管理项目中的实体和事务的管理
- 采用Hibernate进行Dao层的搭建。
- 同时用Shiro框架进行了简单的权限控制，每个用户对应不同的角色，每个角色对应有不同的权限，用户登陆需要认证，访问action时hi需要授权（动态从数据库查取用户权限）。
- 前台页面使用HignCharts处理了一个简单的图标，用easyUi搭建前台所有页面
- 使用CXF进行了服务的调用（调用的是CRM项目中发布的一个服务）
- 使用POI完成了处理excel表格长传解析数据和将数据导出成excel表格的功能
- 采用json-lib完成项目中多处用到的Object对象转JSON数组的功能
- 采用了一个MD5utils工具类进行密码的MD5加密
- 采用ehcache框架进行了简单的登陆用户的权限缓存
- 数据库使用的是mysql，最后将项目部署在linux上成功运行


---
## SSM简单练习项目---CRM是一个页面项目
- 采用springMVC+spring+Mybatis框架整合开发
- 没有过多的功能，只包含从数据库中分页条件查询、修改数据、删除数据
- 用于熟悉SSM框架开发的流程


---

所有.sql里面存放的是sql建表语句
