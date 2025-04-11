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

/**
 * Singleton class managing the safari simulation, including entities, game state, and interactions.
 */
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

    /**
     * Private constructor initializing the safari with a date timer and zero passengers.
     */
    private Safari() {
        dateTimer = new DateTimer();
        dateTimer.start();
        passengers = 0;
    }

    /**
     * Shuts down the safari by stopping the date timer and entities executor.
     */
    public void shutDown() {
        dateTimer.stopTimer();
        entitiesExecutor.stop();
    }

    /**
     * Resets the safari to a new game state with the specified difficulty.
     *
     * @param diff The difficulty level for the new game.
     */
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

    /**
     * Clears all entity lists and related data structures.
     */
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

    /**
     * Gets the total number of passengers transported.
     *
     * @return The number of passengers.
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Adds a specified number of passengers to the total count.
     *
     * @param count The number of passengers to add.
     */
    public void addPassengers(int count) {
        this.passengers += count;
    }

    /**
     * Updates the game date, triggers calendar updates, and processes payments and game state checks.
     */
    public void updateDate() {
        date++;
        System.out.println("Date event triggered: " + date);
        Calendar.Instance.setDate(date);
        payment();
        if (date > 10) {
            bornDay();
        }
    }

    /**
     * Handles payments to rangers and checks game state for win/loss conditions.
     */
    private void payment() {
        RangerPayment.Instance.payRangersByServiceTime();

        if (gameStateChecker != null) {
            if (gameStateChecker.instantLose(coin, animals)) {
                shutDown();
            } else if (gameStateChecker.checkWin(difficultyEnum, date, animals, coin, passengers)) {
                shutDown();
            }
        }
    }

    /**
     * Triggers animal reproduction after a certain date threshold.
     */
    private void bornDay() {
        bornAnimals();
    }

    /**
     * Initiates the purchase process for an entity if sufficient coins are available.
     *
     * @param message The type of entity to buy (e.g., "lion", "jeep").
     */
    public void buySomething(String message) {
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(message));
        if (coin >= price) {
            ToolBarCardLayout.Instance.showCard("buying");
            shopping = message;
        }
    }

    /**
     * Places a purchased entity at the specified coordinates and deducts the price from coins.
     *
     * @param x The x-coordinate for placement.
     * @param y The y-coordinate for placement.
     */
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

    /**
     * Places a specified number of poachers at random coordinates.
     *
     * @param num The number of poachers to place.
     */
    public void placePoachers(int num) {
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < num; i++) {
            x = rand.nextInt(Resources.Instance.map.getWidth());
            y = rand.nextInt(Resources.Instance.map.getHeight());
            Poacher poacher = new Poacher(x, y);
            poachers.add(poacher);
            ScheduledFuture<?> poacherTaskVisibility = entitiesExecutor.addScheduleAtFixedRate(poacher::poacherVisibility);
            ScheduledFuture<?> poacherTaskMove = entitiesExecutor.addScheduleAtFixedRate(poacher::move);
            poacher.setTask(poacherTaskVisibility);
            poacher.setTask2(poacherTaskMove);
            System.out.println("poacher out");
        }
    }

    /**
     * Returns a list of rangers and jeeps for specific operations.
     *
     * @return A list containing all rangers and jeeps.
     */
    public List<Entity> rangersAndJeeps() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(rangers);
        entities.addAll(jeeps);
        return entities;
    }

    /**
     * Sells an entity by its ID, adding its price to coins and removing it from the simulation.
     *
     * @param id The ID of the entity to sell.
     */
    public void sellSomething(int id) {
        ToolBarCardLayout.Instance.showCard("selling");

        Entity entity = getEntityById(id);
        int price = (int) Prices.getPriceByEnum(Prices.getPricesByString(entity.getClass().getSimpleName().toLowerCase()));
        entity.setAlive(false);
        coin += price;
        removeEntityById(id);
    }

    /**
     * Sets the selling mode.
     *
     * @param mode True to enable selling mode, false to disable.
     */
    public void setSellingMode(boolean mode) {
        this.selling = mode;
    }

    /**
     * Gets the current selling mode state.
     *
     * @return True if selling mode is active, false otherwise.
     */
    public boolean getSellingMode() {
        return this.selling;
    }

    /**
     * Retrieves an entity by its unique ID.
     *
     * @param id The ID of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    public Entity getEntityById(int id) {
        return getAllEntities().stream()
                .filter(e -> e.id == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes an entity from the simulation by its ID.
     *
     * @param id The ID of the entity to remove.
     */
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

    /**
     * Gets a list of all entities in the simulation.
     *
     * @return A list containing all entities.
     */
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

    /**
     * Gets a list of animals available for selling.
     *
     * @return A list of animal entities.
     */
    public List<Entity> getAlmostAllEntitiesForSell() {
        return animals.stream()
                .filter(e -> e instanceof Animal)
                .collect(Collectors.toList());
    }

    /**
     * Gets a sorted list of all entities by their y-coordinate plus height for rendering purposes.
     *
     * @return A sorted list of all entities.
     */
    public List<Entity> getAllEntitiesWithSorted() {
        List<Entity> allEntities = new ArrayList<>(getAllEntities());
        allEntities.sort(Comparator.comparingInt(e -> e.getY() + e.getHeight()));
        return allEntities;
    }

    /**
     * Gets a list of animals and poachers.
     *
     * @return A list containing animals and poachers.
     */
    public List<Entity> getAnimalsPoachers() {
        List<Entity> entities = new ArrayList<>(animals);
        entities.addAll(poachers);
        return entities;
    }

    /**
     * Gets the list of rangers.
     *
     * @return The list of rangers.
     */
    public List<Ranger> getRangers() {
        return rangers;
    }

    /**
     * Gets the current road building state.
     *
     * @return True if road building is active, false otherwise.
     */
    public boolean getRoadBuilding() {
        return roadBuilding;
    }

    /**
     * Sets the road building state.
     *
     * @param roadBuilding True to enable road building, false to disable.
     */
    public void setRoadBuilding(boolean roadBuilding) {
        this.roadBuilding = roadBuilding;
    }

    /**
     * Gets the entry entity.
     *
     * @return The entry entity.
     */
    public Entry getEntry() {
        return entry;
    }

    /**
     * Gets the exit entity.
     *
     * @return The exit entity.
     */
    public Exit getExit() {
        return exit;
    }

    /**
     * Gets the list of paths (roads).
     *
     * @return The list of paths.
     */
    public List<Path> getPaths() {
        return paths;
    }

    /**
     * Clears the temporary paths list.
     */
    public void clearTempPaths() {
        tempPaths.clear();
    }

    /**
     * Gets the list of temporary paths.
     *
     * @return The list of temporary paths.
     */
    public List<Path> getTempPaths() {
        return tempPaths;
    }

    /**
     * Calculates the total price of temporary paths based on pixel count and road price.
     *
     * @return The total price of temporary paths.
     */
    public int getTempPathsPrice() {
        return (int) (tempPaths.stream()
                .mapToInt(Path::getPixelCount)
                .sum() * Prices.getPriceByEnum(Prices.ROAD));
    }

    /**
     * Adds a path to the permanent paths list.
     *
     * @param path The path to add.
     */
    public void addAPathToPaths(Path path) {
        paths.add(path);
    }

    /**
     * Saves a road by setting its end coordinates and adding it as a new road segment.
     *
     * @param x The x-coordinate of the end point.
     * @param y The y-coordinate of the end point.
     * @return True if the road was saved successfully, false if it overlaps water.
     */
    public boolean saveARoad(int x, int y) {
        Path path = tempPaths.get(tempPaths.size() - 1);

        if (path.overlapsWaterArea(x, y)) return false;

        path.setEndX(x);
        path.setEndY(y);
        path.addANewRoad();
        path.endCoorinateCopyToStartCoordinate();
        return true;
    }

    /**
     * Checks if a ranger is currently selected.
     *
     * @return True if a ranger is selected, false otherwise.
     */
    public boolean isSelectedRanger() {
        return selectedRanger;
    }

    /**
     * Sets the ranger selection state.
     *
     * @param selectedRanger True to select a ranger, false to deselect.
     */
    public void setSelectedRanger(boolean selectedRanger) {
        this.selectedRanger = selectedRanger;
    }

    /**
     * Gets the coordinates occupied by an entity.
     *
     * @param entity The entity to get coordinates for.
     * @return A list of coordinates within the entity's bounds.
     */
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

    /**
     * Gets the list of water entities.
     *
     * @return The list of water entities.
     */
    public List<Water> getWaters() {
        return waters;
    }

    /**
     * Gets the map of rangers to their join dates.
     *
     * @return The map of ranger join dates.
     */
    public Map<Ranger, Integer> getRangerJoinDates() {
        return rangerJoinDates;
    }

    /**
     * Gets the current game date.
     *
     * @return The current date.
     */
    public int getDate() {
        return date;
    }

    /**
     * Gets a list of water entities by their IDs.
     *
     * @param watersID The list of water entity IDs.
     * @return A list of water entities matching the IDs.
     */
    public List<Water> getWatersByInteger(List<Integer> watersID) {
        return this.waters.stream()
                .filter(water -> watersID.contains(water.id))
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of plant entities.
     *
     * @return The list of plants.
     */
    public List<Plant> getPlants() {
        return plants.stream()
                .map(entity -> ((Plant) entity))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of plant entities by their IDs.
     *
     * @param plantsID The list of plant entity IDs.
     * @return A list of plant entities matching the IDs.
     */
    public List<Plant> getPlantsByInteger(List<Integer> plantsID) {
        return this.plants.stream()
                .filter(plant -> plantsID.contains(plant.id))
                .map(entity -> ((Plant) entity))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of herbivorous animals (Giraffes and Zebras).
     *
     * @return A list of herbivorous animals.
     */
    public List<Animal> getHerbivorous() {
        return this.animals.stream()
                .filter(animal -> animal instanceof Giraffe || animal instanceof Zebra)
                .map(animal -> ((Animal) animal))
                .collect(Collectors.toList());
    }

    /**
     * Calculates the average coordinate of a specific animal class, defaulting to map center if none exist.
     *
     * @param animalClass The class of animal to average (e.g., Lion.class).
     * @return The average coordinate of the animals.
     */
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

    /**
     * Simulates animal reproduction by creating new instances based on population size.
     */
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

    /**
     * Creates multiple instances of an entity class at a specified coordinate.
     *
     * @param entityClass The class of entity to create.
     * @param i           The number of instances to create.
     * @param coordinate  The coordinate for placement.
     */
    public void createIteration(Class<? extends Entity> entityClass, int i, Coordinate coordinate) {
        for (int j = 0; j < i; j++) {
            createAnEntityForFree(entityClass, coordinate.x, coordinate.y);
        }
    }

    /**
     * Creates an entity for free (no coin cost) and schedules its behavior.
     *
     * @param entityClass The class of entity to create.
     * @param x           The x-coordinate for placement.
     * @param y           The y-coordinate for placement.
     */
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
    }

    /**
     * Gets the GameStateChecker instance for this safari.
     *
     * @return The GameStateChecker instance.
     */
    public GameStateChecker getGameStateChecker() {
        return gameStateChecker;
    }

    /**
     * Gets the list of animals in the safari.
     *
     * @return The list of animals.
     */
    public List<Entity> getAnimals() {
        return animals;
    }
}