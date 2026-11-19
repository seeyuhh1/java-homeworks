package dut.pd3.practics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestWordAutomatonTest {

    private TestWordAutomaton automaton;

    @BeforeEach
    void setUp() {
        automaton = new TestWordAutomaton();
    }

    @ParameterizedTest(name = "Рядок \"{0}\" має завершитись у стані {1}")
    @CsvSource({
            "'', S",
            "abcdef, S",
            "test, S",
            "abcT, 1",
            "T, 1",
            "abcTE, 2",
            "TE, 2",
            "abcTES, 3",
            "TES, 3",
            "TEST, F",
            "abcTESTabc, F",
            "TEST123, F",
            "123TEST, F",
            "TEST_TEST, F"
    })
    @DisplayName("Перевірка базових станів автомата (S, 1, 2, 3, F)")
    void testAutomatonStandardCases(String input, String expectedStateLabel) {
        State actualState = automaton.process(input);
        Assertions.assertEquals(expectedStateLabel, actualState.getLabel());
    }

    @ParameterizedTest(name = "Кейс 3*: рядок \"{0}\" має перейти у стан {1}")
    @CsvSource({
            "TTEST, F",
            "TTTEST, F",
            "abcTTESTxyz, F",
            "TETEST, F",
            "TETETEST, F",
            "abcTETESTxyz, F",
            "TTES, 3",
            "TETES, 3",
            "TT, 1",
            "TET, 1"
    })
    @DisplayName("Перевірка накладень префіксів (завдання 3*)")
    void testAutomatonOverlappingCases(String input, String expectedStateLabel) {
        State actualState = automaton.process(input);
        Assertions.assertEquals(expectedStateLabel, actualState.getLabel());
    }

    @Test
    @DisplayName("Перевірка null на вході")
    void testNullInput() {
        Assertions.assertEquals(State.S, automaton.process(null));
    }
}