package dut.pd3.practics.automaton;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestWordAutomaton automaton = new TestWordAutomaton();

        List<String> samples = List.of(
                "abcTESTabc",
                "abcTES",
                "abcTE",
                "abcT",
                "abcdef",
                "TTEST",
                "TETEST"
        );

        for (String text : samples) {
            State result = automaton.process(text);
            System.out.println("\"" + text + "\" -> " + result);
        }
    }
}