package ru.yri.resttemplates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplatesApplication.class, args);
        RestTemplate template = new RestTemplate();
        String url = "http://91.241.64.178:7081/api/users";
        String result = template.getForObject(url, String.class);
        System.out.println();
        System.out.println("Результат из командной строки");
        System.out.println(result);

    }

}
