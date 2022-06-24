package tests.practice;

import org.apache.logging.log4j.core.config.Order;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GittiGidiyorPage;
import utilities.ConfigReader;
import utilities.Driver;
import java.util.Random;

public class StudentPractice_Log4J {
    //sitede bilgisayar kelimesini aratin
    //cıkan sonuclarda 2. sayfaya giderek random bir urun seciniz,
    //fiyatını yazdırınız ve  sepete ekleyiniz ,
    //cıkan Alisveris Sepetim de sepete git deyiniz..
    //secilen urun ile sepettekini karsilastirniz
    //secilen urun mikatarini arttiriniz
    //secilen urunu sepetten silip, sepetin bos oldugunu dogrulayiniz..

    //link : https://logging.apache.org/log4j/2.x/manual/configuration.html


    GittiGidiyorPage gittiGidiyorPage=new GittiGidiyorPage();
    Actions actions=new Actions(Driver.getDriver());
    String fiyat;

    @Test (priority=1)
    public void bilgisayar_arat() {
        Driver.getDriver().get(ConfigReader.getProperty("GittiGidiyorUrl"));
        gittiGidiyorPage.aramaKutusu.sendKeys("bilgisayar"+ Keys.ENTER);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().toString().contains("bilgisayar"));
    }

    @Test (priority=2)
    public void ikinci_sayfaya_git_kontrol_et() throws InterruptedException {
        gittiGidiyorPage.cookies.click();
        actions.moveToElement(gittiGidiyorPage.ikincisayfa).perform();
        gittiGidiyorPage.ikincisayfa.click();
        Thread.sleep(1000);
        Assert.assertTrue( Driver.getDriver().getCurrentUrl().toString().contains("2"),"ikinci sayfaya gidemedi");
    }

    @Test (priority=3)
    public void rastgele_urun_sec_sepete_ekle() throws InterruptedException {
        Random rnd = new Random();
        int sayi = rnd.nextInt(gittiGidiyorPage.urunlerList.size());
        gittiGidiyorPage.urunlerList.get(sayi+1).click();
        gittiGidiyorPage.cookies.click();
        fiyat=gittiGidiyorPage.urunfiyat.getText();
        gittiGidiyorPage.sepeteEkle.click();
        Thread.sleep(1000);
        Assert.assertTrue(gittiGidiyorPage.sepetegit.isDisplayed(),"sepete eklemedi");
    }

    @Test  (priority=4)
    public void secilen_urun_fiyat_ile_sepettekini_karsilastir() {
        gittiGidiyorPage.sepetegit.click();
        String sepettekiFiyati=gittiGidiyorPage.sepettekiFiyat.getText();
        Assert.assertEquals(fiyat,sepettekiFiyati);
    }

    @Test (priority=5)
    public void secilen_urun_miktar_arttir() {
        gittiGidiyorPage.urunadedi.click();
        Assert.assertTrue(gittiGidiyorPage.urunadedi.getText().contains("2"),"ürün adedi 2 tane dğil");
    }

    @Test (priority=6)
    public void urun_sepetten_silinerek_sepeti_bos_oldugunu_kontrol_edilir() throws InterruptedException {
        gittiGidiyorPage.urundelete.click();
        Thread.sleep(1000);
        Assert.assertTrue(gittiGidiyorPage.bosSepet.isDisplayed(),"ürün silinemedi");
    }
}
