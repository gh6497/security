package cn.len.security.demo.browser.code;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * @author len
 * @date 2019年 02月06日
 */
public interface CodeOperation {


    void operate(ServletWebRequest request) throws Exception;

    boolean support(String type);
}
