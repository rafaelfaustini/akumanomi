package akumanomi.Entity;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.google.inject.Inject;

import akumanomi.AkumaNoMi;
import akumanomi.api.Action.Esper.CheckEsperAction;
import akumanomi.api.Action.Esper.CreateEsperAction;
import akumanomi.api.Action.Esper.RemoveEsperAction;

public class Esper {
    private boolean hasErrors;
    private CreateEsperAction createEsperAction;
    private RemoveEsperAction removeEsperAction;
    private CheckEsperAction checkEsperAction;
    private AkumaNoMi plugin;
    
    @Inject
    public Esper(CreateEsperAction createEsperAction, RemoveEsperAction removeEsperAction,
            CheckEsperAction checkEsperAction, AkumaNoMi plugin) {
        this.createEsperAction = createEsperAction;
        this.removeEsperAction = removeEsperAction;
        this.checkEsperAction = checkEsperAction;
        this.plugin = plugin;
    }
    public boolean hasErrors() {
        return hasErrors;
    }
    public void clearErrors() {
        hasErrors = false;
    }
    public void createEsper(Player player, String fruitKey) {
        String playerID = player.getUniqueId().toString();
        createEsperAction.CreateEsper(playerID, fruitKey);
        hasErrors = hasErrors && createEsperAction.hasErrors();
    }
    public void removeEsper(Player player) {
        String playerID = player.getUniqueId().toString();
        removeEsperAction.RemoveEsper(playerID);
        hasErrors = hasErrors && removeEsperAction.hasErrors();
    }

    public boolean checkEsper(Player player) {
        String playerID = player.getUniqueId().toString();
        boolean isEsper = checkEsperAction.CheckEsper(playerID);
        return isEsper;
    }

    public void setEsperPermission(Player player) {
        boolean isEsper = checkEsper(player);
        if(isEsper) {
            PermissionAttachment attachment = player.addAttachment(plugin);
            attachment.setPermission("akumanomi.esper", true);
        }
    }

}
