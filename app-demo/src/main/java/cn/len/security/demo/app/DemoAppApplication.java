package cn.len.security.demo.app;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.swing.text.View;

/**
 * @author len
 * @date 2019年 02月04日
 */
@RestController
@SpringBootApplication
public class DemoAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }

    @GetMapping("/~")
    public ModelAndView me() {
        final Profile profile = new Profile();
        profile.setAverta("ww");
        profile.setNickName("nickName");
        final MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(profile);
        modelAndView.setView(mappingJackson2JsonView);
        return modelAndView;
    }


}
@Data
class Profile {
   private String averta;

   private String nickName;
}
