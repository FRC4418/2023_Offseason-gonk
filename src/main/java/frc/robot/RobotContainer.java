// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.stuypulse.stuylib.input.gamepads.AutoGamepad;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.commands.ConveryorIdle;
import frc.robot.commands.DrivetrainDrive;

import frc.robot.commands.IntakeUp;
import frc.robot.commands.ShooterEject;
import frc.robot.commands.shooterShoot;
import frc.robot.commands.climberDown;
import frc.robot.commands.climberUp;
import frc.robot.commands.engageRatchet;
import frc.robot.commands.IntakeLower;
import frc.robot.constants.Ports;
import frc.robot.constants.Settings;
import frc.robot.constants.Ports.Gamepad;
import frc.robot.commands.auton.realAutoCode;
import frc.robot.commands.auton.InfineteDrive;
import frc.robot.commands.auton.driveDistanceMeters;
import frc.robot.commands.blankAuto;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;


import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;




//import com.kauailabs.navx.frc;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here... 
  private final Drivetrain drivetrain = new Drivetrain();
  private final Shooter shooter = new Shooter();
  private final Intake intake = new Intake();
  private final Climber climber = new Climber();
  //private final realAutoCode realAutoCode = new realAutoCode(drivetrain, 2.2);
  private final driveDistanceMeters driveDistanceMeters = new driveDistanceMeters(drivetrain, 10.0);
  private final ConveryorIdle ConveryorIdle = new ConveryorIdle(shooter);
  public final AutoGamepad driver = new AutoGamepad(Ports.Gamepad.DRIVER);
  private final DrivetrainDrive DrivetrainDrive = new DrivetrainDrive(drivetrain, driver);
  public final blankAuto blankAuto = new blankAuto();

  public final AHRS ahrs = new AHRS();

  private static SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureDefaultCommands();
    configureButtonBindings();
    configureAutons();
    
  }


  private void configureDefaultCommands() {
    drivetrain.setDefaultCommand(new DrivetrainDrive(drivetrain, driver));
    climber.setDefaultCommand(new engageRatchet(climber));
    shooter.setDefaultCommand(new ConveryorIdle(shooter));
    intake.setDefaultCommand(new IntakeUp(intake));
  }


  private void configureButtonBindings() {
    // driver.getDPadUp().whenHeld(new climberUp(climber));
    // driver.getDPadDown().whenHeld(new climberDown(climber));
    
    // driver.getTopButton().onTrue(new IntakeLower(intake));
    // driver.getLeftButton().onTrue(new shooterShoot(shooter, intake));
    // driver.getRightButton().onTrue(new ShooterEject(shooter, intake));
    driver.getDPadUp().whileTrue(new IntakeLower(intake));
    driver.getDPadLeft().whileTrue(new shooterShoot(shooter, intake));
    driver.getDPadRight().whileTrue(new ShooterEject(shooter, intake));
  }


  // Add commands to the autonomous command chooser
  public void configureAutons() {
    //autoChooser.addOption("RealAutoCode", new realAutoCode(drivetrain, Settings.Drivetrain.TIME_MOVING.get()));
    autoChooser.setDefaultOption("Do Nothing", new blankAuto());
    autoChooser.addOption("Drive for N meters", new driveDistanceMeters(drivetrain, Settings.Drivetrain.DISPLACEMENT_METERS.get()));

    SmartDashboard.putData("Autonomous", autoChooser);
  }


  public Command getAutonomousCommand() {
    // return autoChooser.getSelected();
    return new InstantCommand();
  }
}