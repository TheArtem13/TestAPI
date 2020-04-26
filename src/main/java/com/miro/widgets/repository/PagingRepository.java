package com.miro.widgets.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.miro.widgets.data.Widget;

@Repository
public interface PagingRepository extends PagingAndSortingRepository<Widget, Long> {
}
