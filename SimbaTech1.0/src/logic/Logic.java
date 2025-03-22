package logic;

public class Logic {

    public static Logic Instance = new Logic();

    private int date = 1;
    private Difficulty difficulty;
    private int coin;
    private boolean lionShopping;

    private Logic() {

    }

    public void reset(Difficulty diff) {
        date = 1;
        difficulty = diff;
        lionShopping = false;
        coin = 1;
    }

    public int getCoin() {
        return coin;
    }
}
