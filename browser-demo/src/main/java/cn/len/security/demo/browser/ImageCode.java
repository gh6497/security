package cn.len.security.demo.browser;

import cn.len.security.demo.browser.code.ValidateCode;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Getter
public class ImageCode extends ValidateCode  implements Serializable {


    private static final long serialVersionUID = 7595191369211076553L;
    private BufferedImage image;

    public ImageCode(String code, long seconds, BufferedImage image) {
        super(code, seconds);
        this.image = image;
    }

    public ImageCode(String code, LocalDateTime expiredTime, BufferedImage image) {
        super(code, expiredTime);
        this.image = image;
    }

    public ImageCode(String code, BufferedImage image) {
        super(code);
        this.image = image;
    }

    public void destroyedImage() {
        this.image = null;
    }
}
