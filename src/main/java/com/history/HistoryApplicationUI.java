package com.history;

import com.history.view.FirstPageView;
import com.history.view.TestFormView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class HistoryApplicationUI extends UI {
    private VerticalLayout layout;
    private FirstPageView firstPage = new FirstPageView(this);
    private TestFormView testForm;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        firstPage.setSizeFull();
        setupFirstPage();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        setContent(layout);
    }

    private void setupFirstPage() {
        layout.addComponent(firstPage);
    }

    public void firstPageState() {
        layout.removeComponent(testForm);
        firstPage.getContent().setValue(testForm.getResults());
        setupFirstPage();

    }
    public void testPageState(TestFormView testForm) {
        this.testForm = testForm;
        this.testForm.setSizeFull();
        layout.removeComponent(firstPage);
        layout.addComponent(testForm);
    }


}
