package com.onurersen.javadesignpatterns.composite;

public class Military {

    public static void main(String[] args){

        Soldier corporal1 = new Corporal();
        corporal1.setExperienceYears(3);
        corporal1.setName("Arnold Warner");

        Soldier corporal2 = new Corporal();
        corporal2.setExperienceYears(2);
        corporal2.setName("Henry Jordan");

        Soldier corporal3 = new Corporal();
        corporal3.setExperienceYears(4);
        corporal3.setName("Steven Curry");

        Soldier sarge1 = new Sarge();
        sarge1.setExperienceYears(5);
        sarge1.setName("Randall Kennedy");

        Soldier sarge2 = new Sarge();
        sarge2.setExperienceYears(8);
        sarge2.setName("Tony McAlpine");

        Soldier captain = new Captain();
        captain.setExperienceYears(22);
        captain.setName("John Mayfield");

        // add corporal1 and corporal2 as leaves to sarge1 node
        sarge1.add(corporal1);
        sarge1.add(corporal2);

        // add corporal3 as leaf to sarge2
        sarge2.add(corporal3);

        // add sarge1 and sarge2 as leaves to captain
        captain.add(sarge1);
        captain.add(sarge2);

        // print captain report
        captain.report();

    }

}
