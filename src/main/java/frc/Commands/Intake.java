/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// Visit http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/Timer.html for timer docs 

package frc.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import frc.robot.Drive;

public class Intake extends Command {

  public int time;
  private static Boolean finished;
  private static final double GOSPEED = .5; // Replace with speed percentage
  private static final double STOPSPEED = 0; // LEAVE AT ZERO
  public Timer beltTimer;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finished = false;
    beltTimer = new Timer();
    beltTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Drive.intake.set(ControlMode.PercentOutput, GOSPEED);
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
    Drive.intake.set(ControlMode.PercentOutput, STOPSPEED);
    beltTimer.stop();
    beltTimer.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Drive.intake.set(ControlMode.PercentOutput, STOPSPEED);
  }
}