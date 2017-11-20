package pe.com.bbva.visitame.service.impl;

import java.net.ConnectException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.visitame.helper.GeolocalizacionHelper;
import pe.com.bbva.visitame.service.GeolocalizacionService;
import pe.com.bbva.visitame.util.GsonMapperUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Service
public class GeolocalizacionServiceImpl implements GeolocalizacionService {

	@Autowired
	private GeolocalizacionHelper geolocalizacionHelper;
	
	public Map<String, Object> listarUnidadAtencion(String latitud, String longitud, String radius, String filter,
			String startAt, String limit, String type) {	
			Map<String, Object> result = null;
			try {
				ZicResult resultJsonString = geolocalizacionHelper.listarUnidadAtencion(latitud, longitud, radius, filter, startAt, limit, type);
				result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
			} catch (ConnectException e) {
				result = GsonMapperUtil.getMapDefaultError();
			}
			return result;}

}
