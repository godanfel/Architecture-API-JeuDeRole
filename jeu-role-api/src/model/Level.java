package model;

public record Level(int value) {

    public Level {
        if (value < 1 || value > 5) {
            throw new LevelInvalidException("Level invalide");
        }
    }
    public static Level initial(){
        return new Level(1);
    }

    public boolean isMax(){
        return value == 5;
    }

    public Level next(){
        return new Level(value + 1);
    }
}
