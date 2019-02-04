/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Constants {
    //constants for drive
	//.013
	public static double kpStraight = .0125;
	public static double kiStraight = .001;
	public static double kdStraight = .00075;
	//was .0125
	public static double kpStraightHi = .012;
	public static double kiStraightHi = .000025;
	public static double kdStraightHi = .005;
	public static double kiDrive = .000025;
	public static double kpTurn = 0.01;
    //encoder ticks per inch on wheel
    public static int ticksPerInch = 217;
    //drive to position slow down rate
	public static double slowDownPercent = 0.13;
	public static TalonSRX pickup1,pickup2, intake, clawRotate, clawIntake1, clawIntake2, climber1, climber2, climber3, climber4; 
	public static void constantInit() {
		pickup1 = new TalonSRX(5);
		pickup2 = new TalonSRX(6);
		intake = new TalonSRX(7);
		clawRotate = new TalonSRX(8);
		clawIntake1 = new TalonSRX(9);
		clawIntake2 = new TalonSRX(10);
		climber1 = new TalonSRX(11);
		climber2 = new TalonSRX(12);
		climber3 = new TalonSRX(13);
		climber4 = new TalonSRX(14);
	}
	
	
}
