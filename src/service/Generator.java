package service;

import model.Direction;
import model.Fish;
import model.Location;

import java.util.List;

public interface Generator {
    Integer generateAmount();

    List<Fish> generateFish(Integer number, Location location);
    Direction generateDirection();
}
