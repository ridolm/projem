package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class InstagramPage {
    public InstagramPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameKutusuElement;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordKutusuElement;

    @FindBy(xpath = "//*[text()='Log In']")
    public WebElement loginButonuElement;

    @FindBy(xpath = "//*[text()='Save Your Login Info?']")
    public WebElement sifreKaydetYazisiElement;

    @FindBy(xpath = "//*[text()='Not Now']")
    public WebElement notNowButonuElement;

    @FindBy(xpath = "//*[text()='Turn on Notifications']")
    public WebElement turnOnNotificationYaziElement;

    @FindBy(xpath = "//div[@class='_aaav']")
    public WebElement homePageProfileDdMElement;

    @FindBy(xpath = "//*[text()='Profile']")
    public WebElement profileButonuElement;

    @FindBy(xpath = "//div[@class='_aa_m']")
    public WebElement profilIsmiElement;
}