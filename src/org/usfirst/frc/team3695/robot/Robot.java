
package org.usfirst.frc.team3695.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3695.robot.auto.CommandGroupAuto;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;

/** the magic place where everything happens */
public class Robot extends IterativeRobot {

	// choosers
		SendableChooser<Autonomous> autoChooser = new SendableChooser<>();
		// add choosers as needed, these put drop down options in the smart dash
		
		
	// subsystems
		public static final SubsystemDrive SUB_DRIVE = new SubsystemDrive();
		// add subsystems as needed

		
	// autonomous
		private CommandGroupAuto auto;
		
		
	/** runs when robot is turned on */
	public void robotInit() {
		new OI();
		// autoChooser initialization
		autoChooser.addDefault(Autonomous.NOTHING.toString(), Autonomous.NOTHING);
		for(int i = 1; i < Autonomous.values().length; i++) {
			autoChooser.addObject(Autonomous.values()[i].toString(), Autonomous.values()[i]); }
		SmartDashboard.putData("Auto Mode", autoChooser); }

	
	/** runs when robot gets disabled */
	public void disabledInit() { }

	
	/** like autonomous periodic, but for disabled mode */
	public void disabledPeriodic() {
		Scheduler.getInstance().run(); }

	
	/** runs when autonomous start */
	public void autonomousInit() {
		if(autoChooser.getSelected() != null) {
			auto = new CommandGroupAuto(autoChooser.getSelected());
			auto.start(); } }

	
	/** repetitive loop that runs at 50hz from autonomousInit to teleopInit */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); }

	
	/** runs when teleop starts*/
	public void teleopInit() {
		if (auto != null)
			auto.cancel(); }

	
	/** like autonomous periodic, but for teleop mode */
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); }

	
	/** like autonomous periodic, but for test mode*/
	public void testPeriodic() {
		LiveWindow.run(); }
}
