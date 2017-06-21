package com.yin.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class WechatApplication {
	  private static Logger logger = LoggerFactory.getLogger(WechatApplication.class);  

	  @Bean
	  public EmbeddedServletContainerCustomizer containerCustomizer() {

	      return new EmbeddedServletContainerCustomizer() {
	          @Override
	          public void customize(ConfigurableEmbeddedServletContainer container) {

	              ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
	              ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
	              ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

	              container.addErrorPages(error401Page, error404Page, error500Page);
	          }
	      };
	  }
	public static void main(String[] args) {
		 SpringApplication newRun= new SpringApplication(WechatApplication.class); 
//	        newRun.setBannerMode(Banner.Mode.OFF);  
	        newRun.run(args);
		logger.info("My Spring Boot Application Started");
	}
}
