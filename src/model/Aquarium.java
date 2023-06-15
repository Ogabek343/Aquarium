package model;

import service.Generator;
import service.GeneratorImpl;
import java.util.HashMap;
import java.util.Map;

public class Aquarium {
    private final int xLength = 8;
    private final int yLength = 8;
    private final int zLength = 8;

    public int getxLength() {
        return xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public int getzLength() {
        return zLength;
    }
    private final Generator generator;

    private final Map<String,Fish>[][][] coordination = new Map[xLength][yLength][zLength];

    private Aquarium(){
        for (int x = 0; x<xLength; x++){
            for (int y = 0; y<yLength; y++){
                for (int z = 0; z<zLength; z++){
                    coordination[x][y][z] = new HashMap<>();
                }
            }
        }
        generator = new GeneratorImpl();
    }

    public void removeFromAquarium(Fish fish) {
        getCoordination(fish.getLocation()).remove(fish.getFishName());
    }

    private static class AquariumInner{
        private final static Aquarium INSTANCE = new Aquarium();
    }
    public static Aquarium getInstance(){
        return AquariumInner.INSTANCE;
    }

    public void AddFishToAquarium(Location location, Fish fish){
        getCoordination(location).put(fish.getFishName(),fish);
    }

    public void changeLocation(Fish fish, Direction direction){

        getCoordination(fish.getLocation()).remove(fish.getFishName());
        Map<String, Fish> newCoordination = getNewCoordination(fish.getLocation(), direction);
        if (!newCoordination.isEmpty()){
               newCoordination.values().forEach(fish1 -> {
                   if (!fish1.getGender().equals(fish.getGender())
                           && fish.isReadyToGiveBirth() && fish1.isReadyToGiveBirth()){
                       if (fish.getAge()>= fish.getParentEnabledFrom()&&fish1.getAge()>= fish.getParentEnabledFrom()){
                           System.out.println("Parents: "+fish+fish1);
                           generator.generateFish(2,fish.getLocation()).forEach(Thread::start);
                       }
                   }
               });
        }
        newCoordination.put(fish.getFishName(),fish);
    }
    public Map<String, Fish> getCoordination(Location location){
        return this.coordination[location.getxLocation()-1]
                [location.getyLocation()-1][location.getzLocation()-1];
    }
    public Map<String, Fish> getNewCoordination(Location location, Direction direction){
        switch (direction){
            case UP -> location.setzLocation(location.getzLocation()==zLength ? location.getzLocation()-1: location.getzLocation()+1);
            case DOWN -> location.setzLocation(location.getzLocation()==(1) ? location.getzLocation()+1: location.getzLocation()-1);
            case FORWARD -> location.setxLocation(location.getxLocation()==xLength ? location.getxLocation()-1: location.getxLocation()+1);
            case BACKWARD -> location.setxLocation(location.getxLocation()==1 ? location.getxLocation()+1: location.getxLocation()-1);
            case LEFT -> location.setyLocation(location.getyLocation()==yLength ? location.getyLocation()-1: location.getyLocation()+1);
            case RIGHT -> location.setyLocation(location.getyLocation()==1 ? location.getyLocation()+1: location.getyLocation()-1);
        }
        return getCoordination(location);
    }
}
