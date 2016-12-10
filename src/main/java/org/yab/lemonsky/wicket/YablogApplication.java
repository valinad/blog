package org.yab.lemonsky.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import org.yab.lemonsky.wicket.pages.Index;
import org.yab.lemonsky.wicket.pages.add_feed.AddFeedPage;
import org.yab.lemonsky.wicket.pages.login.YabSignInPage;
import org.yab.lemonsky.wicket.security.YabAuthenticationSession;

//import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * User: Danila Vereshchakov
 * Date: 27.08.2016
 */
public class YablogApplication extends AuthenticatedWebApplication {

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        //for supporting JSR303 in entity classes
        //for more info see https://ci.apache.org/projects/wicket/guide/6.x/guide/forms2.html#forms2_4
//        new BeanValidationConfiguration().configure(this);

        new AnnotatedMountScanner().scanPackage("org.yab.lemonsky.wicket.pages").mount(this);
//        getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Index.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return YabSignInPage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return YabAuthenticationSession.class;
    }
}
