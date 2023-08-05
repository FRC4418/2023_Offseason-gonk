package frc.robot.constants;


/** This file contains the different ports of motors, solenoids and sensors */
public interface Ports {

    public interface Gamepad {
        int DRIVER = 0;
        int OPERATOR = 1;
    }

    public interface Drivetrain {
        // Motors
        int LEFT_FRONT = 3;
        int LEFT_BACK = 2;

        int RIGHT_FRONT = 4;
        int RIGHT_BACK = 5;
    }

    public interface Shooter {
        // Motors
        int TOP = 22;
    }

    public interface Intake{
        // Motors
        int INTAKE = 12;
        int UPPER_INTAKE = 21;
        int SPIN_ROLLERS = 11;
    }


    public interface Climber{
        // Motors
        int WINCH = 31;
        int RATCHET = 4;
    }

    
}
