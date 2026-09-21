package model;

//ici on peut prevoir les resultats des roles des des qu'on veut tirer
public class FixedDiceRoller implements DiceRoller{
    
    private final int valeurFixee;

    public FixedDiceRoller(int valeurFixee) {
        this.valeurFixee = valeurFixee;
    }

    @Override
    public int roll(int sides) {
        return valeurFixee;
    }
}
