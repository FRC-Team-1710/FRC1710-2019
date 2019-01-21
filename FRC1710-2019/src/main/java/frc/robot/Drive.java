/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;


public class Drive {

    
    public static CANSparkMax R1,R2, L1, L2;
    public static Joystick driveStick = new Joystick(0);
    //driveStick = new Joystick(0);

    public static double getTurnPower() {
		return -driveStick.getRawAxis(4);
	}
	public static double getForwardPower() {
		 
        return driveStick.getRawAxis(1);
    }

    
    
    
    

    public static void initializeDrive(){
      
     L1 = new CANSparkMax(1, MotorType.kBrushless); //init the motors
     L2 = new CANSparkMax(2, MotorType.kBrushless);
    R1 = new CANSparkMax(3, MotorType.kBrushless); // init the motors
     R2 = new CANSparkMax(4, MotorType.kBrushless);
     R2.follow(R1);
     L2.follow(L1);
    }
     
  
    
   
   
   public static void arcadeDrive(double side, double forward){
        //if(shift ==true){
           

            //high gear
            //setShifters(true);
            R1.set(side - forward);
            L1.set(side + forward);
            
       
        
        // }
        }
       
}



