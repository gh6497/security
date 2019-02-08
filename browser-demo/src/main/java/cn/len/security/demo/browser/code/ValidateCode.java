package cn.len.security.demo.browser.code;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Getter
public class ValidateCode implements Code,Serializable {

    private static final long DEFAULT_EXPIRED_TIME = 600L;
    private static final long serialVersionUID = 425788639978161269L;
    /** 验证码  */
    private final String code;
    /**  过期时间 */
    private final LocalDateTime expiredTime;

   public ValidateCode(String code, long seconds) {
       this(code, LocalDateTime.now().plusSeconds(seconds));
   }

   public ValidateCode(String code, LocalDateTime expiredTime){
       this.code = code;
       this.expiredTime= expiredTime;
   }

   public ValidateCode(String code) {
       this(code, DEFAULT_EXPIRED_TIME);
   }


    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("验证码:").append(this.code).append("在").append(expiredTime).append("将会过期");
        return stringBuilder.toString();
    }
}
