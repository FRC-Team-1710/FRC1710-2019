/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Drive;

public class TurnToAngle extends Command {
  double _angle;
	boolean _slow;
	int count;
  public TurnToAngle(double angle) {
    _angle = angle;
    	_slow = false;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  public TurnToAngle(double angle, boolean slow) {
    _angle = angle;
    _slow = slow;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    count = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(_slow){
      Drive.straightDriveTele(0,_angle);
    }else{
      Drive.straightDriveTele(0,_angle);
    }
    count++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Math.abs(Drive.getNavxAngle()) - Math.abs(_angle)) < 5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Done turning " + Drive.getNavxAngle());
    Drive.stopDriving();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
