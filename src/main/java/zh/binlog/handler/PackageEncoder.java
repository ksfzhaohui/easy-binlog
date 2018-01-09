package zh.binlog.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import zh.binlog.dataBean.DataPackage;

/**
 * 数据包编码器
 * 
 * @author hui.zhao
 *
 */
public class PackageEncoder extends MessageToByteEncoder<DataPackage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, DataPackage msg, ByteBuf out) throws Exception {
		msg.encodeAsByteBuf(out);
	}

}
