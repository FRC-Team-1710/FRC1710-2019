/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/



package frc.robot;



import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMaxLowLevel;



import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {

  public static DifferentialDrive m_myRobot;

  public static Joystick m_leftStick;
  private Joystick m_rightStick;

  public static CANSparkMax m_leftMotor;
  public static CANSparkMax m_leftMotorTwo; //this one will follow m_leftMotor

  public static CANSparkMax m_rightMotor;
  public static CANSparkMax m_rightMotorTwo; //this one will follow m_rightMotor


  @Override

  public void robotInit() {

  /**

   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object

   * 

   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the

   * first parameter

   * 

   * The motor type is passed as the second parameter. Motor type can either be:

   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless

   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed

   * 

   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change

   * these parameters to match your setup

   */

    m_leftMotor = new CANSparkMax(1, MotorType.kBrushless); //init the motors
    m_leftMotorTwo = new CANSparkMax(2, MotorType.kBrushless);

    m_rightMotor = new CANSparkMax(3, MotorType.kBrushless); // init the motors
    m_rightMotorTwo = new CANSparkMax(4, MotorType.kBrushless);
    
    m_leftMotorTwo.follow(m_leftMotor); //have the infearior motor follow their master
    m_rightMotorTwo.follow(m_rightMotor);


    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor); // tank drive XD


    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);

  }



  @Override

  public void teleopPeriodic() {

    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());

  }

}