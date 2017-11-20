package pe.com.bbva.visitame.service;

import java.util.Map;

public interface GeolocalizacionService {

	public Map<String, Object> listarUnidadAtencion( String latitud , String longitud , String radius , String filter , String startAt , String limit , String type );
	
}
