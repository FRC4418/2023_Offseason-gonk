// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.constants.Settings;
import frc.robot.subsystems.Intake;

public class shooterShoot extends CommandBase {
  private Shooter shooter;
  private Intake intake;

  public shooterShoot(Shooter shooter, Intake intake) {
    this.shooter = shooter;
    this.intake = intake;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Settings.Shooter.SHOOT_SPEED.get()
    Shooter.setVelocity(Settings.Shooter.SHOOT_SPEED.get()*10000.0);
    Intake.upIntake(Settings.Intake.UPPER_SHOOT_SPEED.get());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.setVelocity(0.0);
    Intake.upIntake(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
