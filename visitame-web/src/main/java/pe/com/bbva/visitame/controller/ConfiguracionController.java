package pe.com.bbva.visitame.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.bbva.visitame.service.ConfiguracionService;
import pe.com.bbva.visitame.util.Constantes;

@RestController
@RequestMapping({Constantes.MAPPING_SERVICE})
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConfiguracionController {
	
	private final String PATH_CONFIGURACION = "/configuracion";
	
	@Autowired
	private ConfiguracionService configuracionService;

	@GetMapping(PATH_CONFIGURACION+"/valores")
	public ResponseEntity  obtenerValoresPorCodLista(String codigoLista){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(configuracionService.obtenerValoresPorCodLista(codigoLista));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping(PATH_CONFIGURACION+"/parametro")
	public ResponseEntity  obtenerParametro(String codigoParametro){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(configuracionService.obtenerParametro(codigoParametro));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}

}
