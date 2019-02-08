package cn.len.security.demo.browser.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author len
 * @date 2019年 02月07日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AuthenticationFailureResponse extends Response{
    public static final int CODE_ERROR = 1;
    public static final int ACCOUNT_ERROR = 2;
    public static final int OTHER_ERROR= 3;

}
