package pe.com.bbva.visitame.service.impl;

import java.net.ConnectException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.bbva.visitame.helper.ConfiguracionHelper;
import pe.com.bbva.visitame.service.ConfiguracionService;
import pe.com.bbva.visitame.util.GsonMapperUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {

	@Autowired
	private ConfiguracionHelper configuracionHelper;
	
	public Map<String, Object> obtenerValoresPorCodLista(String codLista) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = configuracionHelper.obtenerValores(codLista);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

	public Map<String, Object> obtenerParametro(String codigoParametro) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = configuracionHelper.obtenerParametro(codigoParametro);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

}
