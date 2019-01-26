/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.Utility.RobotMath;
import frc.robot.Constants;
import frc.robot.Drive;

public class NewDriveToPos extends Command {
static double lastAngle;
  int _encGoal, count;
    boolean _startReset, _shouldFixHeading, _newStart, _needToResetStart,_foundSlowDownStart,_fixingHeading;
    double _totalTicks, _currentTicks,_percentComplete,_output,_slowDownStart,_speed,_deltaPos,_goalDist, _heading, _startingPosition;

  Timer driveTime = new Timer();

  public NewDriveToPos(int encGoal, double speed, double heading) {
    _speed = speed;
    _encGoal = (int) (encGoal);
    _heading = heading;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drive.R1.getEncoder().getPosition();
    Drive.L1.getEncoder().getPosition();;

    count = 0;
    _startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    if(!RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20)){
      _shouldFixHeading = true;
      _totalTicks = _encGoal + _startingPosition;
    }else{
      _shouldFixHeading = false;
      _totalTicks = _encGoal + _startingPosition;
    }
    _goalDist = Math.abs(_encGoal);
    driveTime.start();
    System.out.println("Start pos: " + _startingPosition);
    System.out.println("Goal pos: " + _totalTicks);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _currentTicks = (Math.abs(Drive.getRightPosition()) + Math.abs(Drive.getLeftPosition()))/2;
      _percentComplete = _currentTicks/_totalTicks;
      if(_encGoal < 0) {
        if(Math.abs(_percentComplete) > Constants.slowDownPercent) {
          count++;
          if(_foundSlowDownStart == false) {
            _slowDownStart = _currentTicks;
            _foundSlowDownStart = true;
          } else {
            _deltaPos = _currentTicks - _slowDownStart;
              _output =  ((Math.pow(_deltaPos/(_goalDist + Math.abs(_slowDownStart)), 3) - 1) * _speed); 
          }
        } else {
          _output = -_speed;
        }
    } else {
         if(_percentComplete > Constants.slowDownPercent) {
          count++;
          if(_foundSlowDownStart == false) {
            _slowDownStart = _currentTicks;
            _foundSlowDownStart = true;
          } else {
            _deltaPos = _currentTicks - _slowDownStart;
                  _output =  ( (1 - Math.pow(_deltaPos/(_goalDist + Math.abs(_slowDownStart)), 3)) * _speed);
          }
         } else {
           _output = _speed;
         }
        }
        System.out.println(Drive.getNavxAngle());
         if(_heading -2 > Drive.getNavxAngle() ){
          //_fixingHeading = false;
          double currentAngle = Drive.getNavxAngle();
          double error = (currentAngle - _heading);
          //double angleDeriv = currentAngle - lastAngle;
         //double turnOutput = (error * Constants.kpStraight) + (angleDeriv * Constants.kdStraight);
          double change = error * .1;
        
         
         
         Drive.R1.set(change);
         Drive.L1.set(change);

         }else if(Drive.getNavxAngle() -2 > _heading){
          _fixingHeading = false;
          Drive.R1.set(_output);
          Drive.L1.set(-1* _output);
         }
      //  _fixingHeading = false;
//Drive.straightDriveTele(_output,_heading);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return (_currentTicks >= (_totalTicks)&& RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20) && !_fixingHeading);
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTime.stop();

    System.out.println("End pos: " + _currentTicks );
    Drive.stopDriving();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
