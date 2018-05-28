/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
	@Value("${build.version}")
	private String version;
	@Value("${build.date}")
	private String date;
	@Value("${build.name}")
	private String name;
	private Logger logger = Logger.getLogger(VersionController.class);

	@GetMapping(value="/get_version", produces = "application/json; charset=utf-8")
	public @ResponseBody String getVersion() {
		JSONObject o = new JSONObject();
		
		try {
			o.put("version", version);
			o.put("date", date);
			o.put("name", name);
			o.put("error","");
			o.put("success", true);
		} catch (Exception e) {
			logger.error(null, e);
			o.put("error",e.getMessage());
			o.put("success", false);
		}
		
		return o.toString(4);
	}
}
