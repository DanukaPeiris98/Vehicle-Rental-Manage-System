package courseworkoopSpring.lk.danuka.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHi(){
        return "Hi";
    }
}
