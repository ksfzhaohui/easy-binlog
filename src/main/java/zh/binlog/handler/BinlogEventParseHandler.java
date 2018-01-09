package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.dataBean.DataPackage;
import zh.binlog.event.EventHeader;
import zh.binlog.event.EventParserFactory;
import zh.binlog.event.IEventParser;
import zh.binlog.util.ByteUtil;

/**
 * binlog事件解析类
 * 
 * @author zhaohui
 *
 */
public class BinlogEventParseHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(BinlogEventParseHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage datePackage) throws Exception {
		ByteBuf contentBuf = (ByteBuf) datePackage.getContent();
		// logger.info(ByteUtil.bytesToHexString(contentBuf.array()));
		
		contentBuf.skipBytes(1);
		EventHeader header = new EventHeader();
		header.setTimestamp(ByteUtil.readInt(contentBuf, 4));
		header.setTypeCode((byte) ByteUtil.readInt(contentBuf, 1));
		header.setServerId(ByteUtil.readInt(contentBuf, 4));
		header.setEventLen(ByteUtil.readInt(contentBuf, 4));
		header.setNextPosition(ByteUtil.readInt(contentBuf, 4));
		header.setFlags(ByteUtil.readInt(contentBuf, 2));
		logger.info(header.toString());

		IEventParser parser = EventParserFactory.getEventParser(header.getTypeCode());
		if (parser == null) {
			logger.error("不支持的binlog事件类型解析;typeCode = " + header.getTypeCode());
		}

		parser.parse(contentBuf, header);
	}

}
