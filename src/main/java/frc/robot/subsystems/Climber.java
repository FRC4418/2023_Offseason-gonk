// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Ports;
import frc.robot.constants.Settings;

public class Climber extends SubsystemBase {
  Servo ratchet = new Servo(Ports.Climber.RATCHET);
  double setPin;
  final WPI_TalonFX winch = new WPI_TalonFX(Ports.Climber.WINCH);

  public Climber() {
    winch.configFactoryDefault();
		winch.setInverted(false);
  }
  public void ratchetRelease() {
    double rAngle = Settings.Climber.RATCHET_RELEASE_ANGLE.get();
     ratchet.setAngle(-30);
  }
  
  public void ratchetEngage() {
    setPin = Settings.Climber.RATCHET_ENGAGE_ANGLE.get();
    ratchet.setAngle(30);
  }

  public void armsUp() {
    winch.set(ControlMode.PercentOutput, Settings.Climber.WINCH_POWER.get());
  }

  public void armsDown() {
    winch.set(ControlMode.PercentOutput, -Settings.Climber.WINCH_POWER.get());
  }
  
  public void ending() {
    winch.set(ControlMode.PercentOutput, 0.0);
    ratchet.setAngle(30);
  }
  

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public static void climbServos() {
  }
}
