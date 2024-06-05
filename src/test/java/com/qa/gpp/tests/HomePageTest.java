package com.qa.gpp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.gpp.baseTest.BaseTest;
import com.qa.gpp.pages.HomePage;

public class HomePageTest extends BaseTest {
	@Test
	public void homePageTitle() {
		String actPageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(actPageTitle, "みんなで知ろう、膿疱性乾癬(GPP)のこと。|GPPひろば");
		
	}
	@Test
	public void logoExist() {
		Assert.assertTrue(homePage.isLogoExist());
	}

	
}
