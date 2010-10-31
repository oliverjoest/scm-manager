/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.scm;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

import sonia.scm.repository.RepositoryManager;
import sonia.scm.util.ServiceUtil;
import sonia.scm.util.Util;
import sonia.scm.web.ScmWebPlugin;
import sonia.scm.web.ScmWebPluginContext;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import sonia.scm.web.security.Authenticator;

/**
 *
 * @author Sebastian Sdorra
 */
public class ContextListener extends GuiceServletContextListener
{

  /**
   * Method description
   *
   *
   * @param servletContextEvent
   */
  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
    List<ScmWebPlugin> plugins = ServiceUtil.getServices(ScmWebPlugin.class);

    for (ScmWebPlugin plugin : plugins)
    {
      plugin.contextDestroyed(webPluginContext);
    }

    super.contextDestroyed(servletContextEvent);
  }

  /**
   * Method description
   *
   *
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    webPluginContext =
      new ScmWebPluginContext(servletContextEvent.getServletContext());

    List<ScmWebPlugin> plugins = ServiceUtil.getServices(ScmWebPlugin.class);

    for (ScmWebPlugin plugin : plugins)
    {
      plugin.contextInitialized(webPluginContext);
    }

    super.contextInitialized(servletContextEvent);
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  protected Injector getInjector()
  {
    List<Module> modules = new ArrayList<Module>();

    modules.add(new ScmServletModule(webPluginContext));

    Collection<Module> pluginModules = webPluginContext.getInjectModules();

    if (Util.isNotEmpty(pluginModules))
    {
      modules.addAll(pluginModules);
    }

    Injector injector = Guice.createInjector(modules);

    // init RepositoryManager
    injector.getInstance(RepositoryManager.class).init(SCMContext.getContext());

    // init Authenticator
    injector.getInstance(Authenticator.class).init(SCMContext.getContext());

    return injector;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private ScmWebPluginContext webPluginContext;
}
