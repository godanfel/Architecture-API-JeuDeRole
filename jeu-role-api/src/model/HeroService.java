package model;

import java.util.ArrayList;
import java.util.List;

public final class HeroService {

    private final List<Hero> heroes = new ArrayList<>();

    public Hero createHero(
            String name,
            Race race,
            HeroClass heroClass,
            int force,
            int dexterite,
            int constitution,
            int intelligence,
            int sagesse,
            int charisme
    ) {

        HeroName heroName = new HeroName(name);

        if (nameAlreadyExists(heroName)) {
            throw new IllegalArgumentException(
                    "Un héros portant ce nom existe déjà."
            );
        }

        Attributes attributes = Attributes.create(
                force,
                dexterite,
                constitution,
                intelligence,
                sagesse,
                charisme,
                race
        );

        Hero hero = Hero.create(
                heroName,
                race,
                heroClass,
                attributes
        );

        heroes.add(hero);

        return hero;
    }

    private boolean nameAlreadyExists(HeroName name) {

        return heroes.stream()
                .anyMatch(hero -> hero.name().equals(name));
    }

    public List<Hero> findAll() {
        return List.copyOf(heroes);
    }
}