package cn.ppl.hello.controller;

import cn.ppl.hello.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/springcloud-demo/hello/")
public class HelloController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/hello")
    public String hello(){
        return "say,hello--port:"+port;
    }

    @RequestMapping(value = "/get/{name}",method = RequestMethod.GET)
    public String name( @PathVariable("name") String name){

        return "say,hello "+name+",---port:"+port;
    }

    @RequestMapping(value = "/hello2get",method = RequestMethod.GET)
    public User user(@RequestHeader("name") String name,@RequestHeader("age") int age){
        return new User(name,age,"port:"+port);
    }

    @RequestMapping(value = "/hello3post",method = RequestMethod.POST)
    public String postUser(@RequestBody User u){
        return "hello,"+u.getName()+", "+u.getAge()+",port:"+port;
    }

    @RequestMapping(value = "/public/get")
    public String pub(){
        return "public get...no token";
    }
}
