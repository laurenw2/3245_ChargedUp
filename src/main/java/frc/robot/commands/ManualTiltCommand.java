package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TiltSubsystem;

public class ManualTiltCommand extends CommandBase{

    private final TiltSubsystem m_tiltubsystem;
    private XboxController m_controller;
    
    public ManualTiltCommand(TiltSubsystem tiltSubsystem, XboxController controller){
        m_tiltubsystem = tiltSubsystem;
        m_controller = controller;
        addRequirements(m_tiltubsystem);
    }

    @Override
    public void initialize() {
        //m_tiltubsystem.resetEncoder();
    }
        
    @Override
    public void execute() {
        m_tiltubsystem.moveManual(m_controller);
        
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }

}
