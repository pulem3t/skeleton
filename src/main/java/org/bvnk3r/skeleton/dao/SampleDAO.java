/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.dao;

import java.util.List;

import org.bvnk3r.skeleton.entity.Sample;

public interface SampleDAO {
	
	List<Sample> getSamples(int page, int count);
	List<Sample> getSamples(String authorId, int page, int count);
	Sample getSample(String sampleId);
	String addSample(Sample sample);
	void deleteSample(String sampleId);
	void updateSample(Sample sample);
}
