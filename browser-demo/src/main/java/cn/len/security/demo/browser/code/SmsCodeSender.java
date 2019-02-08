package cn.len.security.demo.browser.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Slf4j
public class SmsCodeSender implements CodeSender<String> {
    @Override
    public void send(String  validateCode, ServletWebRequest request) throws Exception {
        log.info("验证码" + validateCode);
    }
}