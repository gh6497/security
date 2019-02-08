package cn.len.security.demo.browser.code;

import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class SmsCodeOperation extends AbstractCodeOpertion {
    private static final String SUPPORT_TYPE = "sms";

    private final SmsCodeGenerator smsCodeGenerator;

    private final SmsCodeSender smsCodeSender;

    @Override
    public void operate(ServletWebRequest webRequest) throws Exception {
        final ValidateCode generate = smsCodeGenerator.generate(webRequest);
        save(webRequest, generate);
        smsCodeSender.send(generate.getCode(),webRequest);
    }

    @Override
    public boolean support(String type) {
        return SUPPORT_TYPE.equalsIgnoreCase(type);
    }
}
