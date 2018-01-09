package zh.binlog.event.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zh.binlog.event.EventHeader;
import zh.binlog.event.IEventParser;
import zh.binlog.util.ByteUtil;

public class XidEventParser implements IEventParser {

	private Logger logger = LoggerFactory.getLogger(XidEventParser.class);

	@Override
	public void parse(ByteBuf msg, EventHeader eventHeader) {
		long xid = ByteUtil.readLong(msg, 8);
		logger.info("xid = " + xid);
	}

}
