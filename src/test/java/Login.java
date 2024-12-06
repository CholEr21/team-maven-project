import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

public class Login {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username) throws Exception {
        driver.get("http://10.247.251.110:8021/");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();
        Thread.sleep(3000);

    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = "C:\\Users\\Bushan.BC\\Data\\file.xlsx";  
        List<String[]> data = ExcelUtils.readExcelData(filePath, "Sheet1");
        return data.toArray(new Object[0][]);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close browser after each test
        }
    }
}
