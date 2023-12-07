import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class StepImplementation extends Hook {


    public WebElement findElement(String locatorType, String locatorValue) {
        try {
            switch (locatorType.toLowerCase()) {
                case "id":
                    return appiumDriver.findElement(By.id(locatorValue));
                case "xpath":
                    return appiumDriver.findElement(By.xpath(locatorValue));
                case "css":
                    return appiumDriver.findElement(By.cssSelector(locatorValue));
                // Farklı locator tipleri bu alana eklenebilir..
                default:
                    throw new IllegalArgumentException("Desteklenmeyen locator tipi: " + locatorType);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    @Step("<locatorType> tipindeki <locatorValue> locatorlı <locatorName> ismindeki elementi sayfada kontrol et")
    public void checkElement(String locatorType, String locatorValue, String locatorName) {
        WebElement element = findElement(locatorType, locatorValue);
        Assertions.assertTrue(element != null, "Element bulunamadi: " + locatorType + " = " + locatorValue);
        logger.info(locatorName + " Elementi sayfada goruntulendi. ");
    }

    @Step("<locatorType> tipindeki <locatorValue> locatorlı <locatorName> ismindeki elemente tıkla")
    public void clickWithElement(String locatorType, String locatorValue, String locatorName) {
        findElement(locatorType, locatorValue).click();
        logger.info(locatorName + " Elemente tiklandi.");
    }

    @Step("<locatorType> tipindeki <locatorValue> locatorlı <locatorName> ismindeki elemente <text> degerini yaz")
    public void sendkeyWithElement(String locatorType, String locatorValue, String locatorName, String text) {
        findElement(locatorType, locatorValue).sendKeys(text);
        logger.info(locatorName + " Elemente " + text + " degeri yazildi. ");
    }

    @Step("Rastgele 10 haneli email adresi oluştur ve <locatorType> tipindeki <locatorValue> locatorlı <locatorName> alanına yaz")
    public void generateRandomEmail(String locatorType, String locatorValue, String locatorName) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder email = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            int index = rnd.nextInt(allowedChars.length());
            email.append(allowedChars.charAt(index));
        }

        email.append("@gmail.com");

        String emailString = email.toString();
        logger.info("Oluşturulan rastgele email: " + emailString);

        WebElement element = findElement(locatorType, locatorValue);
        element.sendKeys(emailString);
        logger.info(locatorName + " alanına rastgele email adresi yazıldı: " + emailString);
    }

        @Step("<locatorValue> değeri olan <LocatorName> isimli alanda rastgele seçim yapılır")
            public void selectRandomItem(String locatorValue, String locatorName){
             List<MobileElement> availableElements = appiumDriver.findElements(By.xpath(locatorValue));
            Random random = new Random();
            if (!availableElements.isEmpty()) {
                WebElement selectedDay = availableElements.get(random.nextInt(availableElements.size()));
                selectedDay.click();
                logger.info("Rastgele öge seçimi yapıldı");
            } else {
                logger.info("Seçilebilir öge bulunamadı.");
            }
        }
    }



