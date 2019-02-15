/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import sun.awt.SunToolkit.InfiniteLoop;

/**
 * Add your docs here.
 */
// create a for loop
// every 50 loops
// initialize LED loop
public class LEDs {
    static char t = 't'; //true 
    static char f = 'f'; //false
    static char l = 'l'; //limelight target
    static int intT = Character.getNumericValue(t);
    static int intF = Character.getNumericValue(f);
    static int intL = Character.getNumericValue(l);
    public void LEDinidcator(){
        for (int i=0; i <= 10000; i++){
            if ((i % 50) == 0){
                if (Constants.limitSwitch.get()) {
                    //hatch attatched
                    Pixy.arduino.write(2, intT);
                } else if(Vision.foundTarget()){
                       //found limelight target
                       Pixy.arduino.write(2, intL);
                } else {
                    //no limelight target and no hatch attatched
                    Pixy.arduino.write(2, intF);
                }
            }
        }
    }
        
}
