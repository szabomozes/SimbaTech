package safari;

import entity.Entity;
import entity.mobile.Jeep;
import entity.mobile.JeepTimer;
import entity.mobile.person.RangerTimer;
import road.Path;
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
    private List<Path> tempPaths = new ArrayList<>();
    private boolean selling = false;
    private List<Jeep> jeeps = new ArrayList<>();
    private List<JeepTimer> jeepTimers = new ArrayList<>();
    private boolean selectedRanger = false;
    private List<RangerTimer> rangerTimers = new ArrayList<>();

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

        coin = 10000;
        date = 0;
        updateDate();
        difficultyEnum = diff;
        shopping = null;
        roadBuilding = false;
        selling = false;
        selectedRanger = false;

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
        tempPaths.clear();
        jeeps.clear();

        for (JeepTimer jeepTimer : jeepTimers) {
            jeepTimer.stop();
        }
        jeepTimers.clear();
        for (RangerTimer rangerTimer : rangerTimers) {
            rangerTimer.stop();
        }
        rangerTimers.clear();

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

    public void buySoemthing(String message) {
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(message));
        if (coin >= price) {
            ToolBarCardLayout.Instance.showCard("buying");
            shopping = message;
        }
    }

    public void placeSomething(int x, int y) {
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(shopping));
        switch (shopping) {
            case "lion":
                lions.add(new Lion(x, y));
                coin -= price;
                break;
            case "leopard":
                leopards.add(new Leopard(x, y));
                coin -= price;
                break;
            case "zebra":
                zebras.add(new Zebra(x, y));
                coin -= price;
                break;
            case "giraffe":
                giraffes.add(new Giraffe(x, y));
                coin -= price;
                break;
            case "baobab":
                baobabs.add(new Baobab(x, y));
                coin -= price;
                break;
            case "palmtree":
                palmTrees.add(new PalmTree(x, y));
                coin -= price;
                break;
            case "pancium":
                panciums.add(new Pancium(x, y));
                coin -= price;
                break;
            case "water":
                waters.add(new Water(x, y));
                coin -= price;
                break;
            case "ranger":
                Ranger ranger = new Ranger(x, y);
                rangers.add(ranger);
                rangerTimers.add(new RangerTimer(ranger));
                coin -= price;
                break;
            case "jeep":
                coin -= price;
                break;
        }
        shopping = null;
    }

    public void sellSomething(int id) {
        ToolBarCardLayout.Instance.showCard("selling");

        String message = getEntityById(id).getClass().getSimpleName().toLowerCase();
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(message));

        coin += price;
        removeEntityById(id);
        System.out.println("deleted");
    }

    public void setSellingMode(boolean mode) {
        this.selling = mode;
    }

    public boolean getSellingMode() {
        return this.selling;
    }

    public Entity getEntityById(int id) {
        List<Entity> allentities = getAllEntities();
        for (Entity e : allentities) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public void removeEntityById(int id) {
        Entity actual = getEntityById(id);
        String message = actual.getClass().getSimpleName().toLowerCase();
        switch (message) {
            case "giraffe":
                giraffes.remove((Giraffe)actual);
                break;
            case "leopard":
                leopards.remove((Leopard)actual);
                break;
            case "lion":
                lions.remove((Lion)actual);
                break;
            case "zebra":
                zebras.remove((Zebra)actual);
                break;
            case "palmtree":
                palmTrees.remove((PalmTree)actual);
                break;
            case "baobab":
                baobabs.remove((Baobab)actual);
                break;
            case "pancium":
                panciums.remove((Pancium)actual);
                break;
            case "water":
                waters.remove((Water)actual);
                break;
            case "ranger":
                rangers.remove((Ranger)actual);
                break;
        }
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
        allEntities.addAll(jeeps);


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

    public void clearTempPaths() {
        tempPaths.clear();
    }

    public List<Path> getTempPaths() {
        return tempPaths;
    }

    public int getTempPathsPrice() {
        int sum = 0;
        for (Path path : tempPaths) {
            sum += path.getPixelCount();
        }
        sum = (int) (sum * Prices.getPriceByEnum(Prices.ROAD));
        return sum;
    }

    public void addAPathToPaths(Path path) {
        paths.add(path);
    }

    public void saveARoad(int x, int y) {
        Path path = tempPaths.getLast();
        path.setEndX(x);
        path.setEndY(y);
        path.addANewRoad();
        path.endCoorinateCopyToStartCoordinate();
        System.out.println("Mentve");
    }

    public void addAJeep() {
        Jeep jeep = new Jeep(EntityCreate.entryX, EntityCreate.entryY);
        jeeps.add(jeep);
        jeepTimers.add(new JeepTimer(jeep));
    }

    public boolean isSelectedRanger() {
        return selectedRanger;
    }

    public void setSelectedRanger(boolean selectedRanger) {
        this.selectedRanger = selectedRanger;
    }
}
