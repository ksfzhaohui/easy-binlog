package zh.binlog.event;

import java.util.HashMap;
import java.util.Map;

import zh.binlog.event.parser.FormatDescriptionEventParser;
import zh.binlog.event.parser.QueryEventParser;
import zh.binlog.event.parser.RotateEventParser;
import zh.binlog.event.parser.XidEventParser;

public class EventParserFactory {

	private static Map<Integer, IEventParser> eventParserMap = new HashMap<>();

	static {
		eventParserMap.put(EventType.ROTATE_EVENT, new RotateEventParser());
		eventParserMap.put(EventType.FORMAT_DESCRIPTION_EVENT, new FormatDescriptionEventParser());
		eventParserMap.put(EventType.XID_EVENT, new XidEventParser());
		eventParserMap.put(EventType.QUERY_EVENT, new QueryEventParser());
	}

	public static IEventParser getEventParser(int eventType) {
		return eventParserMap.get(eventType);
	}

}
