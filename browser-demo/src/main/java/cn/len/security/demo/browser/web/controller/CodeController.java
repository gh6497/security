package cn.len.security.demo.browser.web.controller;

import cn.len.security.demo.browser.code.CodeOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Controller
@RequestMapping("/code")
@RequiredArgsConstructor
@Slf4j
public class CodeController {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
   private final List<CodeOperation> codeOperations;
    @GetMapping("{type}")
    public void code(ServletWebRequest request, @PathVariable String type) throws Exception {
        final CodeOperation codeOperation = getCodeOperation(type);
        if (codeOperation != null) {
            codeOperation.operate(request);
        } else {
            final String msg = "未适配" + type + "类型验证码";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

    private CodeOperation getCodeOperation(String type){
        for (CodeOperation codeOperation : codeOperations) {
            if (codeOperation.support(type)) {
                return  codeOperation;
            }
        }
        return null;
    }
}


