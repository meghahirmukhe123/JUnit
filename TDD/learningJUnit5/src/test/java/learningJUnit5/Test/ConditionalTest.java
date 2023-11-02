package learningJUnit5.Test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

//this class is to learn conditional tests
public class ConditionalTest {
	
	@Test
	@Disabled
	void basicTest()
	{
		System.out.println("just a basic test");
	}
	
	
	
	@Test
	@EnabledOnOs(OS.WINDOWS)  //this condition shows following test will run for windows operating system
	void basicTestForWindowsOperatingSystem()
	{
		System.out.println("@EnabledOnOs(OS.WINDOWS)  //this condition shows following test will run for windows operating system");
	}
	
	
	
	@Test
	@EnabledOnOs(OS.MAC)  //this condition shows following test will run for MAC operating system
	void basicTestForMacOperatingSystem()
	{
		System.out.println("@EnabledOnOs(OS.MAC)  //this condition shows following test will run for MAC operating system");
	}
	
	
	@Test
	@EnabledOnOs({OS.MAC, OS.WINDOWS})  //this condition shows following test will run for MAC and windows operating system
	void basicTestForMacAndWindowsOperatingSystem()
	{
		System.out.println("@EnabledOnOs({OS.MAC, OS.WINDOWS})  //this condition shows following test will run for MAC and windows operating system");
	}

}
