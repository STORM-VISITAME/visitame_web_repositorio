package pe.com.bbva.visitame.service.impl;

import java.net.ConnectException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.visitame.helper.TicketHelper;
import pe.com.bbva.visitame.service.TicketService;
import pe.com.bbva.visitame.util.GsonMapperUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketHelper ticketHelper;

	public Map<String, Object> generarTicket(String codOficina, String dni) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = ticketHelper.generarTicket(codOficina, dni);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

}
