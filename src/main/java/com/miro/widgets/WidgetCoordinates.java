package com.miro.widgets;

import org.springframework.stereotype.Component;

import com.miro.widgets.data.Widget;
import com.miro.widgets.repository._Coordinates;

@Component
public class WidgetCoordinates implements _Coordinates {

	@Override
	public Double getXACoordinate(Widget widget) {
		//down lewt angle. Center minus half width
		Double xVal = Double.valueOf(widget.getxValue());
		Double result = xVal - widget.getWidth() / 2.0;
		return result;
	}

	@Override
	public Double getYACoordinate(Widget widget) {
		//down lewt angle. Center minus half height
		Double yVal = Double.valueOf(widget.getyValue());
		Double result = yVal - widget.getHeight() / 2.0;
		return result;
	}

	@Override
	public Double getXBCoordinate(Widget widget) {
		//up left angle. Center minus half width
		Double xVal = Double.valueOf(widget.getxValue());
		Double result = xVal - widget.getWidth() / 2.0;
		return result;
	}

	@Override
	public Double getYBCoordinate(Widget widget) {
		//up left angle. Center plus half height
		Double yVal = Double.valueOf(widget.getyValue());
		Double result = yVal + widget.getHeight() / 2.0;
		return result;
	}

	@Override
	public Double getXCCoordinate(Widget widget) {
		//up right angle. Center plus half width
		Double xVal = Double.valueOf(widget.getxValue());
		Double result = xVal + widget.getWidth() / 2.0;
		return result;
	}

	@Override
	public Double getYCCoordinate(Widget widget) {
		//up right angle. Center plus half height
		Double yVal = Double.valueOf(widget.getyValue());
		Double result = yVal + widget.getHeight() / 2.0;
		return result;
	}

	@Override
	public Double getXDCoordinate(Widget widget) {
		//down right angle. Center plus half width
		Double xVal = Double.valueOf(widget.getxValue());
		Double result = xVal + widget.getWidth() / 2.0;
		return result;
	}

	@Override
	public Double getYDCoordinate(Widget widget) {
		//down right angle. Center minus half height
		Double yVal = Double.valueOf(widget.getyValue());
		Double result = yVal - widget.getHeight() / 2.0;
		return result;
	}

	//https://ru.onlinemschool.com/pictures/rectangle/rectangle1.png
	
	

}
