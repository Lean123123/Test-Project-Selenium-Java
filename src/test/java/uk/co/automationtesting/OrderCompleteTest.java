package uk.co.automationtesting;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;

@Listeners(resources.Listeners.class)


public class OrderCompleteTest extends Hooks {

    public OrderCompleteTest() throws IOException {
        super();
    }

    @Test
    public void endToEndTest() throws IOException {

        ExtentManager.log("Starting OrderCompleteTest...");
        HomePage home = new HomePage();
        home.getTestStoreLink().click();
        ExtentManager.pass("Reached shop homepage...");


        ShopHomePage shopHome = new ShopHomePage();
        shopHome.getProdOne().click();
        ExtentManager.pass("Succes click product.");


        ShopProductPage shopProd = new ShopProductPage();
        ExtentManager.pass("Reached shop product page...");
        Select option = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("M");
        ExtentManager.pass("Succes increase size...");
        shopProd.getQuantIncrease().click();
        ExtentManager.pass("Succes increase quant ...");
        shopProd.getAddToCartBtn().click();
        ExtentManager.pass("Succes add item to cart");


        ShopContentPanel cPanel = new ShopContentPanel();
        cPanel.getCheckoutBtn().click();

        ShoppingCart cart = new ShoppingCart();
        ExtentManager.pass("Reached shopping cart page");

        cart.getHavePromo().click();
        ExtentManager.pass("Succes selected the promo botton");
        cart.getPromoTextbox().sendKeys("20OFF");
        cart.getPromoAddBtn().click();
        cart.getProceedCheckoutBtn().click();
        ExtentManager.pass("Succes selected checkout botton ");


        OrderFormPersInfo pInfo = new OrderFormPersInfo();
        pInfo.getGenderMr().click();
        pInfo.getFirstNameField().sendKeys("Juan");
        pInfo.getLastnameField().sendKeys("García");
        pInfo.getEmailField().sendKeys("juan@garcia.com");
        pInfo.getTermsConditionsCheckbox().click();
        pInfo.getContinueBtn().click();
        ExtentManager.pass("Succes enter customer data");


        OrderFormDelivery orderDelivery = new OrderFormDelivery();
        orderDelivery.getAddressField().sendKeys("Washington Street 2112");
        orderDelivery.getCityField().sendKeys("Houston");
        Select state = new Select(orderDelivery.getStateDropdown());
        state.selectByVisibleText("Texas");
        orderDelivery.getPostcodeField().sendKeys("07721");
        orderDelivery.getContinueBtn().click();
        ExtentManager.pass("Succes enter delivery info");

        OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
        shipMethod.getDeliveryMsgTextbox().sendKeys("If im not in, please leave on my porch");
        shipMethod.getContinueBtn().click();
        ExtentManager.pass("Succes selecting shipping method");


        OrderFormPayment payment = new OrderFormPayment();
        payment.getPayByCheckRadioBtn().click();
        payment.getTermsConditionsCheckbox().click();
        payment.getOrderBtn().click();
        ExtentManager.pass("Succes placed order");



    }
}
