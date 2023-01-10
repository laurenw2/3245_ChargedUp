// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoCommandBalance;
import frc.robot.commands.AutoCommandNoBalance;
import frc.robot.commands.ManualArmCommand;
import frc.robot.commands.ManualElevatorCommand;
import frc.robot.commands.ManualLobterRunCommand;
import frc.robot.commands.ManualLobterStretchCommand;
import frc.robot.commands.ManualTiltCommand;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LobterRunSubsystem;
import frc.robot.subsystems.LobterStretchSubsystem;
import frc.robot.subsystems.TiltSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  //subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final ArmSubystem m_armSubsystem = new ArmSubystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final LobterRunSubsystem m_lobterRunSubsystem = new LobterRunSubsystem();
  private final LobterStretchSubsystem m_lobterStretchSubsystem = new LobterStretchSubsystem();
  private final TiltSubsystem m_tiltSubsystem = new TiltSubsystem();

  //commands
  //auto command
  private final AutoCommandBalance m_autonomousBalanceCommand = new AutoCommandBalance(m_driveTrain); 
  private final AutoCommandNoBalance m_autonomousNoBalanceCommand = new AutoCommandNoBalance(m_driveTrain); 

  //drive commands
  private final ArcadeDrive m_fastDrive = new ArcadeDrive(m_driveTrain, 1, driver);
  private final ArcadeDrive m_slowDrive = new ArcadeDrive(m_driveTrain, 0.3, driver);
  private final ArcadeDrive m_arcadeDefault = new ArcadeDrive(m_driveTrain, 0.8, driver);

  //arm commands
  private final ManualArmCommand m_armManualCommand = new ManualArmCommand(m_armSubsystem, operator);
  
  //elevator commands
  private final ManualElevatorCommand m_elevatorManualCommand = new ManualElevatorCommand(m_elevatorSubsystem, operator);

  //lobter commands
  private final ManualLobterRunCommand m_lobterRunManualCommand = new ManualLobterRunCommand(m_lobterRunSubsystem, operator);
  private final ManualLobterStretchCommand m_lobterStretchManualCommand = new ManualLobterStretchCommand(m_lobterStretchSubsystem, operator);

  //tilt commands
  private final ManualTiltCommand m_tiltManualCommand = new ManualTiltCommand(m_tiltSubsystem, operator);

  public RobotContainer() {
    m_driveTrain.setDefaultCommand(m_arcadeDefault);
    m_armSubsystem.setDefaultCommand(m_armManualCommand);
    m_elevatorSubsystem.setDefaultCommand(m_elevatorManualCommand);
    m_lobterRunSubsystem.setDefaultCommand(m_lobterRunManualCommand);
    m_lobterStretchSubsystem.setDefaultCommand(m_lobterStretchManualCommand);
    m_tiltSubsystem.setDefaultCommand(m_tiltManualCommand);
    configureButtonBindings();
    m_chooser.setDefaultOption("no balance", m_autonomousNoBalanceCommand);
    m_chooser.addOption("balance", m_autonomousBalanceCommand);
  }

  
  private void configureButtonBindings() {
    JoystickButton fastButton = new JoystickButton(driver, Constants.fastButton);
    JoystickButton slowButton = new JoystickButton(driver, Constants.slowButton);

    fastButton.whenPressed(m_fastDrive);
    slowButton.whenPressed(m_slowDrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
