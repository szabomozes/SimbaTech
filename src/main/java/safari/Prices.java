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
    JEEP(15);

    private final int price;

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
