package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.Utility.PID;

public class IntakeControl {
    public static long start = System.currentTimeMillis();
    public static long finish = System.currentTimeMillis();
    public static long TimeElapsed = (finish - start);
    
    public static double error;
    public static double output;
    public static double ticksToLine = 650;
    public static final double P = .01;
    public static final double I = .002;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;

    public boolean isConflicting;

    public  void IntakeTransfer(){
        double current = ((Drive.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 10; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Drive.pickup1.set(ControlMode.PercentOutput, output);
        Drive.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = true;
        }
    }

    public void IntakeHatch(){
        double current = ((Drive.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 20; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Drive.pickup1.set(ControlMode.PercentOutput, output);
        Drive.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = false;
            
        }
    }

    public void IntakeBall(){
        double current = ((Drive.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 30; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Drive.pickup1.set(ControlMode.PercentOutput, output);
        Drive.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = false;
            
        }
    }

    public void IntakeRest(){
        double current = ((Drive.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 40; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Drive.pickup1.set(ControlMode.PercentOutput, output);
        Drive.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = true;
            
        }
    }
    public boolean isConflicting(){
        if(isConflicting == true){
            return true;
        } else {
            return false;
        }
    }
}