package logic;

import entity.mobile.Giraffe;
import entity.mobile.Leopard;
import entity.mobile.Lion;
import entity.mobile.Zebra;
import panels.game.Calendar;
import panels.game.toolbar.ToolBarCardLayout;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static Logic Instance = new Logic();

    private DifficultyEnum difficultyEnum;
    private int date;
    public int coin;
    private DateTimer dateTimer;
    public final int lionPrice = 1;
    public final int leopardPrice = 1;
    public final int zebraPrice = 1;
    public final int giraffePrice = 1;
    public String shopping;
    public List<Lion> lions = new ArrayList<>();
    public List<Leopard> leopards = new ArrayList<>();
    public List<Zebra> zebras = new ArrayList<>();
    public List<Giraffe> giraffes = new ArrayList<>();


    private Logic() {
        dateTimer = new DateTimer();
        dateTimer.start();
    }

    public void shutDown() {
        dateTimer.stopTimer();
    }

    public void reset(DifficultyEnum diff) {
        if (dateTimer != null) {
            dateTimer.stop();
        }

        date = 0;
        updateDate();
        difficultyEnum = diff;
        shopping = null;
        coin = 5;

        lions.clear();
        leopards.clear();
        zebras.clear();
        giraffes.clear();

        dateTimer = new DateTimer();
        dateTimer.start();
    }

    public void updateDate() {
        date++;
        System.out.println("Date event triggered: " + date);
        Calendar.Instance.setDate(date);
    }

    private int getPriceByMessage(String message) {
        switch (message) {
            case "lion":
                return lionPrice;
            case "leopard":
                return leopardPrice;
            case "zebra":
                return zebraPrice;
            case "giraffe":
                return giraffePrice;
            default:
                return 999999999;
        }
    }

    public void buySoemthing(String message) {
        int price = getPriceByMessage(message);
        if (coin >= price) {
            ToolBarCardLayout.Instance.showCard("buying");
            shopping = message;
        }
    }
}
