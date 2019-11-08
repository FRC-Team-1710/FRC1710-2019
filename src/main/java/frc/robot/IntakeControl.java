package frc.robot; //adds a package for frc

import com.ctre.phoenix.motorcontrol.ControlMode; //adds control libraries
import frc.Utility.PID; //adds PID libraries

public class IntakeControl { //class init
    public static long start = System.currentTimeMillis(); //
    public static long finish = System.currentTimeMillis() + 360000;
    public static long TimeElapsed = (finish - start);
    
    
// if(TimeElapsed == 0) {sleep();}
//////////////////////////////////////////////////////////////////////////////WAAAAAAAAAAAAAAAAAAAAAAA

    public static double error;
    public static double output;
    public static double ticksToLine = 650;
    public static final double P = .01;
    public static final double I = .002;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;

    public boolean isConflicting;

    public  void IntakeTransfer(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 10; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = true;
        }
    }

    public void IntakeHatch(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 20; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = false;
        }
    }

    public void IntakeBall(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 30; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = false;
            
        }
    }

    public void IntakeRest(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 40; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
        if(error == 0){
            isConflicting = true;
            
        }
    }

    public boolean isConflicting(){
        if(isConflicting == true){
            return true;
        } else{
            return false;
        }
    }
}