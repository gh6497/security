package cn.len.security.demo.browser.code;

import java.time.LocalDateTime;

/**
 * @author len
 * @date 2019年 02月06日
 */
public interface Code {
    /**
     * 获取验证码
     * @return 验证码
     */
    String getCode();

    /**
     * 获取验证码过期时间
     * @return 验证码过期时间
     */
    LocalDateTime getExpiredTime();
}
