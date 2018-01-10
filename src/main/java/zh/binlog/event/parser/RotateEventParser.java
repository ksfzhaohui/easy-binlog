package zh.binlog.event.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zh.binlog.event.EventHeader;
import zh.binlog.event.IEventParser;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.NamePositionStore;

public class RotateEventParser implements IEventParser {

	private Logger logger = LoggerFactory.getLogger(RotateEventParser.class);

	@Override
	public void parse(ByteBuf msg, EventHeader eventHeader) {
		long binlogPosition = ByteUtil.readLong(msg, 8);
		int variablePartLen = (int) (eventHeader.getEventLen() - EventHeader.EVENT_HEADER_LEN - 8);
		byte variablePart[] = new byte[variablePartLen];
		msg.readBytes(variablePart);
		String binlogName = new String(variablePart);

		logger.info("binlogPosition = " + binlogPosition + ",binlogName = " + binlogName);

		NamePositionStore.putNamePosition(binlogName, binlogPosition);
	}

}
