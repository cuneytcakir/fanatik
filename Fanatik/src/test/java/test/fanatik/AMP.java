package test.fanatik;

import org.testng.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AMP

{

	public static void main(String[] args) {
		JUnitCore.main("test.fanatik.AMP");
	}

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void HaberDetayAMP() {

		String SliderHref = null;
		String AmpSliderHref = null;
		String amphtml = null;
		String[] treeSet = new String[15];

		driver.get("http://www.fanatik.com.tr");
		for (int i = 1; i < 15; i++) {
			SliderHref = driver.findElement(By.xpath(".//*[@id='main-slider']/ol/li[" + i + "]/a")).getAttribute("href")
					.trim();
			treeSet[i] = SliderHref;
		}

		for (int i = 1; i < 15; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");
			driver.get(treeSet[i]);
			System.out.println("İçerisinde amphtml tag'i aranan sayfa: " + treeSet[i]);
			try {
				amphtml = driver.findElement(By.cssSelector("link[rel='amphtml']")).getAttribute("href").trim();
				System.out.println("Bulunan haber detay AMP sayfası: " + amphtml);

			} catch (Exception e) {
				Assert.fail("Haber detay amp link çekilemiyor! tag yok veya link boş! " + AmpSliderHref);
			}

			if (!amphtml.equals(AmpSliderHref)) {
				Assert.fail("Bulunan haber detay AMP link sayfa ile uyuşmuyor!");
			} else {
				Assert.assertTrue(true);
				System.out.println("Kontrol başarılı." + "\n");
			}
		}

	}

	@Test
	public void FotoGaleriAMP() {
		String SliderHref = null;
		String AmpSliderHref = null;
		String amphtml = null;
		String[] treeSet = new String[10];

		driver.get("http://www.fanatik.com.tr/foto-galeri");
		for (int i = 1; i < 10; i++) {
			SliderHref = driver.findElement(By.xpath("html/body/div[6]/div/div/div[25]/div[" + i + "]/div/a"))
					.getAttribute("href").trim();
			treeSet[i] = SliderHref;
		}

		for (int i = 1; i < 10; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");
			driver.get(treeSet[i]);
			System.out.println("İçerisinde amphtml tag'i aranan sayfa: " + treeSet[i]);
			try {
				amphtml = driver.findElement(By.cssSelector("link[rel='amphtml']")).getAttribute("href").trim();
				System.out.println("Bulunan foto galeri AMP sayfası: " + amphtml);

			} catch (Exception e) {
				Assert.fail("Foto galeri amp link çekilemiyor! tag yok veya link boş! " + AmpSliderHref);
			}

			if (!amphtml.equals(AmpSliderHref)) {
				Assert.fail("Bulunan foto galeri AMP link sayfa ile uyuşmuyor!");
			} else {
				Assert.assertTrue(true);
				System.out.println("Kontrol başarılı." + "\n");
			}
		}
	}

	@Test
	public void FotoGaleriCanonical() throws InterruptedException {

		String SliderHref = null;
		String AmpSliderHref = null;
		String canonicalHref = null;
		String[] treeSet = new String[10];

		driver.get("http://www.fanatik.com.tr/foto-galeri");
		for (int i = 1; i < 10; i++) {
			SliderHref = driver.findElement(By.xpath("html/body/div[6]/div/div/div[25]/div[" + i + "]/div/a"))
					.getAttribute("href").trim();
			treeSet[i] = SliderHref;
		}
		for (int i = 1; i < 10; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");
			driver.get(AmpSliderHref);
			Thread.sleep(2000);
			System.out.println("İçerisinde canonical href aranan sayfa: " + AmpSliderHref);
			try {
				canonicalHref = driver.findElement(By.cssSelector("link[rel='canonical']")).getAttribute("href").trim();
				System.out.println("Bulunan canonical link: " + canonicalHref);

			} catch (Exception e) {
				Assert.fail("Foto galeri linki çekilemiyor! tag yok veya link boş! " + canonicalHref);
			}

			if (!canonicalHref.equals(treeSet[i])) {
				Assert.fail("Bulunan Foto galeri linki sayfa ile uyuşmuyor!");
			} else {
				Assert.assertTrue(true);
				System.out.println("Kontrol başarılı." + "\n");
			}
		}
	}

	@Test
	public void VideoGaleriAMP() {
		String SliderHref = null;
		String AmpSliderHref = null;
		String amphtml = null;
		String[] treeSet = new String[10];

		driver.get("http://www.fanatik.com.tr/video-galeri");
		for (int i = 1; i < 10; i++) {
			SliderHref = driver.findElement(By.xpath("html/body/div[6]/div/div/div[22]/div[" + i * 2 + "]/div/a"))
					.getAttribute("href").trim();
			treeSet[i] = SliderHref;
		}

		for (int i = 1; i < 10; i++) {
			AmpSliderHref = treeSet[i].replaceAll("http://www.fanatik.com.tr", "http://www.fanatik.com.tr/amp");
			driver.get(treeSet[i]);
			System.out.println("İçerisinde amphtml tag'i aranan sayfa: " + treeSet[i]);
			try {
				amphtml = driver.findElement(By.cssSelector("link[rel='amphtml']")).getAttribute("href").trim();
				System.out.println("Bulunan video galeri AMP sayfası: " + amphtml);

			} catch (Exception e) {
				Assert.fail("Video galeri amp link çekilemiyor! tag yok veya link boş! " + AmpSliderHref);
			}

			if (!amphtml.equals(AmpSliderHref)) {
				Assert.fail("Bulunan video galeri AMP link sayfa ile uyuşmuyor!");
			} else {
				Assert.assertTrue(true);
				System.out.println("Kontrol başarılı." + "\n");
			}
		}
	}

}