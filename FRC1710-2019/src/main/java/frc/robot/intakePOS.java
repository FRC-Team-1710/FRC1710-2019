/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.Joystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class intakePOS extends Command {

  public static TalonSRX IntakeArmAxsisR;
  public static TalonSRX IntakeArmAxsisL;
  
  private static final int LEFT_ID = 5; //Replace with current id
  private static final int RIGHT_ID = 6; //Replace with current id

  private double AxisPOS = 0.0; // the axis position of just one of the motors
  
  private static final double ENC_resting_POS = 0.0;  //where the arm actually is
  private static final double ENC_ballIntake_POS = 0.0;
  private static final double ENC_hatchIntake_POS = 0.0;

  private static final double MOVE_SPEED = -0.75; //change the values acc.
  private static final double STOP = 0.0;

  
  public intakePOS(double wantedPOS) {
    
    do {
        GetEncoder();
        IntakeArmAxsisR.set(ControlMode.PercentOutput, MOVE_SPEED * .35);
        IntakeArmAxsisR.set(ControlMode.PercentOutput, MOVE_SPEED * -.35);
      }while(IsNotSatisfied(wantedPOS, AxisPOS)); //returns false if congruent thus breaking the loop
      
  }
  public static boolean IsNotSatisfied(double requiredPOS, double currentPOS) { //are we in the right position?
    if(requiredPOS==currentPOS){return false;} 
    else{return true;} 
  }
  private void GetEncoder() { //get encoder values
    R_ENC = new encoder(); //yes there are errors 
    AxisPOS = get.R_ENC(); //there is no encoder attached so that why(acc. to abby)
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    IntakeArmAxsisR = new TalonSRX (3); //insert appropriate value ################################
    IntakeArmAxsisL = new TalonSRX (4); //insert appropriate value ################################
    
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
