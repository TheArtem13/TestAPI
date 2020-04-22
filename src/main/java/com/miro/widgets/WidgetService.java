package com.miro.widgets;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.miro.widgets.data.Widget;
import com.miro.widgets.repository.WidgetRepository;


@Component
public class WidgetService {
	
	@Autowired
    WidgetRepository repository;
	
//	@Autowired
//	WidgetContainer widgetContainer;
	
	public Page<Widget> GetSortedList(@Nullable Integer page, @Nullable Integer size){
		Integer sizeVal = 10;
		Integer pageVal = 0;
		if(size != null) {
			sizeVal = size;
		}
		if(page != null) {
			pageVal = page;
		}
		Pageable paging = PageRequest.of(pageVal, sizeVal, Sort.by("zIndex").ascending());
		Page<Widget> pagedResult = repository.findAll(paging);
		return pagedResult;
	}
	
	public Optional<Widget> GetWidgetById(Long id) {
		Optional<Widget> result = repository.findById(id);
		return result;
	}
	
	public Widget CreateNewWidget(Integer xValue, Integer yValue, double weight, double height, @Nullable Integer zIndex) {
		List<Widget> currentList = (List<Widget>) repository.findAll();
		Widget newWidget = new Widget();
		newWidget.setxValue(xValue);
		newWidget.setyValue(yValue);
		newWidget.setWeight(weight);
		newWidget.setHeight(height);
		newWidget.setLastModification(new Date());
		if(zIndex != null) {
			//find and update all other indexes
			newWidget.setzIndex(zIndex);
			this.UpdateZIndexes(zIndex, currentList);
		}else {
			//find max z-index and set +1
			newWidget.setzIndex(GetMaxZindex(currentList));
		}
		
		repository.save(newWidget);
		return newWidget;
	}
	
	public Optional<Widget> EditWidget( @Nullable Long id, @Nullable Integer xValue, @Nullable Integer yValue, @Nullable Double weight, @Nullable Double height, @Nullable Integer zIndex) {
		Optional<Widget> widget = repository.findById(id);
		if(widget != null) {
			widget.get().setLastModification(new Date()); //update last modification
			widget.get().setxValue((xValue != null) ? xValue : widget.get().getxValue()); //if this value not null, update this
			widget.get().setyValue((yValue != null) ? yValue : widget.get().getyValue());
			widget.get().setWeight((weight != null) ? weight : widget.get().getWeight());
			widget.get().setHeight((height != null) ? height : widget.get().getHeight());
//			widget.get().setzIndex((zIndex != null) ? zIndex : widget.get().getzIndex());
			if(zIndex != null) {
				this.UpdateZIndexes(zIndex, (List<Widget>) repository.findAll()); //update z-index in other widgets
				widget.get().setzIndex(zIndex);
			}
		}
		return widget;
	}
	
	public void DeleteWidget(Long id) {
		Optional<Widget> widget = repository.findById(id);
		if(widget != null) {
			long thisId = widget.get().getId();
			repository.deleteById(thisId);
//			widgetContainer.widgetsList.remove(widget.get());
		}
	}
	
//	private Integer GetNewIdetifier(List<Widget> currentList) { //Find id for new widget
//		Integer newItemId = 1;
//		if(currentList.size() > 0) {
//			Widget maxIdWidget = Collections.max(currentList, Comparator.comparing(s -> s.getId()));
//			newItemId = maxIdWidget.getId() + 1;
//		}
//		return newItemId;
//	}
	
	private Integer GetMaxZindex(List<Widget> currentList) { //Find max z-index in exist widgets
		Integer result = 1;
		if(currentList.size() > 0) {
			Widget maxZindexWidget = Collections.max(currentList, Comparator.comparing(s -> s.getzIndex()));
			result = maxZindexWidget.getzIndex() + 1;
		}
		return result;
	}
	
	private void IncrementZIndex(Widget widget) { //++ current z-index
		Integer zIndex = widget.getzIndex();
		widget.setzIndex(zIndex + 1);
	}
	
	private void UpdateZIndexes(Integer zIndex, List<Widget> currentList) { //Update z-indexes after insert new
		List<Widget> upperWidgets = currentList.stream()
				.filter(p -> p.getzIndex() >= zIndex).collect(Collectors.toList());
		for (Widget widget : upperWidgets) {
			IncrementZIndex(widget);
		}
	}

	

}