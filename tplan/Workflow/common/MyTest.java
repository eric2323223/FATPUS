package tplan.Workflow.common;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import com.tplan.robot.ApplicationSupport;
import com.tplan.robot.AutomatedRunnable;
import com.tplan.robot.scripting.DefaultJavaTestScript;
import com.tplan.robot.scripting.JavaTestScript;

public class MyTest extends DefaultJavaTestScript{
	
//	public void test() {
//	      try {
////	         mouseMove(new Point(714, 442), "700");
////	         mouseMove(new Point(714, 441), "900");
//	         mouseClick(new Point(714, 441), "11400");
//	      } catch (IOException ex) {
//	         ex.printStackTrace();
//	      }
//
//	   }

	   public static void main(String args[]) {
	      MyTest test = new MyTest();
	      ApplicationSupport robot = new ApplicationSupport();
	      String[] parameter = new String[]{"--nodisplay"};
	      AutomatedRunnable t = robot.createAutomatedRunnable(test, "javatest", parameter, System.out, false);
	      new Thread(t).start();
	   }

	   public void test() {
		      try {
		    	  connect("java://localhost");

//		          mouseMove(new Point(304, 142), "6400");
//		          mouseRightClick(new Point(861, 812), -1, "6800");
		    	  waitForMatch(new File[]{new File("c:\\MyCatch.png")}, "search", "1s");
		    	  Point point = new Point(getVariableAsInt("_SEARCH_X")+20, getVariableAsInt("_SEARCH_Y")+20);
		    	  System.out.println(point.toString());
		    	  //    	  mouseMove(point);
		    	  mouseClick(point);
//		    	  mouseRightClick(new Point(1000, 1000), -1, "6800");
//		          type("hello");
		          
		          
//		    	  connect("10.35.180.238", "test");
		    	  
//		    	  waitForMatch(new File[]{new File("c:\\home.png")},"search", "5s");
//		    	  waitForMatchEdge(new File[]{new File("c:\\home.png")}, "5s");
//		    	  waitForMatchEdge(new File[]{new File("c:\\login_button_android.png")});
//		    	  Point point = new Point(getVariableAsInt("_SEARCH_X")+10, getVariableAsInt("_SEARCH_Y")+10);
//		    	  mouseClick(point);
		    	  disconnect();
		    	  System.out.println("finished...");
//		         type("Hello world!");
		      } catch (IOException ex) {
		         ex.printStackTrace();
		      }
		   }


}
