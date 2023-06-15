package model;

import service.Generator;
import service.GeneratorImpl;

public class Fish extends Thread {
    private final String name;
    private final Gender gender;
    private final int deathAge; //in months
    private int age; //in months
    private final Location location;

    public Location getLocation() {
        return location;
    }

    public int getParentEnabledFrom() {
        //in months
        return 10;
    }

    private boolean readyToGiveBirth;

    public Fish(String name, Gender gender, int deathAge, int age, Location location, boolean readyToGiveBirth) {
        this.name = name;
        this.gender = gender;
        this.deathAge = deathAge;
        this.age = age;
        this.location = location;
        this.readyToGiveBirth = readyToGiveBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFishName() {
        return name;
    }

    public boolean isReadyToGiveBirth() {
        return readyToGiveBirth;
    }

    public void setReadyToGiveBirth(boolean readyToGiveBirth) {
        this.readyToGiveBirth = readyToGiveBirth;
    }


    @Override
    public void run() {
        Aquarium aquarium = Aquarium.getInstance();
        aquarium.AddFishToAquarium(getLocation(),this);
        Generator generator = new GeneratorImpl();
        while (this.age<this.deathAge){
            try {
                Thread.sleep(1000);
                setAge(this.age+1);
                this.setReadyToGiveBirth(this.age>=getParentEnabledFrom());
                System.out.println(this);
                aquarium.changeLocation(this, generator.generateDirection());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getFishName()+" died");
        aquarium.removeFromAquarium(this);
        this.interrupt();
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", deathAge=" + deathAge +
                ", age=" + age +
                ", location=" + location +
                ", readyToGiveBirth=" + readyToGiveBirth +
                '}';
    }
}