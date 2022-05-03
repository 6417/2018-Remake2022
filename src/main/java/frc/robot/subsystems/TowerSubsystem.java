package frc.robot.subsystems;

import java.util.concurrent.atomic.AtomicBoolean;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Counter.Mode;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class TowerSubsystem {
    
    TalonSRX motorHorizontal;
    TalonSRX motorVertical1;
    TalonSRX motorVertical2;
    TalonSRX motorGrabber1;
    TalonSRX motorGrabber2;

    Joystick joystick;

    JoystickButton verticalTrigger;
    JoystickButton grabberTrigger;

    AtomicBoolean verticalTriggerActive;
    AtomicBoolean grabberActive;
    boolean isactive;


    public TowerSubsystem() {

        motorHorizontal = new TalonSRX(Constants.MotorIDs.idHorizontal);        
        motorVertical1 = new TalonSRX(Constants.MotorIDs.idVertical1);   
        motorVertical2 = new TalonSRX(Constants.MotorIDs.idVertical2);        
        motorGrabber1 = new TalonSRX(Constants.MotorIDs.idGrabber1);        
        motorGrabber2 = new TalonSRX(Constants.MotorIDs.idGrabber2);

        isactive = false;

        motorHorizontal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 20);

        motorVertical2.follow(motorVertical1);
        motorGrabber2.setInverted(true);
        motorGrabber2.follow(motorGrabber1);
        
        joystick = new Joystick(3);

        verticalTrigger = new JoystickButton(joystick, Constants.JoystickButtonIDs.idVerticalTrigger);
        grabberTrigger = new JoystickButton(joystick, Constants.JoystickButtonIDs.idGrabberTrigger);
        

        verticalTriggerActive = new AtomicBoolean(false);
        grabberActive = new AtomicBoolean(false);
        
        verticalTrigger.whenPressed(() -> {motorVertical1.set(ControlMode.PercentOutput, 0.4);});
        grabberTrigger.whenPressed(() -> {grabberActive.set(true);});
        verticalTrigger.whenReleased(() -> {motorVertical1.set(ControlMode.PercentOutput, 0.1);});
        grabberTrigger.whenReleased(() -> {grabberActive.set(false);});
    }

    public void towerControl(){
        if (verticalTriggerActive.get()){
        
            if (isactive == true){
                verticalTriggerActive.set(false);
                isactive = false;
                System.out.println("Trigger has been turned false");
            } else{
                verticalTriggerActive.set(true);
                isactive = true;
                System.out.println("Trigger has been turned true");
            }
        } else{
            isactive = false;
        }
    }

    public void periodic(){
        double joystickYPos = joystick.getRawAxis(1);
        if (Math.abs(joystickYPos) < 0.1){
            joystickYPos = 0;
        }

        System.out.println(motorHorizontal.isRevLimitSwitchClosed());

        if (verticalTriggerActive.get()){
            motorVertical1.set(ControlMode.PercentOutput, joystickYPos);
        } else {
            motorHorizontal.set(ControlMode.PercentOutput, joystickYPos);
        }
    }
}