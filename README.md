# 2019

###  tinytomcat
#### 迷你服务器：[simple-web-server](https://github.com/ackFinding/2019/tree/master/tinytomcat/simple-web-server)  
实现访问静态页面，程序入口是HttpServer类
#### 迷你servlet容器：[simple-servlet](https://github.com/ackFinding/2019/tree/master/tinytomcat/simple-servlet) 
实现访问静态页面和处理servlet，程序入口是HttpServer类。servlet路径等配置在Constants类，访问servlet会到该路径下找对应class文件。
#### 迷你tomcat：[simple-tomcat](https://github.com/ackFinding/2019/tree/master/tinytomcat/simple-tomcat)  
实现了简单的tomcat，程序入口是HttpServer类。实际是在迷你servlet容器基础上实现了访问url和servlet的对应关系。具体配置在resourcs\server.properties，详情见：[实现一个简单的Tomcat](https://www.jianshu.com/p/9c14795b58b0)
****
### chat-robot
#### 简介
Springboot搭建，将微信公众号接入图灵机器人，实现简单的文本交流   
https://github.com/ackFinding/2019/tree/master/chat-robot
#### 注意
需要自行修改代码中的apikey和userid
****
### distributed
#### 简介
redis和zookeeper实现分布式锁demo
