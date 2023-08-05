// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Settings;
import frc.robot.subsystems.Drivetrain;


public class distanceDrive extends CommandBase {
  /** Creates a new distanceDrive. */
  private Drivetrain drivetrain;
  private double AvgDistance;
  private double distanceDriveMeters;

  public distanceDrive(Drivetrain drivetrain, Number distanceDriveMeters) {
    this.drivetrain = drivetrain;
    AvgDistance = 0.0;
    // addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    AvgDistance = drivetrain.getAverageDistance()*Settings.Drivetrain.Encoders.ENCODER_DISTANCE_PER_PULSE;
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (distanceDriveMeters < AvgDistance){
      return true;
    }
    else {
      return false;
    }
  }
}
