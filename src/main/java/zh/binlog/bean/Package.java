package zh.binlog.bean;

/**
 * mysql数据包
 * 
 * @author zhaohui
 *
 */
public class Package {

	/** 数据包头 **/
	private Header header;
	/** 数据包内容 **/
	private Object content;

	public Package(Header header, Object content) {
		super();
		this.header = header;
		this.content = content;
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
