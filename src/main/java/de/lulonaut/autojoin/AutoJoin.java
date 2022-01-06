package de.lulonaut.autojoin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = "autojoin", name = "AutoJoin", version = "1.0", clientSideOnly = true)
public class AutoJoin {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.config = new Configuration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (Config.getToggle()) {
            MinecraftForge.EVENT_BUS.register(this);
        }
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
            FMLClientHandler.instance().connectToServer(new GuiMultiplayer(Minecraft.getMinecraft().currentScreen), new ServerData("server", Config.getServer(), false));
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
