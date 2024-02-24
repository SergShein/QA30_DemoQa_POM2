package com.demoqa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }

    public void clickWithJS(WebElement element,int x,int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void typeWithJs(WebElement element, String text,int x, int y) {
        if (text != null) {
            clickWithJS(element,x,y);
            element.clear();
            element.sendKeys(text);
        }
    }
    public boolean shouldHaveText(WebElement element, String text, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    @FindBy(id = "adplus-anchor")
    WebElement adplusAnchor;
    public void hideAd(){
        if (adplusAnchor.isDisplayed()){
            js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
            js.executeScript("document.querySelector('footer').style.display='none';");

        }
    }
}

