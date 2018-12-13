package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.HashMap;


import commandGroups.Testing;

import commandGroups.diagnostics.DriveDiagnostic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	static HashMap<String, CommandGroup> autoMap = new HashMap<String, CommandGroup>();
	
	/**
	 * initializes the hashmap that stores every auto. The key is formatted as follows:
	 * cube amount, starting position, destination, switch position, scale position
	 */
	public static void initAutoMap() {
		
		
		
		///autoMap.put("223RR", new LeftStartRightScaleRightSwitch());
		autoMap.put("000RR", new Testing());
		autoMap.put("000LR", new Testing());
		autoMap.put("000RL", new Testing());
		autoMap.put("000LL", new Testing());
	}
	
	public static CommandGroup getAuto(char switchPos, char scalePos, int cubeAmount, int destination, int startPosition) {
		StringBuilder key = new StringBuilder();
		key.append(cubeAmount);
		key.append(startPosition);
		key.append(destination);
		key.append(switchPos);
		key.append(scalePos);
		
		if(autoMap.containsKey(key.toString())) {
			SmartDashboard.putString("key generated", key.toString());
			SmartDashboard.putString("auto that ran", autoMap.get(key.toString()).getName());
			return autoMap.get(key.toString());
		} else {
			return Constants.defaultAuto;
		}
	}
	
}
