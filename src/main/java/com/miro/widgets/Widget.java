package com.miro.widgets;

import java.util.Date;

public class Widget {
	  private Integer id;

	  private Double weight;
	  private Double height;
	  private Date lastModification;
	  private Integer xValue;
	  private Integer yValue;
	  private Integer zIndex;
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Date getLastModification() {
		return lastModification;
	}
	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}
	public Integer getxValue() {
		return xValue;
	}
	public void setxValue(Integer xValue) {
		this.xValue = xValue;
	}
	public Integer getyValue() {
		return yValue;
	}
	public void setyValue(Integer yValue) {
		this.yValue = yValue;
	}
	public Integer getzIndex() {
		return zIndex;
	}
	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
