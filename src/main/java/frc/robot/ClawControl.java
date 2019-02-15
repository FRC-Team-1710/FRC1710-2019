/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//this is code to control the pistons on the claw

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick; 
import frc.Utility.PID;
import edu.wpi.first.wpilibj.Timer;

public class ClawControl {
    public static DoubleSolenoid LPiston, RPiston;
    public static Joystick driveStick = new Joystick(0);
    public static TalonSRX ClawMotor;
    public static long start = System.currentTimeMillis();
    public static long finish = System.currentTimeMillis();
    public static long TimeElapsed = (finish - start);
    public static double goal = 100;
    public static double error = 0;
    public static double output = 0;
    public static double ticksToLine = 650;
    public static double current;
    public static final double P = .01;
    public static final double I = .002;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;

    public ClawControl() {
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
            ClawMotor.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(2)){
            HatchTransfer();
            ClawMotor.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(3)){
            FrontDeposit();
            ClawMotor.set(ControlMode.PercentOutput, output);
        } else if (Drive.mechStick.getRawButton(4)){
            BackDeposit();
            ClawMotor.set(ControlMode.PercentOutput, output);
        } else {
            ClawMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void BallTransfer(){
        current = ((ClawMotor.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    }

    public static void HatchTransfer(){
        current = ((ClawMotor.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 20;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    }

    public static void FrontDeposit(){
        current = ((ClawMotor.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 90;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    }

    public static void BackDeposit(){
        current = ((ClawMotor.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 180;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    } 

    public static void GetEncoder(){
        if (Drive.driveStick.getRawButton(4)){
            ClawMotor.set(ControlMode.PercentOutput, .5);
            System.out.println(ClawMotor.getSelectedSensorPosition());
        }else {
            ClawMotor.set(ControlMode.PercentOutput, 0);
        }
    }
}
