package pe.com.bbva.visitame.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.bbva.visitame.service.GeolocalizacionService;
import pe.com.bbva.visitame.util.Constantes;

@RestController
@RequestMapping({Constantes.MAPPING_SERVICE})
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeolocalizacionController {

	private final String PATH_GEOLOCALIZACION = "/geolocalizacion";
	
	@Autowired
	private GeolocalizacionService geolocalizacionService;

	@GetMapping(PATH_GEOLOCALIZACION+"/pois")
	public ResponseEntity  registrarUsuarioApp( String latitud , String longitud , String radius , String filter , String startAt , String limit , String type ){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(geolocalizacionService.listarUnidadAtencion(latitud, longitud, radius, filter, startAt, limit, type));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
