package cn.len.security.demo.browser.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author len
 * @date 2019年 02月06日
 */
public interface CodeValidator<T extends  Code> {

    void validate(ServletWebRequest request);
}
