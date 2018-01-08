package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.bean.DataPackage;

/**
 * binlog事件解析类
 * 
 * @author zhaohui
 *
 */
public class BinlogEventParseHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(BinlogEventParseHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage msg) throws Exception {
		logger.info("====jjjjj===");

	}

}
