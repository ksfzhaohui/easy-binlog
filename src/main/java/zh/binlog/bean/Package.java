package zh.binlog.bean;

public class Package {

	private Header header;
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
