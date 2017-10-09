

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import all.entity.Item;
import all.exception.RssException;
import all.service.RssService;

public class RssServiceTest {
	
	private RssService rssService;
	
	@Before 
	public void setUp() throws Exception{ 
		rssService = new RssService();
	}

	@Test
	public void test() throws RssException {
		List<Item> items = rssService.getItems(new File("src/test/resources/example.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals("How to generate web.xml in Eclipse", firstItem.getTitle());
		assertEquals("23 03 2014 12:01:34", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
	}
}
