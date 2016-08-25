package com.jianglibo.vaadin.dashboard.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.jianglibo.vaadin.dashboard.annotation.VaadinFormFieldWrapper;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTableWrapper;
import com.jianglibo.vaadin.dashboard.config.ApplicationConfig;
import com.jianglibo.vaadin.dashboard.config.ApplicationConfigWrapper;
import com.jianglibo.vaadin.dashboard.config.ComboItem;
import com.jianglibo.vaadin.dashboard.domain.BaseEntity;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;


@Component
public class ComboBoxFieldFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComboBoxFieldFactory.class);

	private final MessageSource messageSource;
	
	private final ApplicationConfig appConfig;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	public ComboBoxFieldFactory(MessageSource messageSource, ApplicationConfigWrapper appConfigWrapper) {
		this.messageSource = messageSource;
		this.appConfig = appConfigWrapper.unwrap();
	}
	
	public ComboBox createCombo(VaadinTableWrapper vtw, VaadinFormFieldWrapper vffw) {
		
		String caption = null;
		try {
			caption = MsgUtil.getFieldMsg(messageSource, vtw.getVt().messagePrefix(), vffw);
		} catch (NoSuchMessageException e) {
		}

		ComboBox cb = new ComboBox(caption);
		
		cb.setNewItemsAllowed(vffw.getVff().newItemAllowed());
		cb.setItemCaptionMode(
			    ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		
		if (!Strings.isNullOrEmpty(vffw.getVff().jpql())) {
			return buildJpqlCombox(vtw, vffw, cb);
		} else {
			return buildYmlCombox(vtw, vffw, cb);
		}
	}
	
	private ComboBox buildYmlCombox(VaadinTableWrapper vtw, VaadinFormFieldWrapper vffw, ComboBox cb) {
		List<ComboItem> comboItems = appConfig.getComboDatas().get(vffw.getVff().comboKey());
		
		if (comboItems == null) {
			LOGGER.warn("comboitem key '{}' does not exists in application.yml under common-datas.", vffw.getVff().comboKey());
			comboItems = Lists.newArrayList();
		}
		
		for(ComboItem ci : comboItems) {
			Object v = convertItemValue(ci);
			cb.addItem(v);
			cb.setItemCaption(v, MsgUtil.getComboItemMsg(messageSource, vffw.getVff().comboKey(), ci));
		}
		return cb;
	}
	
	private ComboBox buildJpqlCombox(VaadinTableWrapper vtw, VaadinFormFieldWrapper vffw, ComboBox cb) {
		String jpql = vffw.getVff().jpql();
		List<? extends BaseEntity> results = entityManager.createQuery(jpql, BaseEntity.class).getResultList();
		
		for(BaseEntity be : results) {
			cb.addItem(be);
			cb.setItemCaption(be, be.getDisplayName());
		}
		return cb;
	}

	public Object convertItemValue(ComboItem ci) {
		switch (ci.getValueType()) {
		case "Integer":
			return Integer.valueOf(ci.getValue());
		case "Long":
			return Long.valueOf(ci.getValue());
		default:
			return ci.getValue();
		}
	}
}
