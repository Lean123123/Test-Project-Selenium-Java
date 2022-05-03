package uk.co.automationtesting;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;

@Listeners(resources.Listeners.class)

public class AddRemoveItemBasketTest extends Hooks {

    public AddRemoveItemBasketTest() throws IOException {
        super();
    }


    @Test
    public void addRemoveItem() throws InterruptedException, IOException {

        ExtentManager.log("Starting AddRemoveItemBasketTest...");
        HomePage home = new HomePage();
        home.getTestStoreLink().click();

        ShopHomePage shopHome = new ShopHomePage();
        ExtentManager.pass("Reached shop homepage...");
        shopHome.getProdOne().click();

        ShopProductPage shopProd = new ShopProductPage();
        ExtentManager.pass("Reached shop product page");
        Select option = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("M");
        ExtentManager.pass("Succesfully selected product syze...");
        shopProd.getQuantIncrease().click();
        shopProd.getAddToCartBtn().click();
        ExtentManager.pass("Succesfully add product to basket...");

        ShopContentPanel cPanel = new ShopContentPanel();
        cPanel.getContinueShopBtn().click();
        shopProd.getHomepageLink().click();
        shopHome.getProdTwo().click();
        shopProd.getAddToCartBtn().click();
        cPanel.getCheckoutBtn().click();

        ShoppingCart cart = new ShoppingCart();
        cart.getDeleteItemTwo().click();

        waitForElementInvisible(cart.getDeleteItemTwo(),Duration.ofSeconds(10));

        System.out.println(cart.getTotalAmount().getText());
        try {
            Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
            ExtentManager.pass("The total amount matches the expected amount");
        } catch (AssertionError e) {
            Assert.fail("Cart did not match amount " + cart.getTotalAmount().getText() + "expected $45.23");
            ExtentManager.fail("The total amount did not match the expected");
        }
    }

}
