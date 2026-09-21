package model;

public record AbilityScore(int score){
    public int getModifier(){
        return Math.floorDiv((score-10), 2);
    }

    public AbilityScore {
        if (score < 1){
            throw new AbilityScoreInvalidException("below minimum Ability score required");
        }
        else if (score > 20){
            throw new AbilityScoreInvalidException("above maximum ability score required");
        }


    }
}