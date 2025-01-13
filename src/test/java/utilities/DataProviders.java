package utilities;
//This class is only provided for dataprovider annotations for entire project

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
	    String path = "/Users/spandanakn/eclipse-workspace/OpenCartV121/testData/OpenCart_LoginData.xlsx"; // taking xl file from testData

	    ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = xlutil.getCellCount("Sheet1", 1);

	    String logindata[][] = new String[totalrows][totalcols]; // created for two dimension array which can store

	    for(int i=1; i<=totalrows; i++) // 1 (index 0 is header so we are ignoring 0th index)// read the data from xl storing in two dimensional array
	    {
	        for(int j=0; j<totalcols; j++) // 0 // i is rows j is col
	        {
	            logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
	        }
	    }

	    return logindata; // returning two dimension array
	}

}
