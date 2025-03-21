package panels.game.toolbar.buttons.speed;

public enum SpeedEnum {
    SNAIL,
    HIPPOPOTAMUS,
    EAGLE;

    public SpeedEnum next() {
        SpeedEnum[] values = SpeedEnum.values();
        int ordinal = this.ordinal();
        return values[(ordinal + 1) % values.length];
    }
}