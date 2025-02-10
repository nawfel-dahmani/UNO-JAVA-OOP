public class Card {
    enum Color {
        blue, red, green, yellow,wild;
        private static final Color[] color = Color.values();

        public static Color getColor(int i) {
            return Color.color[i];
        }
    }
    enum Number {
        zero, one, two, three, four, five, six, seven, eight, nine,DrawTwo,skip,Reveres,wild,wild_four;

        private static final Number[] numbers = Number.values();

        public static Number getColor(int i) {
            return Number.numbers[i];
        }
    }

    private final Color color;
    private final Number number;

    public Card(final Color color,final Number number) {
        this.color = color;
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(color)
                .append(" ")
                .append(number)
                .toString();
    }

}

