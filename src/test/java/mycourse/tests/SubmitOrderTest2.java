package mycourse.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mycourse.TestComponents.BaseTest;
import mycourse.pageObjmodel.CartPage;
import mycourse.pageObjmodel.CheckOutPage;
import mycourse.pageObjmodel.OrderPage;
import mycourse.pageObjmodel.ProductCatalog;
import mycourse.pageObjmodel.confirmationPage;

public class SubmitOrderTest2 extends BaseTest{
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		//ProductCatalog addProduct = login.loginApplication(email, password);
		ProductCatalog addProduct = login.loginApplication(input.get("email"), input.get("password"));
		@SuppressWarnings("unused")
		List<WebElement> products = addProduct.getPrdocutList();
		//addProduct.addProductToCart(productName);
		addProduct.addProductToCart(input.get("productName"));
		
		CartPage cartPage = login.goTocartPage();
		//Boolean match = cartPage.VerifyProductDisplay(productName);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		confirmationPage cnfmpage = checkOut.submitOrder();
		String confirmMsg = cnfmpage.getMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test (dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalog addProduct = login.loginApplication("aarthin31@gmail.com", "P@ssw0rd");
		OrderPage ordersPage = addProduct.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	//How this method should get executed only when the result test is failure
	//How to attach the Screenshot to HTML report
	//Next lecture - Extent Reports
//	public String getScreenShot(String testCaseName) throws IOException
//	{
//		TakesScreenshot tS = (TakesScreenshot)driver;
//		File source = tS.getScreenshotAs(OutputType.FILE);
//		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".jpg");
//		FileUtils.copyFile(source, file);
//		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".jpg";
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//return new Object[][] {{"aarthin31@gmail.com", "P@ssw0rd", "ADIDAS ORIGINAL"},{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}};
		/*HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("email", "aarthin31@gmail.com");
		map1.put("password", "P@ssw0rd");
		map1.put("productName","ADIDAS ORIGINAL");
		
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("email", "anshika@gmail.com");
		map2.put("password", "Iamking@000");
		map2.put("productName", "ZARA COAT 3");*/
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\mycourse\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
