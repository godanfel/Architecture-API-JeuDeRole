package model;

public final class Health {

    private final int maximum;
    private int current;

    private Health(int maximum) {

        if (maximum < 1) {
            throw new IllegalArgumentException(
                    "Les PV maximum doivent être au moins de 1."
            );
        }

        this.maximum = maximum;
        this.current = maximum;
    }

    public static Health startingAt(int maximum) {
        return new Health(maximum);
    }

    public void takeDamage(int damage) {

        if (damage < 0) {
            throw new IllegalArgumentException(
                    "Les dégâts ne peuvent pas être négatifs."
            );
        }

        current = Math.max(0, current - damage);
    }

    public void heal(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Les soins ne peuvent pas être négatifs."
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

    public boolean isOutOfCombat() {
        return current == 0;
    }
}