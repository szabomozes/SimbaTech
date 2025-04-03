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
                return 50;
            }
            case EAGLE -> {
                return 5;
            }
            default -> {
                return 300;
            }
        }
    }

    public double getJeepNanoSec() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 10_000_000; // 100ms (0,1mp)
            }
            case EAGLE -> {
                return 5_000_000; // 50ms (0,05mp)
            }
            default -> {
                return 20_000_000; // 200ms (0,2mp)
            }
        }
    }
    public int getJeepSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 42; // 50ms (0,05mp)
            }
            default -> {
                return 1; // 200ms (0,2mp)
            }
        }
    }
}