/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends Command {

  public int time;
  private static Boolean finished;
  private static TalonSRX BeltMotor;
  private static final int IDNUMBER = 1; // Replace with current TalonSRX ID number
  private static final double GOSPEED = .5; // Replace with speed percentage
  private static final double STOPSPEED = 0; // LEAVE AT ZERO
  public Timer beltTimer;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    BeltMotor = new TalonSRX(IDNUMBER);
    finished = false;
    beltTimer = new Timer();
    beltTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    BeltMotor.set(ControlMode.PercentOutput, GOSPEED);
    
    if (beltTimer.hasPeriodPassed(3) == true) {
      finished = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    BeltMotor.set(ControlMode.PercentOutput, STOPSPEED);
    beltTimer.stop();
    beltTimer.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    BeltMotor.set(ControlMode.PercentOutput, STOPSPEED);
  }
}
