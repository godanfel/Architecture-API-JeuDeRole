package model;

public class Potion implements Item {

    private final static int NUMBER_OF_DICE = 2;
    private final static int SIDES_OF_DICE = 4;
    private final static int DICE_FLAT_BONUS = 2;
    private final DiceRoller diceRoll;

    public Potion(DiceRoller diceRoll) {
        this.diceRoll = diceRoll;
    }

    @Override
    public void use(Hero hero) {
        int diceTotal = 0;
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            diceTotal = diceTotal + diceRoll.roll(SIDES_OF_DICE);
        }
        hero.heal(diceTotal + DICE_FLAT_BONUS);
    }

    @Override
    public boolean isConsumedOnUse() {
        return true;
    }
}