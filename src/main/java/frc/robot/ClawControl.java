/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//this is code to control the pistons on the claw
//##########################################
//make sure to add motor outputs!!!!!!!!!!
//##########################################

package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.Utility.PID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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

    public boolean isConflicting;

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

    public void ClawTele(){
        if (Drive.mechStick.getRawButton(1)){
            BallTransfer();

            Drive.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(2) == true){
            HatchTransfer();
            Drive.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(3) == true){
            FrontDeposit();
            Drive.clawRotate.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(4) == true){
            BackDeposit();
            Drive.clawRotate.set(ControlMode.PercentOutput, output);
        } else {
            Drive.clawRotate.set(ControlMode.PercentOutput, 0);
        }
    }

    public void BallTransfer(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 10;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        if(error == 0){
            isConflicting = false;
        }
    }

    public void HatchTransfer(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 20;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        if(error == 0){
            isConflicting = false;
        }
    }

 /*   public static void autoHatchPlace(){
        if(ultrasonic == true && Drive.driveStick.getRawButton(autoHatchPlace) && haveHatch == true && inPosition == true){
            
            Drive.arcadeDrive(0,.3,false);
            Timer.delay(1);
            Robot.clawOpen1.set(Value.kReverse);
            Robot.clawOpen2.set(Value.kReverse);
            Timer.delay(.5);
            Drive.arcadeDrive(0,-.5,false);
            Timer.delay(1);
            Drive.arcadeDrive(0, 0,false);
            boolean haveHatch = false;
        }

    }

    public static void autoHatchGet(){
        if(ultrasonic == true && Drive.driveStick.getRawButton(autoHatchGet) && haveHatch == false && inPosition == true){
            Drive.arcadeDrive(0,.3,false);
            Timer.delay(1);
            Robot.clawOpen1.set(Value.kForward);
            Robot.clawOpen2.set(Value.kForward);
            Drive.arcadeDrive(0,-.5,false);
            Timer.delay(1);
            Drive.arcadeDrive(0, 0,false);
            boolean haveHatch = true;
        }
    }*/

    public void FrontDeposit(){
        double current = ((Drive.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 90;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        if(error == 0){
            isConflicting = true;
        }
    }

    public void BackDeposit(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 180;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        if(error == 0){
            isConflicting = false;
        } 
    }
    public void resting(){
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 10;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        if(error == 0){
            isConflicting = false;
        }
    }
    public boolean is_not_conflicting(){
        if(isConflicting == true){
            return true;
        } else {
            return false;
        }
    }
    
    public static void GetEncoder(){
        if (Drive.driveStick.getRawButton(4)){
            System.out.println(Constants.clawRotate.getSelectedSensorPosition());
        }
    }
}
