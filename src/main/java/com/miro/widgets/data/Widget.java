package com.miro.widgets.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;

	  private Double width;
	  private Double height;
	  private Date lastModification;
	  private Integer xValue;
	  private Integer yValue;
	  private Integer zIndex;
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
