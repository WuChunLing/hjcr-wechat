package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.service.QRcodeService;
import com.hjcr.wechat.service.TemplateService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
public class TemplateHandler {

	
	@Autowired
	private TemplateService templateService;
	@Autowired
	private QRcodeService qRcodeService;

	
	/*
	 * 添加模板
	 */
	@RequestMapping(value = "/addTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, Template template) {
		String path;
		ResultMessage result = new ResultMessage();
		try {
			path = qRcodeService.uploadPhoto(file, request);
			template.setTemplatePath(path); // 获取上传保存的路径
			templateService.addTemplate(template); // 添加模板
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("error");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
		
	}

	@ModelAttribute
	public void getTemplatebyupdata(@RequestParam(value = "templateId", required = false) Integer templateId,
			Map<String, Object> map) {
		if (templateId != null) {
			map.put("template", (Object) templateService.getTemplate(templateId));
		}
	}

	/*
	 * 更新模板
	 */
	@RequestMapping(value = "/updateTemplate")
	public ResponseEntity<ResultMessage> updateTemplate(HttpServletRequest request, HttpServletResponse response,
			Template template) {
		ResultMessage result = new ResultMessage();
		try {
			templateService.updateTemplate(template); // 添加模板
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("error");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
		result.setResultInfo("success");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/*
	 * 删除模板
	 */
	@RequestMapping(value = "/deteleTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deteleTemplate(@RequestParam("templateId") int templateId) {
		ResultMessage result = new ResultMessage();
		try {
			templateService.deleTemplate(templateId);
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.setServiceResult(false);
			result.setResultInfo("error");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}

	/*
	 * 修改默认模板
	 */
	public ResponseEntity<ResultMessage> reviseTemplate(@RequestParam("templateId") int templateId) {
		ResultMessage result = new ResultMessage();
		try {
			templateService.deleTemplate(templateId);
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.setServiceResult(false);
			result.setResultInfo("error");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}
	
	/*
	 * 获取所有模板信息
	 */

	@RequestMapping(value = "getAllTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllTemplate() {
		ResultMessage result = new ResultMessage();
		try {
			result.setResultInfo("success");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allTemplate", templateService.getAllTemplate());
			result.setResultParm(map);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}

	/*
	 * 获取模板信息
	 */
	
	@RequestMapping(value = "getTemplate")
	public ResponseEntity<ResultMessage> getAllTemplate(@RequestParam("templateId")int templateId) {
		ResultMessage result = new ResultMessage();
		try {
			result.setResultInfo("success");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allTemplate", templateService.getTemplate(templateId));
			result.setResultParm(map);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}
}
