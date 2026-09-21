package model;

public record HeroName(String name) {
    public HeroName {
        if (name.length() > 30 || name.length() < 2){
            throw new HeroNameInvalidException("Invalid name choice, name doesnt fit length required");
        }else if (!(name.matches("^[a-zA-Z]+(\\s+[a-zA-Z]+)*$"))){
            throw new HeroNameInvalidException("Invalid name choice, Hero name contains invalid character(s)");
        }
    }
}