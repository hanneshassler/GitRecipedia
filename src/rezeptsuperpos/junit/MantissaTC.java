package rezeptsuperpos.junit;

import junit.framework.TestCase;
import rezeptsuperpos.Mantissa;



public class MantissaTC extends TestCase{

	 /*
	 String testVal="2.415248915553237";
     testVal="0.015248915553237";
     testVal="0.15248915553237";
     testVal="0.1524";
     testVal="2.415248915553237";
     testVal="241524.8915553237";   
     testVal="0.07";
     System.out.println(shorten(testVal,5));
     
     double testValDouble=2.415248915553237;
     System.out.println(shorten(testValDouble,5));
     */
	
	public void             test_Mantissa1() {		   
		   String testVal="2.415248915553237";
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("2.4152"));
	}
	public void             test_Mantissa2() {		   
		   String testVal="0.015248915553237";		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("0.015248"));
	}
	
	public void             test_Mantissa3() {		   
		   String testVal="0.15248915553237";		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("0.15248"));
	}
	
	public void             test_Mantissa4() {		   
		   String testVal="0.1524";		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("0.1524"));
	}
	
	public void             test_Mantissa5() {		   
		   String testVal="241524.8915553237"; 		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("241524.8"));
	}
	
	public void             test_Mantissa6() {		   
		   String testVal="0.07";		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("0.07"));
	}
	
	public void             test_Mantissa7() {		   
		   double testVal=2.415248915553237;		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("2.4152"));
	}
	
	public void             test_Mantissa8() {		   
		   double testVal=0.015248915553237;		   
		   assertTrue(Mantissa.shorten(testVal,5).equalsIgnoreCase("0.015248"));
	}

}
