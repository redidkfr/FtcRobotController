package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//use @Disabled so that this program does not show up on the DS
@TeleOp
public class HelloFTC extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Hello","World");
    }

    @Override
    public void loop() {

    }
}
