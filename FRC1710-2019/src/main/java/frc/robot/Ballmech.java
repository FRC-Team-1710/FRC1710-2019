package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Drive;
import frc.robot.Constants;

public class Ballmech {

    private static final double INTAKE_POWER = 0.5;
    private static final double OUTTAKE_POWER = -0.75;
    private static final double STOP = 0.0;
    
    //Determines what functions to use when triggers are used
    public static void ballMechTeleop() {
        if (Drive.getDriveLeftTrigger() > 0 && Drive.getDriveRightTrigger() > 0) { neutralizeBall(); }
        else if (Drive.getDriveLeftTrigger() > 0) { intakeBall(); }
        else if (Drive.getDriveRightTrigger() > 0) { outtakeBall(); }
        else { neutralizeBall(); }
    }

    //Intializes the intake function and determines speed percentages/inversions
    public static void intakeBall() {
        Constants.intake.set(ControlMode.PercentOutput, INTAKE_POWER); 
    }

    //Initializes the outtake function and determines speed percentages/inversions
    public static void outtakeBall() {
        Constants.intake.set(ControlMode.PercentOutput, OUTTAKE_POWER);
    }

    //Initializes the neutralize function for if the driver accidentally uses both triggers at the same time
    public static void neutralizeBall() {
        Constants.intake.set(ControlMode.PercentOutput, STOP);
    }

}