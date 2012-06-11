import actions.JavaRickRollAction;
import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http;

import java.lang.reflect.Method;

public class JavaGlobal extends GlobalSettings {
    @Override
    public Action onRequest(Http.Request request, Method actionMethod) {
        return new JavaRickRollAction();
    }
}
