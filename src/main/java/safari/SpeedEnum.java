package safari;

public enum SpeedEnum {
    SNAIL,
    HIPPOPOTAMUS,
    EAGLE;

    public SpeedEnum next() {
        SpeedEnum[] values = SpeedEnum.values();
        int ordinal = this.ordinal();
        return values[(ordinal + 1) % values.length];
    }

    public int getDateSec() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 10; // 10mp
            }
            case EAGLE -> {
                return 1; // 1mp
            }
            default -> {
                return 60; // 60mp
            }
        }
    }

    public double getJeepSec() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1; // 0,1mp
            }
            case EAGLE -> {
                return 0.01; // 0,01mp
            }
            default -> {
                return 1; // 1mp
            }
        }
    }
}