package com.yin.wechat.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  
@EnableSwagger2  
@ComponentScan("com.yin.wechat.controller")  
public class Swagger2Config {
    @Bean  
    public Docket createRestApi(){  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("com.yin.wechat.controller"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
  
  
    private ApiInfo apiInfo() {  
        ApiInfo apiInfo = new ApiInfoBuilder().title("spring boot restful webservice")  
                .description("Spring Boot 整合 和spring-data-JPA")  
                .license("我的主页")  
                .licenseUrl("www.baidu.com")  
                .contact(new Contact("殷雷雷", "个人邮箱", "954793346@qq.com"))  
                .version("1.0")  
                .build();  
  
        return apiInfo;  
    }  
    @Bean(name="captchaProducer")  
    public DefaultKaptcha getKaptchaBean(){  
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();  
        Properties properties=new Properties();  
        properties.setProperty("kaptcha.border", "yes");  
        properties.setProperty("kaptcha.border.color", "105,179,90");  
        properties.setProperty("kaptcha.textproducer.font.color", "blue");  
        properties.setProperty("kaptcha.image.width", "125");  
        properties.setProperty("kaptcha.image.height", "45");  
        properties.setProperty("kaptcha.session.key", "code");  
        properties.setProperty("kaptcha.textproducer.char.length", "4");  
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");          
        Config config=new Config(properties);  
        defaultKaptcha.setConfig(config);  
        return defaultKaptcha;  
    }  
}
