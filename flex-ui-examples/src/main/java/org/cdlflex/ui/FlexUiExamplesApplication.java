package org.cdlflex.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.cdlflex.ui.pages.HomePage;

/**
 * WebApplication for the Flex UI examples showcase.
 */
public class FlexUiExamplesApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();

        // add your configuration here
    }
}
