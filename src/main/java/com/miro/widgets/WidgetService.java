package com.miro.widgets;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.lang.Nullable;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
//@Scope("application")
public class WidgetService {
	List<Widget> widgetsList = new LinkedList<Widget>();
	
	public void saveNewWidget(Widget widget) {
		widgetsList.add(widget);
	}
	
	public List<Widget> getWidgets(){
		return widgetsList;
	}
	
	public List<Widget> GetSortedList(){
		Collections.sort(widgetsList, new Comparator<Widget>() {
		      @Override
		      public int compare(final Widget object1, final Widget object2) {
		          return object1.getzIndex().compareTo(object2.getzIndex());
		      }
		  });
		return widgetsList;
	}
	
	public Optional<Widget> GetWidgetById(Integer id) {
		Optional<Widget> result = widgetsList.stream()
		.filter(x -> x.getId() == id).findFirst();
		return result;
	}
	
	public Widget CreateNewWidget(Integer xValue, Integer yValue, double weight, double height, @Nullable Integer zIndex) { //Set new widget in memory
		List<Widget> currentList = getWidgets(); //Get list of exist widgets for fing new id(increment)
		Integer newItemId = GetNewIdetifier(currentList);
		Widget newWidget = new Widget();
		newWidget.setId(newItemId);
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
		
		saveNewWidget(newWidget);
		return newWidget;
	}
	
	public Optional<Widget> EditWidget(Integer id, @Nullable Integer xValue, @Nullable Integer yValue, @Nullable Double weight, @Nullable Double height, @Nullable Integer zIndex) {
		Optional<Widget> widget = GetWidgetById(id);
		if(widget != null) {
			widget.get().setLastModification(new Date()); //update last modification
			widget.get().setxValue((xValue != null) ? xValue : widget.get().getxValue()); //if this value not null, update this
			widget.get().setyValue((yValue != null) ? yValue : widget.get().getyValue());
			widget.get().setWeight((weight != null) ? weight : widget.get().getWeight());
			widget.get().setHeight((height != null) ? height : widget.get().getHeight());
//			widget.get().setzIndex((zIndex != null) ? zIndex : widget.get().getzIndex());
			if(zIndex != null) {
				this.UpdateZIndexes(zIndex, getWidgets()); //update z-index in other widgets
				widget.get().setzIndex(zIndex);
			}
		}
		return widget;
	}
	
	public void DeleteWidget(Integer id) {
		Optional<Widget> widget = GetWidgetById(id);
		if(widget != null) {
			widgetsList.remove(widget.get());
		}
	}
	
	private Integer GetNewIdetifier(List<Widget> currentList) { //Find id for new widget
		Integer newItemId = 1;
		if(currentList.size() > 0) {
			Widget maxIdWidget = Collections.max(currentList, Comparator.comparing(s -> s.getId()));
			newItemId = maxIdWidget.getId() + 1;
		}
		return newItemId;
	}
	
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
	
	private void UpdateZIndexes(Integer zIndex, List<Widget> currentList) {
		List<Widget> upperWidgets = currentList.stream()
				.filter(p -> p.getzIndex() >= zIndex).collect(Collectors.toList());
		for (Widget widget : upperWidgets) {
			IncrementZIndex(widget);
		}
	}
	
	
}