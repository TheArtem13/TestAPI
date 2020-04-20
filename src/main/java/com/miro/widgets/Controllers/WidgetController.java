package com.miro.widgets.Controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miro.widgets.Widget;
import com.miro.widgets.WidgetService;

@Controller
//@RequestMapping(path="/widgets")
public class WidgetController {
	@Autowired
	private WidgetService widgetsContainer;

	@GetMapping(path="/")
	  public @ResponseBody Iterable<Widget> getAllWidgets() {
//		Iterable<Widget> result = widgetsContainer.getWidgets();
		Iterable<Widget> result = widgetsContainer.GetSortedList();
	    return result;
	  }
	
	@GetMapping(path="/widget")
	public @ResponseBody Optional<Widget> GetWidgetById(Integer id){
		Optional<Widget> widget = widgetsContainer.GetWidgetById(id);
		return widget;
	}
	
	@PostMapping(path="/")
	public @ResponseBody Widget setNewWidget(Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Widget newWidget = widgetsContainer.CreateNewWidget(xValue, yValue, weight, height, zIndex);
		return newWidget;
	}
	
	@PostMapping(path="/edit")
	public @ResponseBody Optional<Widget> editWidget(Integer id, Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Optional<Widget> widget = widgetsContainer.EditWidget(id, xValue, yValue, weight, height, zIndex);
		return widget;
	}
	
	@DeleteMapping(path="/")
	public @ResponseBody Iterable<Widget> getDeleteWidget(Integer id){
		widgetsContainer.DeleteWidget(id);
		Iterable<Widget> result = widgetsContainer.getWidgets();
	    return result;
	}
}
