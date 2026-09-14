package model;

import java.util.ArrayList;

public class Inventaire
{
    private ArrayList<Objet> objets;
    private Arme armeEquipee;
    private Armure armureEquipee;

    public Inventaire()
    {
        objets = new ArrayList<>();
        armeEquipee = null;
        armureEquipee = null;
    }

    public boolean ajouterObjet(Objet objet)
    {
        if (objets.size() >= 10)
        {
            return false;
        }

        objets.add(objet);
        return true;
    }

    public boolean retirerObjet(Objet objet)
    {
        return objets.remove(objet);
    }

    public ArrayList<Objet> getObjets()
    {
        return objets;
    }

    public boolean equiperArme(Arme nouvelleArme) {
        if (objets.contains(nouvelleArme)) {
            this.armeEquipee = nouvelleArme;
            return true;
        }
        return false;
    }

    public boolean equiperArmure(Armure nouvelleArmure) {
        if (objets.contains(nouvelleArmure)) {
            this.armureEquipee = nouvelleArmure;
            return true;
        }
        return false;
    }

    //public boolean utiliserPotion(Potion potion, Personnage heros) {
        //if (objets.contains(potion)) {
          //  int soinRendu = (int)(Math.random() * 4 + 1) + (int)(Math.random() * 4 + 1) + potion.getBonusSoin();

            //heros.soigner(soinRendu);
            //objets.remove(potion);
            //return true;
        //}
      //  return false;
    }
}