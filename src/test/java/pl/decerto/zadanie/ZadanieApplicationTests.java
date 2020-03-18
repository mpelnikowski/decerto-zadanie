package pl.decerto.zadanie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.decerto.zadanie.criteria.ApiSearchCriteria;
import pl.decerto.zadanie.criteria.RandomSearchCriteria;
import pl.decerto.zadanie.service.NumberService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZadanieApplicationTests {

    private static final int API_MIN_VALUE = -10000;
    private static final int API_MAX_VALUE = 10000;
    private static final int RANDOM_MIN_VALUE = -10000;
    private static final int RANDOM_MAX_VALUE = 10000;
    private static final int ZERO = 0;
    private static final String EXCEPTION_MESSAGE = "The maximum value must be greater than the minimum value";

    @Autowired
    private NumberService numberService;

    @Test
    void contextLoads() {
    }

    @Test
    void runWithoutException() throws InterruptedException, IOException {
		ApiSearchCriteria apiSearchCriteria = ApiSearchCriteria.builder().min(API_MIN_VALUE).max(API_MAX_VALUE).build();
		RandomSearchCriteria randomSearchCriteria = RandomSearchCriteria.builder().min(RANDOM_MIN_VALUE).max(RANDOM_MAX_VALUE).build();
		Integer sum = numberService.sum(apiSearchCriteria, randomSearchCriteria);
		assertNotNull(sum);
    }

    @Test()
    void whenExceptionThrown_thenAssertionSucceeds() {
		ApiSearchCriteria apiSearchCriteria = ApiSearchCriteria.builder().min(ZERO).max(ZERO).build();
		RandomSearchCriteria randomSearchCriteria = RandomSearchCriteria.builder().min(ZERO).max(ZERO).build();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            numberService.sum(apiSearchCriteria, randomSearchCriteria);
        });

		String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(EXCEPTION_MESSAGE));
    }
}
