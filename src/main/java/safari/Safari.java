package safari;

import core.Resources;
import entity.Entity;
import entity.mobile.Jeep;
import entity.mobile.person.Poacher;
import map.AnimalCreate;
import map.Coordinate;
import map.PlantCreate;
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
import java.util.concurrent.ScheduledFuture;
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
    private Map<Ranger, Integer> rangerJoinDates = new HashMap<>();
    private GameStateChecker gameStateChecker;
    private int passengers;

    private Safari() {
        dateTimer = new DateTimer();
        dateTimer.start();
        passengers = 0;
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
        passengers = 0;
        updateDate();
        difficultyEnum = diff;
        System.out.println("Difficulty: " + difficultyEnum);
        gameStateChecker = new GameStateChecker(difficultyEnum);
        shopping = null;
        roadBuilding = false;
        selling = false;
        selectedRanger = false;

        clearAllEntities();

        entry = EntityCreate.getEntry();
        exit = EntityCreate.getExit();

        AnimalCreate.getLions(difficultyEnum);
        AnimalCreate.getLeopards(difficultyEnum);

        AnimalCreate.getGiraffes(difficultyEnum);
        AnimalCreate.getZebras(difficultyEnum);

        rangers.addAll(EntityCreate.getRangers(difficultyEnum));
        for (Ranger ranger : rangers) {
            rangerJoinDates.put(ranger, date);
            entitiesExecutor.addScheduleAtFixedRate(ranger::handleRangerMovement);
        }

        plants.addAll(PlantCreate.getBaobabs(difficultyEnum));
        plants.addAll(PlantCreate.getPalmTrees(difficultyEnum));
        plants.addAll(PlantCreate.getPanciums(difficultyEnum));

        waters.addAll(EntityCreate.getWaters(difficultyEnum));


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
        rangerJoinDates.clear();
    }

    public int getPassengers() {
        return passengers;
    }

    public void addPassengers(int count) {
        this.passengers += count;
    }

    public void updateDate() {
        date++;
        System.out.println("Date event triggered: " + date);
        Calendar.Instance.setDate(date);
        payment();
        if (date > 10) {
            bornDay();
        }


    }

    private void payment() {
        //havi fizetés
        RangerPayment.Instance.payRangersByServiceTime();

        if (gameStateChecker != null) {
            if (gameStateChecker.instantLose(coin, animals)) {
                shutDown();
            } else if (gameStateChecker.checkWin(difficultyEnum, date, animals, coin,passengers)) {
                shutDown();
            }
        }
    }

    private void bornDay() {
        // TODO: honaponta
        bornAnimals();
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
                Lion lion = new Lion(x, y);
                animals.add(lion);
                ScheduledFuture<?> lionTask = entitiesExecutor.addScheduleAtFixedRate(lion::handleLionMovement);
                lion.setTask(lionTask);
                break;
            case "leopard":
                Leopard leopard = new Leopard(x, y);
                animals.add(leopard);
                ScheduledFuture<?> leopardTask = entitiesExecutor.addScheduleAtFixedRate(leopard::handleLeopardMovement);
                leopard.setTask(leopardTask);
                break;
            case "zebra":
                Zebra zebra = new Zebra(x, y);
                animals.add(zebra);
                ScheduledFuture<?> zebraTask = entitiesExecutor.addScheduleAtFixedRate(zebra::handleZebraMovement);
                zebra.setTask(zebraTask);
                break;
            case "giraffe":
                Giraffe giraffe = new Giraffe(x, y);
                animals.add(giraffe);
                ScheduledFuture<?> giraffeTask = entitiesExecutor.addScheduleAtFixedRate(giraffe::handleGiraffeMovement);
                giraffe.setTask(giraffeTask);
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
                rangerJoinDates.put(ranger, date);
                ScheduledFuture<?> rangerTask = entitiesExecutor.addScheduleAtFixedRate(ranger::handleRangerMovement);
                ranger.setTask(rangerTask);
                break;
            case "jeep":
                Jeep jeep = new Jeep(EntityCreate.entryX, EntityCreate.entryY);
                jeeps.add(jeep);
                ScheduledFuture<?> jeepTask = entitiesExecutor.addScheduleAtFixedRate(jeep::handleJeepMovement);
                jeep.setTask(jeepTask);
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
            poachers.add(poacher);
            ScheduledFuture<?> poacehrTaskVisibility = entitiesExecutor.addScheduleAtFixedRate(poacher::poacherVisibility);
            ScheduledFuture<?> poacehrTaskMove = entitiesExecutor.addScheduleAtFixedRate(poacher::move);
            poacher.setTask(poacehrTaskVisibility);
            poacher.setTask2(poacehrTaskMove);
            System.out.println("poacher out");
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
        entity.setAlive(false);
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
            rangerJoinDates.remove(entity);
        } else if (entity instanceof Jeep) {
            jeeps.remove(entity);
        } else if (entity instanceof Poacher) {
            poachers.remove(entity);
        }
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
        entities.addAll(poachers);
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

    public Map<Ranger, Integer> getRangerJoinDates() {
        return rangerJoinDates;
    }

    public int getDate() {
        return date;
    }

    public List<Water> getWatersByInteger(List<Integer> watersID) {
        return this.waters.stream()
                .filter(water -> watersID.contains(water.id))
                .collect(Collectors.toList());
    }

    public List<Plant> getPlants() {
        return plants.stream()
                .map(entity -> ((Plant) entity))
                .collect(Collectors.toList());
    }

    public List<Plant> getPlantsByInteger(List<Integer> plantsID) {
        return this.plants.stream()
                .filter(plant -> plantsID.contains(plant.id))
                .map(entity -> ((Plant)entity))
                .collect(Collectors.toList());
    }
    public List<Animal> getHerbivorous() {
        return this.animals.stream()
                .filter(animal -> animal instanceof Giraffe || animal instanceof Zebra)
                .map(animal -> ((Animal)animal))
                .collect(Collectors.toList());
    }

    public Coordinate avgCoordinateOf(Class<? extends Entity> animalClass) {
        DoubleSummaryStatistics xStats = animals.stream()
                .filter(animalClass::isInstance)
                .mapToDouble(Entity::getX)
                .summaryStatistics();

        DoubleSummaryStatistics yStats = animals.stream()
                .filter(animalClass::isInstance)
                .mapToDouble(Entity::getY)
                .summaryStatistics();

        if (xStats.getCount() == 0) {
            return new Coordinate(Resources.Instance.map.getWidth() / 2, Resources.Instance.map.getHeight() / 2);
        }

        return new Coordinate((int) xStats.getAverage(), (int) yStats.getAverage());
    }

    public void bornAnimals() {

        int lions = (int) animals.stream()
                .filter(a -> a instanceof Lion)
                .count();
        createIteration(Lion.class, lions / 10, avgCoordinateOf(Lion.class));
        int leopards = (int) animals.stream()
                .filter(a -> a instanceof Leopard)
                .count();
        createIteration(Leopard.class, leopards / 10, avgCoordinateOf(Leopard.class));
        int zebras = (int) animals.stream()
                .filter(a -> a instanceof Zebra)
                .count();
        createIteration(Zebra.class, zebras / 10, avgCoordinateOf(Zebra.class));
        int giraffes = (int) animals.stream()
                .filter(a -> a instanceof Giraffe)
                .count();
        createIteration(Giraffe.class, giraffes / 10, avgCoordinateOf(Giraffe.class));
    }

    public void createIteration(Class<? extends Entity> entityClass, int i, Coordinate coordinate) {
        for (int j = 0; j < i; j++) {
            createAnEntityForFree(entityClass, coordinate.x, coordinate.y);
        }
    }

    public void createAnEntityForFree(Class<? extends Entity> entityClass, int x, int y) {
        if (entityClass == Lion.class) {
            Lion lion = new Lion(x, y);
            animals.add(lion);
            ScheduledFuture<?> lionTask = entitiesExecutor.addScheduleAtFixedRate(lion::handleLionMovement);
            lion.setTask(lionTask);
        } else if (entityClass == Leopard.class) {
            Leopard leopard = new Leopard(x, y);
            animals.add(leopard);
            ScheduledFuture<?> leopardTask = entitiesExecutor.addScheduleAtFixedRate(leopard::handleLeopardMovement);
            leopard.setTask(leopardTask);
        } else if (entityClass == Zebra.class) {
            Zebra zebra = new Zebra(x, y);
            animals.add(zebra);
            ScheduledFuture<?> zebraTask = entitiesExecutor.addScheduleAtFixedRate(zebra::handleZebraMovement);
            zebra.setTask(zebraTask);
        } else if (entityClass == Giraffe.class) {
            Giraffe giraffe = new Giraffe(x, y);
            animals.add(giraffe);
            ScheduledFuture<?> giraffeTask = entitiesExecutor.addScheduleAtFixedRate(giraffe::handleGiraffeMovement);
            giraffe.setTask(giraffeTask);
        }
        // TODO: még több entity ha kell

    }


    public List<Entity> getAnimals() {
        return animals;
    }
}
