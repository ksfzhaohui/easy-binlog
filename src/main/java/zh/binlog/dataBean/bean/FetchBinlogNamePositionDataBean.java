package zh.binlog.dataBean.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import zh.binlog.dataBean.IDataBean;
import zh.binlog.util.CommandType;

public class FetchBinlogNamePositionDataBean implements IDataBean {

	private String sql;

	public FetchBinlogNamePositionDataBean(String sql) {
		this.sql = sql;
	}

	@Override
	public byte[] toByteArray() throws Exception {
		byte[] sqlBytes = this.sql.getBytes();
		ByteBuf byteBuf = Unpooled.buffer();
		byteBuf.writeByte(CommandType.COM_QUERY);
		byteBuf.writeBytes(sqlBytes);

		return byteBuf.array();
	}

}
