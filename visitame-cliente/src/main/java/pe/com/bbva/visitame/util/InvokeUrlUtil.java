package pe.com.bbva.visitame.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class InvokeUrlUtil {

	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static StringBuffer invokeGetUrl(String url) {

		URLConnection invokeConection;
		BufferedReader in;
		HttpURLConnection httpURLConnection = null;
		try {
			URL invokeUrl = new URL(url);
			invokeConection = invokeUrl.openConnection();
			httpURLConnection = (HttpURLConnection) invokeConection;
			
			 if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                 in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream() , "UTF-8" ));
             } else {
            	 in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream() , "UTF-8" ));
             }
			
			String inputLine;

			StringBuffer strResponse = new StringBuffer(StringUtils.EMPTY);

			while ((inputLine = in.readLine()) != null) {
				strResponse.append(inputLine);
			}
			in.close();
			return strResponse;
		} catch (Exception e) {}

		return null;
	}
	
	public static StringBuffer invokeNoGetUrl(String url, String metodo , Map<String, Object> params) {
		return InvokeUrlUtil.invokeNoGetUrl(url, metodo, params, null);
	}
	
	public static StringBuffer invokeNoGetUrl(String url, String metodo , Map<String, Object> params , String subMetodo) {
		URLConnection invokeConection;
		BufferedReader in;
		HttpURLConnection httpURLConnection = null;
		try {
			URL invokeUrl = new URL(url+"?");

//	        StringBuilder postData = new StringBuilder();
//	        for (Map.Entry<String,Object> param : params.entrySet()) {
//	        	if(param.getValue()!=null) {
//		            if (postData.length() != 0) postData.append("&");
//		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//		            postData.append("=");
//		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//	        	}
//	        }
	        
	        Gson gson = new Gson();
            String json = gson.toJson(params);

	        //byte[] postDataBytes = postData.toString().getBytes("UTF-8");
			
			invokeConection = invokeUrl.openConnection();
			httpURLConnection = (HttpURLConnection) invokeConection;
			httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
            httpURLConnection.setRequestMethod(metodo);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            if(subMetodo != null) {
            	httpURLConnection.setRequestProperty("X-HTTP-Method-Override", subMetodo);
            }
            httpURLConnection.setUseCaches (false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.getOutputStream().write(json.getBytes("UTF-8"));
            httpURLConnection.getOutputStream().flush();
            httpURLConnection.getOutputStream().close();
            
			 if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                 in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream() , "UTF-8" ));
             } else {
            	 in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream() , "UTF-8" ));
             }
			
			String inputLine;

			StringBuffer strResponse = new StringBuffer(StringUtils.EMPTY);

			while ((inputLine = in.readLine()) != null) {
				strResponse.append(inputLine);
			}
			in.close();
			return strResponse;
		} catch (Exception e) {}

		return null;
	}

}
