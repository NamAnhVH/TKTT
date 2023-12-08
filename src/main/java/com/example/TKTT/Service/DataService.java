package com.example.TKTT.Service;

import com.example.TKTT.Model.Data;
import com.example.TKTT.Model.Doc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    public List<Doc> searchData(String query) {
        String apiUrl = "http://localhost:8983/solr/nutch/select?df=content&fl=url,content,title&indent=true&q.op=OR&q="
                + query +
                "&useParams=";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Data> responseEntity = restTemplate.getForEntity(apiUrl, Data.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Data responseData = responseEntity.getBody();
            assert responseData != null;
            return responseData.getResponse().getDocs();
        }
        return new ArrayList<>();
    }
}
