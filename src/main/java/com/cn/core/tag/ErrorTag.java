package com.cn.core.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;

/**
 * @modify  支持配置EscapeXml,防止xss跨站脚本攻击
 * 			EscapeXml : true,转义特殊字符; false : 不转义特殊字符; default : true;
 * 
 *
 */
@SuppressWarnings("serial")
public class ErrorTag extends TagSupport
{
  private String condition;
  // 默认为_exceptionMessage
  private String errorName = "_exceptionMessage";
  private boolean escapeXml_=true;

  public int doEndTag()
    throws JspException
  {
    return 6;
  }

  public int doStartTag()
    throws JspException
  {
    try
    {
      if (this.condition != null)
        try
        {
          Boolean localBoolean = (Boolean)ExpressionUtil.evalNotNull("condition", "condition", this.condition, Boolean.class, this, this.pageContext);
          if (!localBoolean.booleanValue())
            return 0;
        }
        catch (NullAttributeException localNullAttributeException)
        {
          return 0;
        }
      Object localObject = this.pageContext.getRequest().getAttribute(this.errorName);
      
      if (localObject != null && !("".equals(localObject))){
    	  Object errorValue = null;
    	  StringBuffer errorMsg = new StringBuffer();
    	  if(escapeXml_){
    		  errorValue=localObject;
//    		  errorValue=TagUtil.escapeXml((String)localObject);
    	  }else{
    		  errorValue=localObject;
    	  }
    	  errorMsg.append(errorValue).append("<script>$(document).ready(function(){$('.errBox').css('display','block');});</script>");
    	  this.pageContext.getOut().print(errorMsg);
      }
        
    }
    catch (IOException localIOException)
    {
      throw new JspException("http.tag_fail_to_write", localIOException);
    }
    return 0;
  }

  public String escapeHtml(String srcStr) {
		if (srcStr == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < srcStr.length(); i++) {
			char c = srcStr.charAt(i);
			switch (c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&#034;");
				break;
			case '\'':
				sb.append("&#039;");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
  
  public void setCondition(String paramString)
  {
    this.condition = paramString;
  }

  public void setErrorName(String paramString)
  {
    this.errorName = paramString;
  }

  public void setEscapeXml(boolean escapeXml) {
	this.escapeXml_ = escapeXml;
  }
  
	  
}