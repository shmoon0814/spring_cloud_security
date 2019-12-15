package m.s.h.cloudserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy //줄 프록시 서버 선언
@EnableEurekaServer //유레카 서버
@SpringBootApplication
public class CloudserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudserverApplication.class, args);
	}
}

