package safari;

import entity.Entity;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Ranger;
import entity.notmobile.Water;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
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
    private boolean selling = false;


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

        date = 0;
        updateDate();
        difficultyEnum = diff;
        shopping = null;
        coin = 100;
        selling = false;

        lions.clear();
        leopards.clear();
        zebras.clear();
        giraffes.clear();
        palmTrees.clear();
        baobabs.clear();
        panciums.clear();
        waters.clear();
        rangers.clear();

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
                return Prices.LION.getPrice();
            case "leopard":
                return Prices.LEOPARD.getPrice();
            case "zebra":
                return Prices.ZEBRA.getPrice();
            case "giraffe":
                return Prices.GIRAFFE.getPrice();
            case "palmtree":
                return Prices.PALMTREE.getPrice();
            case "baobab":
                return Prices.BAOBAB.getPrice();
            case "pancium":
                return Prices.PANICUM.getPrice();
            case "water":
                return Prices.WATER.getPrice();
            case "ranger":
                return Prices.RANGER.getPrice();
            case "jeep":
                return Prices.JEEP.getPrice();
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
                coin -= Prices.LION.getPrice();
                break;
            case "leopard":
                leopards.add(new Leopard(x, y));
                coin -= Prices.LEOPARD.getPrice();
                break;
            case "zebra":
                zebras.add(new Zebra(x, y));
                coin -= Prices.ZEBRA.getPrice();
                break;
            case "giraffe":
                giraffes.add(new Giraffe(x, y));
                coin -= Prices.GIRAFFE.getPrice();
                break;
            case "baobab":
                baobabs.add(new Baobab(x, y));
                coin -= Prices.BAOBAB.getPrice();
                break;
            case "palmtree":
                palmTrees.add(new PalmTree(x, y));
                coin -= Prices.PALMTREE.getPrice();
                break;
            case "pancium":
                panciums.add(new Pancium(x, y));
                coin -= Prices.PANICUM.getPrice();
                break;
            case "water":
                waters.add(new Water(x, y));
                coin -= Prices.WATER.getPrice();
                break;
            case "ranger":
                rangers.add(new Ranger(x, y));
                coin -= Prices.RANGER.getPrice();
                break;
            case "jeep":
                coin -= Prices.JEEP.getPrice();
                break;
        }
        shopping = null;
    }

    public void sellSomething(int id) {
        ToolBarCardLayout.Instance.showCard("selling");
        Entity actual = getEntityById(id);
        switch (typeOfEntity(actual)) {
            case "giraffe":
                coin += Prices.GIRAFFE.getPrice();
                break;
            case "leopard":
                coin += Prices.LEOPARD.getPrice();
                break;
            case "lion":
                coin += Prices.LION.getPrice();
                break;
            case "zebra":
                coin += Prices.ZEBRA.getPrice();
                break;
            case "ranger":
                coin += Prices.RANGER.getPrice();
                break;
            default:
                break;
        }
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
        switch (typeOfEntity(actual)) {
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
            case "palm-tree":
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

    public String typeOfEntity(Entity e) {
        if (e instanceof Giraffe) return "giraffe";
        else if (e instanceof Leopard) return "leopard";
        else if (e instanceof Lion) return "lion";
        else if (e instanceof Zebra) return "zebra";
        else if (e instanceof PalmTree) return "palm-tree";
        else if (e instanceof Baobab) return "baobab";
        else if (e instanceof Pancium) return "pancium";
        else if (e instanceof Water) return "water";
        else if (e instanceof Ranger) return "ranger";
        // else if (e instanceof Jeep) return "giraffe";
        return "error";
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
}
