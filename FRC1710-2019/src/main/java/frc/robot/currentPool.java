/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.*;

import com.revrobotics.CANSparkMax;
/**
 * Add your docs here.
 */
public class CurrentPool {
    static List<Pair<CANSparkMax, Integer>> priority = new ArrayList<Pair<CANSparkMax, Integer>>();

    static private int getPriorityCount(int prorityVal) {
        int count = 0;
        for (int i = 0; i < priority.size(); i++) {
            if (priority.get(i).getR() == prorityVal) {
                count++;
            }
        }
        return count;
    }

    static private List<Pair<CANSparkMax, Integer>> getPriorityPairs(int prorityVal){
        List<Pair<CANSparkMax, Integer>> listOfPriorityPair = new ArrayList<Pair<CANSparkMax, Integer>>();
        for (int i = 0; i < priority.size(); i++) {
            if (priority.get(i).getR() == prorityVal) {
                listOfPriorityPair.add(priority.get(i));
            }
        }
        return listOfPriorityPair;
    }

    public static void currentPool(){
        int firstPriorityCounter = 0;

        int R1Priority = 1;
        int R2Priority = 1;
        int L1Priority = 1;
        int L2Priority = 1;

        double currentR1 = Drive.R1.getOutputCurrent();
        double currentR2 = Drive.R2.getOutputCurrent();
        double currentL1 = Drive.L1.getOutputCurrent();
        double currentL2 = Drive.L2.getOutputCurrent();
        
        priority.add(new Pair<CANSparkMax,Integer>(Drive.R1, R1Priority)); 
        priority.add(new Pair<CANSparkMax,Integer>(Drive.R2, R2Priority)); 
        priority.add(new Pair<CANSparkMax,Integer>(Drive.L1, L1Priority)); 
        priority.add(new Pair<CANSparkMax,Integer>(Drive.L2, L2Priority));

        firstPriorityCounter = getPriorityCount(1);
        double maxCurrentPool = 100;
        double totalCurrentDraw = currentR1 + currentR2 + currentL1 + currentL2;
        if(totalCurrentDraw > maxCurrentPool){
            
            double ratio = maxCurrentPool / totalCurrentDraw;
            //this is where current limits are set due to more current draw
            Drive.R1.setSmartCurrentLimit((int) (currentR1 * ratio));
            Drive.R2.setSmartCurrentLimit((int) (currentR2 * ratio));
            Drive.L1.setSmartCurrentLimit((int) (currentL1 * ratio));
            Drive.L2.setSmartCurrentLimit((int) (currentL2 * ratio));

     }else{
         //reset current limits
        Drive.R1.setSmartCurrentLimit((Integer) null);
        Drive.R2.setSmartCurrentLimit((Integer) null);
        Drive.L1.setSmartCurrentLimit((Integer) null);
        Drive.L2.setSmartCurrentLimit((Integer) null);
     }
        
        
        

       
    
        System.out.println("R1: " + Drive.R1.getOutputCurrent());
        System.out.println("R2: " + Drive.R2.getOutputCurrent());
        System.out.println("L1: " + Drive.L1.getOutputCurrent());
        System.out.println("L2: " + Drive.L2.getOutputCurrent());
    }
}
