### SpringCloud

#### CAP原则

```
CAP原则又称CAP定理，指的是在一个分布式系统中， Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性），三者不可得兼。
一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访问同一份最新的数据副本）
可用性（A）：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性）
分区容忍性（P）：以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择。
CAP原则的精髓就是要么AP，要么CP，要么AC，但是不存在CAP。如果在某个分布式系统中数据无副本， 那么系统必然满足强一致性条件， 因为只有独一数据，不会出现数据不一致的情况，此时C和P两要素具备，但是如果系统发生了网络分区状况或者宕机，必然导致某些数据不可以访问，此时可用性条件就不能被满足，即在此情况下获得了CP系统，但是CAP不可同时满足 [1]  。
```



#### Eureka: 服务注册于发现

1. ##### 什么是Eureka?

   ​	Netflix在设计Eureka时，遵循的就是AP原则.

      Eureka是SpringCloud的核心模块之一，Eureka是一个基于RestFul的服务，用于定位服务，以实现云端中间层服务发现和故障转移，服务注册与发现对与微服务来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，功能类十余Dubbo的注册中心，比如Zookeeper;

2. 原理讲解

   Eureka的基本架构

   - ​	Eureka采用了C/S的架构设计，EurekaServer作为服务注册功能的服务器，他是服务注册中心

   - 而系统中的其他微服务，使用Eureka的客户端连接到EurekaServer并维持心跳连接，这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的一些其他模块（比如Zuul）就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑；

   - Eureka包含两个组件：Eureka Server 和 Eureka Client.

   - Eureka Client 是一个Java客户端，用于简化EurekaServer的交互，客户端同时也具备一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳（默认周期为30秒）。如果EurekaServer在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中吧这个服务节点移除掉（默认周期为90秒）

     

     3. 三大角色
        - Eureua Server :提供服务的注册与发现。类似Zookeeper
        - Service Provider: 将自身服务注册到Eureka中，从而使消费方能够找到。
        - Service Consumer: 服务消费方从Eureka中获取服务列表，从而找到消费服务

#### 使用Eureka

##### Eureka Server

- 导入依赖

  ```pom
  <!-- Spring Cloud 服务注册组件 -->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <!-- 此处的依赖是SpringBoot2.0以后专用的，如果您使用的SpringBoot版本低于2.0请使用spring-cloud-starter-eureka-server -->
              <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
          </dependency>
          <!-- Spring Boot 安全组件 -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-security</artifactId>
          </dependency>
  ```

- 添加配置

  ```yml
  #==================== Eureka ============================
  eureka:
    instance:
      hostname: localhost  #配置使用主机名注册服务
      instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}:@project.version@
      prefer-ip-address: true #可以将IP注册到Eureka Server上，而如果不配置就是机器的主机名。
      leaseRenewalIntervalInSeconds: 10 #租期更新时间间隔(默认30秒).每间隔10s，向服务端发送一次心跳，证明自己依然”存活“
      leaseExpirationDurationInSeconds: 30 #租期到期时间(默认90秒).告诉服务端，如果我30s之内没有给你发心跳，就代表我“死”了，将我踢出掉
    server:       #监控页面
      enable-self-preservation: false #测试时关闭自我保护机制，保证不可用服务及时踢出
      eviction-interval-timer-in-ms: 4000 #清理间隔(单位毫秒，默认是60*1000)
    client:
      register-with-eureka: false  #表示是否将自身注册到Eureka注册中心
      fetch-registry: false #是否到eureka服务器中抓取注册信息,因为自己本身就是eureka，所有不需要
      service-url:
        defaultZone: http://${spring.security.user.name}:${spring.security.user.password}@${eureka.instance.hostname}:${server.port}/eureka/
  ```

- 添加注解

  ```java
  @EnableEurekaServer  //添加到启动类上，标识这是一个eureka服务端
  ```

- 测试访问

  ![image-20200909133900293](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200909133900293.png)

##### Eureka Client

- 将eureka客户端注册金eureka服务端
- 在新的服务里面添加依赖

```pom
   <!-- Spring Cloud Eureka Client -->
   <dependency>
   		<groupId>org.springframework.cloud</groupId>
   		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
   </dependency>
```

- 添加配置文件

  ```yml
  #==================== Eureka Client============================
  eureka:
    instance:
      hostname: localhost #配置使用主机名注册服务
      instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}:@project.version@ #可以修改eureka上status的默认描述信息
      prefer-ip-address: true       #可以将IP注册到Eureka Server上，而如果不配置就是机器的主机名
      leaseRenewalIntervalInSeconds: 10 #租期更新时间间隔(默认30秒).每间隔10s，向服务端发送一次心跳，证明自己依然”存活“
      leaseExpirationDurationInSeconds: 30 #租期到期时间(默认90秒).告诉服务端，如果我30s之内没有给你发心跳，就代表我“死”了，将我踢出掉
    client:
      service-url:
        defaultZone: http://zh:zh12@localhost:8801/eureka/ #这里zh:zh12 是自己添加的安全认证，不想添加可以直接在eurekaServer端和这里统一去掉
  ```

- 添加启动注解

  ```java
  @EnableDiscoveryClient #添加到启动类上，标识这是一个eureka的客户端，启动成功后去注册到eureka服务注册中心
  ```

- 访问eureka注册中心

![image-20200909135921848](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200909135921848.png)

##### CAP 和 ACID

RDBMS (MySql,Oracle,SqlServer ) ==> ACID

NoSql (Redis, Mongdb) ==> CAP

###### ACID

- A: Atormicity  原子性
- C: Consistency 一致性
- I : Isolation 隔离性
- D: Durability 持久性

###### CAP

- C: Consistency 强一致性

- A: Availability 可用性

- P: Partition tolerance 分区容错性

  CAP的三进二：CA ,AP ,CP

##### CAP理论的核心

- 一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求
- 根据CAP原理，将NoSql数据库分成了满足CA原则，满足CP原则和满足AP原则三大类：
- CA: 单点集群，满足一致性，可用性的系统，通常可扩展性较差
- CP: 满足一致性，分区容错性的系统，通常性能不是特别高
- AP: 满足可用性，分区容错性的系统通常可能对一致性要求低一些

##### Eureka 和 Zookeeper的区别

- Zookeeper 保证的是CP
- Eureka保证的是AP

#### Ribbon

##### Ribbon是什么

- Spring Cloud Ribbon是基于Netflix Ribbon 实现的一套客户端负载均衡的工具

- 主要功能是提供客户端的软件负载均衡算法，将Netfilx的中间成服务连接在一起，Ribbon的客户端组件提供一系列完整的配置项，如：连接超时，重试等等，简单的说，就是在配置文件中列出LoadBalancer(简称LB:负载均衡)后面所有的机器，Ribbon会自动的帮助基于某种规则（如简单轮询，随机连接等等）去连接这些机器，我们也可以很简单的使用Ribbon实现自定义的负载均衡算法！

  ###### 

##### Ribbon能干嘛

- LB,即负载均衡（Load Balance）,在微服务或分布式集群中经常用的一种应用。

- 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而到达系统的HA(高可用)。

- 常见的负载均衡软件有Nging, Lvs等等

- Dubbo,SpringCloud中均给我们提供了负载均衡，SpringCloud的负载均衡算法可以自定义

- 负载均衡简单分类

  - ​	集中式LB
    - 即在服务的消费方和提供方之间使用独立的LB设施，如Nging,由该设施负责吧访问请求通过某种策略转发至服务的提供方！

  - 进程式LB
    - ​	将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    - Ribbon就属于进程内LB,他只是一个类库。集成与消费方进程，消费方通过它来获取到服务提供方的地址！

##### 集成Ribbon

- 新创建项目ribbon_server

- 添加依赖

```pom
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Cloud Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Ribbon 依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
    </dependencies>
```

- 添加配置文件

```yml
server:
  port: 8010
  servlet:
    session:
      timeout: 3600
  tomcat:
    uri-encoding: utf-8
    

#==================== Eureka Client============================
eureka:
  client:
    register-with-eureka: false #不向Eureka中注册自己
    service-url:
      defaultZone: http://zh:zh12@localhost:8801/eureka/ #这里zh:zh12 是自己添加的安全认证，不想添加可以直接在eurekaServer端和这里统一去掉

```

- 启动类上添加注解

```java
@EnableEurekaClient //添加到启动类上，代表这是一个eureka客户端
```

- 编写配置文件，配置ribbon负载均衡策略

```java
package com.zh.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

服务提供者创建三份

![image-20200909174057465](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200909174057465.png)

- 服务提供者注册到eureka的服务起名必须相同，都是  eureka-client，他们都一个相同路径的接口，因为时测试，所以只打印一句话，

  >8001打印的是：------8001-client---------
  >
  >8002打印的是：------8002-client---------
  >
  >8003打印的是：------8003-client---------

- ###### ribbon_server服务中使用RestTemplate 调用接口

```java
/**
 * 测试Ribbon
 * 调用方
 */
@RestController
@RequestMapping("/ribbon")
public class TestConsumerRibbonController {
    //因为要实现负载均衡，所以使用服务名调用
    private static final String REST_URL_PREFIX= "http://EUREKA-CLIENT";
    @Autowired
    RestTemplate restTemplate;

    //远程调用
    @GetMapping
    public String getTestRibbon(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/test",String.class);
    }
}
```

- 启动测试

  ![image-20200909175050183](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200909175050183.png)

**多次访问ribbon_server服务中的接口，发现默认实现的是轮询策略**

**feign默认是集成Ribbon的，使用feign实现负载均衡，只需要在RestTemplate的Bean上加@LoadBalanced 注解，即可实现默认的轮询策略**

###### feign的使用

- 添加依赖

```pom
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-okhttp</artifactId>
</dependency>
```

- 启动类上添加注解

```java
@EnableFeignClients(basePackages = {"com.zh.feign"})//启动类上添加该注解，开启feign，basePackages为feign所在包的扫描路径
```

- 编写feign的调用接口

```java
@FeignClient(value="EUREKA-CLIENT") //value的值为被调用方服务的服务名
public interface EurekaClientFeign {

    @GetMapping("/test")
    public String getTestRibbon();

}

```

#### Hystrix:服务熔断

中文官网：https://www.springcloud.cc/

##### 什么是Hystrix

 	Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时，异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

​		“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个服务预期的，可处理的备选响应（FallBack）,而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间，不必要的占用，从而避免了故障在分布式系统中蔓延。乃至雪崩。

##### Hystrix的作用

- 服务降级
- 服务熔断
- 服务限流
- 接近实时的监控

##### 服务熔断

- 是什么

  ​	熔断机制是对应雪崩效应的一种微服务链路保护机制

  ​	当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回错误的响应信息**。当检测到该节点微服务调用响应正常后恢复调用链路，在SpringCloud框架里熔断机制通过Hystrix实现，Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制，熔断机制的注解是@HystrixCommand.

```java
//参数fallback 是熔断的处理实体，需要实现当前接口
@FeignClient(value="EUREKA-CLIENT",fallback= EurekaClientFeignFallback.class)
public interface EurekaClientFeign {

    @GetMapping("/test")
    public String getTestRibbon();

}
```

#### 监控

- 新创建module,添加依赖

```pom
  <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 监控和管理生产环境的模块 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>

        <!-- Spring Cloud Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!--feign依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-okhttp</artifactId>
        </dependency>

        <!-- Ribbon 依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>

    </dependencies>
```

- 启动类上添加注解

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableHystrixDashboard //开启监控
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class,args);
    }
}

```

- 其他被监控的服务添加依赖

```pom
  <!-- 监控和管理生产环境的模块 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

- 被监控的服务启动类里面添加监控的servlet

  ```java
      //增加一个被监控的bean
      @Bean
      public ServletRegistrationBean getServletRegistrationBean (){
          ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
          registrationBean.addUrlMappings("/actuator/hystrix.stream");//添加被监控的url
          return registrationBean;
      }
  ```

  

- 启动被监控服务，并访问/actuator/hystrix.stream路径

![image-20200910151027526](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200910151027526.png)

#### Zuul：路由网关

##### 概述：

​	Zuul包含了对请求的路由和过滤两个最重要的功能：

​	其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础，Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。

##### 使用：

- 新创建服务zuul_server

- 添加依赖

  ```pom
   <dependencies>
  
          <!-- zuul 依赖-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-hystrix</artifactId>
              <version>1.4.6.RELEASE</version>
          </dependency>
  
  
          <!-- Spring Cloud Eureka Client -->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
  
          <!--feign依赖-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-openfeign</artifactId>
          </dependency>
          <dependency>
              <groupId>io.github.openfeign</groupId>
              <artifactId>feign-okhttp</artifactId>
          </dependency>
  
          <!-- Ribbon 依赖-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
          </dependency>
  
      </dependencies>
  ```

- 编写配置类，注册到eureka

```yml
server:
  port: 9010

#==================== Eureka Client============================
eureka:
  client:
    register-with-eureka: true #向Eureka中注册自己
    service-url:
      defaultZone: http://zh:zh12@localhost:8801/eureka/ #这里zh:zh12 是自己添加的安全认证，不想添加可以直接在eurekaServer端和这里统一去掉
  instance:
    hostname: localhost #配置使用主机名注册服务
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}:@project.version@ #可以修改eureka上status的默认描述信息
    prefer-ip-address: true       #可以将IP注册到Eureka Server上，而如果不配置就是机器的主机名
    leaseRenewalIntervalInSeconds: 10 #租期更新时间间隔(默认30秒).每间隔10s，向服务端发送一次心跳，证明自己依然”存活“
    leaseExpirationDurationInSeconds: 30 #租期到期时间(默认90秒).告诉服务端，如果我30s之内没有给你发心跳，就代表我“死”了，将我踢出掉

```

- 启动类添加注解

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy  //开启网关
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
```

- 启动测试，启动一个eureka_server，一个eureka_client,再将网关起来，通过网关+client服务名+路径访问

  ![image-20200910165426643](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200910165426643.png)

- 自定义配置zuul

  ```yml
  #===================zuul网关配置==========================
  zuul:
    routes:
      client_8001:
        path: /client/**          #暴漏服务名
        serviceId: eureka-client  #映射原服务名
        sensitiveHeaders: "*"   
  ```

  

- 启动测试

![image-20200910170035850](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200910170035850.png)

- 忽略被映射路径访问

```yml
#===================zuul网关配置==========================
zuul:
  routes:
    client_8001:
      path: /client/**          #暴漏服务名
      serviceId: eureka-client  #映射原服务名
      sensitiveHeaders: "*"
  ignored-services: eureka-client  #忽略原路径访问，不可以使用eureka-client访问
#  ignored-services: "*"  #忽略全部的原路径访问，
#  prefix: /zhang #统一的访问前缀
```

![image-20200910172249583](C:\Users\zh970\AppData\Roaming\Typora\typora-user-images\image-20200910172249583.png)

