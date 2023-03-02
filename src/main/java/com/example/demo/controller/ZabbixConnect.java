package com.example.demo.controller;

import com.example.demo.model.Connect;
import com.example.demo.model.ResponseConnect;
import com.example.demo.service.ZabbixConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class ZabbixConnect {

    @Autowired
    private ZabbixConnectService zabbixConnectService;

    @PostMapping("/connect")
    public ResponseEntity<ResponseConnect> createNewConnect(@RequestBody Connect connect)
    {
        ResponseConnect newConnect = this.zabbixConnectService.getNewConnect(connect);

        if(newConnect == null){
            return new ResponseEntity<>(newConnect, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newConnect, HttpStatus.OK);
        }
    }
}
