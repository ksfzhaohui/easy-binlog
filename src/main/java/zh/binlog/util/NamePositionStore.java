package zh.binlog.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * binlog名称和位置存储器
 * 
 * @author hui.zhao.cfs
 *
 */
public class NamePositionStore {

	private static Logger log = LoggerFactory.getLogger(NamePositionStore.class);

	public static final String BINLOG_NAME = "binlogName";
	public static final String BINLOG_POSITIION = "binlogPosition";

	private static Map<String, String> binlogMap = new HashMap<String, String>();

	private static String lineSeparator = (String) System.getProperties().get("line.separator");
	private static String localStoreUrl = "namePosition";

	static {
		loadNamePosition();
	}

	public static synchronized Map<String, String> loadNamePosition() {
		binlogMap = load();
		return binlogMap;
	}

	public static synchronized Map<String, String> getNamePosition() {
		return binlogMap;
	}

	public static synchronized void putNamePosition(String binlogName, long binlogPosition) {
		binlogMap.put(BINLOG_NAME, binlogName);
		binlogMap.put(BINLOG_POSITIION, binlogPosition + "");

		store(binlogMap);
	}

	public static synchronized void putNamePosition(long binlogPosition) {
		binlogMap.put(BINLOG_POSITIION, binlogPosition + "");
		store(binlogMap);
	}

	/**
	 * 存储配置数据到文件
	 * 
	 * @param map
	 */
	private static synchronized void store(Map<String, String> map) {
		Iterator<String> keyItor = map.keySet().iterator();
		Writer fw = null;
		try {
			fw = new BufferedWriter(new FileWriter(new File(localStoreUrl)));
			while (keyItor.hasNext()) {
				String key = (String) keyItor.next();
				fw.write(key + "=" + (String) map.get(key) + lineSeparator);
			}
			log.info("刷新本地存储信息成功，调整内容:" + map.toString());
		} catch (IOException e) {
			log.error("更新本地存储配置信息失败", e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
			fw = null;
		}
	}

	/**
	 * 从文件中加载存储的配置数据
	 * 
	 * @return
	 */
	private static synchronized HashMap<String, String> load() {
		HashMap<String, String> map = new HashMap<String, String>();
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(localStoreUrl)));
			String line = bfr.readLine();
			while (line != null) {
				if ((line.trim().length() != 0) && (line.trim().indexOf("#") != 0)) {
					int idx = line.indexOf("=");
					if (idx != -1) {
						map.put(new String(line.substring(0, idx)), new String(line.substring(idx + 1)));
					}
					line = bfr.readLine();
				}
			}
			if (null != bfr) {
				try {
					bfr.close();
				} catch (IOException e) {
				}
			}
			bfr = null;
			log.info("从本地存储加载配置，内容:" + map.toString());
		} catch (Exception e) {
			log.error("从本地存储{}加载配置信息失败", localStoreUrl, e);
			throw new RuntimeException(e.getMessage());
		} finally {
			if (null != bfr) {
				try {
					bfr.close();
				} catch (IOException e) {
				}
			}
			bfr = null;
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("binlogName", "bin-log.000002");
		map.put("binlogPosition", "583");

		NamePositionStore.store(map);

		Map<String, String> loadMap = NamePositionStore.load();
		System.out.println(loadMap.toString());
	}

}
