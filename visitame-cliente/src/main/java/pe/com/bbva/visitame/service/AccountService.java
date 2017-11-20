package pe.com.bbva.visitame.service;

import java.util.Map;

public interface AccountService {

	public  Map<String, Object> getCustomer(String documentNumber , String documentType);
	
	public  Map<String, Object> validarUsuario(String documentNumber , String documentType , String desDocumentType , String captchaResponse , String ipRemote , String codOficina);
	
	public  Map<String, Object> actualizarDatosContacto( String documentNumber , String documentType , String email , String telefono , String tipoOperador,String nroTicket,String direccion );
	
}
