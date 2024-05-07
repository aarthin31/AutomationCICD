package mycourse.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import mycourse.TestComponents.BaseTest;
import mycourse.pageObjmodel.CartPage;
import mycourse.pageObjmodel.CheckOutPage;
import mycourse.pageObjmodel.OrderPage;
import mycourse.pageObjmodel.ProductCatalog;
import mycourse.pageObjmodel.confirmationPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";

	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		
		ProductCatalog addProduct = login.loginApplication("aarthin31@gmail.com", "P@ssw0rd");
		@SuppressWarnings("unused")
		List<WebElement> products = addProduct.getPrdocutList();
		addProduct.addProductToCart(productName);
		
		CartPage cartPage = login.goTocartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
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
	
}
