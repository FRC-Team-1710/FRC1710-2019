package frc.robot;
public class Ballmech {

    
    //Identifies the buttons to be used to intake and outtake the ball
    public static double ballintake(){
        return Drive.drivestick.getRawAxes(2);
    }

    public static double ballouttake(){
        return Drive.drivestick.getRawAxes(3);
    } {

    //Executes code if the ballintake trigger is actively being pushed
    if (ballintake==true) {
        Robot.CANSparkMAX(5).percentoutput(.5);
        Robot.CANSparkMAX(6).percentoutput(-.5);
    }

    //Executes code if the ballouttake trigger is actively being pushed
    if (ballouttake==true) {
        Robot.CANSparkMAX(5).percentoutput(-.5);
        Robot.CANSparkMAX(6).percentoutput(.5);
    }


}
}