package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.bean.DataPackage;
import zh.binlog.util.ByteUtil;

/**
 * 处理mysql发送的认证结果包
 * 
 * @author zhaohui
 *
 */
public class AuthenticateResultHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(AuthenticateResultHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage pk) throws Exception {
		ByteBuf msg = (ByteBuf) pk.getContent();
		int mark = msg.readByte();
		if (mark == 0) {
			logger.info("Authenticate success:" + ByteUtil.bytesToHexString(msg.array()));
		} else {
			logger.info("Authenticate fail:" + ByteUtil.bytesToHexString(msg.array()));
		}
	}

}
