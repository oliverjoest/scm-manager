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



package sonia.scm;

/**
 * Exception for problems with the SCM-Manager configuration.
 *
 * @author Sebastian Sdorra
 */
public class ConfigurationException extends RuntimeException
{

  /** Field description */
  private static final long serialVersionUID = 3462977946341972841L;

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs a {@link ConfigurationException}
   *
   */
  public ConfigurationException()
  {
    super();
  }

  /**
   * Constructs a {@link ConfigurationException}
   *
   *
   * @param message
   */
  public ConfigurationException(String message)
  {
    super(message);
  }

  /**
   * Constructs a {@link ConfigurationException}
   *
   *
   * @param cause
   */
  public ConfigurationException(Throwable cause)
  {
    super(cause);
  }

  /**
   * Constructs a {@link ConfigurationException}
   *
   *
   * @param message
   * @param cause
   */
  public ConfigurationException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
