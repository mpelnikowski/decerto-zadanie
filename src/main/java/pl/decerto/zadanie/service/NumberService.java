package pl.decerto.zadanie.service;

import org.springframework.stereotype.Service;
import pl.decerto.zadanie.criteria.ApiSearchCriteria;
import pl.decerto.zadanie.criteria.RandomSearchCriteria;
import pl.decerto.zadanie.util.HttpClientUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@Service
public class NumberService {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public Integer sum(ApiSearchCriteria apiSearchCriteria, RandomSearchCriteria randomSearchCriteria) throws IOException, InterruptedException {
        return getNumberFromApi(apiSearchCriteria) + generateNumberByRandom(randomSearchCriteria);
    }

    public Integer getNumberFromApi(ApiSearchCriteria apiSearchCriteria) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(HttpClientUtil.buildUri(apiSearchCriteria))
                .setHeader("User-Agent", "Decerto zadanie")
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return Integer.valueOf(httpResponse.body().replace("\n", ""));
    }

    public Integer generateNumberByRandom(RandomSearchCriteria randomSearchCriteria) {
        Random r = new Random();
        return r.nextInt((randomSearchCriteria.getMax() - randomSearchCriteria.getMin()) + 1) + randomSearchCriteria.getMin();
    }
}
