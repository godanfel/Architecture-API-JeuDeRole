package model;

public record Experience(int value) {
    public Experience {
        if (value < 0) {
            throw new ExperienceInvalidException("Experience cannot be negative");
        }
    }

    public static Experience initial(){
        return new Experience(0);
    }

    public Experience add(int amount){
        if (amount < 0) {
            throw new ExperienceInvalidException("Amount cannot be negative");
        }
        return new Experience(value + amount);
    }
}
