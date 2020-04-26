package com.miro.widgets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import com.miro.widgets.data.Widget;

public interface WidgetRepository {
	Page<Widget> GetSortedList(@Nullable Integer page, @Nullable Integer size);
	Optional<Widget> GetWidgetById(Long id);
	Widget CreateNewWidget(Integer xValue, Integer yValue, double weight, double height, @Nullable Integer zIndex);
	Optional<Widget> EditWidget( @Nullable Long id, @Nullable Integer xValue, @Nullable Integer yValue, @Nullable Double weight, @Nullable Double height, @Nullable Integer zIndex);
	boolean DeleteWidget(Long id);
	Integer GetMaxIncrementedZindex(List<Widget> currentList);
	void IncrementZIndex(Widget widget);
	void UpdateZIndexes(Integer zIndex, List<Widget> currentList);
}
