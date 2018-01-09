package zh.binlog.event.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zh.binlog.event.EventHeader;
import zh.binlog.event.IEventParser;
import zh.binlog.util.ByteUtil;

public class FormatDescriptionEventParser implements IEventParser {

	private Logger logger = LoggerFactory.getLogger(FormatDescriptionEventParser.class);

	@Override
	public void parse(ByteBuf msg, EventHeader eventHeader) {
		long binlogVersion = ByteUtil.readInt(msg, 2);
		String serverVersion = ByteUtil.readFixedLenString(msg, 50);
		long timestamp = ByteUtil.readInt(msg, 4);
		byte headerLength = msg.readByte();
		StringBuffer eventTypeFixDataLen = new StringBuffer();
		for (int i = 0; i < 27; i++) {
			eventTypeFixDataLen.append(msg.readByte() + ",");
		}

		logger.info("binlogVersion = " + binlogVersion + ",serverVersion = " + serverVersion + ",timestamp = "
				+ timestamp + ",headerLength = " + headerLength + ",eventTypeStr = " + eventTypeFixDataLen);

	}

}
