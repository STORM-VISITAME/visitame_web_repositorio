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
public class TicketHelper {

	private static final Logger logger = LogManager.getLogger(TicketHelper.class);
	private static final String LOG_PROMPT = "TicketHelper > ";
	
	@Value("${visitame.servicio.rest.base}")
	private String urlVisitameApiService;
	
	public ZicResult generarTicket( String codOficina , String dni ) throws ConnectException {
		
		logger.info(LOG_PROMPT+" generarTicket > ("+codOficina+" , "+dni+" )");
		
		ZicResult resultService = new ZicResult();
		
		StringBuilder url = new StringBuilder(urlVisitameApiService);
		
		StringBuilder parametrosEnvio = new StringBuilder(StringUtils.EMPTY);
		
		parametrosEnvio.append(
				MessageFormat.format(
						EndPointsUtil.END_POINTS_TICKET.GENERATE_TICKET+"?codOficina={0}&dni={1}"
						,codOficina
						,dni
						)
				);
		
		
		
		url.append(parametrosEnvio);
		try {
			resultService.setEntidad(InvokeUrlUtil.invokeGetUrl(url.toString()));
		} catch (Exception e) { resultService.calcularError(e); } 
		
		return resultService;
	}
	
}
