package model;

public class Arme extends Objet
{
    private String degats;
    private String caracteristique;

    public Arme(String nom, String degats, String caracteristique)
    {
        super(nom);
        this.degats = degats;
        this.caracteristique = caracteristique;
    }

    public String getDegats()
    {
        return degats;
    }

    public String getCaracteristique()
    {
        return caracteristique;
    }


}