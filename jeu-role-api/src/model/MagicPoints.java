package model;

public final class MagicPoints {

    private final int maximum;
    private int current;

    private MagicPoints(int maximum) {

        if (maximum < 0) {
            throw new IllegalArgumentException(
                    "Les points de magie maximum ne peuvent pas être négatifs."
            );
        }

        this.maximum = maximum;
        this.current = maximum;
    }

    public static MagicPoints startingAt(int maximum) {
        return new MagicPoints(maximum);
    }

    public void spend(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Le coût en magie ne peut pas être négatif."
            );
        }

        if (amount > current) {
            throw new IllegalStateException(
                    "Le héros ne possède pas suffisamment de magie."
            );
        }

        current -= amount;
    }

    public void restore(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "La restauration ne peut pas être négative."
            );
        }

        current = Math.min(maximum, current + amount);
    }

    public int current() {
        return current;
    }

    public int maximum() {
        return maximum;
    }
}