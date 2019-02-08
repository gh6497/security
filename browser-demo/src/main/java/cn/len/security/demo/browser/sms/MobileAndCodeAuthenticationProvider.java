package cn.len.security.demo.browser.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class MobileAndCodeAuthenticationProvider implements AuthenticationProvider {

   private final UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAndCodeAuthenticationToken mobileAndCodeAuthenticationToken = (MobileAndCodeAuthenticationToken) authentication;
        String  principal = (String) mobileAndCodeAuthenticationToken.getPrincipal();
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(principal);
        } catch (UsernameNotFoundException e) {
            throw new InternalAuthenticationServiceException("该用户不存在");
        }
        return new MobileAndCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(MobileAndCodeAuthenticationToken.class);
    }
}
