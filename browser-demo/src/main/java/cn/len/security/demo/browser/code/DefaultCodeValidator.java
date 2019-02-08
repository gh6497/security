package cn.len.security.demo.browser.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author len
 * @date 2019年 02月07日
 */
public class DefaultCodeValidator implements CodeValidator<ValidateCode> {
    @Override
    public void validate(ServletWebRequest request) {

    }
}
