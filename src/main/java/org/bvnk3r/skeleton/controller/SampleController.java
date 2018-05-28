/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bvnk3r.skeleton.entity.Sample;
import org.bvnk3r.skeleton.exception.BadRequestException;
import org.bvnk3r.skeleton.service.SampleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;
	private Logger logger = Logger.getLogger(SampleController.class);

	@PostMapping(value="/add_sample", consumes = "application/json; charset=utf-8", 
			produces = "application/json; charset=utf-8")
	public @ResponseBody String addSample(@RequestBody String json) {
		JSONObject o = new JSONObject();
		
		try {
			if(json == null || json.equals("")) {
				throw(new BadRequestException(json));
			}
			
			String sampleId = sampleService.addSample(json);
			logger.info("SAMPLES: Added sample with id = " + sampleId);
			
			o.put("sampleId", sampleId);
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("sampleId", "");
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}

	@GetMapping(value="/delete_sample/{sampleId}", produces = "application/json; charset=utf-8")
	public @ResponseBody String delSample(@PathVariable("sampleId") String sampleId) {
		JSONObject o = new JSONObject();
		
		try {
			if(sampleId == null || sampleId.equals("")) {
				throw(new BadRequestException(sampleId));
			}
			
			logger.info("SAMPLES: Deleting sample with id = " + sampleId);
			sampleService.deleteSample(sampleId);
			
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}

	@PostMapping(value="/update_sample", consumes = "application/json; charset=utf-8", 
			produces = "application/json; charset=utf-8")
	public @ResponseBody String updateSample(@RequestBody String json) {
		JSONObject o = new JSONObject();
		
		try {
			if(json == null || json.equals("")) {
				throw(new BadRequestException(json));
			}
			
			logger.info("SAMPLES: Updating sample with id = " + new JSONObject(json)
					.getJSONObject("sample").getString("id"));
			sampleService.updateSample(json);
			
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}

	@GetMapping(value="/get_sample/{sampleId}", produces = "application/json; charset=utf-8")
	public @ResponseBody String getSample(@PathVariable("sampleId") String sampleId) {
		JSONObject o = new JSONObject();
		
		try {
			if(sampleId == null || sampleId.equals("")) {
				throw(new BadRequestException(sampleId));
			}
			
			Sample sample = sampleService.getSample(sampleId);
			
			o.put("sample", sample);
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}
	
	@PostMapping(value="/get_samples/", produces = "application/json; charset=utf-8")
	public @ResponseBody String getSamples(@RequestBody String json) {
		JSONObject o = new JSONObject();
		
		try {
			if(json == null || json.equals("")) {
				throw(new BadRequestException(json));
			}
			
			List<Sample> samples = sampleService.getSamples(json);
			//Хак, ибо пока не понял откуда StackOverflow
			List<JSONObject> res = new ArrayList<>();
			samples.stream().forEach(c -> res.add(new JSONObject(c.toString())));
			
			o.put("samples", res);
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("messages", new ArrayList<Sample>());
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}
}
