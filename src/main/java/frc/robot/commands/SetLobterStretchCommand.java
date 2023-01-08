package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LobterStretchSubsystem;

public class SetLobterStretchCommand extends CommandBase{

    private final LobterStretchSubsystem m_lobterStretchSubsystem;
    private final String m_size;
    
    public SetLobterStretchCommand(LobterStretchSubsystem lobterStretchSubsystem, String size){
        m_lobterStretchSubsystem = lobterStretchSubsystem;
        m_size = size;
        addRequirements(m_lobterStretchSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        if(m_size == "cone"){
            m_lobterStretchSubsystem.setCone();
        }
        if(m_size == "cube"){
            m_lobterStretchSubsystem.setCube();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}