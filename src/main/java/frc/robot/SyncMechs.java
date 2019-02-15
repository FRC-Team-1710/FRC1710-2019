/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.SyncMechs;

/**
 * Go to ____ position by using a series of steps by placing ClawControl and IntakeControl 
 * in various positions until both are situated according to driver needs.
 */
public class SyncMechs {
    String restingPosition;
    String BallIntake;
    String HatchIntakeFront;
    String HatchIntakeBack;
    String HatchTransfer;
    String BallOuttakeFront;
    String BallOuttakeBack;
    String HatchOuttakeFront;
    String HatchOuttakeBack;

    public void intakeMovements(String greg) {
        MovementFunctions.movingPosition();
        if(greg == restingPosition) {
            MovementFunctions.restingPosition();
        } else if(greg == BallIntake) {
            MovementFunctions.BallIntake();
        } else if(greg == HatchIntakeFront) {
            MovementFunctions.HatchIntakeFront();
        } else if(greg == HatchIntakeBack) {
            MovementFunctions.HatchIntakeBack();
        } else if(greg == HatchTransfer) {
            MovementFunctions.HatchTransfer();
        } else if(greg == BallOuttakeFront) {
            MovementFunctions.BallOuttakeFront();
        } else if(greg == BallOuttakeBack) {
            MovementFunctions.BallOuttakeBack();
        } else if(greg == HatchOuttakeFront) {
            MovementFunctions.HatchOuttakeFront();
        } else if(greg == HatchOuttakeBack) {
            MovementFunctions.HatchOuttakeBack();
        } else {
            MovementFunctions.restingPosition();
        }
    }

    
}
