/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Climber {
   public static void Climb() {
    if (Drive.driveStick.getRawButton(4)){
        Drive.C1.set(ControlMode.PercentOutput, -.5);
        Drive.C2.set(ControlMode.PercentOutput, -.5);
        Drive.C3.set(ControlMode.PercentOutput, -.5);
      }else {
        Drive.C1.set(ControlMode.PercentOutput, 0);
        Drive.C2.set(ControlMode.PercentOutput, 0);
        Drive.C3.set(ControlMode.PercentOutput, 0);
      }
}
}
