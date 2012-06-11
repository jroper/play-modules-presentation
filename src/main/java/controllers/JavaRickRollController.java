package controllers;

import actions.JavaRickRoll;
import actions.JavaRickRollAction;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class JavaRickRollController extends Controller {

    @With(JavaRickRollAction.class)
    public static Result rickRoll() {
        return ok("Rick rolled");
    }


    @JavaRickRoll
    public static Result rickRollNotCustom() {
        return ok("Rick rolled");
    }


    @JavaRickRoll(headerName = "X-Custom-Rick-Roll")
    public static Result rickRollCustom() {
        return ok("Rick rolled");
    }
}
