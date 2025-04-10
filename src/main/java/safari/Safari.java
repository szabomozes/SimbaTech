package safari;

import core.Resources;
import entity.Entity;
import entity.mobile.Jeep;
import entity.mobile.person.Poacher;
import map.Coordinate;
import timer.EntitiesExecutor;
import road.Path;
import entity.mobile.animal.*;
import entity.mobile.person.Ranger;
import entity.notmobile.*;
import entity.notmobile.plant.*;
import map.EntityCreate;
import panels.game.Calendar;
import panels.game.toolbar.ToolBarCardLayout;

import java.util.*;
import java.util.stream.Collectors;

public class Safari {
    public static final Safari Instance = new Safari();

    private DifficultyEnum difficultyEnum;
    private int date;
    public int coin;
    private DateTimer dateTimer;
    public String shopping;
    private List<Entity> animals = new ArrayList<>();
    private List<Entity> plants = new ArrayList<>();
    private List<Entity> baobabs = new ArrayList<>();
    private List<Water> waters = new ArrayList<>();
    private List<Ranger> rangers = new ArrayList<>();
    private Entry entry = null;
    private Exit exit = null;
    private boolean roadBuilding = false;
    private List<Path> paths = new ArrayList<>();
    private List<Path> tempPaths = new ArrayList<>();
    private boolean selling = false;
    private List<Jeep> jeeps = new ArrayList<>();
    private List<Poacher> poachers = new ArrayList<>();
    private boolean selectedRanger = false;
    private EntitiesExecutor entitiesExecutor = EntitiesExecutor.Instance;

    private Safari() {
        dateTimer = new DateTimer();
        dateTimer.start();
    }

    public void shutDown() {
        dateTimer.stopTimer();
        entitiesExecutor.stop();
    }

    public void reset(DifficultyEnum diff) {
        if (dateTimer != null) {
            dateTimer.stop();
        }

        entitiesExecutor.reset();

        coin = 1000;
        date = 0;
        updateDate();
        difficultyEnum = diff;
        shopping = null;
        roadBuilding = false;
        selling = false;
        selectedRanger = false;

        clearAllEntities();

        entry = EntityCreate.getEntry();
        exit = EntityCreate.getExit();
        animals.addAll(EntityCreate.getLions());

        dateTimer = new DateTimer();
        dateTimer.start();
    }

    private void clearAllEntities() {
        animals.clear();
        plants.clear();
        waters.clear();
        rangers.clear();
        paths.clear();
        tempPaths.clear();
        jeeps.clear();
        poachers.clear();
    }

    public void updateDate() {
        date++;
        System.out.println("Date event triggered: " + date);
        Calendar.Instance.setDate(date);
    }

    public void buySomething(String message) {
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
                animals.add(new Lion(x, y));
                break;
            case "leopard":
                animals.add(new Leopard(x, y));
                break;
            case "zebra":
                animals.add(new Zebra(x, y));
                break;
            case "giraffe":
                animals.add(new Giraffe(x, y));
                break;
            case "baobab":
                plants.add(new Baobab(x, y));
                break;
            case "palmtree":
                plants.add(new PalmTree(x, y));
                break;
            case "pancium":
                plants.add(new Pancium(x, y));
                break;
            case "water":
                waters.add(new Water(x, y));
                break;
            case "ranger":
                Ranger ranger = new Ranger(x, y);
                rangers.add(ranger);
                entitiesExecutor.addScheduleAtFixedRate(ranger::handleRangerMovement);
                break;
            case "jeep":
                Jeep jeep = new Jeep(EntityCreate.entryX, EntityCreate.entryY);
                jeeps.add(jeep);
                entitiesExecutor.addScheduleAtFixedRate(jeep::handleJeepMovement);
                break;
        }

        coin -= price;
        shopping = null;
    }

    public void placePoachers(int num) {
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < num; i++) {
            x = rand.nextInt(Resources.Instance.map.getWidth());
            y = rand.nextInt(Resources.Instance.map.getHeight());
            Poacher poacher = new Poacher(x, y);
            poacher.move();
            poachers.add(poacher);
            entitiesExecutor.addScheduleAtFixedRate(poacher::poacherVisibility);
            entitiesExecutor.addScheduleAtFixedRate(poacher::move);
            System.out.println("poacher out");
            //poacher.reveal();
        }
    }

    public List<Entity> rangersAndJeeps() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.addAll(rangers);
        entities.addAll(jeeps);
        return entities;
    }

    public void sellSomething(int id) {
        ToolBarCardLayout.Instance.showCard("selling");

        Entity entity = getEntityById(id);
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(entity.getClass().getSimpleName().toLowerCase()));

        coin += price;
        removeEntityById(id);
    }

    public void setSellingMode(boolean mode) {
        this.selling = mode;
    }

    public boolean getSellingMode() {
        return this.selling;
    }

    public Entity getEntityById(int id) {
        return getAllEntities().stream()
                .filter(e -> e.id == id)
                .findFirst()
                .orElse(null);
    }

    public void removeEntityById(int id) {
        Entity entity = getEntityById(id);
        if (entity == null) return;

        if (entity instanceof Animal) {
            animals.remove(entity);
        } else if (entity instanceof Plant) {
            plants.remove(entity);
        } else if (entity instanceof Water) {
            waters.remove(entity);
        } else if (entity instanceof Ranger) {
            rangers.remove(entity);
        } else if (entity instanceof Jeep) {
            // TODO: deleteTimer(entity);
            jeeps.remove(entity);
        } /*else if (entity instanceof Poacher) {
            poachers.remove(entity);
        }*/
    }

    public List<Entity> getAllEntities() {
        List<Entity> allEntities = new ArrayList<>();
        allEntities.addAll(animals);
        allEntities.addAll(plants);
        allEntities.addAll(waters);
        allEntities.addAll(rangers);
        allEntities.addAll(jeeps);
        allEntities.addAll(poachers);
        return allEntities;
    }

    public List<Entity> getAlmostAllEntitiesForSell() {
        return animals.stream()
                .filter(e -> e instanceof Animal)
                .collect(Collectors.toList());
    }

    public List<Entity> getAllEntitiesWithSorted() {
        List<Entity> allEntities = new ArrayList<>(getAllEntities());
        allEntities.sort(Comparator.comparingInt(e -> e.getY() + e.getHeight()));
        return allEntities;
    }

    public List<Entity> getAnimalsPoachers() {
        List<Entity> entities = new ArrayList<>(animals);
        entities.addAll(jeeps);
        return entities;
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
        return tempPaths.stream()
                .mapToInt(path -> path.getPixelCount())
                .sum() * (int) Prices.getPriceByEnum(Prices.ROAD);
    }

    public void addAPathToPaths(Path path) {
        paths.add(path);
    }

    public void saveARoad(int x, int y) {
        Path path = tempPaths.get(tempPaths.size() - 1);
        path.setEndX(x);
        path.setEndY(y);
        path.addANewRoad();
        path.endCoorinateCopyToStartCoordinate();
    }

    public boolean isSelectedRanger() {
        return selectedRanger;
    }

    public void setSelectedRanger(boolean selectedRanger) {
        this.selectedRanger = selectedRanger;
    }


    private List<Coordinate> getEntityCoordinates(Entity entity) {
        List<Coordinate> coordinates = new ArrayList<>();
        int startX = entity.getX();
        int startY = entity.getY();
        int endX = startX + entity.getWidth();
        int endY = startY + entity.getHeight();

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                coordinates.add(new Coordinate(x, y));
            }
        }

        return coordinates;
    }

    public List<Water> getWaters() {
        return waters;
    }

    public List<Entity> getAnimals() {
        return animals;
    }
}
