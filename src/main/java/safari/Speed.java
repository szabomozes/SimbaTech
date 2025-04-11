package safari;

/**
 * Singleton class managing the speed settings of the safari simulation.
 */
public class Speed {

    public static Speed Instance = new Speed();

    public SpeedEnum speedEnum = SpeedEnum.SNAIL;

    /**
     * Gets the current speed setting of the simulation.
     *
     * @return The current SpeedEnum value.
     */
    public SpeedEnum getCurrentSpeedEnum() {
        return speedEnum;
    }

    /**
     * Resets the speed setting to the default (SNAIL).
     */
    public void reset() {
        speedEnum = SpeedEnum.SNAIL;
    }
}