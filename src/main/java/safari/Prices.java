package safari;

/**
 * Enum representing the prices of various entities and services in the safari simulation.
 */
public enum Prices {
    LION(10),       // Price for a lion
    LEOPARD(8),     // Price for a leopard
    ZEBRA(3),       // Price for a zebra
    GIRAFFE(5),     // Price for a giraffe
    PALMTREE(2),    // Price for a palm tree
    BAOBAB(6),      // Price for a baobab
    PANICUM(1),     // Price for a pancium (assumed typo corrected to "PANCIUM")
    WATER(7),       // Price for a water entity
    RANGER(12),     // Price for a ranger
    JEEP(15),       // Price for a jeep
    ROAD(0.01),     // Price per unit of road
    PASSENGER(10);  // Revenue per passenger

    private final double price;

    /**
     * Constructs a Prices enum with the specified price value.
     *
     * @param price The price associated with the enum constant.
     */
    Prices(double price) {
        this.price = price;
    }

    /**
     * Retrieves the price of a given Prices enum constant.
     *
     * @param priceEnum The Prices enum constant.
     * @return The price value associated with the enum constant.
     */
    public static double getPriceByEnum(Prices priceEnum) {
        return priceEnum.price;
    }

    /**
     * Converts a string representation to the corresponding Prices enum constant.
     * Defaults to LION if the string does not match any known entity.
     *
     * @param message The string representation of an entity (e.g., "lion", "zebra").
     * @return The corresponding Prices enum constant.
     */
    public static Prices getPricesByString(String message) {
        switch (message.toLowerCase()) {
            case "lion":
                return Prices.LION;
            case "leopard":
                return Prices.LEOPARD;
            case "zebra":
                return Prices.ZEBRA;
            case "giraffe":
                return Prices.GIRAFFE;
            case "palmtree":
                return Prices.PALMTREE;
            case "baobab":
                return Prices.BAOBAB;
            case "pancium":
                return Prices.PANICUM;
            case "water":
                return Prices.WATER;
            case "ranger":
                return Prices.RANGER;
            case "jeep":
                return Prices.JEEP;
            default:
                return Prices.LION; // Default case
        }
    }
}