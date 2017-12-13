package pe.com.bbva.visitame.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.com.bbva.visitame.service.QueryService;
import pe.com.bbva.visitame.util.ConectividadUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping(value="/tests")
public class TestController {
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("testConexion/test");
		return modelAndView;
	}
	
	@PostMapping("/telnet")
	public ResponseEntity  telnet(String ip , Integer puerto){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(ConectividadUtil.telnet(ip, puerto));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("message", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/ping")
	public ResponseEntity  ping(String ip){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(ConectividadUtil.ping(ip));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("message", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/query")
	public ResponseEntity  executorQuery(String query , String tipo){
		Map<String, Object> data = new HashMap();
		try {
			data.putAll(queryService.queryExecuter(query, tipo));
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			data.put("message", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
	}
	

}
