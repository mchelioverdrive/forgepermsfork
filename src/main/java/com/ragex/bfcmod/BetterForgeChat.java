package com.ragex.bfcmod;

import com.ragex.bfcmod.commands.NickCommands;
import com.ragex.bfcmod.config.*;
import com.ragex.bfcmod.events.*;
import com.ragex.bfcmod.utils.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.IGuiHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(modid = BetterForgeChat.MODID, version = BetterForgeChat.VERSION, name = "Better Forge Chat")
public class BetterForgeChat {

    public static final Logger LOGGER = LogManager.getLogger("BetterForgeChat");

    public static final String MODID = "bfcmod";
    public static final String VERSION = "V1.2.1";
    public static final String CHAT_ID_STR = "&cBetter &9&lForge&r &eChat&r &d(c) Jeremiah Lowe 2022-2023&r\n";

    @Mod.Instance(MODID)
    public static BetterForgeChat instance;

    private ChatEventHandler chatHandler = new ChatEventHandler();
    private PlayerEventHandler playerEventHandler = new PlayerEventHandler();
    private PermissionsHandler permissionsHandler = new PermissionsHandler();
    private CommandRegistrationHandler commandRegistrator = new CommandRegistrationHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Configuration and pre-initialization tasks
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationEventHandler());
        MinecraftForge.EVENT_BUS.register(permissionsHandler);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Registering commands and event handlers
        MinecraftForge.EVENT_BUS.register(chatHandler);
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(commandRegistrator);
    }



    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Final initialization and integration tasks
        System.out.println("Mod loaded up and ready to go! (c) Jeremiah Lowe 2022 - 2023!");
    }

    // Example event handler structure (to be implemented as needed)
    public static class ChatEventHandler {
        @SubscribeEvent
        public void onChatMessageReceived(/* Event args */) {
            // Handle chat message events here
        }
    }

    public static class PlayerEventHandler {
        @SubscribeEvent
        public void onPlayerLogin(/* Event args */) {
            // Handle player events here
        }
    }

    public static class PermissionsHandler {
        @SubscribeEvent
        public void onPermissionCheck(/* Event args */) {
            // Handle permissions events here
        }
    }

    public static class CommandRegistrationHandler {
        @SubscribeEvent
        public void onCommandRegister(/* Event args */) {
            // Handle command registration here
        }
    }

    public static class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            return null; // Implement server-side GUI logic here
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            return null; // Implement client-side GUI logic here
        }
    }
}