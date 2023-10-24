package ru.kpfu.itis.arifulina.db.client.impl;

import com.google.gson.*;
import ru.kpfu.itis.arifulina.db.client.HttpClient;
import ru.kpfu.itis.arifulina.db.client.exc.HttpClientException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HTTPClientImpl implements HttpClient {
    private String testUrl;
    private String accessToken;

    public HTTPClientImpl(String testUrl, String accessToken) {
        this.testUrl = testUrl;
        this.accessToken = accessToken;
    }

    @Override
    public InputStream get(String url, Map<String, Object> params) throws HttpClientException {
        try {
            if (params != null && !params.isEmpty()) {
                StringBuilder urlParams = new StringBuilder();
                params.forEach((key, value) -> urlParams.append(key).append("=").append(value).append("&"));
                url = url + "?" + urlParams.substring(0, urlParams.length() - 1);
            }

            HttpURLConnection connection = getHttpURLConnectionGet(url);

            return connection.getInputStream();
        } catch (IOException e) {
            throw new HttpClientException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public InputStream post(String url, Map<String, Object> params) throws HttpClientException {
        try {
            HttpURLConnection connection = getHttpURLConnectionPost(url);

            Gson gson = new Gson();
            String jsonInput = gson.toJson(params);

            try (OutputStream out = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                out.write(input, 0, input.length);
                out.flush();
            }

            return connection.getInputStream();
        } catch (IOException e) {
            throw new HttpClientException(e.getMessage(), e.getCause());
        }
    }

    private HttpURLConnection getHttpURLConnectionPost(String url) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Accept-Charset", "utf-8");
        if (accessToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        }

        connection.setDoOutput(true);
        return connection;
    }

    private HttpURLConnection getHttpURLConnectionGet(String url) throws IOException {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        if (accessToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        }
        return connection;
    }
}
