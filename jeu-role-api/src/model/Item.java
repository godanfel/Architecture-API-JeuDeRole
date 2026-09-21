package model;//pour chaque item on a besoin de savoir deux choses, est ce que il est un item d'ussage unique or est il utilisable apres de l'avoir utilise
// ainsi que quel type d'item qu'il est, ici je reponds les deux questions,
// le premier methode utilise l'item d'accord a sa nature, soit une potion, armure ou arme
// le deuxieme dit le hero si le item en question doit etre detruit apres son utilisation

public interface Item {
    public abstract void use(Hero hero);
    public abstract boolean isConsumedOnUse();

}
