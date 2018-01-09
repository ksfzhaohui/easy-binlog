package zh.binlog.dataBean.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import zh.binlog.dataBean.IDataBean;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.PowerType;

public class AuthenticateDataBean implements IDataBean {

	/** 认证需要的用户名密码 **/
	private String userName;
	private String password;

	/** 编码和挑战随机数 **/
	private byte encode;
	private String randomNumber;

	public AuthenticateDataBean(byte encode, String randomNumber, String userName, String password) {
		this.encode = encode;
		this.randomNumber = randomNumber;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public byte[] toByteArray() throws Exception {
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

		ByteBuf byteBuf = Unpooled.buffer();
		byteBuf.writeBytes(clientPowerBytes);
		byteBuf.writeBytes(maxLenBytes);
		byteBuf.writeBytes(encodeBytes);
		byteBuf.writeBytes(zeroBytes);
		byteBuf.writeBytes(userNameBytes);
		byteBuf.writeByte((byte) passwordBytes.length);
		byteBuf.writeBytes(passwordBytes);
		return byteBuf.array();
	}

}
