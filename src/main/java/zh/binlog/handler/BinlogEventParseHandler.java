package zh.binlog.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * binlog事件解析类
 * 
 * @author zhaohui
 *
 */
public class BinlogEventParseHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

	}

}
