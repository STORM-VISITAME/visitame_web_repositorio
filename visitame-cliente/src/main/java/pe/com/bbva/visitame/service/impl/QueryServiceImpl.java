package pe.com.bbva.visitame.service.impl;

import java.net.ConnectException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.visitame.helper.QueryHelper;
import pe.com.bbva.visitame.service.QueryService;
import pe.com.bbva.visitame.util.GsonMapperUtil;
import pe.com.bbva.visitame.util.ZicResult;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private QueryHelper queryHelper;
	
	public Map<String, Object> queryExecuter(String query, String tipo) {
		Map<String, Object> result = null;
		try {
			ZicResult resultJsonString = queryHelper.executeQuery(query, tipo);
			result = GsonMapperUtil.stringToMapJson(resultJsonString.getEntidad().toString());
		} catch (ConnectException e) {
			result = GsonMapperUtil.getMapDefaultError();
		}
		return result;
	}

}
