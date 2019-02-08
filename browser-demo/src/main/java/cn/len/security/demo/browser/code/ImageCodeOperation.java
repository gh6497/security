package cn.len.security.demo.browser.code;

import cn.len.security.demo.browser.ImageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * @author len
 * @date 2019年 02月06日
 */
@RequiredArgsConstructor
public class ImageCodeOperation extends AbstractCodeOpertion {
    private static  final  String SUPPORT_TYPE = "image";
    private final ImageCodeGenerator imageCodeGenerator;
    private final ImageCodeSender defaultImageCodeSender;
    @Override
    public void operate(ServletWebRequest request) throws Exception {
        final ImageCode generate = imageCodeGenerator.generate(request);
        final BufferedImage image = generate.getImage();
        generate.destroyedImage();
        save(request, generate);
        defaultImageCodeSender.send(image, request);
    }

    @Override
    public boolean support(String type) {
        return SUPPORT_TYPE.equalsIgnoreCase(type);
    }
}
