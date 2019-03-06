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
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this file is responisble for the  
 */
public class Vision {
    static NetworkTable table;
    static NetworkTableEntry tx;
    static NetworkTableEntry tV;
    static NetworkTableEntry ty;
    static Servo axi = new Servo(1);
    public static int posMult = 1;

    /*
    #############################################################
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    a special thanks to Mr.Fuchs and Cyrus for helping with this :)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    #############################################################
    */
    public static void visionInit(){
      table = NetworkTableInstance.getDefault().getTable("limelight");
      tx = table.getEntry("tx");
      tV = table.getEntry("tv");
      ty = table.getEntry("ty");
    }
 
    //read values periodically
    public static void vision() {
        //limelight stuff
        double t = (double) tV.getNumber(0);
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);

        //just prints the "margin of error" to the console log

     /* double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.HeightDiffOfTargets/tanOfY)/10;
        System.out.println(allowableRange(Constants.MarginOfErrorMAX, Constants.slope, distance));
        */

        //if Y button is pressed
      
          //whatup my diggity dawg
          //read the varible name ;)
            double turnPower = 0.0;

            /*what this block is going to do is affect the turn power values to
            arcade drive to allow for user input until it sees a target
            if a target is in range and satifies the thresholds then stop allowing a
            user input and lock on to the crosshairs of the target,
            fwd power is always dictated by the driver*/
            if(IsActualTarget(t) == true && IsSatified(x, y) == true) { //if satisfied within range
                turnPower = 0.0;
            } else if(IsActualTarget(t) == true && IsInRange(x, y) == false){
              turnPower = -( .05 * x);
            } else if(IsActualTarget(t) == true && IsInRange(x, y) == true){//if it can see a target and its in a certain range
                turnPower = -(Constants.kpAim * x); //if a target is within a certain range turn smothly this is a p loop
            } else if(IsActualTarget(t) == false){
                turnPower = -Drive.getTurnPower() * .5; //if there is no target allow for user input
            } 
            Drive.arcadeDrive(turnPower, -Drive.getForwardPower() * .8, false); // apply the values to robot
        
      }

      public static boolean foundTarget() {
        double t = (double) tV.getNumber(0);
        return IsActualTarget(t);
      }

      public static boolean IsActualTarget(double Is_target) { //can the limelight see a target
        if(Is_target >= 1){
          return true;
        } else {
          return false;
        }
      }
      public static boolean IsSatified(double target_x, double target_y) { //are we happy?
        //make limelight entries local
        double y = ty.getDouble(0.0);
        //this finds the distance between the target and the limelight
        double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.HeightDiffOfTargets/tanOfY)/10;
        if(Math.abs(target_x) <= allowableRange(Constants.MarginOfErrorMAX, Constants.slope, distance)){
          return true;
        } else {
          return false;
        }
      }

      public static boolean IsInRange(double target_x, double target_y) { //are we in range of a target?
        double y = ty.getDouble(0.0);
        //this finds the distance between the target and the limelight
        double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.HeightDiffOfTargets/tanOfY)/10;
        if(Math.abs(target_x) <= 25.0 && Math.abs(target_x) >= allowableRange(Constants.MarginOfErrorMAX, Constants.slope, distance)){
          return true;
        } else {
          return false;
        }
      }
      
        /*
        *ight kiddos, get ready
        *this equasion gives us a "smooth" down turn to zero for our "margin of error" based on distance
        *
        * @param max is the "max" allowable margin of error
        * @param c is the "slope" if how fast the allowable margin turns down from zero 
        * @param distnce is the distance you are from the target
        * @return returns the "margin of error"
        */
      public static double allowableRange(double max, double c, double distance){
        double exp = -c * distance;
        double errorMargin = (-max) * Math.pow(2.0, exp) + max;
        return errorMargin;
      }

      
      public static void setFront(){
        axi.setAngle(180);
      }
      public static void setBack(){
        axi.setAngle(0);
      }
      public static double visionDistance(){
        double y = ty.getDouble(0.0);
        //this finds the distance between the target and the limelight
        double yRad = Math.toRadians(y);
        double tanOfY = Math.tan(yRad);
        double distance = (Constants.HeightDiffOfTargets/tanOfY)/10;
        return distance;
      }
    }

