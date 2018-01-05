package zh.binlog.command;

import io.netty.channel.ChannelHandlerContext;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.PowerType;

public class AuthenticateCommand {

	private static final String userName = "root";
	private static final String password = "root";
	private int encode;
	private String randomNumber;

	public AuthenticateCommand(int encode, String randomNumber) {
		this.encode = encode;
		this.randomNumber = randomNumber;
	}

	public void write(ChannelHandlerContext context) {
		int clientPower = PowerType.CLIENT_LONG_FLAG | PowerType.CLIENT_PROTOCOL_41
				| PowerType.CLIENT_SECURE_CONNECTION;
		byte clientPowerBytes[] = ByteUtil.writeInt(clientPower, 4);
		int maxLen = 512;
		byte maxLenBytes[] = ByteUtil.writeInt(maxLen, 4);
		byte encodeBytes[] = ByteUtil.writeInt(encode, 1);
		byte[] zeroBytes = ByteUtil.writeInt(0, 23);

		byte[] userNameBytes = (userName + "\0").getBytes();
		byte[] passwordBytes = ByteUtil.passwordCompatibleWithMySQL411(password, randomNumber);

	}

}
