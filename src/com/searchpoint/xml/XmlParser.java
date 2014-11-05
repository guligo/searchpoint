package com.searchpoint.xml;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

/**
 * XML representation of item entity.
 * 
 * @author Igors Gulbinskis
 */
@Component
public class XmlParser {

	@SuppressWarnings("unchecked")
	public List<XmlTestItem> parseTestXml(InputStream inputStream) {
		XStream xStream = new XStream();
		xStream.alias("root", List.class);
		xStream.alias("item", XmlTestItem.class);
		return (List<XmlTestItem>) xStream.fromXML(inputStream);
	}

}
