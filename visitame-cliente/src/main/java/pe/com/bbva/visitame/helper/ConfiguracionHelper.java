package pe.com.bbva.visitame.helper;

import java.net.ConnectException;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.com.bbva.visitame.util.EndPointsUtil;
import pe.com.bbva.visitame.util.InvokeUrlUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Component
public class ConfiguracionHelper {
	
	private static final Logger  logger = LogManager.getLogger(ConfiguracionHelper.class);
	private static final String LOG_PROMPT = "ConfiguracionHelper > ";
	
	@Value("${visitame.servicio.rest.base}")
	private String urlVisitameApiService;
	
	public ZicResult obtenerValores(String codLista) throws ConnectException {
		
		logger.info(LOG_PROMPT+" obtenerValores > ("+codLista+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_CONFIGURACION.VALORES_BY_COD_LISTA+"?codigoLista={0}"
						,codLista
						)
				);
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) {
			resultService.calcularError(e); } 
		
		return resultService;
		
	}
	
	public ZicResult obtenerParametro(String codigoParametro) throws ConnectException {
		
		logger.info(LOG_PROMPT+" obtenerParametro > ("+codigoParametro+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_CONFIGURACION.PARAMETROS+"?codigoParametro={0}"
						,codigoParametro
						)
				);
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
		
	}
	
	
	
}
