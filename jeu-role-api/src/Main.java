import model.*;
public static void main(String[] args) {
    HeroName daniel = new HeroName("daniel");
    Hero humain = new Hero( daniel, Species.HUMAIN, HeroClassCategory.MAGE, Map.of(HeroCaracteristic.DEXTERITE, 9,HeroCaracteristic.INTELLIGENCE,16, HeroCaracteristic.FORCE, 12, HeroCaracteristic.CHARISME, 13, HeroCaracteristic.SAGESSE, 11, HeroCaracteristic.CONSTITUTION, 17));

    System.out.println(humain.getName());
    System.out.println(humain.getHeroClass());

    System.out.println(humain.getMaxHP());
    System.out.println(humain.getCurrentHP());

    System.out.println(humain.getMaxMagicPoints());
    System.out.println(humain.getMaxMagicPoints());

    System.out.println(humain.getEquippedWeapon());
    System.out.println(humain.getAllowedWeaponCategories());

    System.out.println(humain.getEquippedArmour());
    System.out.println(humain.getAllowedArmourCategories());

    System.out.println(humain.getCharacteristics());

}