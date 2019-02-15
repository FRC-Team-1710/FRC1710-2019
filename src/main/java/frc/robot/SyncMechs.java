/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Go to ____ position by using a series of steps by placing ClawControl and IntakeControl 
 * in various positions until both are situated according to driver needs.
 */
public class SyncMechs {
    int intakePos = 4;
    int clawPos = 2;
       

   /* public static void SyncMech() {
        if(Drive.driveStick.getPOV(360) == 1) {
            if(encoder values == intakePos1 && other encoder values == clawPos1) { // fill if statement with if the encoder values = where they want to be
                Constants.pickup1.set(ControlMode.PercentOutput, 0);
                Constants.pickup2.set(ControlMode.PercentOutput, 0);
                Constants.clawRotate.set(ControlMode.PercentOutput, 0);
            } else if(encoder values == intakePos1 && other encoder values == clawPos2) {
                //pid stuff
            } else if(encoder values == intakePos1 && other encoder values == clawPos3) {
                //pid stuff
            } else if(encoder values == intakePos1 && other encoder values == clawPos4) {
                //pid stuff
            } else if(encoder values == intakePos1 && other encoder values == clawPos5) {
                //pid stuff
            } else if(encoder values == intakePos1 && other encoder values == clawPos6) {
                //pid stuff
            } else if(encoder values == intakePos2 && other encoder values == clawPos1) {
                //pid stuff
            }
        }
    } */

    public static void hudIdea() {
        if(claw is in ground == true){ //"stacking"
            if (intake down == true){
                

            }

        }

    }

    public static void intakeMovements(int intakePos, int clawPos) {
        // first, go to the movement position
        // then, go to intakePos clawPos
    }

    
}
