package tests.practice;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InstagramPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentPractice_InstagramLogin extends TestBaseRapor {

    //instagram anasayfasina giris yap
    //dogru username ve dogru password bilgileri gir
    //eger login butonu tiklanilabilir olursa tikla
    //"Save Your Login Info?" yazisinda "Not Now" butonuna tikla
    //"Turn on Notifications" yazisinda "Not Now" butonuna tikla
    //giris yapildigini fotografli sekilde dogrula ve rapor belirt

    @DataProvider
    public static Object[][] validEmailPassword() {

        Object[][] emailPassword = {{"testngkontrol1@hotmail.com", "studentsession1"},
                {"batchselenium@hotmail.com", "studentsession2"}};

        return emailPassword;
    }

    @Test(dataProvider = "validEmailPassword")
    public void positiveLoginTest(String validEmail, String validPassword) throws IOException, InterruptedException {
        extentTest = extentReports.createTest("Positive Login Test", "Valid Email ve Password ile giris yapilabilmeli");

        //instagram anasayfasina giris yap
        Driver.getDriver().get(ConfigReader.getProperty("instagramUrl"));
        extentTest.info("Instagram anasayfaya gidildi");

        //dogru username ve dogru password bilgileri gir
        InstagramPage instagramPage = new InstagramPage();
        instagramPage.usernameKutusuElement.sendKeys(validEmail);
        extentTest.info("Valid email girildi");

        instagramPage.passwordKutusuElement.sendKeys(validPassword);
        extentTest.info("Valid password girildi");

        //eger login butonu tiklanilabilir olursa tikla
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(instagramPage.loginButonuElement));
        instagramPage.loginButonuElement.click();
        extentTest.info("Login butonu ulasilabilir oldugunda tiklandi");

        //"Save Your Login Info?" yazisinda "Not Now" butonuna tikla
        instagramPage.notNowButonuElement.click();
        extentTest.info("Sifre Kaydet teklifi icin Not Now butonuna tiklandi");

        //"Turn on Notifications" yazisinda "Not Now" butonuna tikla
        instagramPage.notNowButonuElement.click();
        extentTest.info("Bildiri izinleri icin Not Now butonuna tiklandi");

        //giris yapildigini fotografli sekilde dogrula ve rapor belirt
        instagramPage.homePageProfileDdMElement.click();
        extentTest.info("Profil DropDown basligina tiklandi");

        instagramPage.profileButonuElement.click();
        extentTest.info("Profil butonuna tiklandi");

        Thread.sleep(3000);

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        File file = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("target/instagram/screenshot" + date + ".jpeg"));
        extentTest.info("Giris yapildigini dogrulayan fotograf instagram dosyasina eklendi");

        Assert.assertTrue(instagramPage.profilIsmiElement.isDisplayed());
        extentTest.pass("Valid email ve password ile giris yapilabildigi dogrulandi");
    }
}