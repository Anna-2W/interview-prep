package com.mastery.interview.complexity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class ComplexityQuizTest {

    private static String normalize(String answer) {
        return answer.toLowerCase()
                .replace(" ", "")
                .replace("²", "^2")
                .replace("ⁿ", "^n")
                .replace("log(n)", "logn")
                .replace("n*n", "n^2")
                .replace("nxm", "n*m")
                .replace("n×m", "n*m")
                .replace("m*n", "n*m")
                .replace("o(nm)", "o(n*m)")
                .replace("o(mn)", "o(n*m)");
    }

    private static void check(Supplier<String> answer, String expected) {
        assertThat(normalize(answer.get())).isEqualTo(normalize(expected));
    }

    @Test
    void q01() {
        check(ComplexityQuiz::q01, "O(n)");
    }

    @Test
    void q02() {
        check(ComplexityQuiz::q02, "O(1)");
    }

    @Test
    void q03() {
        check(ComplexityQuiz::q03, "O(n^2)");
    }

    @Test
    void q04() {
        check(ComplexityQuiz::q04, "O(log n)");
    }

    @Test
    void q05() {
        check(ComplexityQuiz::q05, "O(n)");
    }

    @Test
    void q06() {
        check(ComplexityQuiz::q06, "O(n*m)");
    }

    @Test
    void q07() {
        check(ComplexityQuiz::q07, "O(n)");
    }

    @Test
    void q08() {
        check(ComplexityQuiz::q08, "O(n log n)");
    }

    @Test
    void q09() {
        check(ComplexityQuiz::q09, "O(n^2)");
    }

    @Test
    void q10() {
        check(ComplexityQuiz::q10, "O(2^n)");
    }
}
