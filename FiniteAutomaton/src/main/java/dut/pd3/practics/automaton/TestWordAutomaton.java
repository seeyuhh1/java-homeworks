package dut.pd3.practics.automaton;

public class TestWordAutomaton {

    public State process(String input) {
        if (input == null) {
            return State.S;
        }

        State currentState = State.S;

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            currentState = nextState(currentState, symbol);
        }

        return currentState;
    }

    private State nextState(State state, char symbol) {
        return switch (state) {
            case S -> (symbol == 'T') ? State.ONE : State.S;
            case ONE -> switch (symbol) {
                case 'E' -> State.TWO;
                case 'T' -> State.ONE;
                default -> State.S;
            };
            case TWO -> switch (symbol) {
                case 'S' -> State.THREE;
                case 'T' -> State.ONE;
                default -> State.S;
            };
            case THREE -> (symbol == 'T') ? State.F : State.S;
            case F -> State.F;
        };
    }
}