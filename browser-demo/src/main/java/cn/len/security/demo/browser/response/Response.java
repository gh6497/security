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
@ToString
public class Response {
    private int code;

    private String message;
}
