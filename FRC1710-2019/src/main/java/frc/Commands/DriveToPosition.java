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

public class DriveToPosition extends Command {

  int _encGoal, count;
	boolean _isInHighGear, _endBehavior, _direction, _startReset, _shouldFixHeading,
			_newStart, _needToResetStart, _foundSlowDownStart, _hellaAccurate, _fixingHeading;
	double _totalTicks, _currentTicks, _percentComplete, _output, _slowDownStart,
			_speed, _deltaPos, _goalDist, _heading, _startingPosition;
    
	Timer driveTime = new Timer();

  public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction) {
    _speed = speed;
    _encGoal = (int) (encGoal);// * Constants.ticksPerInch;
    _isInHighGear = false;//isInHighGear;
    _heading = heading;
    _endBehavior = endBehavior;
    _direction = direction;
    _hellaAccurate = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
     	Drive.R1.getEncoder();
    	Drive.L1.getEncoder();
    	
    	//Drive.setShifters(_isInHighGear);

    	count = 0;
    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	if(!RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20)) {
    		_shouldFixHeading = true;
            _totalTicks = _encGoal;// + _startingPosition;	
    	} else {
    		_shouldFixHeading = false;
            _totalTicks = _encGoal;// + _startingPosition;	
    	}
    	_goalDist = Math.abs(_encGoal);
    	driveTime.start();
		System.out.println("Start pos: " + _startingPosition);
		System.out.println("Goal pos: " + _totalTicks);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _currentTicks = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	_percentComplete = _currentTicks/_totalTicks;

    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading);
    		}
    	} else {
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
        	        	
        	if(_hellaAccurate == true) {
        	
        		if(!RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20)) {
        			//turns in place until heading is close to prevent giant over corrections that eat the drive distance
                	Drive.straightDriveTele(0 ,_heading);
                	_fixingHeading = true;
        		} else {
        			if(!_newStart && _shouldFixHeading) {
        		    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
        	            _totalTicks = _encGoal + _startingPosition;
        	            _newStart = true;
        			} else {
                    	_fixingHeading = false;
                    	Drive.straightDriveTele(_output, _heading);
        			}
        		}
        	} else {
        		
            	_fixingHeading = false;
            	Drive.straightDriveTele(_output, _heading);
        	}
       	}
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
	

		return (_currentTicks >= (_totalTicks));
	
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
	driveTime.stop();

	System.out.println("End pos: " + _currentTicks);
	Drive.stopDriving();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
