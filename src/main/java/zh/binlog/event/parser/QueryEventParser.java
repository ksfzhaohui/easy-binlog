package zh.binlog.event.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zh.binlog.event.EventHeader;
import zh.binlog.event.IEventParser;
import zh.binlog.util.ByteUtil;

public class QueryEventParser implements IEventParser {

	private Logger logger = LoggerFactory.getLogger(QueryEventParser.class);

	private static final int QUERY_EVENT_FIX_LEN = 13;

	@Override
	@SuppressWarnings("unused")
	public void parse(ByteBuf msg, EventHeader eventHeader) {
		long threadId = ByteUtil.readInt(msg, 4);
		long time = ByteUtil.readInt(msg, 4);
		int dbNameLen = msg.readByte();
		int errorCode = ByteUtil.readInt(msg, 2);
		int variableLen = ByteUtil.readInt(msg, 2);

		msg.skipBytes(variableLen);

		String dbName = ByteUtil.NullTerminatedString(msg);
		String sql = ByteUtil.readFixedLenString(msg, (int) (eventHeader.getEventLen() - variableLen
				- EventHeader.EVENT_HEADER_LEN - QUERY_EVENT_FIX_LEN - dbName.getBytes().length - 1));

		logger.info("dbName = " + dbName + ",sql = " + sql);
	}

}
