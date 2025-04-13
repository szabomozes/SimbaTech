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
     * @return The date update interval in seconds.
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
     * @return The jeep movement steps.
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
     * @return The ranger movement steps.
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
     * @return The giraffe movement steps.
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
     * @return The giraffe thirst rate.
     */
    public double getGiraffeThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the hunger increase rate for giraffes under this speed setting.
     *
     * @return The giraffe hunger rate.
     */
    public double getGiraffeHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the step size for zebra movement under this speed setting.
     *
     * @return The zebra movement steps.
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
     * @return The zebra thirst rate.
     */
    public double getZebraThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the hunger increase rate for zebras under this speed setting.
     *
     * @return The zebra hunger rate.
     */
    public double getZebraHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the step size for lion movement under this speed setting.
     *
     * @return The lion movement steps.
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
     * @return The lion thirst rate.
     */
    public double getLionThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the hunger increase rate for lions under this speed setting.
     *
     * @return The lion hunger rate.
     */
    public double getLionHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the step size for leopard movement under this speed setting.
     *
     * @return The leopard movement steps.
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
     * @return The poacher movement steps.
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
     * @return The leopard thirst rate.
     */
    public double getLeopardThirst() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }

    /**
     * Gets the hunger increase rate for leopards under this speed setting.
     *
     * @return The leopard hunger rate.
     */
    public double getLeopardHunger() {
        switch (this) {
            case HIPPOPOTAMUS -> {
                return 0.05;
            }
            case EAGLE -> {
                return 0.1;
            }
            default -> {
                return 0.01;
            }
        }
    }
}