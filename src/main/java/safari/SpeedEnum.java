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

    public int getGiraffeSteps() {
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
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public double getGiraffeHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public int getZebraSteps() {
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

    public double getZebraThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public double getZebraHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public int getLionSteps() {
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

    public double getLionThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public double getLionHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public int getLeopardSteps() {
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

    public double getLeopardThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }

    public double getLeopardHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.1;
            }
            case EAGLE -> {
                return 0.5;
            }
            default -> {
                return 0.05;
            }
        }
    }
}