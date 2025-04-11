package safari;

/**
 * Enum representing different speed settings for the safari simulation, affecting various entity behaviors and time progression.
 */
public enum SpeedEnum {
    SNAIL,      // Slow speed setting
    HIPPOPOTAMUS, // Medium speed setting
    EAGLE;      // Fast speed setting

    /**
     * Returns the next speed setting in the sequence, cycling back to the first if at the end.
     *
     * @return The next SpeedEnum value.
     */
    public SpeedEnum next() {
        SpeedEnum[] values = SpeedEnum.values();
        int ordinal = this.ordinal();
        return values[(ordinal + 1) % values.length];
    }

    /**
     * Gets the number of seconds between date updates for this speed setting.
     *
     * @return The date update interval in seconds (SNAIL: 300, HIPPOPOTAMUS: 50, EAGLE: 5).
     */
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

    /**
     * Gets the step size for jeep movement under this speed setting.
     *
     * @return The jeep movement steps (SNAIL: 1, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
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

    /**
     * Gets the step size for ranger movement under this speed setting.
     *
     * @return The ranger movement steps (SNAIL: 1, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
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

    /**
     * Gets the step size for giraffe movement under this speed setting.
     *
     * @return The giraffe movement steps (SNAIL: 1, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
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

    /**
     * Gets the thirst increase rate for giraffes under this speed setting.
     *
     * @return The giraffe thirst rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the hunger increase rate for giraffes under this speed setting.
     *
     * @return The giraffe hunger rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the step size for zebra movement under this speed setting.
     *
     * @return The zebra movement steps (SNAIL: 2, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
    public int getZebraSteps() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 20;
            }
            case EAGLE -> {
                return 50;
            }
            default -> {
                return 2;
            }
        }
    }

    /**
     * Gets the thirst increase rate for zebras under this speed setting.
     *
     * @return The zebra thirst rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the hunger increase rate for zebras under this speed setting.
     *
     * @return The zebra hunger rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the step size for lion movement under this speed setting.
     *
     * @return The lion movement steps (SNAIL: 1, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
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

    /**
     * Gets the thirst increase rate for lions under this speed setting.
     *
     * @return The lion thirst rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the hunger increase rate for lions under this speed setting.
     *
     * @return The lion hunger rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the step size for leopard movement under this speed setting.
     *
     * @return The leopard movement steps (SNAIL: 1, HIPPOPOTAMUS: 20, EAGLE: 50).
     */
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

    /**
     * Gets the step size for poacher movement under this speed setting.
     *
     * @return The poacher movement steps (SNAIL: 1.0, HIPPOPOTAMUS: 1.5, EAGLE: 2.0).
     */
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

    /**
     * Gets the thirst increase rate for leopards under this speed setting.
     *
     * @return The leopard thirst rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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

    /**
     * Gets the hunger increase rate for leopards under this speed setting.
     *
     * @return The leopard hunger rate (SNAIL: 0.05, HIPPOPOTAMUS: 0.1, EAGLE: 0.5).
     */
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