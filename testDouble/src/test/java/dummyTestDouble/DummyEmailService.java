package dummyTestDouble;

import com.testDouble.dummyTestDouble.EmailService;

public class DummyEmailService implements EmailService{

	@Override
	public void senEmail(String message) {
		throw new AssertionError("Method not implemented");
		
	}

}
