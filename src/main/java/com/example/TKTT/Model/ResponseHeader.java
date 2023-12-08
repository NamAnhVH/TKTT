package com.example.TKTT.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseHeader {

    private int status;
    private int QTime;
    private Param params;
}
