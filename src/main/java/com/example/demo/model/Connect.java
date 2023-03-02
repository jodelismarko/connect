package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connect {

    private int id;
    private String jsonrpc;
    private String method;
    private String auth;
    private Params params;

}
