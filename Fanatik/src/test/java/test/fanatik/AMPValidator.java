package test.fanatik;

import java.util.logging.Level;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AMPValidator {
	
	public static void main(String[] args) {
		JUnitCore.main("test.fanatik.AMPValidator");
	}

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.INFO);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		driver = new ChromeDriver(caps);
	}
	
	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void HaberDetay() throws InterruptedException {

		String SliderHref = null;
		String AmpSliderHref = null;
		String[] treeSet = new String[15];

		driver.get("http://www.fanatik.com.tr");
		for (int i = 1; i < 15; i++) {
			SliderHref = driver.findElement(By.xpath(".//*[@id='main-slider']/ol/li[" + i + "]/a")).getAttribute("href")
					.trim();
			treeSet[i] = SliderHref;
		}

		for (int i = 1; i < 15; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");
			driver.get(AmpSliderHref+ "#development=1");
			System.out.println("Kontrol edilen AMP sayfası: " + AmpSliderHref);
			Thread.sleep(4000);
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			if (!logEntries.getAll().toString().contains("AMP validation successful.")) {
				Assert.fail("Haber detay AMP valid değil! " + AmpSliderHref+"\n");
			} else {
				System.out.println("Kontrol edilen haber detay AMP sayfası valid: " + AmpSliderHref+"\n");
			}
		}
	}

	@Test
	public void FotoGaleri() throws InterruptedException {

		String SliderHref = null;
		String AmpSliderHref = null;
		String[] treeSet = new String[10];

		driver.get("http://www.fanatik.com.tr/foto-galeri");
		for (int i = 1; i < 10; i++) {
			SliderHref = driver.findElement(By.xpath("html/body/div[6]/div/div/div[25]/div[" + i + "]/div/a"))
					.getAttribute("href").trim();
			treeSet[i] = SliderHref;
		}

		for (int i = 1; i < 10; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");

			driver.get(AmpSliderHref+ "#development=1");
			System.out.println("Kontrol edilen AMP sayfası: " + AmpSliderHref);
			Thread.sleep(4000);
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			if (!logEntries.getAll().toString().contains("AMP validation successful.")) {
				Assert.fail("Foto Galeri AMP valid değil! " + AmpSliderHref+"\n");
			} else {
				System.out.println("Kontrol edilen foto galeri AMP sayfası valid: " + AmpSliderHref+"\n");
			}
		}
	}

	@Test
	public void VideoGaleri() throws InterruptedException {

		String SliderHref = null;
		String AmpSliderHref = null;
		String[] treeSet = new String[10];

		driver.get("http://www.fanatik.com.tr/video-galeri");
		for (int i = 1; i < 10; i++) {
			SliderHref = driver.findElement(By.xpath("html/body/div[6]/div/div/div[22]/div[" + i * 2 + "]/div/a"))
					.getAttribute("href").trim();
			treeSet[i] = SliderHref;
		}
		
		for (int i = 1; i < 10; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");

			driver.get(AmpSliderHref + "#development=1");
			System.out.println("Kontrol edilen AMP sayfası: " + AmpSliderHref);
			Thread.sleep(4000);
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			if (!logEntries.getAll().toString().contains("AMP validation successful.")) {
				Assert.fail("Video Galeri AMP valid değil! " + AmpSliderHref+"\n");
			} else {
				System.out.println("Kontrol edilen video galeri AMP sayfası valid: " + AmpSliderHref+"\n");
			}
		}
	}

}
