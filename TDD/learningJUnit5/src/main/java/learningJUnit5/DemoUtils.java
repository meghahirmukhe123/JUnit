package learningJUnit5;

import java.util.List;

public class DemoUtils {
	
	
	//method of adding
	public int add(int a,int b)
	{
		return a+b;
	}
	
	//method of checking string is null or not
	public String checkNull(String str)
	{
		if(str != null)
		{
			return str;
		}
		return null;
	}
	
	
	//methods to check string are same or not
	String str= "hello";
	String str2= "world";
	
	public String getStr()
	{
		return str;
	}
	
	public String getStr2()
	{
		return str2;
	}
	
	
	//method to return boolean
	public boolean checkNum()
	{
		
		if(5>3)
		{
			return true;
		}
		return false;
	}
	
	
	//lets learn assertIterable
	List<Character> list = List.of('a','b','c');
	public List<Character> getListOfCharacter()
	{
		return list;
	}
	
			
			
	

}
