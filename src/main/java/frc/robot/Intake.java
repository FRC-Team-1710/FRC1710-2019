package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Drive;

public class Intake {

    //Declares LBelt and RBelt as types of CANSparkMAX
    public static CANSparkMax LeadBelt, FollowBelt;
    private static final double INTAKE_POWER = 0.5;
    private static final double OUTTAKE_POWER = -0.75;
    private static final double STOP = 0.0;
    private static final int LEFT_ID = 5; //Replace with current id
    private static final int RIGHT_ID = 6; //Replace with current id

    //intializes LBelt and RBelt
    public static void initializeBallMech() {
        LeadBelt = new CANSparkMax(LEFT_ID, MotorType.kBrushless);
        FollowBelt = new CANSparkMax(RIGHT_ID, MotorType.kBrushless);
        FollowBelt.follow(LeadBelt, true);
    }
    
    //Determines what functions to use when triggers are used

    //Intializes the intake function and determines speed percentages/inversions
    public static void intakeBall() {
        LeadBelt.set(INTAKE_POWER);
    }

    //Initializes the outtake function and determines speed percentages/inversions
    public static void outtakeBall() {
        LeadBelt.set(OUTTAKE_POWER);
    }

    //Initializes the neutralize function for if the driver accidentally uses both triggers at the same time
    public static void neutralizeBall() {
        LeadBelt.set(STOP);
    }

}