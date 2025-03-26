package logic;

public enum SpeedEnum {
    SNAIL,
    HIPPOPOTAMUS,
    EAGLE;

    public SpeedEnum next() {
        SpeedEnum[] values = SpeedEnum.values();
        int ordinal = this.ordinal();
        return values[(ordinal + 1) % values.length];
    }

    public int getDateTick() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 10; // 60MP -> 1P = 1 nap
            }
            case EAGLE -> {
                return 1; // 10MP -> 0,17P = 1 nap
            }
            default -> {
                return 60; // 600MP -> 10P = 1 nap
            }
        }
    }
}