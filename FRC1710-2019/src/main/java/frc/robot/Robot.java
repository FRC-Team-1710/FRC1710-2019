/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/



package frc.robot;



import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  


  @Override

  public void robotInit() {
    Drive.initializeDrive();
  

    

  }



  @Override

  public void teleopPeriodic() {
    //This makes the robot drive | Turn power is multiplied by .3 to make it slower and drive is by .5 to make is slower as well
   Drive.arcadeDrive(-Drive.getTurnPower() * .2, Drive.getForwardPower() * .35);

   currentPool.currentPool();

  }

}