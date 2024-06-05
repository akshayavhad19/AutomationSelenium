package com.qa.gpp.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class FrameworkException extends RuntimeException { 
  public FrameworkException (String msgs) {
	  super(msgs);
  }
}
