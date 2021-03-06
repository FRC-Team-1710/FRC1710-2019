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
    String RestingPosition;
    String MovingPosition;
    String BallIntake;
    String HatchIntakeFront;
    String HatchIntakeBack;
    String HatchTransfer;
    String BallOuttakeFront;
    String BallOuttakeBack;
    String HatchOuttakeFront;
    String HatchOuttakeBack;

    public void intakeMovements(String input) {
        MovementFunctions.movingPosition();
        if(input == RestingPosition) {
            MovementFunctions.restingPosition();
        } else if(input == BallIntake) {
            MovementFunctions.BallIntake();
        } else if(input == HatchIntakeFront) {
            MovementFunctions.HatchIntakeFront();
        } else if(input == HatchIntakeBack) {
            MovementFunctions.HatchIntakeBack();
        } else if(input == HatchTransfer) {
            MovementFunctions.HatchTransfer();
        } else if(input == BallOuttakeFront) {
            MovementFunctions.BallOuttakeFront();
        } else if(input == BallOuttakeBack) {
            MovementFunctions.BallOuttakeBack();
        } else if(input == HatchOuttakeFront) {
            MovementFunctions.HatchOuttakeFront();
        } else if(input == HatchOuttakeBack) {
            MovementFunctions.HatchOuttakeBack();
        } else {
            MovementFunctions.restingPosition();
        }
    }

    public void SyncMechsInit() {
        if(Drive.mechStick.getPOV(180) == 1) {
            intakeMovements(RestingPosition);
        }
    
        if(Drive.mechStick.getPOV(360) == 1) {
            intakeMovements(MovingPosition);
        }
    
        if(Drive.mechStick.getRawButton(1) == true) {
            intakeMovements(BallIntake);
        }
    
        if(Drive.mechStick.getRawButton(3) == true) {
            intakeMovements(HatchIntakeFront);
        }
    
        if(Drive.mechStick.getRawButton(4) == true) {
            intakeMovements(HatchIntakeBack);
        }
    
        if(Drive.mechStick.getPOV(270) == 1) {
            intakeMovements(HatchTransfer);
        }
    
        if(Drive.mechStick.getRawButton(5) == true) {
            intakeMovements(BallOuttakeFront);
        }
    
        if(Drive.mechStick.getRawButton(6) == true) {
            intakeMovements(BallOuttakeBack);
        }
    
        if(Drive.mechStick.getRawAxis(3) > 0) {
            intakeMovements(HatchOuttakeFront);
        }
    
        if(Drive.mechStick.getRawAxis(4) > 0) {
            intakeMovements(HatchOuttakeBack);
        }
    }
}
