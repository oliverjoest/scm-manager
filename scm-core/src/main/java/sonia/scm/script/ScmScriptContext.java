/**
 * Copyright (c) 2010, Sebastian Sdorra
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of SCM-Manager; nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * http://bitbucket.org/sdorra/scm-manager
 *
 */



package sonia.scm.script;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Returns a implementation of {@link ScmScript}.
 *
 * @author Sebastian Sdorra
 * @since 1.7
 */
public interface ScmScriptContext
{

  /**
   * Returns a implementation of {@link ScmScript}.
   *
   *
   * @param file - the script
   *
   *
   * @return implementation of {@link ScmScript} for the given script
   * @throws IOException
   * @throws ScmScriptException
   */
  public ScmScript createScript(File file)
          throws IOException, ScmScriptException;

  /**
   * Returns a implementation of {@link ScmScript}.
   *
   *
   * @param path - classpath location of the script
   *
   *
   * @return implementation of {@link ScmScript} for the given script
   * @throws IOException
   * @throws ScmScriptException
   */
  public ScmScript createScript(String path)
          throws IOException, ScmScriptException;

  /**
   * Returns a implementation of {@link ScmScript}.
   *
   *
   * @param reader - The script in form of a {@link Reader}
   *
   *
   * @return implementation of {@link ScmScript} for the given script
   * @throws IOException
   * @throws ScmScriptException
   */
  public ScmScript createScript(Reader reader)
          throws IOException, ScmScriptException;
}
