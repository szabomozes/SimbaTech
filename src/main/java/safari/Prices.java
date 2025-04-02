package safari;

public enum Prices {
    LION(10),
    LEOPARD(8),
    ZEBRA(3),
    GIRAFFE(5),
    PALMTREE(2),
    BAOBAB(6),
    PANICUM(1),
    WATER(7),
    RANGER(12),
    JEEP(15),
    ROAD(0.01);

    private final double price;

    Prices(double price) {
        this.price = price;
    }

    public static double getPriceByEnum(Prices priceEnum) {
        return priceEnum.price;
    }

    public static Prices getPricesByString(String message) {
        switch (message) {
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
                return Prices.LION;
        }
    }




}
