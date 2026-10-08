package model.Consommation.Consommation.src.model.consume;

import model.Consommation.Consommation.src.model.present.NoPotion;
import java.util.Random;

public class Healing {
        private int potionCount;
        private static final Random random = new Random();

    public Healing(int potionCount) {
        this.potionCount = potionCount;
    }

    public int usePotion(int currentHealth, int maxHealth) {
        if (potionCount <= 0) {
            throw new NoPotion("Aucune potion dans l'inventaire");
        }
        potionCount--;
        System.out.println("Potion utilisée. Tour terminée");
        int dice1 = random.nextInt(4);
        int dice2 = random.nextInt(4);
        int healingAmount = dice1 + dice2 + 2;

        System.out.println("Vous avez retrouvez :" + healingAmount + "de PV");

        int newHealth = Math.min(currentHealth + healingAmount, maxHealth);

        return newHealth;
    }
    public int getPotionCount(){
        return potionCount;
    }


}
