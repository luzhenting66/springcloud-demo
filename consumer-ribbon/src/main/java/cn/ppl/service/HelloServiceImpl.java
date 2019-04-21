package cn.ppl.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class HelloServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String sayHello(){
        Random random = new Random();
        int nextInt = random.nextInt(3000);
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  restTemplate.getForEntity("http://hello-service/hello/hello", String.class).getBody();
    }

    public String helloFallback(){
        return "error,请求超时！sorry";
    }
}
