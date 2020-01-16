package frc.robot.Subsystems;

import org.usfirst.lib6647.loops.ILooper;
import org.usfirst.lib6647.loops.Loop;
import org.usfirst.lib6647.loops.LoopType;
import org.usfirst.lib6647.oi.JController;
import org.usfirst.lib6647.subsystem.SuperSubsystem;
import org.usfirst.lib6647.subsystem.supercomponents.SuperTalon;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class Shooter extends SuperSubsystem implements SuperTalon {
    private JController joystick;
    private double speed = 0.2;

    public Shooter() {
        super("shooter");

        initTalons(robotMap, getName());

        joystick = Robot.getInstance().getJoystick("driver1");

        InstantCommand stopMotor = new InstantCommand(setSpeed(0));

        joystick.get("A").whileHeld(new InstantCommand(setSpeed(0.2))).whenReleased(stopMotor);
        joystick.get("B").whileHeld(new InstantCommand(setSpeed(0.5))).whenReleased(stopMotor);
        joystick.get("Y").whileHeld(new InstantCommand(setSpeed(0.8))).whenReleased(stopMotor);
    }

    private Runnable setSpeed(double speed) {
        return () -> this.speed = speed;
    }

    @Override
    public void registerLoops(ILooper looper) {
        looper.register(new Loop() {

            @Override
            public void onFirstStart(double timestamp) {
            }

            @Override
            public void onStart(double timestamp) {
                System.out.println("Shooter Loop started at: " + timestamp + "!");
            }

            @Override
            public void onLoop(double timestamp) {
                getTalon("shooterMotor").set(speed);
            }

            @Override
            public void onStop(double timestamp) {
                System.out.println("Loop stopped at: " + timestamp + ".");
            }

            @Override
            public LoopType getType() {
                return LoopType.TELEOP;
            }
        });
    }
}