package model;//la raison de faire WeaponCategory un enum est pour facilement classifier des armes
//on a pas seulements les armes, mais aussi comment elles agissent avec les des et les caracteristiques
//

public enum WeaponCategory implements Item{
    EPEE_LONGUE(8, 1, HeroCaracteristic.FORCE),
    MASSE(6,1, HeroCaracteristic.FORCE),
    HACHE(10,1, HeroCaracteristic.FORCE),
    DAGUE(4,1, HeroCaracteristic.DEXTERITE),
    ARC(8,1, HeroCaracteristic.DEXTERITE),
    BATON(6,1, HeroCaracteristic.FORCE);

    private final int diceFaces;
    private final int dicesNumber;
    private final HeroCaracteristic weaponCaracteristic;

    WeaponCategory(int diceFaces, int dicesNumber, HeroCaracteristic weaponCaracteristic) {
        this.diceFaces = diceFaces;
        this.dicesNumber = dicesNumber;
        this.weaponCaracteristic = weaponCaracteristic;
    }

    public int getDiceFaces() {
        return diceFaces;
    }

    public int getDicesNumber() {
        return dicesNumber;
    }

    public HeroCaracteristic getWeaponCaracteristic() {
        return weaponCaracteristic;
    }

    @Override
    public void use(Hero hero) {
        hero.equipWeapon(this);
    }

    @Override
    public boolean isConsumedOnUse() {
        return false;
    }
}
