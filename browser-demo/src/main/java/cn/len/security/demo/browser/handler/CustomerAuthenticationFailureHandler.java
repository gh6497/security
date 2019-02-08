package cn.len.security.demo.browser.handler;

import cn.len.security.demo.browser.response.AuthenticationFailureResponse;
import cn.len.security.demo.exception.ValidateCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class CustomerAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        int code = AuthenticationFailureResponse.OTHER_ERROR;
        String message = "";
        final AuthenticationFailureResponse authenticationFailureResponse = new AuthenticationFailureResponse();
        if (e instanceof ValidateCodeException) {
            code = AuthenticationFailureResponse.CODE_ERROR;
            message = e.getMessage();
        } else if (e instanceof UsernameNotFoundException) {
            code = AuthenticationFailureResponse.ACCOUNT_ERROR;
            message = e.getMessage();
        }else {
            message= "unknown error!";
        }
        authenticationFailureResponse.setCode(code);
        authenticationFailureResponse.setMessage(message);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authenticationFailureResponse));
    }
}
