/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
 import edu.wpi.first.wpilibj.Ultrasonic;
 import edu.wpi.first.wpilibj.DigitalInput;
 

public class Sensors {
    
    public static Ultrasonic ballUS, frontUS1, frontUS2, backUS1, backUS2, bottomUS1, bottomUS2;
    public static DigitalInput hatchSwitch1, hatchSwitch2, clawSwitch;
        
    public static void ultraSonicInit(){
        ballUS = new Ultrasonic(0, 0);
        frontUS1 = new Ultrasonic(1, 1);
        frontUS2 = new Ultrasonic(2,2);
        backUS1 = new Ultrasonic(3,3);
        backUS2 = new Ultrasonic(4,4);
        bottomUS1 = new Ultrasonic(5,5);
        bottomUS2 = new Ultrasonic(6,6);
    }

    public static void limitSwitchInit() {
        hatchSwitch1 = new DigitalInput(2);
        hatchSwitch2 = new DigitalInput(3);
        clawSwitch = new DigitalInput(7);
    }

    public static boolean haveHatchInRange(){
        if (hatchSwitch1.get()|| hatchSwitch2.get()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean IsClawParallel(){
        if (clawSwitch.get() == true){
            return true;
        } else {
            return false;
        }
    }

    public static double getFrontDistance(){
        double F1 = frontUS1.getRangeInches();
        double F2 = frontUS2.getRangeInches();
        double fDI = (F1+F2)/ 2;
        return fDI;
    }

    public static double getBackDistance(){
        double B1 = backUS1.getRangeInches();
        double B2 = backUS2.getRangeInches();
        double bDI = (B1+B2)/ 2;
        return bDI;
    }
    
    public static double getBottomDistance(){
        double B1 = backUS1.getRangeInches();
        double B2 = backUS2.getRangeInches();
        double bDI = (B1+B2)/ 2;
        return bDI;
    }

    public static boolean getBottomParallel(){
          if (backUS1.getRangeInches() == backUS2.getRangeInches()){
              return true;
          } else {
              return false;
          }
    }

    public static boolean getFrontParallel(){
        if (frontUS1.getRangeInches() == frontUS2.getRangeInches()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean getBackParallel(){
        if (backUS1.getRangeInches() == backUS2.getRangeInches()){
            return true;
        } else {
            return false;
        }
    }
}