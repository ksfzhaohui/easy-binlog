package zh.binlog.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import zh.binlog.bean.Header;
import zh.binlog.bean.DataPackage;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.PowerType;

public class AuthenticateCommand {

	private static final String userName = "root";
	private static final String password = "root";
	private byte encode;
	private String randomNumber;

	public AuthenticateCommand(byte encode, String randomNumber) {
		this.encode = encode;
		this.randomNumber = randomNumber;
	}

	public void write(ChannelHandlerContext context) {
		int clientPower = PowerType.CLIENT_LONG_FLAG | PowerType.CLIENT_PROTOCOL_41
				| PowerType.CLIENT_SECURE_CONNECTION;
		byte clientPowerBytes[] = ByteUtil.writeInt(clientPower, 4);
		int maxLen = 0;
		byte maxLenBytes[] = ByteUtil.writeInt(maxLen, 4);
		byte encodeBytes[] = ByteUtil.writeInt(encode, 1);
		byte zeroBytes[] = ByteUtil.writeInt(0, 23);

		byte[] userNameBytes = (userName + "\0").getBytes();
		byte[] passwordBytes = "".equals(password) ? new byte[0]
				: ByteUtil.passwordCompatibleWithMySQL411(password, randomNumber);

		int packageLen = clientPowerBytes.length + maxLenBytes.length + encodeBytes.length + zeroBytes.length
				+ userNameBytes.length + 1 + passwordBytes.length;
		Header header = new Header(packageLen, 1);
		ByteBuf byteBuf = Unpooled.buffer(packageLen);
		byteBuf.writeBytes(clientPowerBytes);
		byteBuf.writeBytes(maxLenBytes);
		byteBuf.writeBytes(encodeBytes);
		byteBuf.writeBytes(zeroBytes);
		byteBuf.writeBytes(userNameBytes);
		byteBuf.writeByte((byte) passwordBytes.length);
		byteBuf.writeBytes(passwordBytes);
		DataPackage pk = new DataPackage(header, byteBuf);
		context.channel().writeAndFlush(pk);
	}

}
