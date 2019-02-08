package cn.len.security.demo.browser.config;

import cn.len.security.demo.browser.code.*;
import cn.len.security.demo.browser.handler.CustomerAuthenticationFailureHandler;
import cn.len.security.demo.browser.handler.CustomerAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author len
 * @date 2019年 02月08日
 */
@Configuration
public class WebSecurityBean {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CodeOperation imageCodeOperation() {
        return new ImageCodeOperation(new ImageCodeGenerator(), new ImageCodeSender());
    }


    @Bean
   public CodeOperation smsCodeOperation() {
        return new SmsCodeOperation(new SmsCodeGenerator(), new SmsCodeSender());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(ObjectMapper objectMapper) {
        return new CustomerAuthenticationSuccessHandler(objectMapper);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(ObjectMapper objectMapper) {
        return new CustomerAuthenticationFailureHandler(objectMapper);
    }

}
