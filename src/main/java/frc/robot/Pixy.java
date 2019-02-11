/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.nio.charset.StandardCharsets;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Pixy {
    static I2C arduino;
    static byte[] data = new byte[1];

    public static void init(){
        try {
            arduino = new I2C(Port.kOnboard, 2);
            System.out.println(arduino);
        } catch (Exception e) {
            System.out.print("fail");
            System.out.println(e);
        }
    }
    public static void lineFollow(){
        arduino.read(2, 1, data);
        String readArduino = new String(data, StandardCharsets.UTF_8);
        System.out.println(readArduino);
        SmartDashboard.putString("read the arduino", readArduino);
        //System.out.println(readArduino);
        if (readArduino == "f") {
           Drive.arcadeDrive(0, .2, false);
        } else if (readArduino == "r") {
           Drive.arcadeDrive(.02, 0, false);
        } else {
           Drive.arcadeDrive(-.02, 0, false);
        }
    }
}
