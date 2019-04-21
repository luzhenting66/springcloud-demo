package cn.ppl.hystrix;

import cn.ppl.entity.User;
import cn.ppl.feign.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error,超时";
    }

    @Override
    public String name(String name) {
        return "error,超时";
    }

    @Override
    public User user(String name, int age) {
        return new User("超时错误",-100,"port:xxx");
    }

    @Override
    public String postUser(User u) {
        return "error,超时";
    }
}
