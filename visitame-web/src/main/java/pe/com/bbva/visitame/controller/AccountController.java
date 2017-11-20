package pe.com.bbva.visitame.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bbva.visitame.service.AccountService;
import pe.com.bbva.visitame.util.Constantes;

@RestController
@RequestMapping({Constantes.MAPPING_SERVICE})
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AccountController {

	private final String PATH_ACCOUNT = "/account";
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(PATH_ACCOUNT+"/getCustomer")
	public ResponseEntity  getCustomer(String documentNumber, String documentType){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(accountService.getCustomer(documentNumber, documentType));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping(PATH_ACCOUNT+"/validarUsuario")
	public ResponseEntity  validarUsuario(String documentNumber, String documentType, String desDocumentType,
			String captchaResponse, String ipRemote, String codOficina){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(accountService.validarUsuario(documentNumber, documentType, desDocumentType, captchaResponse, ipRemote, codOficina));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping(PATH_ACCOUNT+"/actualizarDatosContacto")
	public ResponseEntity  actualizarDatosContacto(String documentNumber, String documentType, String email,
			String telefono, String tipoOperador,String nroTicket,String direccion){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(accountService.actualizarDatosContacto(documentNumber, documentType, email, telefono, tipoOperador,nroTicket,direccion));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("errorMessage", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
}
