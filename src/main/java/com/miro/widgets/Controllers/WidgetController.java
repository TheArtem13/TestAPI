package com.miro.widgets.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.google.common.util.concurrent.RateLimiter;
import com.miro.widgets.WidgetService;
import com.miro.widgets.data.Widget;

@Controller
//@RequestMapping(path="/widgets")
public class WidgetController {
	
	@Autowired
	private WidgetService widgetService;
	
//	@Autowired
//    WidgetRepository repository;
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Widget>  getAllWidgets(Integer page, Integer size) {
		Page<Widget> result = widgetService.GetSortedList(page, size);
		return result;
		
	  }
	
	@GetMapping(path="/widget")
	public @ResponseBody Optional<Widget> GetWidgetById(Long id){
		Optional<Widget> widget = widgetService.GetWidgetById(id);
		return widget;
	}
	
	@PostMapping(path="/")
	public @ResponseBody Widget setNewWidget(Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Widget newWidget = widgetService.CreateNewWidget(xValue, yValue, weight, height, zIndex);
		return newWidget;
	}
	
	@PostMapping(path="/edit")
	public @ResponseBody Optional<Widget> editWidget(long id, Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Optional<Widget> widget = widgetService.EditWidget(id, xValue, yValue, weight, height, zIndex);
		return widget;
	}
	
	@DeleteMapping(path="/")
	public @ResponseBody Iterable<Widget> getDeleteWidget(long id){
		widgetService.DeleteWidget(id);
		Iterable<Widget> result = widgetService.GetSortedList(0, 10);
	    return result;
	}
	
	
	
}
