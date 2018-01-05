package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.bean.Package;
import zh.binlog.util.ByteUtil;

public class HandshakeHandler extends SimpleChannelInboundHandler<Package> {

	private Logger logger = LoggerFactory.getLogger(HandshakeHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Package pk) throws Exception {
		logger.info("Handshake start");

		if (null == pk) {
			return;
		}

		ByteBuf msg = (ByteBuf) pk.getContent();

		int protocolVersion = msg.readByte();
		String serverVersion = ByteUtil.NullTerminatedString(msg);
		int threadId = ByteUtil.readInt(msg, 4);
		logger.info("protocolVersion = " + protocolVersion + ",serverVersion = " + serverVersion + ",threadId = "
				+ threadId);

		String randomNumber1 = ByteUtil.NullTerminatedString(msg);
		msg.readBytes(2);
		int encode = msg.readByte();
		msg.readBytes(2);
		msg.readBytes(13);
		String randomNumber2 = ByteUtil.NullTerminatedString(msg);
		logger.info("randomNumber1 = " + randomNumber1 + ",encode = " + encode + ",randomNumber2 = " + randomNumber2);
		logger.info("Handshake end");
		
		ctx.pipeline().remove(this);
	}

}
