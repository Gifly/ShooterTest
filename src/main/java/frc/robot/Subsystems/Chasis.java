package frc.robot.Subsystems;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.lib6647.loops.ILooper;
import org.usfirst.lib6647.loops.Loop;
import org.usfirst.lib6647.loops.LoopType;
import org.usfirst.lib6647.oi.JController;
import org.usfirst.lib6647.subsystem.SuperSubsystem;
import org.usfirst.lib6647.subsystem.supercomponents.SuperAHRS;
import org.usfirst.lib6647.subsystem.supercomponents.SuperTalon;
import org.usfirst.lib6647.subsystem.supercomponents.SuperVictor;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

public class Chasis extends SuperSubsystem implements SuperAHRS, SuperTalon, SuperVictor {
    public Chasis() {
        super("chasis");

        initTalons(robotMap, getName());
        initVictors(robotMap, getName());
        initAHRS(robotMap, getName());

        getVictor("backLeft").follow(getTalon("frontLeft"));
        getVictor("backRight").follow(getTalon("frontRight"));
    }

    @Override
    public void registerLoops(ILooper looper) {
        looper.register(new Loop() {
            private DifferentialDrive drive;
            private JController joystick;
            private AHRS navx;

            @Override
            public void onFirstStart(double timestamp) {
                drive = new DifferentialDrive(getTalon("frontLeft"), getTalon("frontRight"));
                joystick = Robot.getInstance().getJoystick("driver1");
                navx = getAHRS("navx");
            }

            @Override
            public void onStart(double timestamp) {
                System.out.println("Loop started at: " + timestamp + "!");
            }

            @Override
            public void onLoop(double timestamp) {
                drive.tankDrive(joystick.getLeftAxis(), joystick.getRightAxis(), false);

            }

            @Override
            public void onStop(double timestamp) {
                System.out.println("Loop stopped at: " + timestamp + ".");
            }

            @Override
            public LoopType getType() {
                return LoopType.TELEOP;
            }
        }, new Loop() {

            @Override
            public void onFirstStart(double timestamp) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStart(double timestamp) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLoop(double timestamp) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStop(double timestamp) {
                // TODO Auto-generated method stub

            }

            @Override
            public LoopType getType() {
                return LoopType.AUTO;
            }
        });
    }
}