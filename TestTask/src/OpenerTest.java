import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class OpenerTest extends Opener {

	String[][] targets = new String[2][2];
	ArrayList<JSONObject>[] empl_array = new ArrayList[2];
	ArrayList<JSONObject> expected = new ArrayList();
	
	@Before
	public void setUp() throws Exception
	{
		targets[0][1] = "CompanyA";
		targets[1][1] = "CompanyB";
		
		JSONObject employee = new JSONObject();
		employee.put("firstName", "Joel");
		
		ArrayList<JSONObject> buffer0 = new ArrayList();
		buffer0.add(employee);
		empl_array[0] = buffer0;
		
		ArrayList<JSONObject> buffer1 = new ArrayList();
		empl_array[1] = buffer1;
		
		expected.add(employee);

	}

	@Test
	public final void testMoveEmployee() 
	{
		moveEmployee(targets, empl_array, "CompanyA","CompanyB", "Joel");
		Assert.assertEquals(empl_array[1], expected);
		//Assert.assertEquals(empl_array[1], empl_array[0]); Проверка на случай, если нужно убедиться, что элемент был удалён из исходного списка
	}

}
