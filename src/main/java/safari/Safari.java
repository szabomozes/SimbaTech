package safari;

import entity.Entity;
import entity.Path;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Ranger;
import entity.notmobile.Entry;
import entity.notmobile.Exit;
import entity.notmobile.Water;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import map.EntityCreate;
import panels.game.Calendar;
import panels.game.toolbar.ToolBarCardLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Safari {
    public static Safari Instance = new Safari();

    private DifficultyEnum difficultyEnum;
    private int date;
    public int coin;
    private DateTimer dateTimer;
    public final int lionPrice = 1;
    public final int leopardPrice = 1;
    public final int zebraPrice = 1;
    public final int giraffePrice = 1;
    public final int palmTreePrice = 1;
    public final int baobabPrice = 1;
    public final int panciumPrice = 1;
    public final int waterPrice = 1;
    public final int rangerPrice = 1;
    public String shopping;
    private List<Lion> lions = new ArrayList<>();
    private List<Leopard> leopards = new ArrayList<>();
    private List<Zebra> zebras = new ArrayList<>();
    private List<Giraffe> giraffes = new ArrayList<>();
    private List<PalmTree> palmTrees = new ArrayList<>();
    private List<Baobab> baobabs = new ArrayList<>();
    private List<Pancium> panciums = new ArrayList<>();
    private List<Water> waters = new ArrayList<>();
    private List<Ranger> rangers = new ArrayList<>();
    private Entry entry = null;
    private Exit exit = null;
    private boolean roadBuilding = false;
    private List<Path> paths = new ArrayList<>();
    private Path tempPath = null;


    private Safari() {
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

        coin = 100;
        date = 0;
        updateDate();
        difficultyEnum = diff;
        shopping = null;
        roadBuilding = false;
        tempPath = null;

        lions.clear();
        leopards.clear();
        zebras.clear();
        giraffes.clear();
        palmTrees.clear();
        baobabs.clear();
        panciums.clear();
        waters.clear();
        rangers.clear();
        paths.clear();

        entry = EntityCreate.getEntry();
        exit = EntityCreate.getExit();
        lions = EntityCreate.getLions();

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
            case "palmtree":
                return palmTreePrice;
            case "baobab":
                return baobabPrice;
            case "pancium":
                return panciumPrice;
            case "water":
                return waterPrice;
            case "ranger":
                return rangerPrice;
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

    public void placeSomething(int x, int y) {
        switch (shopping) {
            case "lion":
                lions.add(new Lion(x, y));
                coin -= lionPrice;
                break;
            case "leopard":
                leopards.add(new Leopard(x, y));
                coin -= leopardPrice;
                break;
            case "zebra":
                zebras.add(new Zebra(x, y));
                coin -= zebraPrice;
                break;
            case "giraffe":
                giraffes.add(new Giraffe(x, y));
                coin -= giraffePrice;
                break;
            case "baobab":
                baobabs.add(new Baobab(x, y));
                coin -= baobabPrice;
                break;
            case "palmtree":
                palmTrees.add(new PalmTree(x, y));
                coin -= palmTreePrice;
                break;
            case "pancium":
                panciums.add(new Pancium(x, y));
                coin -= panciumPrice;
                break;
            case "water":
                waters.add(new Water(x, y));
                coin -= waterPrice;
                break;
            case "ranger":
                rangers.add(new Ranger(x, y));
                coin -= rangerPrice;
                break;
        }
        shopping = null;
    }

    public List<Entity> getAllEntities() {
        List<Entity> allEntities = new ArrayList<>();

        allEntities.addAll(giraffes);
        allEntities.addAll(zebras);
        allEntities.addAll(leopards);
        allEntities.addAll(lions);
        allEntities.addAll(palmTrees);
        allEntities.addAll(baobabs);
        allEntities.addAll(panciums);
        allEntities.addAll(waters);
        allEntities.addAll(rangers);


        allEntities.sort(Comparator.comparingInt(entity -> entity.getY()));

        return allEntities;
    }

    public List<Lion> getLions() {
        return lions;
    }

    public List<Leopard> getLeopards() {
        return leopards;
    }

    public List<Zebra> getZebras() {
        return zebras;
    }

    public List<Giraffe> getGiraffes() {
        return giraffes;
    }

    public List<PalmTree> getPalmTrees() {
        return palmTrees;
    }

    public List<Baobab> getBaobabs() {
        return baobabs;
    }

    public List<Pancium> getPanciums() {
        return panciums;
    }

    public List<Water> getWaters() {
        return waters;
    }

    public List<Ranger> getRangers() {
        return rangers;
    }

    public boolean getRoadBuilding() {
        return roadBuilding;
    }

    public void setRoadBuilding(boolean roadBuilding) {
        this.roadBuilding = roadBuilding;
    }

    public Entry getEntry() {
        return entry;
    }

    public Exit getExit() {
        return exit;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setTempPath(Path temp) {
        tempPath = temp;
    }

    public Path getTempPath() {
        return tempPath;
    }

    public void saveARoad(int x, int y) {
        tempPath.setEndX(x);
        tempPath.setEndY(y);
        tempPath.addANewRoad();
        tempPath.endCoorinateCopyToStartCoordinate();
        System.out.println("Mentve");
    }
}
