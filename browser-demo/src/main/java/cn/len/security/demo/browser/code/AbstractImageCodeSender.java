package cn.len.security.demo.browser.code;

import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author len
 * @date 2019年 02月06日
 */
public abstract class AbstractImageCodeSender implements CodeSender<BufferedImage>{
    @Override
    public void send(BufferedImage bufferedImage, ServletWebRequest request) throws Exception {
        ImageIO.write(bufferedImage, formatName(), Objects.requireNonNull(request.getResponse()).getOutputStream());
    }

    abstract String formatName();

}
