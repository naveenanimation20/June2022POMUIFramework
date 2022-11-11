package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(enabled = false)
	public void productHeaderTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProdHeader = productInfoPage.getProductHeader("MacBook Pro");
		Assert.assertEquals(actProdHeader, "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" , AppConstants.MACBOOK_PRO_IMAGES_COUNT},
				{ "Macbook", "MacBook Air" , AppConstants.MACBOOK_AIR_IMAGES_COUNT},
				{ "iMac", "iMac" , AppConstants.IMAC_IMAGES_COUNT},
				};
	}

	@Test(dataProvider = "getProductInfoData", enabled = false)
	public void productImagesCountTest(String searchKey, String mainProductName, int ImagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("actual product images : " + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	
	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		softAssert.assertEquals(actMetaDataMap.get("Brand"), "Apple11");
		softAssert.assertEquals(actMetaDataMap.get("Product Code"), "Product 1811");
		softAssert.assertEquals(actMetaDataMap.get("Reward Points"), "80011");
		softAssert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
		softAssert.assertAll();
	}
}
