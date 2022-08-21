package akumanomi;

 
import org.bukkit.plugin.java.JavaPlugin;

import com.google.inject.Inject;
import com.google.inject.Injector;

import akumanomi.Command.CommandValidations;
import akumanomi.Command.FruitCommand;
import akumanomi.Config.ConfigManager;
import akumanomi.Helper.LoggerHelper;
import akumanomi.Injector.PluginModule;
import akumanomi.Listener.EsperDeathListener;
import akumanomi.Listener.FruitEatListener;
import akumanomi.Listener.ItemConsumeListener;
import akumanomi.Listener.MoveListener;
import akumanomi.Listener.PlayerDeathListener;
import akumanomi.Listener.PlayerJoinListener;
import akumanomi.Listener.WaterEnterListener;
import akumanomi.api.Internal.DatabaseInitializer;
import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.PaperCommandManager;


public class AkumaNoMi extends JavaPlugin {

    private PaperCommandManager commandManager;
    @Inject private ConfigManager configManager;
    @Inject private DatabaseInitializer databaseInitializer;
    @Inject private FruitCommand fruitCommand;
    @Inject private MoveListener moveListener;
    @Inject private WaterEnterListener waterEnterListener;
    @Inject private ItemConsumeListener itemConsumeListener;
    @Inject private FruitEatListener fruitEatListener;
    @Inject private PlayerDeathListener playerDeathListener;
    @Inject private EsperDeathListener esperDeathListener;
    @Inject private PlayerJoinListener playerJoinListener;

    @Override
    public void onEnable() {
        PluginModule module = new PluginModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        registerEvents();
        registerCommands();
        registerConditions();
        initConfigs();
        
        LoggerHelper.info(configManager.getMessagesConfig().getString("Startup"));
    }


    private void initConfigs() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        databaseInitializer.init();
        configManager.InitConfigs();
    }

    public void registerConditions() {
        commandManager.getCommandConditions().addCondition("playerExecuteOnly", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            CommandValidations.validateIfTriggeredByPlayer(issuer);
        });
        commandManager.getCommandConditions().addCondition("mustHaveItemInHand", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            CommandValidations.validateItemInHand(issuer);
        });
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(fruitCommand);
    }


    private void registerEvents() {
        getServer().getPluginManager().registerEvents(moveListener, this);
        getServer().getPluginManager().registerEvents(waterEnterListener, this);
        getServer().getPluginManager().registerEvents(itemConsumeListener, this);
        getServer().getPluginManager().registerEvents(fruitEatListener, this);
        getServer().getPluginManager().registerEvents(playerDeathListener, this);
        getServer().getPluginManager().registerEvents(esperDeathListener, this);
        getServer().getPluginManager().registerEvents(playerJoinListener, this);

    }
}
