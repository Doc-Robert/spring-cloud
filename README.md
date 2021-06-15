# SPRING CLOUD 框架微服务 STUDY

# 第一章 微服务架构

## 1.1 微服务架构概述

> 概述

<img src="Spring-Cloud.assets/image-20210610154037783.png" alt="image-20210610154037783" />

==基于分布式的微服务架构==

![image-20210610154641866](README.assets/image-20210610154641866.png)

![image-20210610154653789](README.assets/image-20210610154653789.png)

## 1.2 Spring Cloud 简介

> 简介

![image-20210610154803555](README.assets/image-20210610154803555.png)

- 京东商城 微服务 架构体系

![image-20210610155518579](README.assets/image-20210610155518579.png)

- 阿里云

![image-20210610155552053](README.assets/image-20210610155552053.png)

- 京东物流

![image-20210610155628650](README.assets/image-20210610155628650.png)



- 无业务基础服务 与 业务型基础服务

![image-20210610155821166](README.assets/image-20210610155821166.png)

## 1.3 Spring Cloud技术栈

![image-20210610160009015](README.assets/image-20210610160009015.png)

- **主要技术栈**

> - 服务注册与发现
>   - EUREKA
> - 服务负载与调用    
>   - NETFLIX OSS RIBBON
>   - NETFLIX FEIGN
> - 服务熔断降级
>   - HYSTRIX
> - 服务网关
>   - NETFLIX OSS Zuul
> - 服务分布式配置
>   - Spring Cloud Config
> - 服务开发
>   - Spring Boot



## 1.4 总结

![image-20210610160444544](README.assets/image-20210610160444544.png)

# 第二章 springcloud 父工程组件

> 版本选择

版本:springboot 2.0（2.2/2.3） & spring cloud H版 

版本配置

![image-20210610162756709](README.assets/image-20210610162756709.png)



H版 版本

https://docs.spring.io/spring-cloud/docs/Hoxton.SR11/reference/html/

> **SpringCloud** 组件 更替

![image-20210611092542694](README.assets/image-20210611092542694.png)



## 2.1 springcloud 父工程创建

约定 > 配置 > 编码

> 创建微服务cloud整体聚合父工程Project，有8个关键步骤：
>
> - New Project - maven工程 - create from archetype: maven-archetype-site
> - 聚合总父工程名字
> - Maven选版本
> - 工程名字
> - 字符编码 - Settings - File encoding
> - 注解生效激活 - Settings - Annotation Processors
> - Java编译版本选8
> - File Type过滤 - Settings - File Type

父工程pom文件



```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>  
    <groupId>com.lun</groupId>
    <artifactId>LearnCloud</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging><!-- 这里添加，注意不是jar或war -->

    <!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- 子模块继承之后，提供作用：
 锁定版本+子modlue不用写groupId和version -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```



> 注意：
>
> Maven使用`dependencyManagement`元素来提供了一种管理依赖版本号的方式。
>
> 通常会在一个组织或者项目的最顶层的父POM中看到`dependencyManagement`元素。
>
> 使用pom.xml中的`dependencyManagement`元素能让所有在子项目中引用个依赖而不用显式的列出版本量。
>
> Maven会沿着父子层次向上走，直到找到一个拥有`dependencyManagement`元素的项目，然后它就会使用这个

~~~xml
<dependencyManagement>
    <dependencies>
        <dependency>
        <groupId>mysq1</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.2</version>
        </dependency>
    <dependencies>
</dependencyManagement>
~~~

然后在子项目里就可以添加`mysql-connector`时可以不指定版本号，例如：

```xml
<dependencies>
    <dependency>
    <groupId>mysq1</groupId>
    <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

这样做的好处就是：如果有多个子项目都引用同一样依赖，则可以避免在每个使用的子项目里都声明一个版本号，这样当想升级或切换到另一个版本时，只需要在顶层父容器里更新，而不需要一个一个子项目的修改；另外如果某个子项目需要另外的一个版本，只需要声明version就可。

dependencyManagement里只是声明依赖，并不实现引入，因此子项目需要显示的声明需要用的依赖。
如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项,并且没有指定具体版本，才会从父项目中继承该项，并且version和scope都读取自父pom。
如果子项目中指定了版本号，那么会使用子项目中指定的jar版本。

------

IDEA右侧旁的Maven插件有`Toggle ' Skip Tests' Mode`按钮，这样maven可以跳过单元测试

------

父工程创建完成执行`mvn : install`将父工程发布到仓库方便子工程继承。





## 2.2 REST微服务工程构建

### 2.2.1 支付模块的创建

创建微服务模块套路：

1. 建Module
2. 改POM
3. 写YML
4. 主启动
5. 业务类

![image-20210615143755747](README.assets/image-20210615143755747.png)

**1.建名为cloud-provider-payment8001的Maven工程**

**2.改POM**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>geek-springcloud</artifactId>
        <groupId>com.geek.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>
        <!--包含了sleuth+zipkin-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
        <!--eureka-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <!--
        <dependency>
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

**3.写YML**

```yml
#springcloud yml config
server:
  port: 8001


spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: com.mysql.jdbc.Driver            # mysql驱动包
    url: jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.lun.springcloud.entities    # 所有Entity别名类所在包
```

**4.主启动**

PaymentMain8001

```java
package com.geek.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Robert
 * @create 2021/6/15 15:10
 * @Version 1.0
 * @Description: main program class
 */

@SpringBootApplication
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

**5.业务类**

> SQL
>

~~~sql
CREATE TABLE `payment`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `serial` varchar(200) DEFAULT '',
	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4

~~~

> **Entities**：

实体类Payment：

```java
package com.geek.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Robert
 * @create 2021/6/15 15:25
 * @Version 1.0
 * @Description: payment model
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    //继承序列化
    private Long id;

    private String serial;
}

```

JSON封装体CommonResult：

```JAVA
package com.geek.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Robert
 * @create 2021/6/15 15:28
 * @Version 1.0
 * @Description: JSON封装体CommonResult
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code; // Data code; success or fail

    private String message; // Description

    //采用 泛型以确定传回的字符串 为其实体类型
    private T Data; //具体信息

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
```

> **DAO**：

接口PaymentDao：

```JAVA
package com.geek.springcloud.dao;

import com.geek.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Robert
 * @create 2021/6/15 15:35
 * @Version 1.0
 * @Description:
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
```

MyBatis映射文件PaymentMapper.xml，路径：resources/mapper/PaymentMapper.xml	

```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.geek.springcloud.dao.PaymentDao">

    <insert id="create" parameterType="Payment" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial)  values(#{serial});
    </insert>

    <!--作映射-->
    <resultMap id="BaseResultMap" type="com.geek.springcloud.entities.Payment">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <id column="serial" property="serial" jdbcType="VARCHAR"/>
    </resultMap>

    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id=#{id};
    </select>

</mapper>
```



> **Service**：

接口PaymentService

```JAVA
package com.geek.springcloud.service;

import com.geek.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Robert
 * @create 2021/6/15 15:46
 * @Version 1.0
 * @Description:
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
```

实现类

```JAVA
package com.geek.springcloud.service.impl;

import com.geek.springcloud.dao.PaymentDao;
import com.geek.springcloud.entities.Payment;
import com.geek.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/6/15 15:47
 * @Version 1.0
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
```

> **Controller**：

```JAVA
package com.geek.springcloud.controller;

import com.geek.springcloud.entities.CommonResult;
import com.geek.springcloud.entities.Payment;
import com.geek.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/6/15 15:50
 * @Version 1.0
 * @Description:
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("-----插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功");
        }else {
            return new CommonResult(403,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("-----查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(403,"查询失败，没有对应记录，查询id："+id,null);
        }
    }

}
```

**6.测试**

1. 浏览器 - http://localhost:8001/payment/get/1
2. Postman - http://localhost:8001/payment/create?serial=lun2

**7.小总结**

创建微服务模块套路：

1. 建Module
2. 改POM
3. 写YML
4. 主启动
5. 业务类

### 2.2.2 热部署 自动化

开发时使用，生产环境关闭

**1.Adding devtools to your project**

~~~
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
~~~

**2.Adding plugin to your pom.xml**

下段配置复制到聚合父类总工程的pom.xml

~~~xml
<build>
    <!--
	<finalName>你的工程名</finalName>（单一工程时添加）
    -->
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
~~~

**3.Enabling automatic build**

File -> Settings(New Project Settings->Settings for New Projects) ->Complier

下面项勾选

Automatically show first error in editor
Display notification on build completion
Build project automatically
Compile independent modules in parallel
**4.Update the value of**

键入Ctrl + Shift + Alt + / ，打开Registry，勾选：

compiler.automake.allow.when.app.running

actionSystem.assertFocusAccessFromEdt

**5.重启IDEA**.HttpMessageNotReadableException: Required request body is missing: public com.geek.springcloud.entities.CommonResult com.geek.springcloud.controller.PaymentController.create(com.geek.springcloud.entities.Payment)]