package TDD;


//we have create this class so that we can apply FizzBuzz for number 1 to 100
public class MainApp {

	public static void main(String[] args)
	{
		for(int i=1;i<=100;i++)
		{
			//System.out.println(i+" , "+FizzBuzz.compute(i));
			
			//OR
			
			
			System.out.println(FizzBuzz.compute(i));
		}
		
	}
}
