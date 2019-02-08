package cn.len.security.demo.browser.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author len
 * @date 2019年 02月06日
 */
public class MyUserDetail extends User {
    public MyUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUserDetail() {
        super(null,null,null);
    }
}
