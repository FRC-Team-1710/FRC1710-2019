package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Drive;

public class Ballmech {

    //Declares LBelt and RBelt as types of CANSparkMAX
    public static CANSparkMax LBelt, RBelt;

    //intializes LBelt and RBelt
    public static void initializeBallMech() {
        LBelt = new CANSparkMax(5, MotorType.kBrushless);
        RBelt = new CANSparkMax(6, MotorType.kBrushless);
    }
    
   
    //code for when the belt intakes the ball
    public static void runBallMech() {
        if (Drive.driveStick.getRawAxis(2)>0) {
            LBelt.set(-.5);
            RBelt.set(.5);
        } 
    }
    
    //code for when the belt outtakes the ball
    public static void undoBallMech() {
        if (Drive.driveStick.getRawAxis(3)>0) {
            LBelt.set(.5);
            RBelt.set(-.5);
        }
    }

    //code for when the driver accidentally uses both the intake and outtake at the same time (null action)
    public static void problemBallMech() {
        if (Drive.driveStick.getRawAxis(2)>0 && Drive.driveStick.getRawAxis(3)>0) {
            LBelt.set(0);
            RBelt.set(0);
        }
    }

}