package cn.len.security.demo.browser.config;

import cn.len.security.demo.browser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private Pattern mobileRegx = Pattern.compile("^1[3456789]\\d{9}");
    private Pattern emailRegx = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if (isMobile(username)) {
            user = userRepository.findUserByMobile(username);
        } else if (isEmail(username)) {
            user = userRepository.findUserByEmail(username);
        } else {
            user = userRepository.findUserByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    private boolean isMobile(String username) {
        final Matcher matcher = mobileRegx.matcher(username);
        return matcher.matches();

    }

    private boolean isEmail(String username) {
        return emailRegx.matcher(username).matches();
    }
}
