package model;

public class Armure extends Objet
{
    private int classeArmure;

    public Armure(String nom, int classeArmure)
    {
        super(nom);
        this.classeArmure = classeArmure;
    }

    public int getClasseArmure()
    {
        return classeArmure;
    }
}
