import model.Hero;
import model.HeroClass;
import model.HeroService;
import model.Race;

public class Main {

    public static void main(String[] args) {

        HeroService heroService = new HeroService();

        Hero hero = heroService.createHero(
                "Aria",
                Race.ELFE,
                HeroClass.MAGE,

                10, // Force
                16, // Dextérité
                12, // Constitution
                18, // Intelligence
                14, // Sagesse
                10  // Charisme
        );

        System.out.println("Nom : " + hero.name());
        System.out.println("Espèce : " + hero.race());
        System.out.println("Classe : " + hero.heroClass());

        System.out.println("Force : "
                + hero.attributes().force());

        System.out.println("Dextérité : "
                + hero.attributes().dexterite());

        System.out.println("Constitution : "
                + hero.attributes().constitution());

        System.out.println("Intelligence : "
                + hero.attributes().intelligence());

        System.out.println("Sagesse : "
                + hero.attributes().sagesse());

        System.out.println("Charisme : "
                + hero.attributes().charisme());

        System.out.println("Niveau : "
                + hero.level());

        System.out.println("Expérience : "
                + hero.experience());

        System.out.println("PV : "
                + hero.currentHealth()
                + "/"
                + hero.maximumHealth());

        System.out.println("Magie : "
                + hero.currentMagic()
                + "/"
                + hero.maximumMagic());
    }
}