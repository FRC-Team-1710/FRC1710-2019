/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Robot extends TimedRobot {
  Command autonomousCommand;
  double changesInAngle;
  double changesInRotations;
  double startingRotations;
  double startingAngle;
  public static Timer autoTime = new Timer();
  public static Timer time = new Timer();
  public static double[] changeAngle = new double[]{};
  public static double[] changeRotations = new double[]{};
  public static boolean Shift;
  int i;

  @Override
  public void robotInit() {
    autoTime = new Timer();
    Drive.initializeDrive();

    // autonomousCommand = new TestDrive();

    Constants.constantInit();
    Vision.visionInit();
  }

  @Override
  public void autonomousInit(){
    Drive.navx.reset();
    System.out.println("R1: " + (Drive.R1.getEncoder().getPosition() / 10.75));
    System.out.println("L1: " + (Drive.L1.getEncoder().getPosition() / 10.75));
    autonomousCommand.start();
    autoTime.start();
  }

  @Override
  public void autonomousPeriodic(){
    // Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit(){
    Pixy.init();
  }

  @Override
  public void teleopPeriodic() {
    Shift = Drive.driveStick.getRawButton(5); 
    double leftDrive = -Drive.getTurnPower();
    double rightDrive =  Drive.getForwardPower();   
    Climber.Climb();
    ClawControl.GetEncoder();
    CurrentPool.currentPool();
    Ballmech.ballMechTeleop();
    Drive.arcadeDrive(Drive.getTurnPower(), Drive.getForwardPower(), Shift);
    //CurrentPool.currentPool();
    //Ballmech.ballMechTeleop();

    if(Drive.driveStick.getRawButton(4) == true){
      Pixy.lineFollow();
    }

    if (Drive.driveStick.getRawButton(1) == true){
      Vision.vision();
    } else {
      Drive.arcadeDrive((Drive.getTurnPower()), Drive.getForwardPower(),false);
    }
  }

   //System.out.println("R1: " + (Drive.R1.getEncoder().getPosition() / 10.75));
   //System.out.println("L1: " + (Drive.L1.getEncoder().getPosition() / 10.75));
    
    //recording mode
    // if (Drive.driveStick.getRawButton(4) == true){
    //   changesInAngle = Drive.getNavxAngle() - startingAngle;
    //   changesInRotations = (Drive.getRightPosition() + Drive.getLeftPosition() /2) - startingRotations;
    //   //find changes in angles and rotations
    //   //changeAngle = currentAngle - startingAngle
    //   //changeDistance = currentRotations - startingRotations
    // } else if(Drive.driveStick.getRawButtonReleased(4)){
    //   i++;
    //   changeAngle[i] = changesInAngle;
    //   changeRotations[i] = changesInRotations;
    //   System.out.println("Angle Changes: " + changeAngle);
    //   System.out.println("Rotation Chnages: " + changeRotations);
    //   //put changes into the array 
    // } else if(Drive.driveStick.getRawButton(4) == false){
    //   //keep finding starting positions and angles
    //   startingAngle = Drive.getNavxAngle();
    //   startingRotations = (Drive.getRightPosition() + Drive.getLeftPosition() /2);
    // }
      
}

