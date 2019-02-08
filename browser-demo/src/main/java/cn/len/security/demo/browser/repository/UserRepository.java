package cn.len.security.demo.browser.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate template;

    public User findUserByUsername(String username) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("username", username);
        final String sql = "select username,password,user_id from user where username = :username";
        return query(params, sql);

    }

    public User findUserByMobile(String mobile) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("mobile", mobile);
        final String sql = "select username,password,user_id from user where mobile = :mobile";
        return query(params, sql);
    }

    public User findUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("email", email);
        final String sql = "select username,password,user_id from user where username = :email";
        return query(params, sql);
    }

    private User query(Map<String, Object> params, String sql) {
        return template.query(sql, params, rs -> {
            final String usernmae = rs.getString(1);
            final String password = rs.getString(2);
            final String userId = rs.getString(3);
            return new User(usernmae, password, AuthorityUtils.commaSeparatedStringToAuthorityList("read"));
        });
    }
}