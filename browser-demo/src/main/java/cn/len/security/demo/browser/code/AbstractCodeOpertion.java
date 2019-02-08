package cn.len.security.demo.browser.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author len
 * @date 2019年 02月07日
 */

public  abstract class AbstractCodeOpertion implements CodeOperation {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
   protected void save(ServletWebRequest request, ValidateCode validateCode) {
        sessionStrategy.setAttribute(request, "code_key", validateCode);
    }
}
