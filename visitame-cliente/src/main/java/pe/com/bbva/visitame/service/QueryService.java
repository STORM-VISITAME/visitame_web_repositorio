package pe.com.bbva.visitame.service;

import java.util.Map;

public interface QueryService {

	public Map<String, Object> queryExecuter(String query , String tipo);
	
}
