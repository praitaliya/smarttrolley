package com.dao;

import org.apache.commons.lang.StringEscapeUtils;
// string validations for html and javascript escape for security
public class DataValidation
{
    
  public DataValidation() {}
  
  public static String escapeStringForJavaScript(String s)
    throws Exception
  {
    return StringEscapeUtils.escapeHtml(s);
  }
  
  public static String escapeStringForHTML(String s)
    throws Exception
  {
    return StringEscapeUtils.escapeHtml(s);
  }
}
