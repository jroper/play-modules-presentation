package plugins;

import play.Application;
import play.Play;
import play.Plugin;

/**
 * Uptime plugin
 */
public class JavaUptimePlugin extends Plugin {
    private final Application app;
    private long startTime;

    public JavaUptimePlugin(Application app) {
        this.app = app;
    }

    public long getUpTime() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public void onStart() {
        startTime = System.currentTimeMillis();
        System.out.println("I have started!");
    }

    @Override
    public void onStop() {
        System.out.println("I have stopped!");
    }

    @Override
    public boolean enabled() {
        Boolean disabled = app.configuration().getBoolean("javaUptimePlugin");
        return disabled == null || !disabled;
    }

    public static long upTime(Application app) {
        return app.plugin(JavaUptimePlugin.class).getUpTime();
    }

    public static long upTime() {
        return upTime(Play.application());
    }




























    public void $init$() {
        // This is just here because IntelliJ is dumb
    }
}
