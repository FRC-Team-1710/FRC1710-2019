/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*this is code to control the pistons on the claw:
DoubleSolenoid exampleDouble = new DoubleSolenoid(1, 2);

exampleDouble.set(DoubleSolenoid.Value.kOff);
exampleDouble.set(DoubleSolenoid.Value.kForward);
exampleDouble.set(DoubleSolenoid.Value.kReverse);
*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ClawControl {

    DoubleSolenoid piston = new DoubleSolenoid(1, 2);

    //Identifies the button that activates the pistons
    public static DoubleSolenoid pistonIntake() {
        return Drive.drivestick.getRawButton(4);
    }

    //Identifies the button that deactivates the pistons
    public static DoubleSolenoid pistonOuttake() {
        return Drive.drivestick.getRawButton(5);
    }

    //Neutralizes the pistons ????? Not entirely sure what this should do
    public void pistonNeutral() {
        piston.set(DoubleSolenoid.Value.kOff);
    }

    //Activates the pistons
    public void pistonActivate() {
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    //Deactivates the pistons
    public void pistonDeactivate() {
        piston.set(DoubleSolenoid.Value.kForward);
    }

}
