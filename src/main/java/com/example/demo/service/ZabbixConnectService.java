package com.example.demo.service;

import com.example.demo.model.Connect;
import com.example.demo.model.NewConnect;
import com.example.demo.model.NewParams;
import com.example.demo.model.ResponseConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

@Service
public class ZabbixConnectService {

    @Autowired
    private NewConnect newConnect;

    @Autowired
    private ResponseConnect responseConnect;

    public ResponseConnect getNewConnect(Connect connect) {

        NewConnect novaConexao = mappingConnect(connect);

        try {
            return postRequest(novaConexao);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public NewConnect mappingConnect(Connect connect){

        NewConnect connection = new NewConnect();
        NewParams parameters = new NewParams();

        connection.setId(connect.getId());
        connection.setAuth(connect.getAuth());
        connection.setJsonrpc(connect.getJsonrpc());
        connection.setMethod(connect.getMethod());

        parameters.setUsername(connect.getParams().getUser());
        parameters.setPassword(connect.getParams().getPassword());

        connection.setParams(parameters);


        return connection;

    }

    public ResponseConnect postRequest (NewConnect newConnect) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();

        String requestBody = objectMapper.writeValueAsString(newConnect);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://10.8.20.210/api_jsonrpc.php"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .header("Accept","*/*")
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        JSONObject myObject = new JSONObject(response.body());

        responseConnect.setId((Integer) myObject.get("id"));
        responseConnect.setJsonrpc((String) myObject.get("jsonrpc"));
        responseConnect.setResult((String) myObject.get("result"));

        return responseConnect;
    }
}
