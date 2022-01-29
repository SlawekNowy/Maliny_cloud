package pl.gmail.slawekwaga.maliny_cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyControllers {
    // empty controllers for saving

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/login.html")
    public String login() {
        return "login";
    }





}
