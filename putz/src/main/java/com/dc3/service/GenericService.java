package com.dc3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class GenericService {

	protected Sort sortByNameASC(String str) {
		return new Sort(Sort.Direction.ASC, str);
	}
	
	protected boolean shouldExecuteSameQueryInLastPage(int page, Page<?> result) {
		return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
	}

	private boolean isUserAfterOrOnLastPage(int page, Page<?> result) {
		return page >= result.getTotalPages() - 1;
	}

	private boolean hasDataInDataBase(Page<?> result) {
		return result.getTotalElements() > 0;
	}

}
