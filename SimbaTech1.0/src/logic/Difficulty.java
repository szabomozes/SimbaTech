package logic;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public int getToInt() {
        int returnInt = 30;
        switch (this) {
            case MEDIUM -> {
                return 20;
            }
            case HARD -> {
                return 10;
            }
            default -> {
                return 30;
            }
        }
    }
}
