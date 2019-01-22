/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static double slowDownPercent = 0.1;
}
