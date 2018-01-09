package zh.binlog.dataBean.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import zh.binlog.dataBean.IDataBean;
import zh.binlog.util.ByteUtil;
import zh.binlog.util.CommandType;

public class RequestBinlogDumpDataBean implements IDataBean {

	private long serverId;
	private String binlogFilename;
	private long binlogPosition;

	public RequestBinlogDumpDataBean(long serverId, String binlogFilename, long binlogPosition) {
		this.serverId = serverId;
		this.binlogFilename = binlogFilename;
		this.binlogPosition = binlogPosition;
	}

	@Override
	public byte[] toByteArray() throws Exception {
		byte[] positionBytes = ByteUtil.writeLong(this.binlogPosition, 4);
		byte[] flagBytes = ByteUtil.writeInt(0, 2);
		byte[] serverIdBytes = ByteUtil.writeLong(this.serverId, 4);
		byte[] binlogFileNameBytes = this.binlogFilename.getBytes();

		ByteBuf byteBuf = Unpooled.buffer();
		byteBuf.writeByte(CommandType.COM_BINLOG_DUMP);
		byteBuf.writeBytes(positionBytes);
		byteBuf.writeBytes(flagBytes);
		byteBuf.writeBytes(serverIdBytes);
		byteBuf.writeBytes(binlogFileNameBytes);

		return byteBuf.array();
	}

}
