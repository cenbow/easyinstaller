package com.jianglibo.vaadin.dashboard.unused;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jianglibo.vaadin.dashboard.annotation.VaadinFormFieldWrapper;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTableWrapper;
import com.jianglibo.vaadin.dashboard.domain.Domains;
import com.jianglibo.vaadin.dashboard.uicomponent.form.FormBase;
import com.jianglibo.vaadin.dashboard.uicomponent.form.FormBase.HandMakeFieldsListener;
import com.jianglibo.vaadin.dashboard.uifactory.FieldFactories;
import com.jianglibo.vaadin.dashboard.view.BaseEditView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Field;


//@SpringView(name = StepRunEditView.VIEW_NAME)
public class StepRunEditView  extends BaseEditView<StepRun, FormBase<StepRun>, JpaRepository<StepRun,Long>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(StepRunEditView.class);
	

	public static final String VIEW_NAME = StepRunListView.VIEW_NAME + "/edit";

	public static final FontAwesome ICON_VALUE = FontAwesome.FILE_ARCHIVE_O;

   
	@Autowired
	public StepRunEditView(StepRunRepository repository, MessageSource messageSource,Domains domains,FieldFactories fieldFactories,
			ApplicationContext applicationContext) {
		super(messageSource, StepRun.class, domains, fieldFactories, repository);
//		this.messageSource = messageSource;
//		this.repository= repository;
//		this.eventBus = new EventBus(this.getClass().getName());
//		eventBus.register(this);
//		setSizeFull();
//		addStyleName("transactions");
//		StyleUtil.setOverflowAuto(this, true);
//		setMargin(true);
//		
////		header = applicationContext.getBean(HeaderLayout.class).afterInjection(eventBus,false, true, "");
//		
//		addComponent(header);
////		form = (StepRunForm) applicationContext.getBean(StepRunForm.class).afterInjection(eventBus, this);
//		addComponent(form);
//		Component ft = buildFooter();
//		addComponent(ft);
//		setComponentAlignment(form, Alignment.TOP_LEFT);
//		setExpandRatio(form, 1);
	}
	
//    @SuppressWarnings("serial")
//	private Component buildFooter() {
//        HorizontalLayout footer = new HorizontalLayout();
//        footer.setWidth(100.0f, Unit.PERCENTAGE);
//
//        Button ok = new Button(messageSource.getMessage("shared.btn.save", null, UI.getCurrent().getLocale()));
//        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        ok.addClickListener(new ClickListener() {
//            @Override
//            public void buttonClick(ClickEvent event) {
//            	form.save();
//            }
//        });
//        ok.focus();
//        footer.addComponent(ok);
//        footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
//        return footer;
//    }
    

	@Override
	public void detach() {
		super.detach();
		// A new instance of TransactionsView is created every time it's
		// navigated to so we'll need to clean up references to it on detach.
		// DashboardEventBus.unregister(this);
	}
	
//	@Subscribe
//	public void onBackBtnClicked(HistoryBackEvent hbe) {
//		String bu = ifb.getPreviousView();
//		if (Strings.isNullOrEmpty(bu)) {
//			bu = StepRunListView.VIEW_NAME;
//		}
//		UI.getCurrent().getNavigator().navigateTo(bu);
//	}

	@Override
	public void enter(ViewChangeEvent event) {
//		LOGGER.info("parameter string is: {}", event.getParameters());
//		ifb = new ItemViewFragmentBuilder(event);
//		long bid = ifb.getBeanId();
//		if (bid == 0) {
//			bean = new StepRun();
//			header.setLabelTxt(MsgUtil.getViewMsg(messageSource, StepDefine.class.getSimpleName() + ".newtitle"));
//		} else {
//			bean = repository.findOne(bid);
//			header.setLabelTxt(bean.getStepDefine().toString());
//		}
//        form.setItemDataSource(bean);
	}

	@Override
	public Field<?> createField(VaadinTableWrapper vtw, VaadinFormFieldWrapper vffw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected FormBase<StepRun> createForm(MessageSource messageSource, Domains domains, FieldFactories fieldFactories,
			JpaRepository<StepRun, Long> repository, HandMakeFieldsListener handMakeFieldsListener) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected StepRun createNewBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getListViewName() {
		// TODO Auto-generated method stub
		return null;
	}
}
