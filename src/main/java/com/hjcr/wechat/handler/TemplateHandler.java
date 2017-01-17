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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/addTemplate",method=RequestMethod.POST)
	public ResponseEntity<ResultMessage> addTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, Template template) {
		System.out.println(file);
		String path;
		ResultMessage result = new ResultMessage();
		System.out.println(template);
		try {
			path = qRcodeService.uploadPhoto(file, request);  //保存上传的模板文件
			template.setTemplatePath(path); // 获取上传保存的路径
			templateService.addTemplate(template); // 添加模板
			result.setResultInfo("添加成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("dd");
			e.printStackTrace();
			throw new SecurityException("添加失败");
		}
	}


	/*@ModelAttribute
	public void getTemplatebyupdata(@RequestBody Map<String,Object> templemap,
			Map<String, Object> map) {
		String templateId=(String) templemap.get("templateId");
		
		if (!templateId.equals(null)){
			map.put("template", (Object) templateService.getTemplate(Integer.parseInt(templateId)));
		}
>>>>>>> 3f44769c3760c864ceae2da792fb653efd42b6d7
	}
*/
	/*
	 * 更新模板
	 */
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Template template) {
		ResultMessage result = new ResultMessage();
		try {
			if (template.isTemplateConfirm() == 1) { //判断更新的模板是否设置成默认的模板，如果设置了，则把原来的默认模板给取消了
				templateService.reviseTemplate();   
			}
			templateService.updateTemplate(template); // 添加模板
			result.setResultInfo("更新成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("更新失败"); 
		}
	
	}

	/*
	 * 删除模板
	 */
	@RequestMapping(value = "/deteleTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deteleTemplate(@RequestBody Map<String, Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			int templateId = (int) map.get("templateId");  //获取templateId转化成int
			templateService.deleTemplate(templateId);
			result.setResultInfo("删除成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("删除失败"); 
		}
	}


	/*
	 * 获取所有模板信息
	 */

	@RequestMapping(value = "getAllTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllTemplate() {
		ResultMessage result = new ResultMessage();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allTemplate", templateService.getAllTemplate()); //获取所有模板信息
			result.setResultParm(map);
			result.setResultInfo("获取成功"); 
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败"); 
		}
	}

	/*
	 * 获取模板信息
	 */

	@RequestMapping(value = "getTemplate")
	public ResponseEntity<ResultMessage> getAllTemplate(@RequestBody Map<String, Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			String template = (String) map.get("templateId");//获取查看的模板的id转化成int
			int templateId = Integer.parseInt(template);
			Map<String, Object> Templatemap = new HashMap<String, Object>();
			Templatemap.put("allTemplate", templateService.getTemplate(templateId));
			result.setResultInfo("获取成功");
			result.setResultParm(Templatemap);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败"); 
		}
	}
}
