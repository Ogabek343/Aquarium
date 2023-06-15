package service;


import model.Fish;
import model.Gender;

import java.util.List;

public class MainService extends Thread {

    private int maleFishNumber;
    private int femaleFishNumber;
    private final Generator generator;
    private MainService(){
        maleFishNumber = 0;
        femaleFishNumber = 0;
        generator = new GeneratorImpl();
    }

    private static class MainInner{
        private static final MainService INSTANCE = new MainService();
    }

    @Override
    public void start() {
        List<Fish> fish = generator.generateFish(generator.generateAmount(),null);
        fish.forEach(f->increment(f.getGender()));
        fish.forEach(Thread::start);
    }

    public static MainService getInstance(){
        return MainInner.INSTANCE;
    }

    public int getMaleFishNumber() {
        return maleFishNumber;
    }
    public void increment(Gender gender){
        if (gender.equals(Gender.MALE)){
            setMaleFishNumber((getMaleFishNumber()+1));
        } else if (gender.equals(Gender.FEMALE)) {
            setFemaleFishNumber((getFemaleFishNumber()+1));
        }
    }
    public int getFemaleFishNumber() {
        return femaleFishNumber;
    }

    public void setMaleFishNumber(int maleFishNumber) {
        this.maleFishNumber = maleFishNumber;
    }

    public void setFemaleFishNumber(int femaleFishNumber) {
        this.femaleFishNumber = femaleFishNumber;
    }
}
