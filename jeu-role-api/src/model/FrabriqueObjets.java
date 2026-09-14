package model;

public class FrabriqueObjets {

    Arme epee = new Arme("Épée longue", "1d8", "Force");
    Arme hache = new Arme("Hache", "1d10", "Force");
    Arme masse = new Arme("Masse", "1d6", "Force");
    Arme dague = new Arme("Dague", "1d4", "Dextérité");
    Arme arc = new Arme("Arc", "1d8", "Dextérité");
    Arme baton = new Arme("Bâton", "1d6", "Force");
    Armure sansArmure = new Armure("Sans armure", 10);

    Armure cuir = new Armure("Armure de cuir", 11);
    Armure mailles = new Armure("Armure de mailles", 16);

    Potion potionSoin = new Potion("Potion de soin", 2, 8, 2);
}
