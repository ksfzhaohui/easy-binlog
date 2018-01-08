package zh.binlog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.netty.buffer.ByteBuf;

public class ByteUtil {

	/**
	 * 已'\0'结尾的字符串
	 * 
	 * @param msg
	 * @return
	 */
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
			result[i] = (byte) ((value >> (8 * i)) & 0xFF);
		}
		return result;
	}

	/**
	 * 挑战随机数+密码进行加密 参考：mysql-binlog-connector-java
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
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

	/**
	 * 字节数组转换成十六进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
