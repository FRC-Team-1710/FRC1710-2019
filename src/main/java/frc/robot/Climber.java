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
    public static TalonSRX C1, C2;
    public static double MaxSpeed = .75;

    public static void initalizeClimb(){
        C1 = new TalonSRX(11); //make sure to check it is plugged into 11
    }

    public static void Climb() {
        if (Drive.driveStick.getRawButton(4)){
            C1.set(ControlMode.PercentOutput, MaxSpeed);
        } else if (Drive.driveStick.getRawButton(3)) {
            C1.set(ControlMode.PercentOutput, -1 * MaxSpeed);
        } else {
            C1.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void ClimbV2(){
        C1.set(ControlMode.PercentOutput, Drive.driveStick.getRawAxis(1));
    }

    public static void ClimbInitV2(){
        C1 = new TalonSRX(11);
        C2 = new TalonSRX(12);
        C2.follow(C1);
        C2.setInverted(true);
    }
}
