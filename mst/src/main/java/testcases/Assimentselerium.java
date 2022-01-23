package testcases;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.JScrollBar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assimentselerium {
WebDriver driver;
	@BeforeTest
	public void launchingbrowser()  {
		
		System.setProperty("webdriver.chrome.driver","D:\\maven projects\\mst\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	public void navigatingtourl(String url) {
		driver.navigate().to(url);
		
	}
	
	  public void screenshot(WebDriver driver , String name) throws IOException {
	  	TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("D:\\maven projects\\mst\\screenshot\\"+name+".png");
		FileUtils.copyFile(srcFile, DestFile);
	}
	@AfterTest
	  public void close() {
		  driver.quit();
    }
	
	
 @Test
    public  void scrollingsite() throws InterruptedException {
  	navigatingtourl("https://www.tutorialspoint.com/index.htm");
 	Thread.sleep(3000);
	JavascriptExecutor jr = (JavascriptExecutor) driver;
	jr.executeScript("window.scrollBy(0,1000)");
	
	Thread.sleep(2000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,-500)");
  }
	

 @Test
    public void windowhandlings() {
	navigatingtourl("https://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
	driver.findElement(By.xpath("//div[@class='post-body entry-content']//a")).click(); 
	
	String parentwindow = driver.getWindowHandle();
	System.out.println("parent window is " +parentwindow);
	System.out.println("the parent window is "+driver.getTitle());
	Reporter.log(driver.getCurrentUrl(),true);
	
	ArrayList<String> window =new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(window.get(1));
	
	System.out.println("the chaged parent window is "+driver.getTitle());
	Reporter.log(driver.getCurrentUrl(), true);
	}

   @Test
  public void dropdownss() throws InterruptedException {
  navigatingtourl("https://www.facebook.com/login.php");	
  driver.findElement(By.xpath("//div[@class='_xkv fsm fwn fcg']//a[2]")).click();
  Thread.sleep(2000);
  
   WebElement day= driver.findElement(By.id("day"));
   Select obj = new Select(day);
   obj.selectByValue("4");
 
   WebElement mounth= driver.findElement(By.id("month"));
   Select obj1 = new Select(mounth);
   obj1.selectByIndex(6);
 
   WebElement year= driver.findElement(By.id("year"));
   Select obj2 = new Select(year);
   obj2.selectByVisibleText("2000");
 
}
	
@Test

  public void alertshandle() throws InterruptedException {
		
  navigatingtourl("https://demoqa.com/alerts");

  driver.findElement(By.id("alertButton")).click();
  Alert alert1 = driver.switchTo().alert();
  alert1.accept();
  Thread.sleep(2000);

  driver.findElement(By.xpath("(//div[@class='col'])[2]/button")).click();
  Thread.sleep(7000);
  Alert alert2 = driver.switchTo().alert();
  alert2.accept();

  driver.findElement(By.id("confirmButton")).click();
  Alert alert3 = driver.switchTo().alert();
   alert3.dismiss();
  Thread.sleep(2000);


  driver.findElement(By.id("promtButton")).click();
  Alert alert4 = driver.switchTo().alert();
  alert4.sendKeys("selerium");
  alert4.accept();

		}
	
	@Test
	public void print() {
		navigatingtourl("https://www.google.co.in/webhp");
		String title=driver.getTitle();
		Reporter.log(title,true);
		System.out.println("-------------------------------");
		
		String urlpage= driver.getCurrentUrl();
		Reporter.log(urlpage,true);
		System.out.println("-------------------------------------");
		
       String source=driver.getPageSource();
		Reporter.log(source,true);
		
	WebElement text	=driver.findElement(By.xpath("//a[@class='gb_d']"));
	Reporter.log(text.getText(),true);
	System.out.println("---------------------------------");
}
	
	
	@Test
	public void links() {
		navigatingtourl("https://www.google.co.in/webhp");
	List<WebElement> allthelinks =driver.findElements(By.tagName("a"));
     System.out.println(allthelinks.size());
     
     for(WebElement alllinks:allthelinks) {
	  Reporter.log(alllinks.getText(),true);
	}
	}
	@Test
	public void checkbox() {
		navigatingtourl("https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123"); 
	
	driver.findElement(By.id("btnLogin")).click(); 
		driver.findElement(By.xpath("//b[text()='Leave']")).click();
	
	driver.findElement(By.xpath("(//input[@name='leaveList[chkSearchFilter][]'])[4]")).click();
	
	}
	@Test
	public void mouseover() throws InterruptedException {
		
		navigatingtourl("https://www.amazon.in/");
		driver.findElement(By.xpath("//a[text()=' Electronics '] ")).click();
		
   	WebElement move=driver.findElement(By.xpath("//a[@class='nav-a nav-hasArrow'][4]"));
		Actions action = new Actions(driver);
		action.moveToElement(move).build().perform();
	Thread.sleep(3000);
		
	 WebElement link	=driver.findElement(By.xpath("//a[text()='Noise']"));
	Actions action2 = new Actions(driver);
	action2.doubleClick().build().perform();
	
  

}
@Test
	public void readingdata() throws IOException, InterruptedException {
	Properties properties = new Properties();

		FileInputStream fileInputStream = new FileInputStream("D:\\maven projects\\mst\\data.properties");
	   properties.load(fileInputStream);
        String url	=properties.getProperty("url");
        String email =properties.getProperty("email");
	    String password =properties.getProperty("password");
    	driver.get(url);
    	Thread.sleep(3000);
    	screenshot(driver, "facebook");
		driver.findElement(By.id("email")).sendKeys(email);
	    driver.findElement(By.id("pass")).sendKeys(password);
	    Thread.sleep(3000);
        screenshot(driver, "facebook2");
    }
@Test
public void uploadingfile() {
	navigatingtourl("https://demo.guru99.com/test/upload/");
	driver.findElement(By.xpath("//input[@name='uploadfile_0']")).sendKeys("C:\\Users\\Dell\\OneDrive\\Desktop\\resume.docx");
	
}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
