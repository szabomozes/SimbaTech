package safari;

public class Speed{

    public static Speed Instance = new Speed();

    public SpeedEnum speedEnum = SpeedEnum.SNAIL;

    public SpeedEnum getCurrentSpeedEnum() {
        return speedEnum;
    }

    public void reset() {
        speedEnum = SpeedEnum.SNAIL;
    }
}
