package cn.len.security.demo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author len
 * @date 2019年 02月06日
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
