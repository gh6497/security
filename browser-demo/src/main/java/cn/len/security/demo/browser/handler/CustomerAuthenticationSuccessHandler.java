package cn.len.security.demo.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class CustomerAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final SavedRequest savedRequest = requestCache.getRequest(request, response);
        String redirectUrl = request.getContextPath();
        if (StringUtils.isBlank(redirectUrl)) {
            redirectUrl = "/";

        }

        if (savedRequest != null) {
            redirectUrl = savedRequest.getRedirectUrl();

        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        Map<String, Object> res = new HashMap<>(1);
        res.put("redirectUrl", redirectUrl);
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
