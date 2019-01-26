/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
    public static CANEncoder enR1, enR2, enL1, enL2;
    static double lastAngle, angleIntegral, output;
    public static AHRS navx;
    public static CANSparkMax R1,R2, L1, L2;
    public static Joystick driveStick;

    public static double getTurnPower() {
		return -driveStick.getRawAxis(4);
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
		return (R1.getEncoder().getPosition() / 10.75);
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
    public static void straightDriveTele (double power, double heading, boolean high) {
		double currentAngle = Drive.getNavxAngle();
		double error = (currentAngle - heading);
		angleIntegral += error;
		double angleDeriv = currentAngle - lastAngle;
		
		if(high == true) {
			output = (error * Constants.kpStraightHi) + (angleDeriv * Constants.kdStraightHi);
			
			if(Constants.kiStraightHi * angleIntegral > 1) {
				angleIntegral = 1/Constants.kiStraightHi;
			} else if(Constants.kiStraightHi * angleIntegral < -1){
				angleIntegral = -1/Constants.kiStraightHi;
			}
			
			output += (angleIntegral * Constants.kiStraightHi);
		} else {
			output = (error * Constants.kpStraight) + (angleDeriv * Constants.kdStraight);
			
			if(Constants.kiStraight * angleIntegral > 1) {
				angleIntegral = 1/Constants.kiStraight;
			} else if(Constants.kiStraight * angleIntegral < -1){
				angleIntegral = -1/Constants.kiStraight;
			}
			
			output += (angleIntegral * Constants.kiStraight);
		}
		rightDrive(output + power);
		leftDrive(output - power);
		lastAngle = Drive.getNavxAngle();
		SmartDashboard.putNumber("Auto Drive Output", output);
		SmartDashboard.putNumber("Auto Drive Angle", currentAngle);
	}

    public static double getDriveLeftTrigger() {
        return driveStick.getRawAxis(2);
    }

    public static double getDriveRightTrigger() {
        return driveStick.getRawAxis(3);
    }

    public static void initializeDrive(){
        L1 = new CANSparkMax(1, MotorType.kBrushless); //init the motors
        L2 = new CANSparkMax(2, MotorType.kBrushless);
        R1 = new CANSparkMax(3, MotorType.kBrushless); // init the motors
        R2 = new CANSparkMax(4, MotorType.kBrushless);
        R1.setIdleMode(IdleMode.kBrake);
        L1.setIdleMode(IdleMode.kBrake);
        R2.follow(R1);
        L2.follow(L1);

        Drive.navx = new AHRS(SPI.Port.kMXP);

        driveStick = new Joystick(0);
    }
   
   public static void arcadeDrive(double side, double forward){
        R1.set(side - forward);
        L1.set(side + forward);
    }
}



