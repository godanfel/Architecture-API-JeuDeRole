package model;

public record MagicPoints(int current, int max) {
    public MagicPoints{
        if (max < 0) {
            throw new MagicPointsInvalidException("Les points de magie maximum ne peuvent pas être négatifs");
        }
        if (current < 0 || current > max) {
            throw new MagicPointsInvalidException("Les points de magie actuels doivent être entre 0 et le maximum");
        }
    }

    public static MagicPoints initial(int maxForClass) {
        return new MagicPoints(maxForClass, maxForClass);
    }

    public MagicPoints spend(int cost) {
        if (cost > current) {
            throw new MagicPointsInvalidException("Pas assez de points de magie pour lancer ce sort");
        }
        return new MagicPoints(current - cost, max);
    }

    public MagicPoints restore() {
        return new MagicPoints(max, max);
    }


}
