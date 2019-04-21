package cn.ppl.feign;

import cn.ppl.entity.User;
import cn.ppl.hystrix.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "HELLO-SERVICE",fallback =HelloServiceFallback.class )
public interface HelloService {

    @RequestMapping("/springcloud-demo/hello/hello")
    String hello();


    @RequestMapping(value = "/springcloud-demo/hello/get/{name}",method = RequestMethod.GET)
    String name(@PathVariable("name") String name);

    @RequestMapping(value = "/springcloud-demo/hello/hello2get",method = RequestMethod.GET)
    User user(@RequestHeader("name") String name, @RequestHeader("age") int age);

    @RequestMapping(value = "/springcloud-demo/hello/hello3post",method = RequestMethod.POST)
    String postUser(@RequestBody User u);
}
