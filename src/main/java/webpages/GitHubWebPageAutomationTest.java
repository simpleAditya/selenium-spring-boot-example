package webpages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GitHubWebPageAutomationTest
{
	static Logger logger = LoggerFactory.getLogger(GitHubWebPageAutomationTest.class);
	static WebDriver driver;

	@Test
	public void automationTest() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "/home/adityasrivastava/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://github.com/login"); //get github browser object

		boolean condition;

		if( driver.getTitle().equalsIgnoreCase("Sign in to GitHub · GitHub") )
		{
			condition = true;
			logger.info("Valid login page");
		}
		else
		{
			condition = false;
			logger.info("Invalid login page");
		}
		assertTrue(condition,"Login page not loaded."); //validate github login page

		driver.findElement(By.id("login_field")).sendKeys("addy1219");
		driver.findElement(By.id("password")).sendKeys("@D!ty@143");

		driver.findElement(By.name("commit")).click();

		if( driver.getTitle().equalsIgnoreCase("GitHub") )
		{
			condition = true;
			logger.info("Valid home page");
		}
		else
		{
			condition = false;
			logger.info("Invalid home page");
		}
		assertTrue(condition, "Home page title not found"); //validate github home page after successful login

		Thread.sleep(3000);

		driver.navigate().to(driver.getCurrentUrl() + "new");

		driver.findElement(By.id("repository_name")).sendKeys("marvel");
		driver.findElement(By.id("repository_description")).sendKeys("this is an auto-generated repository for automation testing");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='public']")).click();
		driver.findElement(By.xpath("//input[@id='repository_auto_init']")).click();

		Thread.sleep(3000);

		if( driver.findElement(By.xpath("//button[@class='btn-primary btn']")).isEnabled() )
		{
			driver.findElement(By.xpath("//button[@class='btn-primary btn']")).click();
		}

		if( driver.getCurrentUrl().endsWith("/marvel") )
		{
			condition = true;
			logger.info("Repository created successfully");
		}
		else
		{
			condition = false;
			logger.info("Repository creation failed");
		}
		assertTrue(condition, "Repository not created"); //validate creation of new repository named 'marvel'

		Thread.sleep(3000);

		driver.navigate().to(driver.getCurrentUrl() + "/settings");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//details[@class='details-reset details-overlay details-overlay-dark flex-md-order-1 flex-order-2']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")).sendKeys("addy1219/marvel");

		Thread.sleep(3000);

		if( driver.findElement(By.xpath("//form[@action='/addy1219/marvel/settings/delete']/button/span[@class='d-md-inline-block d-none']")).isEnabled() )
		{
			driver.findElement(By.xpath("//form[@action='/addy1219/marvel/settings/delete']/button/span[@class='d-md-inline-block d-none']")).click();
			if( !driver.getTitle().equalsIgnoreCase("GitHub") )
			{
				driver.findElement(By.id("sudo_password")).sendKeys("@D!ty@143");
				driver.findElement(By.className("btn btn-block btn-primary mt-5")).click();
			}
		}

		if( driver.getTitle().equalsIgnoreCase("GitHub") )
		{
			condition = true;
			logger.info("Repository deleted");
		}
		else
		{
			condition = false;
			logger.info("Repository not deleted");
		}
		assertTrue(condition, "Repository not deleted"); //validate repository deletion named 'marvel'

		Thread.sleep(3000);

		driver.navigate().to(driver.getCurrentUrl() + "new");

		Thread.sleep(3000);

		driver.navigate().to(driver.getCurrentUrl() + "/import");

		Thread.sleep(3000);

		driver.findElement(By.id("vcs_url")).sendKeys("https://github.com/simpleAditya/kafka-spring-boot-example.git");
		driver.findElement(By.id("repository_name")).sendKeys("old-repository");

		driver.findElement(By.id("repository_visibility_public")).click();

		Thread.sleep(3000);

		if( driver.findElement(By.xpath("//button[@class='btn-primary btn']")).isEnabled() )
		{
			driver.findElement(By.xpath("//button[@class='btn-primary btn']")).click();
		}

		Thread.sleep(10000);

		driver.navigate().to(driver.getCurrentUrl().replace("/import", ""));

		Thread.sleep(3000);

		if( !driver.getCurrentUrl().contains("/import") )
		{
			condition = true;
			logger.info("Repository imported");
		}
		else
		{
			condition = false;
			logger.info("Repository not imported");
		}
		assertTrue(condition, "Repository not imported"); //validate importing of existing repository named 'old-repository'

		driver.navigate().to(driver.getCurrentUrl() + "/settings");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//details[@class='details-reset details-overlay details-overlay-dark flex-md-order-1 flex-order-2']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")).sendKeys("addy1219/old-repository");

		Thread.sleep(3000);

		if( driver.findElement(By.xpath("//form[@action='/addy1219/old-repository/settings/delete']/button/span[@class='d-md-inline-block d-none']")).isEnabled() )
		{
			driver.findElement(By.xpath("//form[@action='/addy1219/old-repository/settings/delete']/button/span[@class='d-md-inline-block d-none']")).click();
			if( !driver.getTitle().equalsIgnoreCase("GitHub") )
			{
				driver.findElement(By.id("sudo_password")).sendKeys("@D!ty@143");
				driver.findElement(By.className("btn btn-block btn-primary mt-5")).click();
			}
		}

		if( driver.getTitle().equalsIgnoreCase("GitHub") )
		{
			condition = true;
			logger.info("Repository deleted");
		}
		else
		{
			condition = false;
			logger.info("Repository not deleted");
		}
		assertTrue(condition, "Repository not deleted"); //validate repository deletion named 'old-repository'

		Thread.sleep(3000);

		driver.findElement(By.xpath("//summary[@aria-label='View profile and more']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[@class='dropdown-item dropdown-signout']")).click();

		if( driver.getTitle().equalsIgnoreCase("GitHub: Where the world builds software · GitHub") )
		{
			condition = true;
			logger.info("Repository deleted");
		}
		else
		{
			condition = false;
			logger.info("Repository not deleted");
		}
		assertTrue(condition, "Repository not deleted"); //validate github logout page
	}
}