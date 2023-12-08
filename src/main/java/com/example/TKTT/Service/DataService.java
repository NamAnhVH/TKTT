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

    private static final String CUT_FIRST_STRING = "Read\n";
    private static final String CUT_LAST_STRING = "Similar Reads\n";

    public List<Doc> searchData(String query) {
        String apiUrl = "http://localhost:8983/solr/nutch/select?df=content&fl=url,content,title&indent=true&q.op=OR&q="
                + query +
                "&useParams=";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Data> responseEntity = restTemplate.getForEntity(apiUrl, Data.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Data responseData = responseEntity.getBody();
            assert responseData != null;
            return filterContent(responseData.getResponse().getDocs());
        }
        return new ArrayList<>();
    }

    private List<Doc> filterContent(List<Doc> docs) {
        List<Doc> updateContentDocs = new ArrayList<>();
        for (Doc doc: docs){
            List<String> updateContents = new ArrayList<>();
            for(String content: doc.getContent()){
                int indexFirst = content.indexOf(CUT_FIRST_STRING);
                int indexLast = content.indexOf(CUT_LAST_STRING);
                String updateContent = (indexFirst != -1 && indexLast != -1) ? content.substring(indexFirst + CUT_FIRST_STRING.length(), indexLast) : content;
                updateContents.add(updateContent);
            }
            doc.setContent(updateContents);
            updateContentDocs.add(doc);
        }
        return updateContentDocs;
    }
}
