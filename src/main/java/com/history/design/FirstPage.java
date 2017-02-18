package com.history.design;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class FirstPage extends VerticalLayout {
    protected Image foto1;
    protected Button historyLink;
    protected Button humanLink;
    protected Button jurisprudenceLink;
    protected Button mainPage;
    protected Label content;
    protected Button historyTest;
    protected Button humanTest;
    protected Button jurisprudenceTest;

    public FirstPage() {
        Design.read(this);
    }
}
