package com.dc3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc3.model.Event;
import com.dc3.repository.EventRepository;
import com.dc3.service.exception.ServiceException;
import com.dc3.vo.EventListVO;

@Service
@Transactional
public class EventService extends GenericService {

	@Autowired
	private EventRepository eventRepository;

	@Transactional(readOnly = true)
	public EventListVO findAll(int page, int maxResults) {
		Page<Event> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return new EventListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
	}
	
	private Page<Event> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC("eventName"));
		return eventRepository.findAll(pageRequest);
	}

	public void save(Event event) throws ServiceException {
		if (event.getUser().getIdUser() == null) {
			throw new ServiceException("Usuario nulo", "error.event.emptyuser");
		}
		eventRepository.save(event);
	}

}
