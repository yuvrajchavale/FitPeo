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

public class AdvancedScript {

    public static void main(String[] args) {
        WebDriver driver = setupDriver();

        try {
            navigateToRevenueCalculator(driver);
            adjustSlider(driver, 816);
            validateSliderValue(driver, 816);
            updateTextField(driver, 560);
            validateSliderValue(driver, 560);
            selectCPTCodes(driver);
            validateReimbursement(driver, "$110700");
        } catch (Exception e) {
            System.out.println("Test execution failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    //Setup WebDriver and browser configurations.
     
    public static WebDriver setupDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    
     // Navigate to the Revenue Calculator page.
     
    public static void navigateToRevenueCalculator(WebDriver driver) {
        driver.get("https://www.fitpeo.com/");
        WebElement revenueCalculatorLink = driver.findElement(By.xpath("//div[text()='Revenue Calculator']"));
        revenueCalculatorLink.click();
        System.out.println("Navigated to Revenue Calculator page.");
    }
   
    //Adjust the slider to the target value.
     
    public static void adjustSlider(WebDriver driver, int targetValue) {
        WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));
        WebElement valueDisplay = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement targetElement1 = driver.findElement(By.xpath("//h3[normalize-space()='$0.00']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement1);
        

        Actions action = new Actions(driver);
        int currentValue = Integer.parseInt(valueDisplay.getAttribute("value"));

        while (Math.abs(currentValue - targetValue) > 5) {
            if (currentValue < targetValue) {
                action.dragAndDropBy(slider, 5, 0).perform();
            } else {
                action.dragAndDropBy(slider, -5, 0).perform();
            }
            currentValue = Integer.parseInt(valueDisplay.getAttribute("value"));
        }

        System.out.println("Slider adjusted to near target value: " + currentValue);
        Point location1 = slider.getLocation();
    	System.out.println("Location of Slider(X:Co-ordinate) at 816 : "+ location1.getX());
    }

    
     //Validate the slider's current value.
    
     public static void validateSliderValue(WebDriver driver, int expectedValue) {
        WebElement valueDisplay = driver.findElement(By.xpath("//input[@type='number']"));
        int actualValue = Integer.parseInt(valueDisplay.getAttribute("value"));
        if (actualValue == expectedValue) {
            System.out.println("Validation successful. Slider textbox value: " + actualValue);
        } else {
            throw new AssertionError("Slider value validation failed. Expected: " + expectedValue + ", Actual: " + actualValue);
        }
    }

    
    // Update the text field and validate the slider position.
     
    public static void updateTextField(WebDriver driver, int newValue) {
        WebElement valueDisplay = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));
        valueDisplay.click();
        valueDisplay.sendKeys(Keys.CONTROL + "a");
        valueDisplay.sendKeys(Keys.BACK_SPACE);
        valueDisplay.sendKeys(String.valueOf(newValue));
        
        System.out.println("Text field updated to value: " + newValue);
        Point location2 = slider.getLocation();
    	System.out.println("Location of Slider(X:Co-ordinate) at 560 : "+ location2.getX());
    }

    
     //Select specified CPT codes.
     
    public static void selectCPTCodes(WebDriver driver) {
        scrollToElement(driver, By.xpath("//p[contains(text(),'CPT-99091')]"));

        driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
        
        
        WebElement targetElement3 = driver.findElement(By.xpath(" //p[contains(text(),'CPT-99474')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement3);
        
        driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();

        System.out.println("CPT codes selected successfully.");
    }

    
    // Validate the Total Recurring Reimbursement.
     
    public static void validateReimbursement(WebDriver driver, String expectedAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        // Wait for the reimbursement header to be visible
        WebElement reimbursementHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class,'MuiTypography-root MuiTypography-body2 inter css-1xroguk')][4]")));
        
        // Get the actual reimbursement amount
        String actualAmount = reimbursementHeader.getText().trim();
        System.out.println("Expected Amount: " + expectedAmount);
        System.out.println("Actual Amount: " + actualAmount);
        
        // Compare actual and expected values
        if (!actualAmount.equals(expectedAmount)) {
            System.err.println("Reimbursement validation failed!");
            System.err.println("Expected: " + expectedAmount + ", but got: " + actualAmount);
        } else {
            System.out.println("Reimbursement validation passed. Total: " + actualAmount);
        }
    }

    
     //Scroll to a specific element.
     
   public static void scrollToElement(WebDriver driver, By locator) {
      WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
