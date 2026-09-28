package model;

public final class Hero {

    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_EXPERIENCE = 0;

    private final HeroName name;
    private final Race race;
    private final HeroClass heroClass;
    private final Attributes attributes;

    private final Health health;
    private final MagicPoints magicPoints;

    private int level;
    private int experience;

    private Hero(
            HeroName name,
            Race race,
            HeroClass heroClass,
            Attributes attributes,
            Health health,
            MagicPoints magicPoints
    ) {
        this.name = name;
        this.race = race;
        this.heroClass = heroClass;
        this.attributes = attributes;
        this.health = health;
        this.magicPoints = magicPoints;

        this.level = INITIAL_LEVEL;
        this.experience = INITIAL_EXPERIENCE;
    }

    public static Hero create(
            HeroName name,
            Race race,
            HeroClass heroClass,
            Attributes attributes
    ) {

        if (name == null) {
            throw new IllegalArgumentException(
                    "Le nom du héros est obligatoire."
            );
        }

        if (race == null) {
            throw new IllegalArgumentException(
                    "L'espèce du héros est obligatoire."
            );
        }

        if (heroClass == null) {
            throw new IllegalArgumentException(
                    "La classe du héros est obligatoire."
            );
        }

        if (attributes == null) {
            throw new IllegalArgumentException(
                    "Les caractéristiques du héros sont obligatoires."
            );
        }

        int maximumHealth =
                Math.max(
                        1,
                        heroClass.baseHealth()
                                + attributes.constitutionModifier()
                );

        Health health = Health.startingAt(maximumHealth);

        MagicPoints magicPoints =
                MagicPoints.startingAt(heroClass.baseMagic());

        return new Hero(
                name,
                race,
                heroClass,
                attributes,
                health,
                magicPoints
        );
    }

    public HeroName name() {
        return name;
    }

    public Race race() {
        return race;
    }

    public HeroClass heroClass() {
        return heroClass;
    }

    public Attributes attributes() {
        return attributes;
    }

    public int level() {
        return level;
    }

    public int experience() {
        return experience;
    }

    public int currentHealth() {
        return health.current();
    }

    public int maximumHealth() {
        return health.maximum();
    }

    public int currentMagic() {
        return magicPoints.current();
    }

    public int maximumMagic() {
        return magicPoints.maximum();
    }

    public void takeDamage(int damage) {
        health.takeDamage(damage);
    }

    public void heal(int amount) {
        health.heal(amount);
    }

    public void spendMagic(int amount) {
        magicPoints.spend(amount);
    }

    public void restoreMagic(int amount) {
        magicPoints.restore(amount);
    }

    public boolean isOutOfCombat() {
        return health.isOutOfCombat();
    }
}