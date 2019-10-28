package com.coyo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class GetLoginDetails {

    private OAuthClient client;
    private String url = "https://coyotraining.coyocloud.com/api/users/admin/export?_page=1&_pageSize=2&_orderBy=displayName";

    @Autowired
    public GetLoginDetails(OAuthClient client) {
        this.client = client;
    }

    public void getLoginDetails() throws Exception {
        RestTemplate rest = client.restTemplate();

        URI uri = new URI(url);

        //HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> result = rest.exchange(uri, HttpMethod.GET, null, String.class);

        System.out.println(result.getBody());
    }


}
