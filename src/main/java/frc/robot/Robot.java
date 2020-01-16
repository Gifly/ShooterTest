/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.usfirst.lib6647.oi.JController;
import org.usfirst.lib6647.wpilib.LooperRobot;

import frc.robot.Subsystems.Chasis;
import frc.robot.Subsystems.Shooter;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends LooperRobot {
  /** Static {@link Robot} instance. */
  private static Robot instance;

  /**
   * Gets running {@link Robot} instance.
   * 
   * @return instance
   */
  public static Robot getInstance() {
    return instance;
  }

  protected Robot() {
    super(Chasis::new, Shooter::new);
    instance = this;
  }

  @Override
  public void initJoysticks() {
    joysticks.put("driver1", new JController(0));
  }
}
