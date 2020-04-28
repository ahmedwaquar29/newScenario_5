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
	public static WebDriver chrdriver;
	int value = 0;

	@Given("User is on home page")
	public void user_is_on_home_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32 (1)\\chromedriver.exe");
		chrdriver = new ChromeDriver();
		chrdriver.manage().window().maximize();
		chrdriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		chrdriver.get("http://demowebshop.tricentis.com/");
		assertEquals("Demo Web Shop", chrdriver.getTitle());

		
	}

	@Given("Click on Log in link")
	public void click_on_Log_in_link() {
		chrdriver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
		assertEquals("Demo Web Shop. Login", chrdriver.getTitle());
	}

	@When("Enters email and password and clicks login")
	public void enters_email_and_password_and_clicks_login(DataTable dataTable) {
		
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		for (int dat = 0; dat < data.size(); dat++) {
			
			chrdriver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[2]/input")).sendKeys(data.get(dat).get("email"));
			
			chrdriver.findElement(By.name("Password")).sendKeys(data.get(dat).get("password"));
			chrdriver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();
			value = value + chrdriver.findElements(By.linkText(data.get(dat).get("email"))).size();
			
			chrdriver.findElement(By.linkText("Log out")).click();
			chrdriver.findElement(By.linkText("Log in")).click();
		}
		
	}

	@Then("User logged in successfully")
	public void user_logged_in_successfully() {
		assertEquals(2, value);
	}

}
