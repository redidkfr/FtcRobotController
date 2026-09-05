package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.mainstuff;

@TeleOp
public class Gamepad extends OpMode {
    mainstuff hard = new mainstuff();

    @Override
    public void init() {
        hard.init(hardwareMap);

    }

    @Override
    public void loop() {
        //runs 50x a second
        telemetry.addData("x",gamepad1.left_stick_x);
        telemetry.addData("y",gamepad1.left_stick_y);
        telemetry.addData("a button",gamepad1.a);
        telemetry.addData("b button",gamepad1.b);
        hard.setMotorSpeed(0.5);
        telemetry.addData("Motor Revs",hard.getMotorRevs());
        telemetry.addData("Distance", hard.getDistance());
        if(hard.getDistance()<10){
            telemetry.addLine("Too Close");
        }
        telemetry.addData("Heading",hard.getHeading());


    }
}
