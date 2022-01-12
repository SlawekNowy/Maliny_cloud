package pl.gmail.slawekwaga.maliny_cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/index")
    public void index() {

    }
}
