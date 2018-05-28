/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.service;

import java.util.List;

import org.bvnk3r.skeleton.dao.SampleDAO;
import org.bvnk3r.skeleton.entity.Sample;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	
	@Autowired
	private SampleDAO sampleDAO;
	
	public List<Sample> getSamples(String json) {
		JSONObject o = new JSONObject(json);
		return sampleDAO.getSamples(o.getInt("page"), o.getInt("count"));
	}
	
	public Sample getSample(String sampleId) {
		return sampleDAO.getSample(sampleId);
	}
	
	public String addSample(String json) {
		JSONObject o = new JSONObject(json).getJSONObject("sample");
		Sample sample = new Sample(o.toString());
		return sampleDAO.addSample(sample);
	}
	
	public void deleteSample(String sampleId) {
		sampleDAO.deleteSample(sampleId);
	}
	
	public void updateSample(String json) {
		JSONObject o = new JSONObject(json).getJSONObject("sample");
		Sample sample = new Sample(o.toString());
		sample.setLastmodDate(System.currentTimeMillis());
		sampleDAO.updateSample(sample);
	}
}
