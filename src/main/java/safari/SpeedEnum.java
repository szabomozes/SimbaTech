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

    public int getJeepNanoSec() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 10_000_000;
            }
            case EAGLE -> {
                return 5_000_000;
            }
            default -> {
                return 20_000_000;
            }
        }
    }

    public int getJeepSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 42;
            }
            default -> {
                return 1;
            }
        }
    }

    public double getRangerNanoSec() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 10_000_000;
            }
            case EAGLE -> {
                return 5_000_000;
            }
            default -> {
                return 5_000_000;
            }
        }
    }

    public int getRangerSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 42;
            }
            default -> {
                return 1;
            }
        }
    }

    public double getPoacherSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 1.5;
            }
            case EAGLE -> {
                return 2.0;
            }
            default -> {
                return 1.0;
            }
        }
    }
}