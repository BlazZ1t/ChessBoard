public class Position {
    private char letter;
    private int number;

    Position(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public String stringValue() {
        return letter + String.valueOf(number);
    }

}
