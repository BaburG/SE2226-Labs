package week8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class NameCheckerTest {
    static List<String> movie_list;
    static final String URL = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,asc";

    @BeforeAll
    @DisplayName("Get Data")
    static void getData() {
        Bot selenium = new Bot(URL);
        selenium.run();
        movie_list = selenium.getMovieNames();
        System.out.println(movie_list.size() + " movie information collected");
    }

    @ParameterizedTest
    @MethodSource("stringList")
    void checkNames(String name) {
        assertTrue(NameChecker.check(name));
    }

    static Stream<String> stringList() {
        return movie_list.stream();
    }
}