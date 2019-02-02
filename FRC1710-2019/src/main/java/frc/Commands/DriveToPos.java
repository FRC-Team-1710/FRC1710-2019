/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.Utility.PID;
import frc.robot.Constants;
import frc.robot.Drive;
import frc.robot.Robot;



public class DriveToPos extends Command {

  double goalDistance;
  double speed, heading, currentHeading, startingPos,currentRotation, percentCompleted,output,deltaPos, totalRotations,startSlowDown;
  double slowDownStart = .8;
  public static Timer time = new Timer();

  public DriveToPos(double goalDistance_, double speed_, double heading_) {
    goalDistance = goalDistance_;
    speed = speed_;
    heading = heading_;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drive.navx.reset();
    startingPos = Math.abs((Drive.getRightPosition() + Drive.getLeftPosition())/2);
    totalRotations = goalDistance + startingPos;
    System.out.println("Start Pos: " + startingPos);
    System.out.println("Goal Pos: " + goalDistance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentRotation = Math.abs((Drive.getRightPosition() + Drive.getLeftPosition()))/2;
    percentCompleted = currentRotation/totalRotations;
    time.start();
    if(Drive.getNavxAngle() < (heading - 2) ) {
      double currentHeading = Drive.getNavxAngle();
      double error = (currentHeading - heading);
      
   
      Drive.arcadeDrive(-PID.PID(error, .007, .005, .01,time.getFPGATimestamp()),0,false);
     
    }else if(Drive.getNavxAngle() > (heading + 2)){
      double currentHeading = Drive.getNavxAngle();
      double error = (currentHeading - heading);
      //double change = error * .0065;
 
      Drive.arcadeDrive(-PID.PID(error, .007, .005, .01,time.getFPGATimestamp()),0,false);
    
    }else if(currentRotation < (totalRotations - .5)){
      
      Drive.arcadeDrive(0,speed,false);

   
    }else{
      Drive.stopDriving();
      end();
    }
  }


  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return currentRotation >=(totalRotations - 1);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("End Pos: " + currentRotation);
    Drive.stopDriving();
    time.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
