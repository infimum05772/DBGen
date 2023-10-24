package ru.kpfu.itis.arifulina.db.gpt;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import ru.kpfu.itis.arifulina.db.client.impl.HTTPClientImpl;
import ru.kpfu.itis.arifulina.db.client.HttpClient;
import ru.kpfu.itis.arifulina.db.client.exc.HttpClientException;
import ru.kpfu.itis.arifulina.db.gpt.constants.SettingsConstants;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GPTClient {
    private static final String GPT_URL = "https://bothub.chat/api/dev/chat/completion/sync";
    private final HttpClient httpClient;
    public GPTClient(String accessToken) {
        httpClient = new HTTPClientImpl(GPT_URL, accessToken);
    }
    public boolean generateList(String value, int amount) {

        Map<String, Object> params = getParams(value, amount);

        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpClient.post(GPT_URL, params)));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(value.replaceAll(" +", "_") + ".txt")))) {
                String result = br.lines().collect(Collectors.joining());
                bw.write(new JSONObject(result).getJSONObject("aiMessage").getString("content"));
                bw.flush();
            }
            return true;
        } catch (HttpClientException | IOException e) {
            return false;
        }
    }

    private Map<String, Object> getParams(String value, int amount) {
        Map<String, Object> params = new HashMap<>();

        JsonArray messages = new JsonArray();
        JsonObject messageAssistant = new JsonObject();
        messageAssistant.addProperty("role", "user");
        messageAssistant.addProperty("content", "");
        messages.add(messageAssistant);

        JsonObject messageUser = new JsonObject();
        messageUser.addProperty("role", "user");
        messageUser.addProperty("content", "Generate a list: " + amount + " " + value);

        JsonObject settings = new JsonObject();
        settings.addProperty("temperature", SettingsConstants.TEMPERATURE);
        settings.addProperty("model", SettingsConstants.MODEL);
        settings.addProperty("system_prompt", SettingsConstants.SYSTEM_PROMPT);

        params.put("messages", messages);
        params.put("message", messageUser);
        params.put("settings", settings);
        return params;
    }
}