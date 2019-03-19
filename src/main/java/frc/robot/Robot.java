/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.CommandGroups.TestDrive;
import frc.Utility.PID;
import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  Command autonomousCommand;
  double changesInAngle;
  double changesInRotations;
  double startingRotations;
  double startingAngle;
  public static Timer rumbleTime = new Timer();
  public static Timer clawHold = new Timer();
  public static Timer autoTime = new Timer();
  public static Timer time = new Timer();
  public static double[] changeAngle = new double[]{};
  public static double[] changeRotations = new double[]{};
  public static boolean Shift;
  public static DoubleSolenoid lShifter;
  public static DoubleSolenoid rShifter;
  public static DoubleSolenoid clawOpen1, clawOpen2;
  int i;
  public static TalonSRX pickup1, pickup2, intake, clawIntake1, clawIntake2, climber1, climber2, climber3, climber4;
  double encoderGoalClaw, encoderGoalPickup;
  double clawError, pickupOutput, pHold;
  public static CANSparkMax clawRotate;
  public static int visionPosMult = 1;
  public static int hatchPosition = 1;
  public static DigitalInput hatchSwitch1, hatchSwitch2, clawSwitch;
  public Compressor c = new Compressor();
  public static boolean clawOverride;

  @Override
  public void robotInit() {
    
    pHold = .25;
    pickup1 = new TalonSRX(5);
		pickup2 = new TalonSRX(6);
    lShifter = new DoubleSolenoid(0, 7);
    rShifter = new DoubleSolenoid(1, 6);
    clawOpen1 = new DoubleSolenoid(2,4);
    clawOpen2 = new DoubleSolenoid(3,5);
    autoTime = new Timer();
    clawHold = new Timer();
    intake = new TalonSRX(7);

    clawRotate = new CANSparkMax(8, MotorType.kBrushless );
    Drive.initializeDrive();
    Vision.visionInit();
    pickup1.setNeutralMode(NeutralMode.Brake);
    pickup2.setNeutralMode(NeutralMode.Brake);
    pickup1.follow(pickup2);
    pickup1.setInverted(true);
    pickup1.setNeutralMode(NeutralMode.Brake);
    pickup2.setNeutralMode(NeutralMode.Brake);
    Drive.L1.setInverted(false);
    Constants.constantInit();
  }

  @Override
  public void autonomousInit(){
    clawOpen1.set(Value.kReverse);
     clawOpen2.set(Value.kReverse);
    teleopInit();
    autoTime.start();
  }

  @Override
  public void autonomousPeriodic(){
    
    if(autoTime.get() < 2){
      Drive.arcadeDrive(0,.5,false);
    }else{
      autoTime.stop();
      teleopPeriodic();
    } 
  }

  @Override
  public void teleopInit(){
    
    pickup1.setNeutralMode(NeutralMode.Brake);
    pickup2.setNeutralMode(NeutralMode.Brake);
    clawRotate.setIdleMode(IdleMode.kBrake);
    Drive.L1.setInverted(false);
  
  }

  @Override
  public void teleopPeriodic() {

   Drive.arcadeDrive((Drive.getTurnPower()), Drive.getForwardPower(), Drive.driveStick.getRawButton(9));
        
   pickup1.set(ControlMode.PercentOutput, -Drive.mechStick.getRawAxis(1) * .7);
   pickup2.set(ControlMode.PercentOutput,-1 * -Drive.mechStick.getRawAxis(1) * .7);
 
   if(Drive.mechStick.getRawAxis(2) > 0){
      clawRotate.set(Drive.mechStick.getRawAxis(2) * .45);
    }else if(Drive.mechStick.getRawAxis(3) > 0){
      clawRotate.set(-1 *Drive.mechStick.getRawAxis(3) * .45);
    }else{
      clawRotate.set(0);
    } 
  
   if(Drive.driveStick.getRawButtonPressed(1) == true) {
     clawOpen1.set(Value.kReverse);
     clawOpen2.set(Value.kReverse);
    }else if(Drive.driveStick.getRawButtonPressed(2) == true){
      clawOpen1.set(Value.kForward);
     clawOpen2.set(Value.kForward);
    }
    
    if(Drive.mechStick.getRawButton(2)==true){
      c.setClosedLoopControl(true);
    } else{
      c.setClosedLoopControl(false);
    }    
    
   if(Drive.driveStick.getRawButton(5) == true){
      Drive.clawIntake2.set(ControlMode.PercentOutput, .6);
      intake.set(ControlMode.PercentOutput, 0);
    }else if(Drive.driveStick.getRawButton(6) == true){
      Drive.clawIntake2.set(ControlMode.PercentOutput, -1);
      intake.set(ControlMode.PercentOutput, -1); 
        }else{
      Drive.clawIntake2.set(ControlMode.PercentOutput, 0);
      intake.set(ControlMode.PercentOutput, 0);
    }
  } 

  public static void Shifting(boolean isShifted){
    if (isShifted){
      lShifter.set(Value.kReverse);
      rShifter.set(Value.kForward);  
    } else {
      lShifter.set(Value.kForward);
      rShifter.set(Value.kReverse);
    }
  }
}


