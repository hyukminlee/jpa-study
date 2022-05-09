package jpabook.jpashop2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //url 매핑 ex)localhost:8080/hello
    public String hello(Model model) { //Model에다가 데이터를 실어서 컨트롤러에서 view로 데이터를 넘길 수 있다.
        model.addAttribute("data", "hello!!!!");
        return "hello"; //resources:templates/ +{ViewName}+ .html
    }
}
