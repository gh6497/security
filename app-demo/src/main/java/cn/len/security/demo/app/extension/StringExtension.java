package cn.len.security.demo.app.extension;

/**
 * @author len
 * @date 2019年 02月06日
 */
public class StringExtension {
    public static boolean isNotBlank(String string) {
        return string != null && string.trim().length()>0;
    }
}
