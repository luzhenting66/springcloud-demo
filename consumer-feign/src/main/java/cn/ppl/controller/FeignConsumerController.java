package cn.ppl.controller;

import cn.ppl.entity.User;
import cn.ppl.feign.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;


@RestController
@RequestMapping("/springcloud-demo/feignConsumer/")
public class FeignConsumerController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping("newHello")
    public String newHello(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloService.hello()).append("\n");
        stringBuilder.append(helloService.name("张三")).append("\n");
        stringBuilder.append(helloService.user("王五",20)).append("\n");
        stringBuilder.append(helloService.postUser(new User("lzt",29,""))).append("\n");
        return stringBuilder.toString();
    }

    @RequestMapping("get/{name}")
    public String get(@PathVariable("name") String name){
        return helloService.name(name);
    }


}
