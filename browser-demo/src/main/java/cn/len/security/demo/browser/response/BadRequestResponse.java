package cn.len.security.demo.browser.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author len
 * @date 2019年 02月07日
 */
@ToString(callSuper = true)
@Getter
@Setter
public class BadRequestResponse extends Response {

    private String reason;

    private List<Map<String ,Object>> details;
}
