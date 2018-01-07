package zh.binlog.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理mysql发送的认证结果包
 * 
 * @author zhaohui
 *
 */
public class AuthenticateResultHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

	}

}
