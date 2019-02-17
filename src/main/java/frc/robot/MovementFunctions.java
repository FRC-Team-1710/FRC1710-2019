/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * this file is not yet complete
 */
public class MovementFunctions {
    static int currentClawEncoderPosition;
    static int currentIntakeEncoderPosition;

    public static void resetCurrentEncoderPositions() {
      //  currentClawEncoderPosition = clawEncoder.getSensorPosition();
      //  currentIntakeEncoderPosition = IntakeEncoder.getSensorPosition();
    }

    public static void restingPosition() {
        resetCurrentEncoderPositions();

    }

    public static void movingPosition() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void BallIntake() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void HatchIntakeFront() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void HatchIntakeBack() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void HatchTransfer() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void BallOuttakeFront() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void BallOuttakeBack() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void HatchOuttakeFront() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }

    public static void HatchOuttakeBack() {
        resetCurrentEncoderPositions();
        //ask where we currently are
        //then ask where we want to go
        //then ask if we can move
        //then ask how can we get to our destination
    }
}
