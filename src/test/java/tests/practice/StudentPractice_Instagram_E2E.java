package tests.practice;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.InstagramPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class StudentPractice_Instagram_E2E {
    //instagram anasayfasina giris yap
    //dogru username ve dogru password bilgileri gir
    //eger login butonu tiklanilabilir olursa tikla
    //"Save Your Login Info?" yazisinda "Not Now" butonuna tikla
    //"Turn on Notifications" yazisinda "Not Now" butonuna tikla
    //arama kutusunda Ronaldo yazisi icin cikan ilk sonuca tiklayalim
    //icinde bulundugumuz profil sahibine hazir mesaj gonderelim
    //mesaj gonderildigini fotografli sekilde dogrulayalim
    //sayfayi kapat

    @Test
    public void e2eTest() throws IOException {
        //instagram anasayfasina giris yap
        Driver.getDriver().get(ConfigReader.getProperty("instagramUrl"));

        //dogru username ve dogru password bilgileri gir
        InstagramPage instagramPage = new InstagramPage();
        instagramPage.usernameKutusuElement.sendKeys(ConfigReader.getProperty("instagramValidEmail"));
        instagramPage.passwordKutusuElement.sendKeys(ConfigReader.getProperty("instagramValidPassword"));

        //eger login butonu tiklanilabilir olursa tikla
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(instagramPage.loginButonuElement));

        instagramPage.loginButonuElement.click();

        //"Save Your Login Info?" yazisinda "Not Now" butonuna tikla
        instagramPage.notNowButonuElement.click();

        //"Turn on Notifications" yazisinda "Not Now" butonuna tikla
        instagramPage.notNowButonuElement.click();

        //arama kutusunda Ronaldo yazisi icin cikan ilk sonuca tiklayalim
        wait.until(ExpectedConditions.elementToBeClickable(instagramPage.aramaKutusuElement));
        instagramPage.aramaKutusuElement.sendKeys("Ronaldo");

        wait.until(ExpectedConditions.elementToBeClickable(instagramPage.aramaSonucIlkElement));
        instagramPage.aramaSonucIlkElement.click();

        //Icinde bulundugumuz profil sahibine hazir mesaj gonderelim
        wait.until(ExpectedConditions.elementToBeClickable(instagramPage.mesajButonuElement));
        instagramPage.mesajButonuElement.click();

        wait.until(ExpectedConditions.visibilityOf(instagramPage.mesajKutusuElement));
        Actions actions = new Actions(Driver.getDriver());
        actions.click(instagramPage.mesajKutusuElement).
                sendKeys(ConfigReader.getProperty("instagramHazirMesaj") + Keys.ENTER).perform();

        //Mesaj gonderildigini fotografli sekilde dogrulayalim
        File file = instagramPage.mesajCerceveElement.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("target/instagram/mesaj.jpeg"));

        //sayfayi kapat
        Driver.closeDriver();

    }
}
