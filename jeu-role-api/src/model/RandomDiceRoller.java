package model;

import java.util.Random;

public class RandomDiceRoller implements DiceRoller{

    private final Random random = new Random();
    @Override
    public int roll(int sides) {
        return random.nextInt(sides) + 1; //vraie randomness, 1 seul cote
    }
}
