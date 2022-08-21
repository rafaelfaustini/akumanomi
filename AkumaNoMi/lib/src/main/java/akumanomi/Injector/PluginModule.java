package akumanomi.Injector;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import akumanomi.AkumaNoMi;
import akumanomi.Config.CustomConfig;
import akumanomi.Config.ICustomConfig;

public class PluginModule extends AbstractModule {

    private final AkumaNoMi plugin;

    public PluginModule(AkumaNoMi plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        this.bind(AkumaNoMi.class).toInstance(this.plugin);
        this.bind(ICustomConfig.class).to(CustomConfig.class);
    }
}