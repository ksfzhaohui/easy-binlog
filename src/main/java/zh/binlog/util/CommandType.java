package zh.binlog.util;

public class CommandType {
	
		public static final int COM_SLEEP=0x00;	//（内部线程状态）	（无）
		public static final int COM_QUIT=0x01;	//关闭连接	mysql_close
		public static final int COM_INIT_DB=0x02;	//切换数据库	mysql_select_db
		public static final int COM_QUERY=0x03;	//SQL查询请求	mysql_real_query
		public static final int COM_FIELD_LIST=0x04;	//获取数据表字段信息	mysql_list_fields
		public static final int COM_CREATE_DB=0x05;	//创建数据库	mysql_create_db
		public static final int COM_DROP_DB=0x06;	//删除数据库	mysql_drop_db
		public static final int COM_REFRESH	=0x07;	//清除缓存	mysql_refresh
		public static final int COM_SHUTDOWN	=0x08;	//停止服务器	mysql_shutdown
		public static final int COM_STATISTICS	=0x09;	//获取服务器统计信息	mysql_stat
		public static final int COM_PROCESS_INFO	=0x0A;	//获取当前连接的列表	mysql_list_processes
		public static final int COM_CONNECT	=0x0B;	//（内部线程状态）	（无）
		public static final int COM_PROCESS_KILL	=0x0C;	//中断某个连接	mysql_kill
		public static final int COM_DEBUG	=0x0D;	//保存服务器调试信息	mysql_dump_debug_info
		public static final int COM_PING	=0x0E;	//测试连通性	mysql_ping
		public static final int COM_TIME	=0x0F;	//（内部线程状态）	（无）
		public static final int COM_DELAYED_INSERT	=0x10;	//（内部线程状态）	（无）
		public static final int COM_CHANGE_USER	=0x11;	//重新登陆（不断连接）	mysql_change_user
		public static final int COM_BINLOG_DUMP	=0x12;	//获取二进制日志信息	（无）
		public static final int COM_TABLE_DUMP	=0x13;	//获取数据表结构信息	（无）
		public static final int COM_CONNECT_OUT	=0x14;	//（内部线程状态）	（无）
		public static final int COM_REGISTER_SLAVE	=0x15;	//从服务器向主服务器进行注册	（无）
		public static final int COM_STMT_PREPARE	=0x16;	//预处理SQL语句	mysql_stmt_prepare
		public static final int COM_STMT_EXECUTE	=0x17;	//执行预处理语句	mysql_stmt_execute
		public static final int COM_STMT_SEND_LONG_DATA	=0x18;	//发送BLOB类型的数据	mysql_stmt_send_long_data
		public static final int COM_STMT_CLOSE	=0x19;	//销毁预处理语句	mysql_stmt_close
		public static final int COM_STMT_RESET	=0x1A;	//清除预处理语句参数缓存	mysql_stmt_reset
		public static final int COM_SET_OPTION	=0x1B;	//设置语句选项	mysql_set_server_option
		public static final int COM_STMT_FETCH	=0x1C;	//获取预处理语句的执行结果	mysql_stmt_fetch
}
