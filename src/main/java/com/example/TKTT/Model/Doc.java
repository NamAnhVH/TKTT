package com.example.TKTT.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Doc {
    private List<String> title;
    private List<String> url;
    private List<String> content;
}
