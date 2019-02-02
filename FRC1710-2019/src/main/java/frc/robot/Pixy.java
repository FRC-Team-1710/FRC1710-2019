/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Add your docs here.
 */
public class Pixy {
    static I2C arduino;
    static ByteBuffer data;

    protected void init(){
        I2C arduino = new I2C(Port.kOnboard , 2);
    }

    public static void lineFollow(){
        arduino.read(2, 3, data);
        System.out.println(data.toString());
        // if (data.toString() == "frw") {
        //     Drive.L1.set(.2);
        //     Drive.L2.follow(Drive.L1);
        //     Drive.R1.follow(Drive.L1);
        //     Drive.R2.follow(Drive.L1);
        // } else if (data.toString() == "r") {
        //     Drive.L1.set(.4);
        //     Drive.L2.follow(Drive.L1);
        //     Drive.R1.set(-.4);
        //     Drive.R2.follow(Drive.R1);
        // } else {
        //     Drive.L1.set(-.4);
        //     Drive.L2.follow(Drive.L1);
        //     Drive.R1.set(.4);
        //     Drive.R2.follow(Drive.R1);
        // }
    } 
    //public static boolean 
}
