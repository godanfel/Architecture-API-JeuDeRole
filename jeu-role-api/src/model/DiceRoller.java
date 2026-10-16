package model;//par requete du prof on doit avoir une facon de verifier des situations a resultat connu a l'avance
//du type de quel output on aura de chaque roll
//pour ceci j'ai fait 2 types de des, un aleatoire et un previsible
//donc on peut voir le resultat de chaque roll avant d'y essayer

public interface DiceRoller {
    int roll(int sides);
}
