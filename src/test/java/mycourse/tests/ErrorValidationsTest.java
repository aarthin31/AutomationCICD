package mycourse.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import mycourse.TestComponents.BaseTest;
import mycourse.pageObjmodel.CartPage;
import mycourse.pageObjmodel.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	String productName = "ADIDAS ORIGINAL";
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
		@SuppressWarnings("unused")
		ProductCatalog addProduct = login.loginApplication("aarthin301@gmail.com", "P@ssword");
		
		Assert.assertEquals("Incorrect email or password.",login.getErrorMsg());
	}
	
	@Test(retryAnalyzer=mycourse.TestComponents.Retry.class)
	public void ProductErrorValidation()
	{
		ProductCatalog addProduct = login.loginApplication("aarthin31@gmail.com", "P@ssw0rd");
		@SuppressWarnings("unused")
		List<WebElement> products = addProduct.getPrdocutList();
		addProduct.addProductToCart(productName);
//		login.goTocartPage();
//        CartPage cartPage = new CartPage(driver);
		CartPage cartPage = login.goTocartPage();
		Boolean match = cartPage.VerifyProductDisplay("ADIDASS ORIGINAL");
		Assert.assertFalse(match);
	}

}
