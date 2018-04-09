# 整合过程分为：
1. 导包
2. springMVC与spring整合
3. 书写配置文件
   - 书写web.xml：配置springMVC的前端控制器、spring的监听器，包括各自的配置文件位置、还有解决POST提交参数乱码的过滤器（在spring-web包下）
   - 书写springmvc.xml：开启注解扫描、注解驱动、视图解析器（在spring-webmvc包下）
   - 书写application配置文件。暂时不需要写内容。
4. 测试整合情况
5. 整合spring和mybatis
   - 书写sqlMapConfig.xml配置文件：暂时不需要写
   - 书写applicationContext.xml：配置连接池、配置sqlSessionFactoryBean(在mybatisspring包)，需要注入连接池和configLocation、开启mapper扫描MapperScannerConfigure(在mybatisspring包)、配置事务扫描
  
项目结构：
![image](https://ruanwenjun.github.io/images/02.png)