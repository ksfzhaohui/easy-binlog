package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.dataBean.DataPackage;
import zh.binlog.dataBean.bean.AuthenticateDataBean;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.Constants;

/**
 * 处理mysql发送的握手包
 * 
 * @author zhaohui
 *
 */
public class HandshakeHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(HandshakeHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage pk) throws Exception {
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
		byte encode = msg.readByte();
		msg.readBytes(2);
		msg.readBytes(13);
		String randomNumber2 = ByteUtil.NullTerminatedString(msg);
		logger.info("randomNumber1 = " + randomNumber1 + ",encode = " + encode + ",randomNumber2 = " + randomNumber2);
		logger.info("Handshake end");

		AuthenticateDataBean dataBean = new AuthenticateDataBean(encode, randomNumber1 + randomNumber2,
				Constants.userName, Constants.password);
		ctx.channel().writeAndFlush(new DataPackage(1, dataBean));
		ctx.pipeline().remove(this);
	}

}
