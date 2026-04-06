import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GmailTest {

    @Test
    public void deleteUnreadEmailTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 1: Open Gmail
        driver.get("https://mail.google.com/mail/");

        // Step 2: Manual login (recommended)
        System.out.println("Please login manually within 60 seconds...");
        Thread.sleep(60000);

        // Step 3: Find unread emails
        List<WebElement> unreadEmails = driver.findElements(By.cssSelector("tr.zE"));

        if (unreadEmails.size() == 0) {
            System.out.println("No unread emails found. Test skipped.");
            driver.quit();
            return;
        }

        // Step 4: Get last unread email
        WebElement lastUnread = unreadEmails.get(unreadEmails.size() - 1);

        String subject = lastUnread.findElement(By.cssSelector(".bog")).getText();
        System.out.println("Deleting email: " + subject);

        // Step 5: Click checkbox of that email
        WebElement checkbox = lastUnread.findElement(By.cssSelector("div[role='checkbox']"));
        checkbox.click();

        Thread.sleep(2000);

        // Step 6: Click delete button
        driver.findElement(By.cssSelector("div[aria-label='Delete']")).click();

        System.out.println("Email deleted.");

        Thread.sleep(3000);

        // Step 7: Click "More" (if Trash hidden)
        try {
            WebElement moreBtn = driver.findElement(By.xpath("//span[text()='More']"));
            moreBtn.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("More menu not needed.");
        }

        // Step 8: Click Trash
        try {
            WebElement trashBtn = driver.findElement(By.xpath("//a[contains(@href, '#trash')]"));
            trashBtn.click();
        } catch (Exception e) {
            System.out.println("Could not find Trash directly.");
        }

        Thread.sleep(5000);

        // Step 9: Verify email exists in Trash
        List<WebElement> trashEmails = driver.findElements(By.cssSelector(".bog"));

        boolean found = false;

        for (WebElement email : trashEmails) {
            String text = email.getText();
            if (text.contains(subject)) {   // use contains instead of equals
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("✅ TEST PASSED: Email found in Trash.");
        } else {
            System.out.println("❌ TEST FAILED: Email not found in Trash.");
        }

        driver.quit();
    }
}