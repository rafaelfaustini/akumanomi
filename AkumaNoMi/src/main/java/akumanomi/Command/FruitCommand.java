package akumanomi.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.inject.Inject;

import akumanomi.Config.ConfigManager;
import akumanomi.Entity.Fruit;
import akumanomi.Entity.Enum.FruitTypeEnum;
import akumanomi.Helper.TextHelper;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Conditions;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;

@CommandAlias("fruit|akumanomi")
public class FruitCommand extends BaseCommand {

    private Fruit fruit;
    private ConfigManager configManager;

    @Inject
    public FruitCommand (Fruit fruit, ConfigManager configManager) {
        this.fruit = fruit;
        this.configManager = configManager;
    }

    @Subcommand("check") @Conditions("playerExecuteOnly|mustHaveItemInHand")
    @Description("Check if fruit is akumanomi")
    public void onGet(Player player) {
        ItemStack handItem = player.getInventory().getItemInMainHand();
        Boolean isValidFruit = fruit.checkFruit(handItem);
        if(!isValidFruit) {
            player.sendMessage(TextHelper.coloredText(configManager.getMessagesConfig().getString("FruitInvalid")));
            return;
        }
        player.sendMessage(TextHelper.coloredText(configManager.getMessagesConfig().getString("FruitValid")));
    } 


    @Subcommand("give")
    @Description("Gives a akumanomi to a player")
    public void onGive(Player player, FruitTypeEnum fruitType, Player targetPlayer) {
        fruit.generateFruit(targetPlayer, fruitType);
    }

    @Default
    @HelpCommand
    public void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }
}
