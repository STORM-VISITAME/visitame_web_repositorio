package pe.com.bbva.visitame.service.impl;

import java.net.ConnectException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.visitame.helper.AccountHelper;
import pe.com.bbva.visitame.service.AccountService;
import pe.com.bbva.visitame.util.GsonMapperUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Service
public class AccountServiceimpl implements AccountService {

	@Autowired
	private AccountHelper accountHelper;
	
	public Map<String, Object> getCustomer(String documentNumber, String documentType) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = accountHelper.getCustomer(documentNumber, documentType);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

	public Map<String, Object> validarUsuario(String documentNumber, String documentType, String desDocumentType,
			String captchaResponse, String ipRemote, String codOficina) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = accountHelper.validarUsuario(documentNumber, documentType, desDocumentType, captchaResponse, ipRemote, codOficina);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

	public Map<String, Object> actualizarDatosContacto(String documentNumber, String documentType, String email,
			String telefono, String tipoOperador, String nroTicket,String direccion) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = accountHelper.actualizarDatosContacto(documentNumber, documentType, email, telefono, tipoOperador,nroTicket,direccion);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

}
