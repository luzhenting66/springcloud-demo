package cn.ppl.controller;

import cn.ppl.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private HelloServiceImpl helloService;

    @RequestMapping("/hello")
    public String getHello(){
        return  helloService.sayHello();
    }
}
