package pe.com.bbva.visitame.service;

import java.util.Map;

public interface ConfiguracionService {

	public Map<String, Object> obtenerValoresPorCodLista(String codLista);
	
	public Map<String, Object> obtenerParametro(String codigoParametro);
}
