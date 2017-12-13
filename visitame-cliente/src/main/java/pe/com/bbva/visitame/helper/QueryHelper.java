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
public class QueryHelper {

	private static final Logger  logger = LogManager.getLogger(QueryHelper.class);
	private static final String LOG_PROMPT = "QueryHelper > ";
	
	public final static String executeQuery  = "select";
	public final static String executeUpdate = "update";
	public final static String executeScript = "script";
	
	@Value("${visitame.servicio.rest.base}")
	private String urlVisitameApiService;
	
	public ZicResult executeQuery(String query , String tipo) throws ConnectException  {
		
		logger.info(LOG_PROMPT+" executeQuery > ("+query+", "+tipo+")");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		if(executeQuery.equals(tipo)) {
			url.append(EndPointsUtil.END_POINTS_QUERY.SELECT);
		}else if(executeUpdate.equals(tipo)) {
			url.append(EndPointsUtil.END_POINTS_QUERY.INSERT_UPDATE);
		}else if(executeScript.equals(tipo)) {
			url.append(EndPointsUtil.END_POINTS_QUERY.SCRIPT);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("query", query);
		
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeNoGetUrl(url.toString(),EndPointsUtil.HTTP_METODOS.POST, params));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
		
	 }
	
}
