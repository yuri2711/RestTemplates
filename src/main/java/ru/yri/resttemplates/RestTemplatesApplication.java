package ru.yri.resttemplates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.yri.resttemplates.model.User;

@SpringBootApplication
public class RestTemplatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplatesApplication.class, args);

        StringBuilder builder = new StringBuilder();
        RestTemplate template = new RestTemplate();
        String url = "http://91.241.64.178:7081/api/users";

        ResponseEntity<String> response = template.getForEntity(url, String.class);
        String session = response.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.COOKIE, session);

        HttpEntity<User>entity_post =
                new HttpEntity<>(new User(3L, "James", "Brown", (byte) 35), httpHeaders);
        ResponseEntity<String>response_post = template.exchange(
                url,
                HttpMethod.POST,
                entity_post,
                String.class);
        builder.append(response_post.getBody());

        HttpEntity<User> entity_put = new HttpEntity<>(new User(3L,"Thomas", "Shelby", (byte) 35), httpHeaders);
        ResponseEntity<String>response_put = template.exchange(url, HttpMethod.PUT, entity_put, String.class);
        builder.append(response_put.getBody());

        HttpEntity<User>entity_delete = new HttpEntity<>(new User(3L,"James", "Brown", (byte) 35), httpHeaders);
        ResponseEntity<String>response_delete = template.exchange(url + "/3",HttpMethod.DELETE, entity_delete, String.class);
        builder.append(response_delete.getBody());
        System.out.println(builder);

    }

}
