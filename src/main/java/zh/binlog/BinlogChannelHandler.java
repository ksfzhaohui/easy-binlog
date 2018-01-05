package zh.binlog;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import zh.binlog.handler.AuthenticateResultHandler;
import zh.binlog.handler.BinlogEventParseHandler;
import zh.binlog.handler.HandshakeHandler;
import zh.binlog.handler.PackageDecoder;

public class BinlogChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline cp = channel.pipeline();
		cp.addLast(new PackageDecoder());
		cp.addLast(new HandshakeHandler());
		cp.addLast(new AuthenticateResultHandler());
		cp.addLast(new BinlogEventParseHandler());
	}

}
