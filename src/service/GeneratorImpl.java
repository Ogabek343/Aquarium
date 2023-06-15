package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorImpl implements Generator{

    private final int deathAgeMin;//in months
    private final int deathAgeMax;//in months
    private final int initialMinAmount;
    private final int initialMaxAmount;

    public GeneratorImpl() {
        this.initialMinAmount = 10;
        this.initialMaxAmount = 15;
        this.deathAgeMin = 36;
        this.deathAgeMax = 50;
    }

    @Override
    public Integer generateAmount() {
        return new Random().nextInt(initialMinAmount, initialMaxAmount);
    }

    @Override
    public List<Fish> generateFish(Integer number, Location location) {
        List<Fish> fish = new ArrayList<>();
        for (int i = 0; i<number; i++){
            Gender gender = generateGender();
            fish.add(new Fish(
                    createFishName(gender),
                    gender,
                    generateDeathAge(),
                    0,
                    number==1 ? location: generateLocation(),
                    false
            ));
            System.out.println("New fish is born "+ fish.get(i));
        }
        return fish;
    }

    private String createFishName(Gender gender) {
        MainService mainService = MainService.getInstance();
        mainService.increment(gender);
        return gender.name() + " Fish " + (gender.equals(Gender.MALE) ? mainService.getMaleFishNumber() : mainService.getFemaleFishNumber());
    }

    private Gender generateGender() {
        Gender[] genders = Gender.values();
        int index = new Random().nextInt(genders.length);
        return genders[index];
    }

    @Override
    public Direction generateDirection(){
        Direction[] direction = Direction.values();
        int index = new Random().nextInt(direction.length);
        return direction[index];
    }


    private Integer generateDeathAge() {
        return new Random().nextInt(deathAgeMin, deathAgeMax);
    }

    private Location generateLocation(){
        Aquarium aquarium = Aquarium.getInstance();
        return new Location(
                new Random().nextInt(1, aquarium.getxLength()),
                new Random().nextInt(1, aquarium.getyLength()),
                new Random().nextInt(1, aquarium.getzLength())
        );
    }
}
