package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.dataBean.DataPackage;

/**
 * binlog事件解析类
 * 
 * @author zhaohui
 *
 */
public class FetchBinlogNamePositionHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(FetchBinlogNamePositionHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage datePackage) throws Exception {
		logger.info("===========FetchBinlogNamePositionHandler==============");

		ctx.pipeline().remove(this);
	}

}
