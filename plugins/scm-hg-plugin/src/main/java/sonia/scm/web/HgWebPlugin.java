/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.scm.web;

//~--- JDK imports ------------------------------------------------------------

import java.io.InputStream;

/**
 *
 * @author Sebastian Sdorra
 */
public class HgWebPlugin implements ScmWebPlugin
{

  /** Field description */
  public static final String SCRIPT = "/sonia/scm/hg.config.js";

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param context
   */
  @Override
  public void contextDestroyed(ScmWebPluginContext context)
  {

    // do nothing
  }

  /**
   * Method description
   *
   *
   * @param context
   */
  @Override
  public void contextInitialized(ScmWebPluginContext context)
  {
    context.addScriptResource(new ClasspathWebResource(SCRIPT));
  }
}
