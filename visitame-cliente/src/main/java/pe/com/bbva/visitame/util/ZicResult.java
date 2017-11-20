
package pe.com.bbva.visitame.util;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ZicResult {
	
	private static final String SYSTEM_ERROR_CAUSE = "system-error-cause";
	private static final String ERROR_NO_DATA = "noData";
	private static final String ERROR_CODE = "error-code";
	private static final String ERROR_MESSAGE = "error-message";
	
	public static final int ERROR_430 = 430;
	public static final String CODIGO_TSEC_CADUCO = "68";
		
	private String codeError;
	public String getCodeError() { return codeError; }
	public void setCodeError(String codeError) { this.codeError = codeError; }
	
	private String messageError;
	public String getMessageError() { return messageError; }
	public void setMessageError(String messageError) { this.messageError = messageError; } 
	
	private Object entidad;
	public Object getEntidad() { return entidad; }
	public void setEntidad(Object entidad) { this.entidad = entidad; }
	
	private Object[] entidades;
	public Object[] getEntidades() { return entidades; }
	public void setEntidades(Object[] entidades) { this.entidades = entidades; }
	
	public void calcularError(Exception e) throws ConnectException{
		if(e instanceof UnknownHttpStatusCodeException){
			UnknownHttpStatusCodeException error = (UnknownHttpStatusCodeException) e;
			Map<String, String> mapError = obtenerCuerpoError(error.getResponseBodyAsString());
			if(ERROR_430 == error.getRawStatusCode()){				
				setCodeError(mapError.get(ERROR_CODE));
				setMessageError(mapError.get(ERROR_MESSAGE));
			} else {
				setCodeError(mapError.get(ERROR_CODE));
				setMessageError(mapError.get(ERROR_MESSAGE));
			}
		}else if(e instanceof HttpClientErrorException){
			HttpClientErrorException error = (HttpClientErrorException) e;
			Map<String, String> mapError = obtenerCuerpoError(error.getResponseBodyAsString());
			if(error.getStatusCode().is4xxClientError()){				
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);
			} else if(error.getStatusCode().is5xxServerError()){
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);
			} else if(error.getStatusCode().is2xxSuccessful()){
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);
			}
		} else if(e instanceof HttpServerErrorException) {
			HttpServerErrorException error = (HttpServerErrorException) e;
			Map<String, String> mapError = obtenerCuerpoError(error.getResponseBodyAsString());
			if(error.getStatusCode().is4xxClientError()){
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);
			} else if(error.getStatusCode().is5xxServerError()){
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);				
			} else if(error.getStatusCode().is2xxSuccessful()){
				escribirMensajePorCodigoEstatus(error.getStatusCode(), mapError);
			}
		} else { throw new ConnectException(); }
	}
	
	private void escribirMensajePorCodigoEstatus(HttpStatus statusCode, Map<String, String> mapError) {
		if(mapError.containsKey(SYSTEM_ERROR_CAUSE) && mapError.get(ERROR_CODE).equals(ERROR_NO_DATA)){
			String[] codigoMensaje = mapError.get(SYSTEM_ERROR_CAUSE).split(":", -1);
			mapError.put(ERROR_CODE, StringUtils.isNotBlank(codigoMensaje[0]) ? codigoMensaje[0] : StringUtils.EMPTY);
			mapError.put(ERROR_MESSAGE, StringUtils.isNotBlank(codigoMensaje[1]) ? codigoMensaje[1] : StringUtils.EMPTY);
		}
		if(statusCode.compareTo(HttpStatus.FORBIDDEN) <= 0 && StringUtils.equals(mapError.get(ERROR_CODE), CODIGO_TSEC_CADUCO)){
			setCodeError(mapError.get(ERROR_CODE));
			setMessageError(mapError.get(ERROR_MESSAGE));
		}else if(statusCode.compareTo(HttpStatus.CONFLICT) <= 0){
			setCodeError(mapError.get(ERROR_CODE));
			setMessageError(mapError.get(ERROR_MESSAGE));
		} else if(statusCode.compareTo(HttpStatus.INTERNAL_SERVER_ERROR) <= 0){
			setCodeError(mapError.get(ERROR_CODE));
			setMessageError(mapError.get(ERROR_MESSAGE));
		} else if(statusCode.compareTo(HttpStatus.NO_CONTENT) <= 0){
			setCodeError(mapError.get(ERROR_CODE));
			setMessageError(mapError.get(ERROR_MESSAGE));
		} else {
			setCodeError(mapError.get(ERROR_CODE));
			setMessageError(mapError.get(ERROR_MESSAGE));
		}
	}
	
	private Map<String, String> obtenerCuerpoError(String responseBodyAsString) {
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotBlank(responseBodyAsString) && validarFormatJson(responseBodyAsString)){
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = (JsonObject) jsonParser.parse(responseBodyAsString.trim());				
			for(Entry<String, JsonElement> element : jObject.entrySet()){
				map.put(element.getKey(), element.getValue().toString().replace("\"", ""));
			}	
		} else {
			map = new HashMap<String, String>(); 
		}
		return map;
	}
	private boolean validarFormatJson(String bodyRespuesta) {
		JsonParser jsonParser = new JsonParser();
	    try {
	    	jsonParser.parse(bodyRespuesta);
	    } catch (JsonSyntaxException ex) {
	    	 return false;
	    }
		return true;
	}	
}
