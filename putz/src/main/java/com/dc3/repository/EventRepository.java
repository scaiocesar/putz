package com.dc3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dc3.model.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
	
	
}