package cn.len.security.demo.browser.code;

import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * @author len
 * @date 2019年 02月07日
 */
public class SmsCodeGenerator implements CodeGenerator<ValidateCode> {

    private Random random = new Random();

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4  ; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return new ValidateCode(stringBuilder.toString());
    }
}
