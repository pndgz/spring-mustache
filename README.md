# [spring-mustache](https://github.com/pndgz/spring-mustache)

> mustache view resolver for springmvc

## dependency:
compile group: 'com.github.spullara.mustache.java', name: 'compiler', version: '0.8.17'

## using in spring mvc
        <bean id="viewResolver" class="com.countstar.web.mustache.MustacheViewResolver">
            <property name="charset" value="utf-8"/>
            <property name="cache" value="false"/>
            <property name="prefix" value="/templates/"/>
            <property name="suffix" value=".mustache"/>
        </bean>

## using in java code
        //config bean in spring context
        <bean name="mustacheHelper" class="com.countstar.web.mustache.MustacheHelper" />
        ……
        //using in java code
        String html = mustacheHelper.buildTemplate(template, values);
        