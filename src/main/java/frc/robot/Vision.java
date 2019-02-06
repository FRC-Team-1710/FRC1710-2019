/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Vision {
    /*
    #############################################################
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    a special thanks to Mr.Fuchs and Cyrus for helping with this
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    #############################################################
    */
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tV = table.getEntry("tv");

    //read values periodically
    public static void vision() {
        //limelight stuff
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry tV = table.getEntry("tv");
        NetworkTableEntry ty = table.getEntry("ty");
        double t = (double) tV.getNumber(0);
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);

        //just prints the "margin of error" to the console log

        double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.Hdiff/tanOfY)/10;
        System.out.println(allowableRange(Constants.MAX, Constants.con, distance));

        //if A button is pressed
        if(Drive.driveStick.getRawButton(1) == true){

          //whatup my diggity dawg
          //read the varible name ;)
            double turnPower = 0.0;

            /*what this block is going to do is affect the turn power values to
            arcade drive to allow for user input until it sees a target
            if a target is in range and satifies the thresholds then stop allowing a
            user input and lock on to the crosshairs of the target,
            fwd power is always dictated by the driver*/

            if(IsActualTarget(t) == true && IsSatified(x, y) == true) { //if satisfied within range
                turnPower = 0.0; //dont turn
            }else if(IsActualTarget(t) == true && IsInRange(x, y) == true){//if it can see a target and its in a certain range
                turnPower = (Constants.kpAim * x); //if a target is within a certain range turn smothly this is a p loop
            }else if(IsActualTarget(t) == false){
                turnPower = -Drive.getTurnPower() * .2; //if there is no target allow for user input
            }

            Drive.arcadeDrive(turnPower, Drive.getForwardPower() * .35); //apply the values to robot
        }

    }


      public static boolean IsActualTarget(double Is_target) { //can the limelight see a target
        if(Is_target >= 1){return true;}
        else{return false;}
      }
      public static boolean IsSatified(double target_x, double target_y) { //are we happy?
        //make limelight entries local
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        double y = ty.getDouble(0.0);

        //this finds the distance between the target and the limelight
        double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.Hdiff/tanOfY)/10;

        if(Math.abs(target_x) <= allowableRange(Constants.MAX, Constants.con, distance)){return true;}
        else{return false;}
      }

      public static boolean IsInRange(double target_x, double target_y) { //are we in range of a target?
        if(Math.abs(target_x) >= 10.0){return true;}
        else{return false;}
      }

      public static double allowableRange(double max, double c, double distance){
        // ight kiddos, get ready
        //this equasion gives us a "smooth" down turn to zero for our "margin of error"
        //based on distance
        // this is the equasion (-max * 2^k*distance + max)
        double exp = -c * distance;
        double errorMargin = (-max) * Math.pow(2.0, exp) + max;
        return errorMargin;
      }
    }

