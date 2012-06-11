package actions;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class JavaRickRollAction extends Action.Simple {
    @Override
    public Result call(Http.Context ctx) throws Throwable {
        // Check if we should not rick roll
        String header = ctx.request().getHeader("X-Dont-Rick-Roll");
        if (header != null) {
            // Add the header to the response
            ctx.response().getHeaders().put("X-Rick-Roll", "Never gonna give you up");
        }
        return delegate.call(ctx);
    }
}
