package pisystindiatesting;


import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class selTesting {
    static WebDriver driver;
    @BeforeSuite
    public static void setWindow(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://tms.pisystindia.com/admin/login");
        driver.manage().window().maximize();
    }

    @BeforeClass
    public void login() {
        WebElement email = driver.findElement(By.id("admin_email"));
        WebElement password = driver.findElement(By.id("admin_password"));
        email.sendKeys("sales.infinitycorp@gmail.com");
        password.sendKeys("123456");
        WebElement submit = driver.findElement(By.xpath("(//button)[1]"));
        try {
            submit.click();
            Thread.sleep(2000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        WebElement enquiry = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/ul/li[6]/a"));
        try {
            enquiry.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement enquiryShiftAnalysis = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/ul/li[6]/ul/li[2]/a"));
        try{
            enquiryShiftAnalysis.click();
            Thread.sleep(2000);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void logout() throws InterruptedException {
        WebElement infinity = driver.findElement(By.xpath("/html/body/div[2]/div[2]/ul/li/span/span"));
        infinity.click();
        WebElement logout = driver.findElement(By.xpath("/html/body/div[2]/div[2]/ul/li/div/div/ul/li[3]/a"));
        Thread.sleep(500);
        logout.click();
        Thread.sleep(500);
        driver.close();
        ExtendReportBase.reports.flush();
    }

    @Test
    public void searchFunc() throws CustomException {
        ExtendReportBase.test = ExtendReportBase.reports.startTest("Check Search Bar","Checking if search bar works properly with Site Engineer");
        WebElement searchBar = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div/label/input"));
        searchBar.sendKeys("Site Engineer");
        ExtendReportBase.test.log(LogStatus.INFO, "Passed \" Site Engineer \" ");
        WebElement searchCount = driver.findElement(By.xpath("//*[@id=\"example_info\"]"));
        if(searchCount.getText().equalsIgnoreCase("Showing 1 to 3 of 3 entries (filtered from 9 total entries)"))
        {
            System.out.println("Search function works");
            ExtendReportBase.test.log(LogStatus.PASS, "Search function works");
        }
        else
        {
            ExtendReportBase.test.log(LogStatus.FAIL, "Search function does not work");
            throw new CustomException("Search function does not work");
        }

    }

    @Test
    public void shiftDetails() throws CustomException, InterruptedException {
        WebElement shift = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[7]/a"));
        shift.click();
        Thread.sleep(1000);
        WebElement header = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[1]/div/div[1]/h4"));
        if(header.getText().equalsIgnoreCase("All Shifts Details"))
        {
            ExtendReportBase.test.log(LogStatus.PASS, "Shift redirection works");
            System.out.println("Shift Redirection Works");
        }

        else
        {
            ExtendReportBase.test.log(LogStatus.FAIL, "Shift redirection does not work");
            throw new CustomException("Shift Redirection does not work");
        }


    }


}
