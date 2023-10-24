package ru.kpfu.itis.arifulina.db.client;

import ru.kpfu.itis.arifulina.db.client.exc.HttpClientException;

import java.io.InputStream;
import java.util.Map;

public interface HttpClient {
    InputStream get(String url, Map<String, Object> params) throws HttpClientException;

    InputStream post(String url, Map<String, Object> params) throws HttpClientException;
}
