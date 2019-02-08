package cn.len.security.demo.browser.config;

import cn.len.security.demo.browser.filter.CodeValidateFilter;
import cn.len.security.demo.browser.handler.CustomerAuthenticationFailureHandler;
import cn.len.security.demo.browser.handler.CustomerAuthenticationSuccessHandler;
import cn.len.security.demo.browser.sms.MobileAndCodeAuthenticationFilter;
import cn.len.security.demo.browser.sms.MobileAndCodeAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final DataSource dataSource;

    private final ObjectMapper objectMapper;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        configFormLogin(http);
        configRememberMe(http);
        configMobileLogin(http);
        configValidateCodeFilter(http);
        configSession(http);
        configAllowPath(http);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private PersistentTokenRepository persistentTokenRepository() {
        final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    private void configFormLogin(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/signin")
                .loginProcessingUrl("/authenticate/form")
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler);
    }

    private void configRememberMe(HttpSecurity http) throws Exception {
        http.rememberMe()
                .tokenValiditySeconds(10000)
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService);
    }

    private void configSession(HttpSecurity http) {

    }

    private void configMobileLogin(HttpSecurity http) throws Exception {
        final MobileAndCodeAuthenticationFilter mobileAndCodeAuthenticationFilter = new MobileAndCodeAuthenticationFilter("/authenticate/mobile");
        mobileAndCodeAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        mobileAndCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        mobileAndCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        http.addFilterBefore(mobileAndCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(new MobileAndCodeAuthenticationProvider(userDetailsService));
    }

    private void configValidateCodeFilter(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CodeValidateFilter(authenticationFailureHandler), LogoutFilter.class);

    }

    private void configAllowPath(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/signin",
                        "/authenticate/form",
                        "/code/*",
                        "/authenticate/mobile",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated();
    }
}
