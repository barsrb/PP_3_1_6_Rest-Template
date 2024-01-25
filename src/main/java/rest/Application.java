package rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";
        ResponseEntity<User[]> response =
                restTemplate.getForEntity(
                        url,
                        User[].class);

        String set_cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        User user3 = new User();
        user3.setId(3L);
        user3.setName("James");
        user3.setLastName("Brown");
        user3.setAge((byte) 3);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, set_cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user3, headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println(result.getBody());

        user3.setName("Thomas");
        user3.setLastName("Shelby");

        httpEntity = new HttpEntity<>(user3, headers);
        result = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        System.out.println(result.getBody());

        httpEntity = new HttpEntity<>(user3, headers);
        result = restTemplate.exchange(url + "/" + user3.getId(), HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(result.getBody());



    }
}
