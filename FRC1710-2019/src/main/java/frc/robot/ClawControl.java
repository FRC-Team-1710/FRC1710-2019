/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//this is code to control the pistons on the claw


/* package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class ClawControl {
    public static DoubleSolenoid LPiston, RPiston;
    public static Joystick driveStick = new Joystick(0);
    public static TalonSRX ClawMotor;
    public static Encoder encoder;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;
    

    public ClawControl() {
        LPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
        RPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
    }

    DoubleSolenoid piston = new DoubleSolenoid(1, 2);

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
        //if button is pressed the piston will release and then drive backwards 
       if  (Drive.driveStick.getRawButton(2)==true){
           pistonOuttake();
           Timer.delay(TIMERDELAY);
           pistonIntake();
           Drive.leftDrive(REVERSEPOWER);
           Drive.rightDrive(REVERSEPOWER*-1);   
       }
    }
    }
    */
