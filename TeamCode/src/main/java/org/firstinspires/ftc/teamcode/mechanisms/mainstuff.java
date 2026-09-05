package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class mainstuff {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksperRev;
    private DistanceSensor distance;
    private Servo servoPos;
    private CRServo servoRot;
    NormalizedColorSensor pollensensor;
    private IMU imu;

    public enum DetectedColor{
        RED,
        BLUE,
        YELLOW,
        UNKNOWN
    }
     public void init(HardwareMap hw){
         distance = hw.get(DistanceSensor.class, "distance_sensor");
         touchSensor = hw.get(DigitalChannel.class,"touch_sensor");
         touchSensor.setMode(DigitalChannel.Mode.INPUT);
         motor = hw.get(DcMotor.class,"motor1");
         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         ticksperRev = motor.getMotorType().getTicksPerRev();
         motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // brakes upon stop
         servoPos = hw.get(Servo.class, "servo_pos");
         servoRot = hw.get(CRServo.class,"servo_rot");
         pollensensor = hw.get(NormalizedColorSensor.class, "pollen_sensor");
         pollensensor.setGain(7);
         imu = hw.get(IMU.class,"imu");
         RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                 RevHubOrientationOnRobot.LogoFacingDirection.UP,
                 RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
         );

         imu.initialize(new IMU.Parameters(RevOrientation));
     }


     public void setMotorSpeed(double speed){
         // values = -1.0 to 1.0
         motor.setPower(speed);
     }
     public boolean getTouchSensorState(){
         return touchSensor.getState();
     }

     public double getMotorRevs(){
         return motor.getCurrentPosition()/ticksperRev;// normalizing ticks to amount of revolutions done
     }
     public double getDistance(){
         return distance.getDistance(DistanceUnit.CM);
     }

     public void setServoPos(double angle){
         servoPos.setPosition(angle);
     }
     public void setServoRot(double speed){
         servoRot.setPower(speed);
     }

     public DetectedColor getDetectedColor(Telemetry telemetry){
         NormalizedRGBA colors = pollensensor.getNormalizedColors(); //return 4 values
         // to account for discrepancies in alpha values
         float normRed, normGreen, normBlue;
         normRed = colors.red/colors.alpha;
         normGreen = colors.green/colors.alpha;
         normBlue = colors.blue/colors.alpha;

         telemetry.addData("red",normRed);
         telemetry.addData("green",normGreen);
         telemetry.addData("blue",normBlue);

         return DetectedColor.UNKNOWN;
     }
     public double getHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
     }
}
