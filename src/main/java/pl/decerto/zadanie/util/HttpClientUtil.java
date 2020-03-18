package pl.decerto.zadanie.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import pl.decerto.zadanie.criteria.ApiSearchCriteria;

import java.net.URI;

public class HttpClientUtil {

    private static final String SCHEME = "https";
    private static final String HOST = "www.random.org";
    private static final String PATH = "integers/";
    private static final String NUM = "num";
    private static final String NUM_VALUE = "1";
    private static final String COL = "col";
    private static final String COL_VALUE = "1";
    private static final String BASE = "base";
    private static final String BASE_VALUE = "10";
    private static final String FORMAT = "format";
    private static final String FORMAT_VALUE = "plain";
    private static final String RND = "rnd";
    private static final String RND_VALUE = "new";
    private static final String MIN = "min";
    private static final String MAX = "max";

    public static URI buildUri(ApiSearchCriteria apiSearchCriteria) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(NUM, NUM_VALUE);
        params.add(COL, COL_VALUE);
        params.add(BASE, BASE_VALUE);
        params.add(FORMAT, FORMAT_VALUE);
        params.add(RND, RND_VALUE);
        params.add(MIN, String.valueOf(apiSearchCriteria.getMin()));
        params.add(MAX, String.valueOf(apiSearchCriteria.getMax()));
        URI uri = UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .path(PATH)
                .queryParams(params)
                .build()
                .toUri();
        return uri;
    }
}
