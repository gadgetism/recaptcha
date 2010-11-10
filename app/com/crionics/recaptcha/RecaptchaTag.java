package com.crionics.recaptcha;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import net.tanesha.recaptcha.ReCaptchaFactory;
import play.i18n.Lang;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import play.exceptions.TemplateExecutionException;
import play.exceptions.TagInternalException;

/**
 * How to use:
 * #{crionics.recaptcha publicKey:"YOUR_RECAPTCHA_PUBLIC_KEY", privateKey:"YOUR_RECAPTCHA_PRIVATE_KEY" /}
 * 
 * @author Olivier Refalo
 */
@FastTags.Namespace("crionics")
public class RecaptchaTag extends FastTags {
    private static final String ERROR_MSG = "Please provide a valid public and/or private keys";
    private static final String[] SUPPORTED_LANG = { "en", "nl", "fr", "de", "pt", "ru", "es", "tr" };

    public static void _recaptcha(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {

	String publickey = (String) args.get("publicKey");
	String privatekey = (String) args.get("privateKey");

	if (publickey == null || privatekey == null) {
	   // Waiting on a way to access template.template
	   // throw new TemplateExecutionException(template.template, fromLine, ERROR_MSG, new TagInternalException(ERROR_MSG));
	    throw new TemplateExecutionException(null, fromLine, ERROR_MSG, new TagInternalException(ERROR_MSG));
	}
	else {

	    if (publickey.trim().length() == 0 || privatekey.trim().length() == 0)
	    {
	  		 // Waiting on a way to access template.template
	  		 // throw new TemplateExecutionException(template.template, fromLine, ERROR_MSG, new TagInternalException(ERROR_MSG));
	  		  throw new TemplateExecutionException(null, fromLine, ERROR_MSG, new TagInternalException(ERROR_MSG));
	    }
	    else {

		Properties props = new Properties();

		String tabindex = (String) args.get("tabindex");
		if (tabindex != null)
		    props.put("tabindex", tabindex);

		String theme = (String) args.get("theme");
		if (theme != null)
		    props.put("theme", theme);

		String lang = (String) args.get("lang");
		if (lang == null) {
		    // figure what language the application use and see if recaptcha supports it, defaults to en
		    lang = Lang.get();
		    if (lang == null || lang.trim().length() == 0 || !isLangSupported(lang))
			lang = "en";
		}
		props.put("lang", lang);

		String captcha = ReCaptchaFactory.newReCaptcha(publickey, privatekey, false).createRecaptchaHtml(null, props);
		out.print(captcha);
	    }
	}

    }

    private static boolean isLangSupported(String lang) {

	for (int i = SUPPORTED_LANG.length; --i >= 0;)
	    if (SUPPORTED_LANG[i].equals(lang))
		return true;
	return false;
    }

}