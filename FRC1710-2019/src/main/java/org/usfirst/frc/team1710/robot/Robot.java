package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	public static int cubeAmount, startingPosition, destination;
	boolean wristReset;
	public static Timer autoTime;
	
	@Override
	public void robotInit() {
		wristReset = false;
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
		RobotMap.compressor = new Compressor(0);
		
		RobotMap.pdp = new PowerDistributionPanel();
		
		RobotMap.navx.reset();
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		//RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		
		SmartDashboard.putNumber("cube amount", Constants.defaultCubeAmount);
		SmartDashboard.putNumber("Starting position", Constants.defaultStartPos);
		SmartDashboard.putNumber("destination",Constants.defaultDestination);
		AutoHandler.initAutoMap();
		autoTime = new Timer();
	}

	@Override 
	public void autonomousInit() {
		Constants.inAuto = true;
		AutoHandler.initAutoMap();
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		SubsystemManager.masterReset();
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		
		RobotMap.R1.configClosedloopRamp(.5, 0);
		RobotMap.L1.configClosedloopRamp(.5, 0);
		
		char switchPos = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		char scalePos = DriverStation.getInstance().getGameSpecificMessage().charAt(1);
		
		startingPosition = (int) SmartDashboard.getNumber("Starting position", Constants.defaultStartPos);
		destination = (int) SmartDashboard.getNumber("destination", Constants.defaultDestination);
		cubeAmount = (int) SmartDashboard.getNumber("cube amount", Constants.defaultCubeAmount);
		
		CommandGroup autoMode = AutoHandler.getAuto(switchPos, scalePos, cubeAmount,
				destination,startingPosition);
		autoTime.start();
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Robot Heading", RobotMap.navx.getAngle());
		
		Scheduler.getInstance().run();
		RobotMap.compressor.setClosedLoopControl(false);
	}

	@Override
	public void teleopInit() {
		RobotMap.R1.configClosedloopRamp(0, 0);
		RobotMap.L1.configClosedloopRamp(0, 0);
		Constants.inAuto = false;
		
		//RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.compressor.setClosedLoopControl(false);
		
	}
	
	@Override
	public void teleopPeriodic() {
		Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
			
		
		if(RobotMap.mechStick.getRawButton(2) == true) {
			RobotMap.compressor.setClosedLoopControl(true);
		} else {
			RobotMap.compressor.setClosedLoopControl(false);
		}
			
		SmartDashboard.putNumber("Left Drive", Drive.getLeftPosition());
		SmartDashboard.putNumber("Right Drive", Drive.getRightPosition());
		//SmartDashboard.putNumber("Robot Velocity", Drive.getLeftVelocity());
		SmartDashboard.putNumber("Robot Heading", Drive.getNavxAngle());
		SmartDashboard.putNumber("Controller Pov", RobotMap.driveStick.getPOV());
		SmartDashboard.putNumber("Robot Velocity", Drive.getFBVelocity());
		SmartDashboard.putNumber("Robot Velocity", Drive.getLRVelocity());
		Drive.autoShift(Drive.getLeftVelocity(), Constants.shiftLowThreshold, Constants.shiftHighThreshold);

	
	}
	
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		
		Vision.ledEntry.forceSetNumber(1);
	}
	
	@Override
	public void disabledPeriodic() {
	}
}
