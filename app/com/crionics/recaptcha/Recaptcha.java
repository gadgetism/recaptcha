package com.crionics.recaptcha;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import play.mvc.Http.Request;
import play.mvc.Scope.Params;

public class Recaptcha {

    private Recaptcha() {

    }

    public static boolean checkAnswer(String privateK, Request request, Params params) {

	String remoteAddr = request.remoteAddress;

	ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	reCaptcha.setPrivateKey(privateK);
	String challenge = params.get("recaptcha_challenge_field");
	String uresponse = params.get("recaptcha_response_field");

	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
	return reCaptchaResponse.isValid();

    }
}
