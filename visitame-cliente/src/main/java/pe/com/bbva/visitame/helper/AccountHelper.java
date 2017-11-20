package pe.com.bbva.visitame.helper;

import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pe.com.bbva.visitame.util.EndPointsUtil;
import pe.com.bbva.visitame.util.InvokeUrlUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Component
public class AccountHelper {
	
	private static final Logger  logger = LogManager.getLogger(AccountHelper.class);
	private static final String LOG_PROMPT = "AccountHelper > ";
	
	@Value("${visitame.servicio.rest.base}")
	private String urlVisitameApiService;

	public ZicResult getCustomer(String documentNumber , String documentType) throws ConnectException  {
		
		logger.info(LOG_PROMPT+" getCustomer > ("+documentNumber+", "+documentType+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_ACCOUNT.GET_CUSTOMER+"?documentNumber={0}&documentType={1}&test={0}"
						,documentNumber
						,documentType
						)
				);
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
		
	}
	
	public ZicResult validarUsuario(String documentNumber , String documentType , String desDocumentType , String captchaResponse , String ipRemote , String codOficina) throws ConnectException  {
		
		logger.info(LOG_PROMPT+" validarUsuario > ("+documentNumber+" , "+documentType+" , "+desDocumentType+" , "+captchaResponse+" , "+ipRemote+" , "+codOficina+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		url.append(EndPointsUtil.END_POINTS_ACCOUNT.VALIDAR_USUARIO);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("documentNumber", documentNumber);
		params.put("documentType", documentType);
		params.put("desDocumentType", desDocumentType);
		params.put("captchaResponse", captchaResponse);
		params.put("ipRemote", ipRemote);
		params.put("codOficina", codOficina);
		
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeNoGetUrl(url.toString(),EndPointsUtil.HTTP_METODOS.POST, params));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
	}
	

	public ZicResult actualizarDatosContacto( String documentNumber , String documentType , String email , String telefono , String tipoOperador,String nroTicket, String direccion ) throws ConnectException  {
		
		logger.info(LOG_PROMPT+" actualizarDatosContacto > ("+documentNumber+" , "+documentType+" , "+email+" , "+telefono+" , "+tipoOperador+", "+nroTicket+", "+direccion+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		url.append(EndPointsUtil.END_POINTS_ACCOUNT.ACTUALIZAR_DATOS_CONTACTO);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("documentNumber", documentNumber);
		params.put("documentType", documentType);
		params.put("email", (StringUtils.isNotBlank(email)?email:"") );
		params.put("telefono", (StringUtils.isNotBlank(telefono)?telefono:"") );
		params.put("tipoOperador", (StringUtils.isNotBlank(tipoOperador)?tipoOperador:""));
		params.put("nroTicket", (StringUtils.isNotBlank(nroTicket)?nroTicket:""));
		params.put("direccion", (StringUtils.isNotBlank(direccion)?direccion:""));
		
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeNoGetUrl(url.toString(),EndPointsUtil.HTTP_METODOS.PUT, params));
		} catch (Exception e) {
			resultService.calcularError(e);
			} 
		
		return resultService;
	}
	
public ZicResult actualizarDatosContactoG( String documentNumber , String documentType , String email , String telefono , String tipoOperador,String nroTicket, String direccion ) throws ConnectException  {
		
		logger.info(LOG_PROMPT+" actualizarDatosContacto > ("+documentNumber+" , "+documentType+" , "+email+" , "+telefono+" , "+tipoOperador+", "+nroTicket+", "+direccion+")");
		

		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_ACCOUNT.ACTUALIZAR_DATOS_CONTACTO+"?documentNumber={0}&documentType={1}&email={2}"
						,documentNumber
						,documentType
						,email
						)
				);
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		
		return resultService;
	}
	
}
