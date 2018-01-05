package zh.binlog;

public class ServerStart {

	public static void main(String[] args) {
		new Thread(new ClientServer()).start();
	}
}
