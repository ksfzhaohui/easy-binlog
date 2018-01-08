package zh.binlog.bean;

import io.netty.buffer.ByteBuf;
import zh.binlog.util.ByteUtil;

/**
 * mysql数据包
 * 
 * @author zhaohui
 *
 */
public class DataPackage {

	/** 数据包头 **/
	private Header header;
	/** 数据包内容 **/
	private Object content;

	public DataPackage(Header header, Object content) {
		super();
		this.header = header;
		this.content = content;
	}

	/**
	 * 对数据包编码
	 * 
	 * @param out
	 */
	public void encodeAsByteBuf(ByteBuf out) {
		out.writeBytes(ByteUtil.writeInt(header.getPackageLen(), 3));
		out.writeByte((byte) header.getSequenceId());
		out.writeBytes((ByteBuf) content);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
