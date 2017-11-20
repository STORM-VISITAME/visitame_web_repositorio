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
public class GeolocalizacionHelper {
	
	private static final Logger logger = LogManager.getLogger(GeolocalizacionHelper.class);
	private static final String LOG_PROMPT = "GeolocalizacionHelper > ";
	
	@Value("${visitame.servicio.rest.base}")
	private String urlVisitameApiService;
	
	public ZicResult listarUnidadAtencion( String latitud , String longitud , String radius , String filter , String startAt , String limit , String type ) throws ConnectException {
		
		logger.info(LOG_PROMPT+" listarUnidadAtencion > ("+latitud+" , "+longitud+" , "+radius+" , "+filter+" , "+startAt+" , "+limit+" , "+type+" )");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_GEOLOCALIZACION.GET_POIS+"?latitud={0}&longitud={1}&radius={2}&filter={3}&startAt={4}&limit={5}&type={6}"
						,latitud
						,longitud
						,radius
						,filter
						,startAt
						,limit
						,type
						)
				);
		
		
		
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
	}
	
}
