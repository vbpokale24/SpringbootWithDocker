package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.handler.MappedInterceptor;



@SpringBootApplication
//@ComponentScan(basePackages = {"com.org.*"})
@EnableAspectJAutoProxy
//@EnableAutoConfiguration
@Import(SwaggerConfig.class)
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
          = new ReloadableResourceBundleMessageSource();
         
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
        
    @Bean
	public MyHandlerInterceptor myHandlerInterceptor() {
		return new MyHandlerInterceptor();
	}
    
    @Bean
	public MappedInterceptor getMappedInterceptor(MyHandlerInterceptor myHandlerInterceptor) {
		return new MappedInterceptor(new String[] { "/personmanagement/**","/securitymanagement/**"}, new String[] { "/swagger-ui.html"},myHandlerInterceptor);
	}

}
