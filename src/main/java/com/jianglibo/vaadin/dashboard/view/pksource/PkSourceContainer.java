package com.jianglibo.vaadin.dashboard.view.pksource;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTable;
import com.jianglibo.vaadin.dashboard.data.container.JpaContainer;
import com.jianglibo.vaadin.dashboard.domain.Domains;
import com.jianglibo.vaadin.dashboard.domain.PkSource;
import com.jianglibo.vaadin.dashboard.event.view.PageMetaEvent;
import com.jianglibo.vaadin.dashboard.repositories.PkSourceRepository;
import com.jianglibo.vaadin.dashboard.util.ListViewFragmentBuilder;
import com.jianglibo.vaadin.dashboard.util.SortUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
@SpringComponent
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PkSourceContainer extends JpaContainer<PkSource> implements Container.Filterable{
	
	private static Logger LOGGER = LoggerFactory.getLogger(PkSourceContainer.class);
	
	private final PkSourceRepository repository;
	
	@Autowired
	public PkSourceContainer(PkSourceRepository repository, Domains domains) {
		super(PkSource.class, domains);
		this.repository = repository;
	}
	
	public PkSourceContainer afterInjection(EventBus eventBus, Table table) {
		VaadinTable vt = getDomains().getTables().get(PkSource.DOMAIN_NAME);
		setupProperties(table, eventBus, SortUtil.fromString(vt.defaultSort()), vt.defaultPerPage());
		return this;
	}

	@Subscribe
	public void whenUriFragmentChange(ListViewFragmentBuilder vfb) {
		persistState(vfb);
		setList();
	}

	public void setList() {
		Pageable pageable;
		if (getSort() == null) {
			pageable = new PageRequest(getCurrentPage() - 1, getPerPage());
		} else {
			pageable = new PageRequest(getCurrentPage() - 1, getPerPage(), getSort());
		}
		
		Page<PkSource> entities;
		String filterStr = getFilterStr();
		long total;
		if (Strings.isNullOrEmpty(filterStr)) {
			entities = repository.findByArchivedEquals(isTrashed(), pageable);
			total = repository.countByArchivedEquals(isTrashed());
		} else {
			entities = repository.findByPknameContainingIgnoreCaseAndArchivedEquals(filterStr, isTrashed(), pageable);
			total = repository.countByPknameContainingIgnoreCaseAndArchivedEquals(filterStr, isTrashed());
		}
		setCollection(entities.getContent());
		getEventBus().post(new PageMetaEvent(total, getPerPage()));
	}

	public void refresh() {
		setList();
	}

	@Override
	public void addContainerFilter(Filter filter) throws UnsupportedFilterException {
		
	}

	@Override
	public void removeContainerFilter(Filter filter) {
		
	}

	@Override
	public void removeAllContainerFilters() {
		
	}

	@Override
	public Collection<Filter> getContainerFilters() {
		return null;
	}

}