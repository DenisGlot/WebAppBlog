package all.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import all.entity.Item;
import all.exception.RssException;
import all.jaxb.ObjectFactory;
import all.jaxb.TRss;
import all.jaxb.TRssChannel;
import all.jaxb.TRssItem;

@Service
public class RssService {

	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}

	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}

	private List<Item> getItems(Source source) throws RssException {

		List<Item> list = new ArrayList<Item>();
		try {
			JAXBContext jaxb = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jaxbElement.getValue();
			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel tRssChannel : channels) {
				List<TRssItem> items = tRssChannel.getItem();
				for (TRssItem tRssItem : items) {
					Item item = new Item();
					item.setTitle(tRssItem.getTitle());
					item.setDescription(tRssItem.getDescription());
					item.setLink(tRssItem.getLink());
					java.util.Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
							.parse(tRssItem.getPubDate());
					item.setPublishedDate(pubDate);
					list.add(item);
				}

			}
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
		return list;
	}
}
