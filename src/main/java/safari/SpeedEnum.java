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

    public int getJeepSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 50;
            }
            default -> {
                return 1;
            }
        }
    }

    public int getRangerSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 50;
            }
            default -> {
                return 1;
            }
        }
    }

    public double getGiraffeThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.5;
            }
            case EAGLE -> {
                return 1;
            }
            default -> {
                return 0.1;
            }
        }
    }

    public double getGiraffeHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.5;
            }
            case EAGLE -> {
                return 1;
            }
            default -> {
                return 0.1;
            }
        }
    }
}