package test.fanatik;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class URLCheck {
	
	public static void main(String[] args) {
		JUnitCore.main("test.fanatik.URLCheck");
	}
	
	private int BrokenLinksCount;
	private int TotalLinksCount;

	@Test
	public void validateInvalidLinks() throws IOException {
		BrokenLinksCount = 0;
		Set<String> brokenlinks = new TreeSet<String>();
		String[] layout = new String[] { "", "futbol", "transfer", "basketbol", "euroleague", "diger-sporlar",
				"yazarlar", "foto-galeri", "video-galeri", "video/canli-yayin" };
		String filteredLinksCssQuery = "a[href^=/]";
		for (int i = 0; i < layout.length; i++) {
			Document doc = Jsoup.connect("http://www.fanatik.com.tr/" + layout[i]).get();
			Elements link = doc.select(filteredLinksCssQuery);
			HttpClient client = HttpClientBuilder.create().build();
			for (Element element : link) {
				HttpHead request = new HttpHead(element.absUrl("href"));
				try {
					HttpResponse response = client.execute(request);
					if ((response.getStatusLine().getStatusCode() < 200
							|| response.getStatusLine().getStatusCode() >= 400)) {
						BrokenLinksCount++;
						System.out.println("HATA HATA HATA HATA : " + element.absUrl("href") + " status: "
								+ response.getStatusLine().getStatusCode());
						brokenlinks.add(element.absUrl("href"));
					}
					System.out.println("Kontrol edilen URL: " + element.absUrl("href")+"	---> status: "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					System.out.println("Exception URL: " + element.absUrl("href"));
				}
			}
			TotalLinksCount = TotalLinksCount + link.size();
		}	
		System.out.println("Total links count: "+ TotalLinksCount);
		System.out.println("Broken links count: " + BrokenLinksCount);
		for (String str : brokenlinks) {
			System.out.println("Broken Links: " + str);
		}
	}

}
