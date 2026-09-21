package model;

public class DomainException extends RuntimeException {
  public DomainException(String message) {
    super(message);
  }
}

class LevelInvalidException extends DomainException {
  public LevelInvalidException(String s) {
    super(s);
  }
}

class ExperienceInvalidException extends DomainException {
  public ExperienceInvalidException(String s) {
    super(s);
  }
}

class MagicPointsInvalidException extends DomainException {
  public MagicPointsInvalidException(String s) {
    super(s);
  }
}