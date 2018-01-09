package zh.binlog;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import zh.binlog.handler.AuthenticateResultHandler;
import zh.binlog.handler.BinlogEventParseHandler;
import zh.binlog.handler.FetchBinlogNamePositionHandler;
import zh.binlog.handler.HandshakeHandler;
import zh.binlog.handler.PackageDecoder;
import zh.binlog.handler.PackageEncoder;

public class BinlogChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline cp = channel.pipeline();
		cp.addLast("Decoder", new PackageDecoder());
		cp.addLast("Encoder", new PackageEncoder());
		cp.addLast("Handshake", new HandshakeHandler());
		cp.addLast("AuthenticateResult", new AuthenticateResultHandler());
		//cp.addLast("FetchBinlogNamePosition", new FetchBinlogNamePositionHandler());

		cp.addLast("BinlogEventParse", new BinlogEventParseHandler());
	}

}
