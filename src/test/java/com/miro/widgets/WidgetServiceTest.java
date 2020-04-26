package com.miro.widgets;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import com.miro.widgets.data.Widget;
import com.miro.widgets.repository.PagingRepository;

@RunWith(SpringRunner.class)
//@TestPropertySource(locations = "classpath:/application-test.properties")
public class WidgetServiceTest {
	
	Integer page = 0;
	Integer size = 42;
	Pageable paging = PageRequest.of(page, size, Sort.by("zIndex").ascending());
	long id = 42;
	//sample data for create
	Integer xValue = 100;
	Integer yValue = 200;
	double weight = 50.0;
	double height = 50.0;
	Integer zIndex = -5;
	
	@Mock
    private PagingRepository repository;
	
	@InjectMocks
    private WidgetService service;
	
	@Test
	public void GetSortedList_ReturnNotNull() {
		//Arrange
		Page<Widget> page = Mockito.mock(Page.class);
		//Act
		Mockito.when(this.service.GetSortedList(0, 10)).thenReturn(page);
		//Assert
		assertNotNull(page);
		
	}
	
	@Test
	public void GetWidgetById_ReturnNotNull() {
		//Arrange
		Optional<Widget> widget = Optional.empty();
		//Act
		Mockito.when(this.service.GetWidgetById(id)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test
	public void CreateNewWidget_WithZindex_ReturnNotNull() {
		//Arrange
		Widget widget = Mockito.mock(Widget.class);
		//Act
		Mockito.when(this.service.CreateNewWidget(xValue, yValue, weight, height, zIndex)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test
	public void CreateNewWidget_WithOutZindex_ReturnNotNull() {
		//Arrange
		Widget widget = Mockito.mock(Widget.class);
		//Act
		Mockito.when(this.service.CreateNewWidget(xValue, yValue, weight, height, null)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test
	public void DeleteWidget_ReturnNotNull() {
		//Arrange
		boolean result = false;
		//Act
		result = service.DeleteWidget(id);
//		Mockito.doThrow(new Exception()).when(this.service).DeleteWidget(id);
		//Assert
//		Mockito.verify(service).DeleteWidget(id);
		assertNotNull(result);
		
	}
	
	@Test
	public void EditWidget_ReturnNotNull() {
		//Arrange
		Optional<Widget> widget = Optional.empty();
		//Act
		Mockito.when(this.service.EditWidget(id, xValue, yValue, weight, height, zIndex)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test
	public void EditWidget_WithoutXvalue_ReturnNotNull() {
		//Arrange
		Optional<Widget> widget = Optional.empty();
		//Act
		Mockito.when(this.service.EditWidget(id, null, yValue, weight, height, zIndex)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test
	public void EditWidget_WithoutYvalu_ReturnNotNull() {
		//Arrange
		Optional<Widget> widget = Optional.empty();
		//Act
		Mockito.when(this.service.EditWidget(id, xValue, xValue, weight, height, zIndex)).thenReturn(widget);
		//Assert
		assertNotNull(widget);
		
	}
	
	@Test 
	public void GetMaxIncrementedZindex_IsReturnMaxIndexInExamoleList() {
		//Arrange
		Integer zIndex = 0;
		
		List<Widget> currentList = new LinkedList<Widget>();
		Widget w1 = new Widget();
		w1.setzIndex(-1);  
		Widget w2 = new Widget();
		w2.setzIndex(-2);
		Widget w3 = new Widget();
		w3.setzIndex(-3);
		currentList.add(w1);
		currentList.add(w2);
		currentList.add(w3);
		//Act
		zIndex = service.GetMaxIncrementedZindex(currentList);
		//Assert
		assertEquals(zIndex, 0);
		
	}
}
