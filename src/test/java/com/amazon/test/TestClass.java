package com.amazon.test;

import com.amazon.PageObjectModel.ReusableMethods;
import com.amazon.PageObjectModel.TestCaseMethods;
import com.amazon.base.BaseBrowser;
import com.amazon.utils.LogUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testListeners.Listeners.class)
public class TestClass extends BaseBrowser {


    @Test(priority = 1, groups = {"Add_To_Cart"})
    public void validateAddProductIntoCatBySearching() {

        testCaseId = "1";
        testCase = extentReports.createTest("validate Select Product and Add Into Cart");
        LogUtils.printSteps("Open the amazon browser, Select product added into cart");
        String expected = "Apple iPhone 15";
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.searchProductSearchBox(expected);
        ReusableMethods.switchWin();
        Boolean isDisplay = testCaseMethods.addProductIntoAddToCart();
        LogUtils.printSteps("validate Select Product and Add Into Cart");
        Assert.assertTrue(isDisplay, "Product should be added into Add to cart.");
        Assert.assertTrue(testCaseMethods.validateAndNavigateToCartPage().contains(expected), "Product name should equal to add to cart product name");
    }

    @Test(priority = 2, groups = {"Add_To_Cart"})
    public void validateProductAlreadyAddedToAddToCartViewIsDisplayed() {

        testCaseId = "2";
        testCase = extentReports.createTest("Validate product already added to add to cart view is displayed");
        LogUtils.printSteps("Open the amazon browser, Select specific section and added product into cart");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.selectSpecificProduct("Today's Deals");
        testCaseMethods.selectRandomProductMobileSection();
        Boolean isDisplayed = testCaseMethods.addProductIntoCart();
        LogUtils.printSteps("Validate product already added to add to cart view is displayed");
        Assert.assertTrue(isDisplayed, "Product should be added into Add to cart.");
    }

    @Test(priority = 3, groups = {"Add_To_Cart"})
    public void validateTotalAmountSelectedProductIsAccurateCartPage() {

        testCaseId = "3";
        testCase = extentReports.createTest("Validate total amount selected product is accurate cart page");
        LogUtils.printSteps("Open the amazon browser, Add two product in a add to cart");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.searchProductSearchBox("Apple iPhone 15");
        ReusableMethods.switchWin();
        Integer firstProductPrice = testCaseMethods.addProductIntoCartAndGetPrice();
        testCaseMethods.validateAndNavigateToCartPage();
        testCaseMethods.selectProductInAddToCartPage();
        Integer secondProductPrice = testCaseMethods.addProductIntoCartAndGetPrice();
        testCaseMethods.validateAndNavigateToCartPage();
        LogUtils.printSteps("Validate total amount selected product is accurate cart page");
        Assert.assertTrue(firstProductPrice + secondProductPrice == testCaseMethods.getPriceFromShoppingCart(), "Product should be added into Add to cart.");
    }

    @Test(priority = 4, groups = {"Add_To_Cart"})
    public void validateAddToCartPageAfterRemovingProduct() {

        testCaseId = "8";
        testCase = extentReports.createTest("Validate remove product from cart page");
        LogUtils.printSteps("Open the amazon browser, Add product into cart and remove the product from add to cart page");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.searchProductSearchBox("Apple iPhone 15");
        ReusableMethods.switchWin();
        testCaseMethods.addProductIntoAddToCart();
        testCaseMethods.validateAndNavigateToCartPage();
        testCaseMethods.removeProductInShoppingCart();
        LogUtils.printSteps("Validate cart page is empty");
        Assert.assertTrue(testCaseMethods.isCartEmpty(), "Cart page should be empty");
    }

    @Test(priority = 5)
    public void validateLanguageChangesFunctionality() {

        testCaseId = "4";
        testCase = extentReports.createTest("Validate language changes functionality");
        LogUtils.printSteps("Open the amazon browser, Select language");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.selectChangeLanguage("தமிழ்");
        boolean languageChangesDisplayed = testCaseMethods.isLanguageChangesDisplayed();
        LogUtils.printSteps("Validate the language changes display");
        Assert.assertTrue(languageChangesDisplayed, "Language changes should be reflected");
    }

    @Test(priority = 6)
    public void validateBackToTopAndAmazonLogoFunctionality() {

        testCaseId = "6";
        testCase = extentReports.createTest("Validate back to top amazon logo functionality");
        LogUtils.printSteps("Open the amazon browser, Select product and scroll down bottom of the page");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.searchProductSearchBox("Samsung Galaxy S24 plus");
        ReusableMethods.switchWin();
        LogUtils.printSteps("Validate Back to top and amazon logo functionality");
        Assert.assertTrue(testCaseMethods.isBacKToTopButtonDisplayed(), "Back to top button should be display");
        testCaseMethods.clickBackToTopButton();
        Assert.assertTrue(testCaseMethods.clickTitleLogoVerifyHomePage(), "Title logo should be navigate to home page");
    }

    @Test(priority = 7)
    public void validateLocationFieldIsUpdated() {

        testCaseId = "7";
        testCase = extentReports.createTest("Validate location field is updated");
        LogUtils.printSteps("Open the amazon browser, Update pincode");
        String pinCode = "636807";
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        boolean location = testCaseMethods.updateLocation(pinCode);
        LogUtils.printSteps("Validate location field is updated");
        Assert.assertTrue(location, "Mis-Match with actual and expected address");
    }

    @Test(dataProviderClass = TestCaseMethods.class, dataProvider = "userCredential", priority = 8)
    public void validateEmailField(String email, String phone) {

        testCaseId = "5";
        testCase = extentReports.createTest("Validate email field with invalidate credential");
        LogUtils.printSteps("Open the amazon browser, Navigate to signin page and enter invalidate email and phone number");
        TestCaseMethods testCaseMethods = new TestCaseMethods();
        testCaseMethods.refreshPage();
        testCaseMethods.navigateToSignInPage();
        testCaseMethods.enterEmailOrMobile(email);
        LogUtils.printSteps("Validate error message for invalid email or phone");
        Assert.assertTrue(testCaseMethods.getInvalidErrorMessage().equals("We cannot find an account with that email address"), "Error message should be displayed");
        testCaseMethods.enterEmailOrMobile(phone);
        Assert.assertTrue(testCaseMethods.getInvalidErrorMessage().equals("We cannot find an account with that mobile number"), "Error message should be displayed");
    }
}
