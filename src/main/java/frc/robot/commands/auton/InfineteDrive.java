// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import com.stuypulse.stuylib.input.Gamepad;
import com.stuypulse.stuylib.math.SLMath;
import com.stuypulse.stuylib.streams.IStream;
import com.stuypulse.stuylib.streams.filters.LowPassFilter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Settings;
import frc.robot.subsystems.Drivetrain;

public class InfineteDrive extends CommandBase {
  /** Creates a new InfineteDrive. */
  private Drivetrain drivetrain;
  public InfineteDrive(Drivetrain drivetrain) {
    this.drivetrain= drivetrain;
    addRequirements(drivetrain);
  }
      
    // Use addRequirements() here to declare subsystem dependencies.

  public InfineteDrive() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.tankDrive(0.25, 0.25); 

    System.out.println(Settings.Drivetrain.TIME_MOVING.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.tankDrive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
