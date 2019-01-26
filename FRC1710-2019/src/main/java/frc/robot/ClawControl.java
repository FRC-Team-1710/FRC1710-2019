/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//this is code to control the pistons on the claw


package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class ClawControl {

    public static DoubleSolenoid LPiston, RPiston;
    public static Joystick driveStick = new Joystick(0);

    public ClawControl() {
        LPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
        RPiston = new DoubleSolenoid(1, 2); //Replace numbers with ROBORio assigned values
    }

    DoubleSolenoid piston = new DoubleSolenoid(1, 2);

    //Identifies the button that activates the pistons
    public static void pistonIntake() {
        LPiston.set(DoubleSolenoid.Value.kReverse);
        RPiston.set(DoubleSolenoid.Value.kReverse);
    }

    //Identifies the button that deactivates the pistons
    public static void pistonOuttake() {
        LPiston.set(DoubleSolenoid.Value.kForward);
        RPiston.set(DoubleSolenoid.Value.kForward);
    }

    public static void pistonNeutral() {
        LPiston.set(DoubleSolenoid.Value.kOff);
        RPiston.set(DoubleSolenoid.Value.kOff);
    }

}
