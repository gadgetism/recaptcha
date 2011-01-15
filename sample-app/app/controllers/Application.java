package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import ugot.recaptcha.Recaptcha;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void save(@Recaptcha String captcha) {

     if(validation.hasErrors()) {
        System.out.println("ERRORS found - invalid captcha");
        params.flash();
        validation.keep();
      }
      else {
        System.out.println("There are no errors, the catcha was validated");
      }
            
      // no matter what, redisplay the same page
      index();
   }



}
