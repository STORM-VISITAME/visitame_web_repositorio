package pe.com.bbva.visitame.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class GsonMapperUtil {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> stringToMapJson(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
		Gson gson = new Gson();
		map = gson.fromJson(json, Map.class);
		}catch (Exception e) {
			map = GsonMapperUtil.getMapDefaultError();
			
		}
		return map;
	}
	
	public static Map<String, Object> getMapDefaultError() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", 400);
		map.put("statusDescription", "Servicio no disponible");
		map.put("severity", "ALTA");
		map.put("errorMessage", "El servicio no se encuentra disponible");
		return map;
		
	}

}
