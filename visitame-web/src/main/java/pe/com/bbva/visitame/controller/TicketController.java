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

import pe.com.bbva.visitame.service.TicketService;
import pe.com.bbva.visitame.util.Constantes;

@RestController
@RequestMapping({Constantes.MAPPING_SERVICE})
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TicketController {
	
	private final String PATH_GEOLOCALIZACION = "/oficina";
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping(PATH_GEOLOCALIZACION+"/ticket")
	public ResponseEntity  listarUnidadAtencion( String codOficina , String dni ){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(ticketService.generarTicket(codOficina, dni));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}

}
