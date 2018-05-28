/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sample")
@Getter
@Setter
public class Sample {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "text")
	private String text;
	
	@Setter(AccessLevel.NONE)
	@Column(name = "create_date")
	private long createDate;
	
	@Column(name = "lastmod_date")
	private long lastmodDate;
	
	public Sample() {
		this.id = UUID.randomUUID().toString();
		this.text = "";
		this.createDate = System.currentTimeMillis();
		this.lastmodDate = System.currentTimeMillis();
	}
	
	public Sample(String json) {
		JSONObject o = new JSONObject(json);	
		if(!o.has("id") || o.getString("id").equals("")) {
			this.id = UUID.randomUUID().toString();
		}else {
			this.id = o.getString("id");
		}
		this.text = o.getString("text");
		if(o.has("createDate")) {
			this.createDate = o.getLong("createDate");
		} else {
			this.createDate = System.currentTimeMillis();
		}
		this.lastmodDate = System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		JSONObject o = new JSONObject();
		o.put("id", this.getId());
		o.put("text", this.getText());
		o.put("createDate", this.getCreateDate());
		o.put("lastmodDate", this.getLastmodDate());
		return o.toString();
	}
}
