package zh.binlog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import zh.binlog.dataBean.DataPackage;
import zh.binlog.dataBean.bean.RequestBinlogDumpDataBean;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.Constants;

/**
 * 处理mysql发送的认证结果包
 * 
 * @author zhaohui
 *
 */
public class AuthenticateResultHandler extends SimpleChannelInboundHandler<DataPackage> {

	private Logger logger = LoggerFactory.getLogger(AuthenticateResultHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataPackage dataPackage) throws Exception {
		ByteBuf msg = (ByteBuf) dataPackage.getContent();
		int mark = msg.readByte();
		if (mark == 0) {
			RequestBinlogDumpDataBean dataBean = new RequestBinlogDumpDataBean(Constants.serverId,
					Constants.binlogFilename, Constants.binlogPosition);
			ctx.channel().writeAndFlush(new DataPackage(0, dataBean));
			logger.info("Authenticate success:" + ByteUtil.bytesToHexString(msg.array()));
		} else {
			logger.info("Authenticate fail:" + ByteUtil.bytesToHexString(msg.array()));
		}
		ctx.pipeline().remove(this);
	}

}
