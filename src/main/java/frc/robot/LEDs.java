/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Add your docs here.
 */
// create a for loop
// every 50 loops
// initialize LED loop
public class LEDs {
    static char i = 'i'; //initially picked up hatch
    static char h = 'h'; //has been holding hatch
    static char l = 'l'; //limelight target
    static char n = 'n'; //no hatch
    static int intI = Character.getNumericValue(i);
    static int intH = Character.getNumericValue(h);
    static int intL = Character.getNumericValue(l);
    static int intN = Character.getNumericValue(n);

    public void LEDinidcator(){
        if (Sensors.haveHatchInRange()) {
            //hatch attatched
            Pixy.arduino.write(2, intI);
         //   if (Robot.clawOpen2.Value.kReverse){
                Pixy.arduino.write(2, intH);
            }
      //  } else if(Vision.foundTarget()){
            //found limelight target
            Pixy.arduino.write(2, intL);
       // } else {
            //no limelight target and no hatch attatched
            Pixy.arduino.write(2, intN);
        //}
    }    
}
