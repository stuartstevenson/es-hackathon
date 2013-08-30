package com.rightmove.es.domain;

import org.apache.commons.lang.StringUtils;

public class Property {

	private Long id;
	private String incode;
	private String outcode;
	private String summary;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIncode() {
		return incode;
	}
	public void setIncode(String incode) {
		this.incode = incode;
	}
	public String getOutcode() {
		return outcode;
	}
	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((incode == null) ? 0 : incode.hashCode());
		result = prime * result + ((outcode == null) ? 0 : outcode.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (incode == null) {
			if (other.incode != null)
				return false;
		} else if (!incode.equals(other.incode))
			return false;
		if (outcode == null) {
			if (other.outcode != null)
				return false;
		} else if (!outcode.equals(other.outcode))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Property [id=" + id + ", incode=" + incode + ", outcode="
				+ outcode + ", summary=" + StringUtils.substring(summary, 0, 7) + "...]"; // StringUtils avoids null pointer
	}

}
