package zh.binlog.dataBean;

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

	public DataPackage(int sequenceId, Object content) {
		this.header = new Header(0, sequenceId);
		this.content = content;
	}

	/**
	 * 对数据包编码
	 * 
	 * @param out
	 * @throws Exception
	 */
	public void encodeAsByteBuf(ByteBuf out) throws Exception {
		IDataBean databean = (IDataBean) content;
		byte dataBytes[] = databean.toByteArray();
		out.writeBytes(ByteUtil.writeInt(dataBytes.length, 3));
		out.writeByte((byte) header.getSequenceId());
		out.writeBytes(dataBytes);
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
