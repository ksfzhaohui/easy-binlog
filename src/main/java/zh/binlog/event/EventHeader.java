package zh.binlog.event;

public class EventHeader {
	
	/** 事件header长度 **/
	public static final int EVENT_HEADER_LEN = 19;

	private long timestamp;
	private byte typeCode;
	private long serverId;
	private long eventLen;
	private long nextPosition;
	private int flags;

	@Override
	public String toString() {
		return "EventHeader [timestamp=" + timestamp + ", typeCode=" + typeCode + ", serverId=" + serverId
				+ ", eventLen=" + eventLen + ", nextPosition=" + nextPosition + ", flags=" + flags + "]";
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public byte getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(byte typeCode) {
		this.typeCode = typeCode;
	}

	public long getServerId() {
		return serverId;
	}

	public void setServerId(long serverId) {
		this.serverId = serverId;
	}

	public long getEventLen() {
		return eventLen;
	}

	public void setEventLen(long eventLen) {
		this.eventLen = eventLen;
	}

	public long getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition(long nextPosition) {
		this.nextPosition = nextPosition;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

}
