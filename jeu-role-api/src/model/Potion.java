package model;

public class Potion extends Objet
{
    private int soinMin;
    private int soinMax;
    private int bonusSoin;

    public Potion(String nom, int soinMin, int soinMax, int bonusSoin)
    {
        super(nom);
        this.soinMin = soinMin;
        this.soinMax = soinMax;
        this.bonusSoin = bonusSoin;
    }

    public int getSoinMin()
    {
        return soinMin;
    }

    public int getSoinMax()
    {
        return soinMax;
    }

    public int getBonusSoin()
    {
        return bonusSoin;
    }
}