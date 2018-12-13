package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.Constants;


import commands.DriveToPosition;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MasterDiagnostic extends CommandGroup {

    public MasterDiagnostic() {
    	
    	addSequential(new DriveToPosition(15,.5,false,0,false,false));
    }
}
