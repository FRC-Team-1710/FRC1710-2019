/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Commands;

import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Drive;
import frc.robot.Constants;

public class HatchIntake extends Command {

  public double PlaceBack;
  public double PlaceFront;
  public double hatchTransfer;
  public double ballTransfer;
  // replace encoderValues above with the respective encoder value
  public Boolean finished = false;
  public double Speed;
  public double negativeSpeed;
  public double Stop;
  public double currentPosition;
  // current position must be the current encoder value

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Speed = 0.25;
    negativeSpeed = -0.25;
    Stop = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Drive.driveStick.getPOV(360) == 1) {
      if(currentPosition == PlaceBack) { 
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == PlaceFront) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == hatchTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == ballTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      }
      
    }
    if(Drive.driveStick.getPOV(90) == 1) {
      if(currentPosition == PlaceBack) { 
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == PlaceFront) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == hatchTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == ballTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      }
    }
    if(Drive.driveStick.getPOV(180) == 1) {
      if(currentPosition == PlaceBack) { 
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == PlaceFront) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == hatchTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == ballTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, negativeSpeed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      }
    }
    if(Drive.driveStick.getPOV(270) == 1) {
      if(currentPosition == PlaceBack) { 
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == PlaceFront) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == hatchTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Speed);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else if(currentPosition == ballTransfer) {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      } else {
        Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
        if(currentPosition == PlaceBack) {
          finished = true;
        }
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Constants.clawRotate.set(ControlMode.PercentOutput, Stop);
  }
}
