/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.Utility.PID;

/**
 * Add your docs here.
 */
public class MovementFunctions {
    double goal;
    double error;
    double output;
    double P = 0.1;
    double I = 0.02;
    double TimeElapsed;
    double ticksToLine = 
    public static void restingPosition() {
        // check encoder values
        // set values to go to 
    }

    public void gotoupright() {
        double current = ((Constants.clawRotate.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        goal = 90;
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
    }
}
