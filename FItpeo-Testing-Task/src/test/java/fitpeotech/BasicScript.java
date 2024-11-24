package fitpeotech;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicScript {

    public static void main(String[] args) throws Throwable {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.fitpeo.com/");
        driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
        WebElement targetElement1 = driver.findElement(By.xpath("//h3[normalize-space()='$0.00']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement1);
        
        WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));
        WebElement valueDisplay = driver.findElement(By.xpath("//input[@type='number']")); 

        Actions action = new Actions(driver);

        // Target value
        int targetValue = 820;
        int currentValue = Integer.parseInt(valueDisplay.getAttribute("value")); 

        // Adjust the slider dynamically until it reaches close to the target value
        while (Math.abs(currentValue - targetValue) > 5) { // Tolerance of 5 to stop the loop once it's close to 820
            if (currentValue < targetValue) {
                // Move right if current value is less than the target
                action.dragAndDropBy(slider, 5, 0).perform();
            } else if (currentValue > targetValue) {
                // Move left if current value is greater than the target
                action.dragAndDropBy(slider, -5, 0).perform();
            }

            //Thread.sleep(200);

            // Retrieve the updated value
            currentValue = Integer.parseInt(valueDisplay.getAttribute("value"));
            //System.out.println("Current Slider Value: " + currentValue);
        }

        // Once the slider is near the target value (within 5 units),then it print the final value
        System.out.println("Slider is near the target value: " + currentValue);
        Point location1 = slider.getLocation();
    	System.out.println("Location of Slider(X:Co-ordinate) at 820 : "+ location1.getX());
        System.out.println("Text box value: " + valueDisplay.getAttribute("value"));
        
        Thread.sleep(2000);
        valueDisplay.click(); 
    	valueDisplay.sendKeys(Keys.CONTROL+ "a");
    	valueDisplay.sendKeys(Keys.BACK_SPACE);
    	valueDisplay.sendKeys("560");
    	Point location2 = slider.getLocation();
    	System.out.println("Location of Slider(X:Co-ordinate) at 560 : "+ location2.getX());
    	System.out.println("Text box value: " + valueDisplay.getAttribute("value"));

        
        WebElement targetElement2 = driver.findElement(By.xpath("//p[contains(text(),'CPT-99091')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement2);
        
        driver.findElement(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3'][1]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
      
        WebElement targetElement3 = driver.findElement(By.xpath(" //p[contains(text(),'CPT-99474')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement3);
        
        driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'MuiTypography-root MuiTypography-body2 inter css-1xroguk')][4]")));
        String expectedAmount = "$110700";
        String actualAmount = amountElement.getText();
        if (!actualAmount.equals(expectedAmount)) {
            System.err.println("Reimbursement validation failed!");
            System.err.println("Expected: " + expectedAmount + ", but got: " + actualAmount);
        } else {
            System.out.println("Reimbursement validation passed. Total: " + actualAmount);
        }
        
        Thread.sleep(5000);
        
        driver.quit();
    }
}
