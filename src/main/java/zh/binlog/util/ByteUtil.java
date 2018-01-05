package zh.binlog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.netty.buffer.ByteBuf;

public class ByteUtil {

	public static String NullTerminatedString(ByteBuf msg) {
		msg.markReaderIndex();
		int length = 0;
		while ('\0' != msg.readByte()) {
			length++;
		}
		length++;
		msg.resetReaderIndex();
		byte[] str = new byte[length];
		msg.readBytes(str);
		return new String(str, 0, length - 1);
	}

	/**
	 * 小端字节序
	 * 
	 * @param src
	 * @param bits
	 * @return
	 */
	public static int readInt(ByteBuf src, int bits) {
		int result = 0;
		for (int i = 0; i < bits; ++i) {
			result |= (src.readByte() << (8 * i));
		}
		return result;
	}

	/**
	 * 小端字节序
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static byte[] writeInt(int value, int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = (byte) ((i >> (8 * i)) & 0xFF);
		}
		return result;
	}
	
	public static byte[] passwordCompatibleWithMySQL411(String password, String salt) {
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] passwordHash = sha.digest(password.getBytes());
		return xor(passwordHash, sha.digest(union(salt.getBytes(), sha.digest(passwordHash))));
	}

	private static byte[] union(byte[] a, byte[] b) {
		byte[] r = new byte[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}

	private static byte[] xor(byte[] a, byte[] b) {
		byte[] r = new byte[a.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = (byte) (a[i] ^ b[i]);
		}
		return r;
	}

	public static void main(String[] args) {
		System.out.println(4 | 512 | 32768);

		System.out.println(
				PowerType.CLIENT_LONG_FLAG | PowerType.CLIENT_PROTOCOL_41 | PowerType.CLIENT_SECURE_CONNECTION);
	}

}
