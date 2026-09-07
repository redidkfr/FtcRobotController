package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.mainstuff;

@Autonomous
public class StatesAuton extends OpMode {
    mainstuff mechanism = new mainstuff();
    int state;

    @Override
    public void init(){
        mechanism.init(hardwareMap);
        state = 0;
    }

    @Override
    public void loop(){
        telemetry.addData("Current State",state);
        switch(state){
            case 0:
                telemetry.addLine("To exit state, press A");
                if(gamepad1.a){
                    state = 1;
                }
                break;
            case 1:
                telemetry.addLine("To exit state, press B");
                if(gamepad1.b){
                    state = 2;
                }
                break;
            case 2:
                telemetry.addLine("To exit state, press X");
                if(gamepad1.x){
                    state = 3;
                }
                break;
            default:
                telemetry.addLine("Auto State machine finished");
        }
    }
}
