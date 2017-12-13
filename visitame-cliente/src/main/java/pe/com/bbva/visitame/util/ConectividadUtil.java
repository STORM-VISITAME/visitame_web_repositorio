package pe.com.bbva.visitame.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.telnet.TelnetClient;

public class ConectividadUtil {

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";

	public static Map<String, Object> telnet(String ip, Integer puerto) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TelnetClient client = new TelnetClient();
			client.connect(ip, puerto);

			if (client.isConnected()) {
				result.put(SUCCESS, Boolean.TRUE);
				result.put(MESSAGE, "Conexion exitosa");
			} else {
				result.put(SUCCESS, Boolean.FALSE);
				result.put(MESSAGE, "No se pudo establecer la conexion");
			}

		} catch (Exception e) {
			result.put(SUCCESS, Boolean.FALSE);
			result.put(MESSAGE, e.getMessage());
		}
		return result;
	}

	public static Map<String, Object> ping(String ipaddress) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			final InetAddress host = InetAddress.getByName(ipaddress);
			try {
				if (host.isReachable(4000)) {
					result.put(SUCCESS, Boolean.TRUE);
					result.put(MESSAGE, "Conexión exitosa");
				} else {
					result.put(SUCCESS, Boolean.FALSE);
					result.put(MESSAGE, "No se pudo establecer la conexión");
				}
			} catch (IOException e) {
				result.put(SUCCESS, Boolean.FALSE);
				result.put(MESSAGE, e.getMessage());
			}
		} catch (UnknownHostException e) {
			result.put(SUCCESS, Boolean.FALSE);
			result.put(MESSAGE, "No se pudo establecer la conexión");
		}
		return result;
	}

}
