package zh.binlog.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import zh.binlog.bean.Header;
import zh.binlog.util.ByteUtil;

/**
 * 数据包解码器
 * 
 * @author zhaohui
 *
 */
public class PackageDecoder extends ByteToMessageDecoder {

	private Logger logger = LoggerFactory.getLogger(PackageDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (null == in) {
			return;
		}

		if (in.readableBytes() < 4) {
			return;
		}

		in.markReaderIndex();
		int packageLen = ByteUtil.readInt(in, 3);
		int sequenceId = in.readByte();
		Header header = new Header(packageLen, sequenceId);
		if (in.readableBytes() < packageLen) {
			in.resetReaderIndex();
			return;
		}
		logger.info("packageLen = " + packageLen + ",sequenceId = " + sequenceId);

		zh.binlog.bean.Package pk = new zh.binlog.bean.Package(header, in);
		out.add(pk);
	}

}
