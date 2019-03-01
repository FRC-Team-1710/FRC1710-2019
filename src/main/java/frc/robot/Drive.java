/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class Drive {
	public static CANEncoder enR1, enR2, enL1, enL2;
	static double lastAngle, angleIntegral, output;
	public static AHRS navx;
	public static CANSparkMax R1,R2, L1, L2;
	public static Joystick driveStick, mechStick;
	public static DoubleSolenoid Shifters;
	public static int ShiftersForward = 1;
	public static int ShiftersReverse = 0;
	public static Compressor compressor;

	public static TalonSRX pickup1, pickup2, intake, clawRotate, clawIntake1, clawIntake2, climber1, climber2, climber3, climber4;

	
    public static double getTurnPower() {
		return -1 * driveStick.getRawAxis(4);
	}

	public static double getForwardPower() {
		return driveStick.getRawAxis(1);
	}
	
	public static double getLeftPosition() {
		//Gets the encoder from L1 then gets the position of that encoder
		return (L1.getEncoder().getPosition() / 10.75);
	}
	
	public static double getRightPosition() {
		//gets the encoder from R1 then gets the position of that encoder
		return (R1.getEncoder().getPosition() /10.75);
	}
	
	public static double getNavxAngle() {
		return navx.getAngle();
	}
	
	public static void leftDrive(double power) {
		L1.set(power);
	}
	
	public static void rightDrive (double power) {
		R1.set(power);
	}

	public static void stopDriving(){
		R1.set(0);
		L1.set(0);
	}

	public static void straightDriveTele (double power, double heading) {
		double currentAngle = Drive.getNavxAngle();
		double error = (currentAngle - heading);
		angleIntegral += error;
		double angleDeriv = currentAngle - lastAngle;
		
		//if(high == true) {
		//	output = (error * Constants.kpStraightHi) + (angleDeriv * Constants.kdStraightHi);
			
		//	if(Constants.kiStraightHi * angleIntegral > 1) {
		//		angleIntegral = 1/Constants.kiStraightHi;
		//	} else if(Constants.kiStraightHi * angleIntegral < -1){
		//		angleIntegral = -1/Constants.kiStraightHi;
		//	}
			
		//	output += (angleIntegral * Constants.kiStraightHi);
		//} else {
			output = (error * Constants.kpStraight) + (angleDeriv * Constants.kdStraight);
			
			if(Constants.kiStraight * angleIntegral > 1) {
				angleIntegral = 1/Constants.kiStraight;
			} else if(Constants.kiStraight * angleIntegral < -1){
				angleIntegral = -1/Constants.kiStraight;
			}
			
			output += (angleIntegral * Constants.kiStraight);
		//}
		rightDrive(output + power);
		leftDrive(output - power);
		lastAngle = Drive.getNavxAngle();
		SmartDashboard.putNumber("Auto Drive Output", output);
		SmartDashboard.putNumber("Auto Drive Angle", currentAngle);
	}

	public static void setRobotHeading(double heading) {
		double error = (getNavxAngle() - heading);
		rightDrive(error*Constants.kpTurn);
		leftDrive(error*Constants.kpTurn);
	}

    public static double getDriveLeftTrigger() {
        return driveStick.getRawAxis(2);
    }

    public static double getDriveRightTrigger() {
        return driveStick.getRawAxis(3);
    }

    public static void initializeDrive(){
        R1 = new CANSparkMax(1, MotorType.kBrushless); //init the motors
        //R2 = new CANSparkMax(2, MotorType.kBrushless);
        L1 = new CANSparkMax(3, MotorType.kBrushless); // init the motors
		//L2 = new CANSparkMax(4, MotorType.kBrushless);
		pickup1 = new TalonSRX(5);
		pickup2 = new TalonSRX(6);
		intake = new TalonSRX(7);
		//clawRotate = new TalonSRX(8);
		clawIntake1 = new TalonSRX(9);
		clawIntake2 = new TalonSRX(10);
		climber1 = new TalonSRX(11);
		climber2 = new TalonSRX(12);
		climber3 = new TalonSRX(13);
		climber4 = new TalonSRX(14);
		
		///Shifters = new DoubleSolenoid(ShiftersForward,ShiftersReverse);

        //R1.setIdleMode(IdleMode.kBrake);
        //L1.setIdleMode(IdleMode.kBrake);
		//R2.follow(R1);
		
        //L2.follow(L1);
		//L2.setInverted(false);
		L1.setInverted(false);
        Drive.navx = new AHRS(SPI.Port.kMXP);
		driveStick = new Joystick(0);
		mechStick = new Joystick(1);
		compressor = new Compressor(0);
		
		//Shifters = new DoubleSolenoid(ShiftersForward, ShiftersReverse);
    }
   
   public static void arcadeDrive(double side, double forward, boolean isShifted){
        R1.set(side - forward);
		L1.set(side + forward);
		Robot.Shifting(isShifted);
	}
	
	
	
	
	// if pressure starts to get low, it will activate the compressor
	public static void Compressor() {
		compressor.setClosedLoopControl(compressor.getPressureSwitchValue());
	}

	public static void Shifting(boolean isShifted){
		if (isShifted){
		  Robot.lShifter.set(Value.kReverse);
		  Robot.rShifter.set(Value.kReverse);
		} else {
		  Robot.lShifter.set(Value.kForward);
		  Robot.rShifter.set(Value.kForward);
		}
	}
	public static void frontCloseToTarget() {
		if (driveStick.getRawButton(0)) {
			if (Sensors.frontUS1.getRangeInches() < 1 && Sensors.frontUS2.getRangeInches() < 1){
				if (Sensors.frontUS1.getRangeInches() == Sensors.frontUS2.getRangeInches()) {
				stopDriving();
				}
			} else if (Sensors.frontUS1.getRangeInches() > 1 && Sensors.frontUS2.getRangeInches() < 1) {
				arcadeDrive(.4, .2, false);
			} else if (Sensors.frontUS1.getRangeInches() < 1 && Sensors.frontUS2.getRangeInches() > 1) {
				arcadeDrive(-.4, .2, false);
			}
		}
	}

	// public static void backCloseToTarget(){
	// 	if (driveStick.getRawButton(0)) {
	// 		if (backUS1.getRangeInches() < 1 && backUS2.getRangeInches() < 1){
	// 			if (backUS1.getRangeInches() == backUS2.getRangeInches()) {
	// 			stopDriving();
	// 			}
	// 		} else if (backUS1.getRangeInches() > 1 && backUS2.getRangeInches() < 1) {
	// 			arcadeDrive(.4, .2, false);
	// 		} else if (frontUS1.getRangeInches() < 1 && frontUS2.getRangeInches() > 1) {
	// 			arcadeDrive(-.4, .2, false);
	// 		}
	// 	}
	// }
	public static boolean offGroud(){
		if (Sensors.bottomUS1.getRangeInches() > 1 && Sensors.bottomUS2.getRangeInches() > 1) {
			return true; // we are off the ground, probably climing
		} else {
			return false; //either have climbed or not even off the ground
		}
	}
}