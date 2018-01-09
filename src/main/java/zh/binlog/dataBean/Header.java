package zh.binlog.dataBean;

public class Header {

	/** 包体内容长度 **/
	private int packageLen;
	private int sequenceId;

	public Header(int packageLen, int sequenceId) {
		super();
		this.packageLen = packageLen;
		this.sequenceId = sequenceId;
	}

	public int getPackageLen() {
		return packageLen;
	}

	public void setPackageLen(int packageLen) {
		this.packageLen = packageLen;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

}
