package zh.binlog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientServer implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ClientServer.class);
	private Bootstrap b;

	/** mysql地址端口信息 **/
	private String ip = "localhost";
	private int port = 3306;

	@Override
	public void run() {
		try {
			b = new Bootstrap();
			b.group(new NioEventLoopGroup(1)).channel(NioSocketChannel.class)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000).option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new BinlogChannelHandler());

			ChannelFuture future = b.connect(ip, port);
			Channel channel = future.channel();
			CountDownLatch countLatch = new CountDownLatch(1);
			future.addListener(new ChannelListener(countLatch));
			if (!channel.isActive()) {
				countLatch.await(2000, TimeUnit.MILLISECONDS);
			}

			logger.info("connect mysql success");
		} catch (Exception e) {
			logger.error("connect mysql error", e);
		}
	}

	class ChannelListener implements ChannelFutureListener {

		private CountDownLatch countlatch;

		public ChannelListener(CountDownLatch countlatch) {
			this.countlatch = countlatch;
		}

		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			if (future.channel().isActive()) {
				countlatch.countDown();
			}
		}
	}

}
