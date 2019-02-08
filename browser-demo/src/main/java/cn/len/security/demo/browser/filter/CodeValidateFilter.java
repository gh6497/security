package cn.len.security.demo.browser.filter;

import cn.len.security.demo.browser.code.ValidateCode;
import cn.len.security.demo.exception.ValidateCodeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class CodeValidateFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestURI = request.getRequestURI();
        if ((StringUtils.equals(requestURI, "/authenticate/form") || StringUtils.equals(requestURI, "/authenticate/mobile")) && "post".equalsIgnoreCase(request.getMethod())) {
            final ServletWebRequest
                    webRequest = new ServletWebRequest(request, response);
            try {
                validate(webRequest);
                filterChain.doFilter(request, response);

            } catch (ValidateCodeException e) {
                sessionStrategy.removeAttribute(webRequest, "code_key");
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void validate(ServletWebRequest request) {
        ValidateCode code = (ValidateCode) sessionStrategy.getAttribute(request, "code_key");
        if (code == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        final String codeParam = request.getParameter("code");
        if (StringUtils.isBlank(codeParam)) {
            throw new ValidateCodeException("没有输入验证码");
        }
        if (!code.getExpiredTime().isAfter(LocalDateTime.now())) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!code.getCode().equalsIgnoreCase(codeParam)) {
            throw new ValidateCodeException("验证码输入错误");
        }
    }

}
