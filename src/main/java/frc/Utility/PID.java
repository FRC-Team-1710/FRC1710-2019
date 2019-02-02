/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Utility;

import javax.management.timer.Timer;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class PID {
    double error,P,I,D;
	public double output;
	double time;

    public static double PID(double error_, double P_, double I_, double D_, double time_){
        return P_ * error_ + I_ * Math.signum(error_) + D_ * (error_ / time_); 

    }
}
