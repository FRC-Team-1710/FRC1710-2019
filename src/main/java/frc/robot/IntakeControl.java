package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.Utility.PID;

public class IntakeControl {
    public static long start = System.currentTimeMillis();
    public static long finish = System.currentTimeMillis();
    public static long TimeElapsed = (finish - start);
    
    public static double error = 0;
    public static double output = 0;
    public static double ticksToLine = 650;
    public static final double P = .01;
    public static final double I = .002;
    public static final double REVERSEPOWER = .2;
    public static final double TIMERDELAY = 1;

    public static void IntakeTransfer(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 10; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
    }
    public static void IntakeHatch(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 20; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
    }
    public static void IntakeBall(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 30; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
    }
    public static void IntakeRest(){
        double current = ((Constants.pickup1.getSelectedSensorPosition())/ (2*ticksToLine))* 360;
        double goal = 40; //change accordingly.
        error = goal - current;
        output = PID.PID(error, P, I, 0, TimeElapsed);
        Constants.pickup1.set(ControlMode.PercentOutput, output);
        Constants.pickup2.set(ControlMode.PercentOutput, -output);
    }
}