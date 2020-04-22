package com.miro.widgets.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miro.widgets.WidgetService;
import com.miro.widgets.data.Widget;
import com.miro.widgets.repository.WidgetRepository;

@Controller
//@RequestMapping(path="/widgets")
public class WidgetController {
	@Autowired
	private WidgetService widgetsContainer;
	
	@Autowired
    WidgetRepository repository;
	
//	@GetMapping(path="/")
//	  public @ResponseBody Iterable<Widget> getAllWidgets() {
//		Iterable<Widget> result = widgetsContainer.GetSortedList();
//	    return result;
//	  }
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Widget>  getAllWidgets(Integer page, Integer size) {
		Page<Widget> result = widgetsContainer.GetSortedList(page, size);
//		Pageable paging = PageRequest.of(0, 10, Sort.by("zIndex").ascending());
//		Page<Widget> pagedResult = repository.findAll(paging);
		return result;
	  }
	
	@GetMapping(path="/widget")
	public @ResponseBody Optional<Widget> GetWidgetById(Long id){
		Optional<Widget> widget = widgetsContainer.GetWidgetById(id);
		return widget;
	}
	
	@PostMapping(path="/")
	public @ResponseBody Widget setNewWidget(Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Widget newWidget = widgetsContainer.CreateNewWidget(xValue, yValue, weight, height, zIndex);
		return newWidget;
	}
	
	@PostMapping(path="/edit")
	public @ResponseBody Optional<Widget> editWidget(long id, Integer xValue, Integer yValue, Double weight, Double height, Integer zIndex) {
		Optional<Widget> widget = widgetsContainer.EditWidget(id, xValue, yValue, weight, height, zIndex);
		return widget;
	}
	
	@DeleteMapping(path="/")
	public @ResponseBody Iterable<Widget> getDeleteWidget(long id){
		widgetsContainer.DeleteWidget(id);
		Iterable<Widget> result = widgetsContainer.GetSortedList(0, 10);
	    return result;
	}
	
	
	
}
