#### 自定义反向工程-文档
##### 为什么要自定义,方便在哪里?
```
1.为什么要自定义?
    a.基于对注解的热衷,想基于mybatis用纯注解的方式实现一套好用的纯注解的orm
    b.中间修改过一版,基于自定定义的参数拼接公共类,实现的不怎么好,后边就换mybatis原生的Example.
2.个人整理的方便点如下:
    a.生成的类包含: domain,example,mapper,service,serviceImpl
    b.基于封装的service,可以实现大多数的表操作功能(分页对象,使用的PageHelper原生对象).
    c.基于注解偏好,没有xml配置文件.
3.不足,待优化:
    a.多表连表操作,暂时没有想到比较优雅的实现方式.
    b.目前只支持mysql数据库.
```
##### 修改的地方如下:
```
基于mybatis-generator-core的增强
1.修改domain
    a.删除所有注释.
    b.增加自有注解.
    c.增加Field枚举类.
    d.增加序列化接口.
    e.增加序列化ID.
2.修改mapper
    a.使用自定义baseMapper.
    b.增加mapper扫描注释.
3.修改example
    a.增加链式编程逻辑.
    b.增加字段LikeList方法.
    c.增加字段非空判断,取消异常抛出.
    d.增加字段like方法的前缀,后缀方法.
4.增加Service层代码生成插件.
使用方式说明:
1.反向工程,配置样例见源代码(generatorConfig.xml).
2.使用示例见测试用例(FindTest)
3.PluginsTest类用于debug插件代码.
```
#### 使用说明:
1.maven依赖:
```
<dependency>
    <groupId>com.bruce.tool</groupId>
    <artifactId>tool-orm-mybatis</artifactId>
    <version>8.0.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.bruce.tool</groupId>
    <artifactId>tool-orm-mybatis-generator</artifactId>
    <version>8.0.0.RELEASE</version>
</dependency>
```
2.配置文件,见代码或者jar包中resources/generatorConfig.xml和info.properties
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

    <properties resource="info.properties" />

    <context id="tool" targetRuntime="MyBatis3">

        <!-- 清空所有注释,不阻止后续注释的生成 -->
        <!--<plugin type="CleanAllCommentPlugin" />-->
        <!-- 将表字段注释添加到domain类对应的属性上 -->
        <plugin type="DomainJavaDocPlugin" />
        <!-- 屏蔽所有注释,阻止后续注释的生成 -->
        <plugin type="SuppressAllCommentPlugin" />
        <!-- 增加domain类序列化接口功能 -->
        <plugin type="DomainSerializablePlugin" />
        <!-- 基于注解的Mapper生成插件 -->
        <plugin type="AnnotationMapperPlugin" />
        <!-- Example扩展插件-增加likeListValue功能,增加likePrefix,likeSuffix方法 -->
        <plugin type="ExampleExtendPlugin" >
            <property name="likeListValue" value="true"/>
        </plugin>
        <!-- 对domain增加自定义注解的插件-必须配置-否则orm框架无法正常运行 -->
        <plugin type="AnnotationDomainPlugin" />
        <!-- service接口类生成插件 -->
        <plugin type="ServiceInterfacePlugin">
            <property name="service.path" value="${service.path}"/>
            <property name="service.package" value="${service.package}"/>
        </plugin>
        <!-- service实现类生成插件 -->
        <plugin type="ServiceClassPlugin">
            <property name="service.path" value="${service.path}"/>
            <property name="service.package" value="${service.package}"/>
            <property name="xmlmapper.package" value="${xmlmapper.package}"/>
        </plugin>
        <!-- domain字段追加swagger注解 -->
        <!--<plugin type="SwaggerAnnotationDomainPlugin" />-->

        <jdbcConnection driverClass="${db.driver}" connectionURL="${db.url}" userId="${db.name}" password="${db.pwd}">
            <property name="useInformationSchema" value="true"/>
            <property name="remarks" value="true"/>
        </jdbcConnection>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="${domain.package}" targetProject="${domain.path}">
            <property name="enableSubPackages" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="${dao.package}" targetProject="${dao.path}">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="${xmlmapper.package}" targetProject="${xmlmapper.path}">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!-- 数据库所有表-自动生成实体类: 不用指定generateKey,代码自动识别. -->
        <table tableName="%" schema="persimmon" enableInsert="false" enableSelectByExample="true" enableDeleteByExample="false" enableCountByExample="false" enableUpdateByExample="false" >
            <domainObjectRenamingRule searchString="^Per" replaceString=""/>
        </table>
        <!-- 指定具体的表-自动生成实体类: 不用指定generateKey,代码自动识别 -->
        <table tableName="global_region" domainObjectName="Region" enableInsert="false" enableSelectByExample="true" enableDeleteByExample="false" enableCountByExample="false" enableUpdateByExample="false"></table>
        <table tableName="admin_user" domainObjectName="AdminUser" enableInsert="false" enableSelectByExample="true" enableDeleteByExample="false" enableCountByExample="false" enableUpdateByExample="false"></table>

    </context>
</generatorConfiguration> 
```
```
dao.path=./src/test/java
domain.path=./src/test/java
xmlmapper.path=./src/test/java
service.path=./src/test/java

dao.package=com.bruce.tool.orm.mybatis.generator.dao
domain.package=com.bruce.tool.orm.mybatis.generator.domain
xmlmapper.package=com.bruce.tool.orm.mybatis.generator.mapper
service.package=com.bruce.tool.orm.mybatis.generator.service

db.name=root
db.pwd=123456
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://127.0.0.1:3306/persimmon?useUnicode=true&characterEncoding=UTF-8&nullCatalogMeansCurrent=true&useInformationSchema=true
```
3.反向工程生成实体类
```
方式一:
    1.依赖maven包,修改配置文件.
    2.在命令行,先编译工程,在执行如下命令:
        mvn mybatis-generator:generate
方式二:
    1.依赖maven包,修改配置文件.
    2.参考测试用例PluginsTest类,执行如下代码,可debug:
        @Test
        public void run(){
            String config = this.getClass().getClassLoader().getResource("generatorConfig.xml").getFile();
            String[] arg = { "-configfile", config, "-overwrite" };
            ShellRunner.main(arg);
        }
```
4.注意事项
```
1.因为service里边的分页方法,已经使用了pageHelper,所以引用的项目,不用额外再配置pagehelper,否色会报错如下:
    在系统中发现了多个分页插件，请检查系统配置!
```
5.感谢
```
感谢雨佳提供的批量更新方法.
感谢小海提供的example改动方案.
```