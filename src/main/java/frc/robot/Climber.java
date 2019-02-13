/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Climber {
    public static TalonSRX C1,C2, C3;
    public static double MaxSpeed = .5;
    public static double a = .05;
    public static int totalENC = 5000;

    public static void initalizeClimb(){
        C1 = new TalonSRX(11);
        C2 = new TalonSRX(12);
        C3 = new TalonSRX(13);
        C2.follow(C1);
        C3.follow(C1);
    }

    public static void Climb() {
        if (Drive.driveStick.getRawButton(4)){
            C1.set(ControlMode.PercentOutput, MaxSpeed);
            C2.set(ControlMode.PercentOutput, MaxSpeed);
            C3.set(ControlMode.PercentOutput, MaxSpeed);
        } else if (Drive.driveStick.getRawButton(3)) {
            C1.set(ControlMode.PercentOutput, -1 * MaxSpeed);
            C2.set(ControlMode.PercentOutput, -1 * MaxSpeed);
            C3.set(ControlMode.PercentOutput, -1 * MaxSpeed);
        } else {
            C1.set(ControlMode.PercentOutput, 0);
            C2.set(ControlMode.PercentOutput, 0);
            C3.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void ClimbSpeed() { 
        // need to test and then incorperate into above function
        if (C1.getSelectedSensorPosition() <= 1300) {
            C1.set(ControlMode.PercentOutput, (MaxSpeed * (1 - Math.exp(-1 * a * (C1.getSelectedSensorPosition()/ totalENC)))));
        } else {
            C1.set(ControlMode.PercentOutput, MaxSpeed);
        }
    }
}
