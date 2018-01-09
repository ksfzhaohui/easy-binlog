package zh.binlog.event;

import io.netty.buffer.ByteBuf;

public interface IEventParser {

	public void parse(ByteBuf msg, EventHeader eventHeader);

}
