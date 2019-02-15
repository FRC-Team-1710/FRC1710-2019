/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//this is code to control the pistons on the claw

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick; 
import frc.Utility.PID;
import edu.wpi.first.wpilibj.Timer;

public class ClawControl {
    public static DoubleSolenoid LPiston, RPiston;
    public static Joystick driveStick = new Joystick(0);
    public static long start = System.currentTimeMillis();
    public static long finish = System.currentTimeMillis();
    public static long TimeElapsed = (finish - start);
    //remove once we get robot
    public static double goal;
    public static double error;
    public static double output;


    public static double ticksToLine = 650;
    public static double current;
    public static final double P = .01;
    public static final double I = .002;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;

    public static void initializeClawControl() {
        LPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
        RPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
    }
   
    //Identifies the button that activates the pistons
    public static void pistonIntake() {
        LPiston.set(DoubleSolenoid.Value.kReverse);
        RPiston.set(DoubleSolenoid.Value.kReverse);
    }

    //Identifies the button that deactivates the pistons
    public static void pistonOuttake() {
        LPiston.set(DoubleSolenoid.Value.kForward);
        RPiston.set(DoubleSolenoid.Value.kForward);
    }

    public static void pistonNeutral() {
        LPiston.set(DoubleSolenoid.Value.kOff);
        RPiston.set(DoubleSolenoid.Value.kOff);
    }

    public static void placeHatch() {
        //need to add vision 
       if  (Drive.driveStick.getRawButton(2)==true){
           pistonOuttake();
           Timer.delay(TIMERDELAY);
           pistonIntake();
           Drive.leftDrive(REVERSEPOWER);
           Drive.rightDrive(REVERSEPOWER*-1);   
       }
    }

    public static void ClawTele(){
        if (Drive.mechStick.getRawButton(1)){
            BallTransfer();
            Constants.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(2) == true){
            HatchTransfer();
            Constants.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(3) == true){
            FrontDeposit();
            Constants.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(4) == true){
            BackDeposit();
            Constants.clawRotate.set(ControlMode.PercentOutput, output);
        } else {
            Constants.clawRotate.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void BallTransfer(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 10;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    }

    public static void HatchTransfer(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 20;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
    }

    public static void FrontDeposit(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 90;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        where(error);
    }

    public static void BackDeposit(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 180;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    } 

    public static void GetEncoder(){
        if (Drive.driveStick.getRawButton(4)){
            System.out.println(Constants.clawRotate.getSelectedSensorPosition());
        }
    }
}
