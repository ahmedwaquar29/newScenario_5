package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class StepDefinition {
	public static WebDriver driver;
	public static String productRead;
	int flag = 0;

	@Given("User is on home page")
	public void user_is_on_home_page() {
		System.out.println("user navigate to testme app");
		System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("http://demowebshop.tricentis.com/");
		assertEquals("Demo Web Shop", driver.getTitle());

		
	}

	@Given("Click on Log in link")
	public void click_on_Log_in_link() {
		driver.findElement(By.linkText("Log in")).click();
		assertEquals("Demo Web Shop. Login", driver.getTitle());
	}

	@When("Enters email and password and clicks login")
	public void enters_email_and_password_and_clicks_login(DataTable dataTable) {
		
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).get("email"));
			driver.findElement(By.name("Email")).sendKeys(list.get(i).get("email"));
			System.out.println(list.get(i).get("password"));
			driver.findElement(By.name("Password")).sendKeys(list.get(i).get("password"));
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();
			flag = flag + driver.findElements(By.linkText(list.get(i).get("email"))).size();
			System.out.println(list.get(i).get("email") + " " + flag);
			driver.findElement(By.linkText("Log out")).click();
			driver.findElement(By.linkText("Log in")).click();
		}
		
	}

	@Then("User logged in successfully")
	public void user_logged_in_successfully() {
		assertEquals(2, flag);
	}

}
