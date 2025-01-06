package br.com.testeSillion.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class UrlFilterService {

    public UrlFilterService() {
    }

    public String searchContentFromUrl(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public int countContentFromUrl(String content, String target) throws Exception {

        content = content.toLowerCase();
        target = target.toLowerCase();

        int counter = 0;
        int index = content.indexOf(target);
        while (index != -1) {
            counter++;
            index = content.indexOf(target, index + target.length());
        }
        return counter;
    }

    public HashMap<String, Integer> countEachWordContentFromUrl(String[] words, String content) throws Exception {
        HashMap<String, Integer> wordsCounts = new HashMap<>();
        for (String word : words) {
            wordsCounts.put(word, countContentFromUrl(content, word));
        }
        return wordsCounts;
    }

    public void showResult(Integer counterPhrases, String phrase, HashMap<String, Integer> wordCount ) {
        System.out.println("Resultados");
        System.out.println(phrase + " => " + counterPhrases + " vezes");
        for (Map.Entry<String, Integer> e : wordCount.entrySet()) {
            System.out.println(e.getKey() + " => repete " + e.getValue() + " vezes");
        }
    }
}
