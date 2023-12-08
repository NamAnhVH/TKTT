package com.example.TKTT.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<Doc> docs;
}
