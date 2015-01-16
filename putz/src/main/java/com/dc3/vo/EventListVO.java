package com.dc3.vo;

import java.util.List;

import com.dc3.model.Event;

public class EventListVO {
	
	private int pagesCount;
    private long totalContacts;

    private String actionMessage;
    private String searchMessage;

    private List<Event> events;
    
    public EventListVO(int pages, long totalContacts, List<Event> events) {
        this.pagesCount = pages;
        this.events = events;
        this.totalContacts = totalContacts;
    }

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalContacts() {
		return totalContacts;
	}

	public void setTotalContacts(long totalContacts) {
		this.totalContacts = totalContacts;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String getSearchMessage() {
		return searchMessage;
	}

	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
