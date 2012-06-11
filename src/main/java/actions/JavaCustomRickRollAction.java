package actions;

import play.Play;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class JavaCustomRickRollAction extends Action<JavaRickRoll> {
    @Override
    public Result call(Http.Context ctx) throws Throwable {
        // Check if we should not rick roll
        String header = ctx.request().getHeader("X-Dont-Rick-Roll");
        if (header != null) {
            // Add the header to the response
            ctx.response().getHeaders().put(getHeaderName(), "Never gonna give you up");
        }
        return delegate.call(ctx);
    }

    private String getHeaderName() {
        if (configuration.headerName().equals(JavaRickRoll.DEFAULT)) {
            String header =  Play.application().configuration().getString("rickroll.header");
            if (header == null) {
                return "X-Rick-Roll";
            } else {
                return header;
            }
        } else {
            return configuration.headerName();
        }
    }
}
