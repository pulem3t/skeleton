/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.bvnk3r.skeleton.dao.SampleDAO;
import org.bvnk3r.skeleton.entity.Sample;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class SampleDAOHibernate implements SampleDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;
	private Logger logger = Logger.getLogger(SampleDAOHibernate.class);

	@SuppressWarnings("unchecked")
	public List<Sample> getSamples(int page, int count) {
		try {
			String hql = "FROM Sample";
			return (List<Sample>) entityManager.createQuery(hql).setFirstResult((page-1)*count).setMaxResults(count).getResultList();
		} catch (Exception e) {
			logger.error(null, e);
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sample> getSamples(String authorId, int page, int count) {
		try {
			String hql = "FROM Sample as sample WHERE sample.author.id = '"+authorId+"'";
			return (List<Sample>) entityManager.createQuery(hql).setFirstResult((page-1)*count).setMaxResults(count).getResultList();
		} catch (Exception e) {
			logger.error(null, e);
			return new ArrayList<>();
		}
	}

	public Sample getSample(String sampleId) {
		try {
			return entityManager.find(Sample.class, sampleId);
		} catch (Exception e) {
			logger.error(null, e);
			return null;
		}
	}

	public String addSample(Sample sample) {
		try {
			entityManager.persist(sample);
			return sample.getId();
		} catch (Exception e) {
			logger.error(null, e);
			return null;
		}
	}

	public void deleteSample(String sampleId) {
		try {
			entityManager.remove(getSample(sampleId));
		} catch (Exception e) {
			logger.error(null, e);
		}

	}

	public void updateSample(Sample sample) {
		try {
			entityManager.merge(sample);
		} catch (Exception e) {
			logger.error(null, e);
		}
	}
}
