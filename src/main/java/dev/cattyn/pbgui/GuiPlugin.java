package dev.cattyn.pbgui;

import dev.cattyn.pbgui.manager.VisibilityManager;
import dev.cattyn.pbgui.modules.ClickGuiModule;
import me.earth.pingbypass.PingBypass;
import me.earth.pingbypass.api.module.impl.Categories;
import me.earth.pingbypass.api.plugin.impl.AbstractUnloadablePlugin;
import me.earth.pingbypass.api.plugin.impl.PluginUnloadingService;
import net.minecraft.client.Minecraft;

public class GuiPlugin extends AbstractUnloadablePlugin {

    @Override
   public void load(PingBypass pingBypass, PluginUnloadingService unloadingService) {
      Gui gui = this.initalizePhobot(pingBypass, unloadingService);
      this.registerCommands(pingBypass, gui, unloadingService);
      this.registerModules(pingBypass, gui, unloadingService);
      GuiApi.setGui(gui);
      GuiApi.getGui().getPingBypass().getModuleManager().getCategoryManager().register(Categories.MOVEMENT);
    }

   public void unload() {
      super.unload();
   }

   private Gui initalizePhobot(PingBypass pingBypass, PluginUnloadingService unloadingService) {
      return new Gui(pingBypass, Minecraft.getInstance(), new VisibilityManager());
   }

   private void registerCommands(PingBypass pingBypass, Gui phobot, PluginUnloadingService unloadingService) {
   }

   private void registerModules(PingBypass pingBypass, Gui phobot, PluginUnloadingService unloadingService) {
      unloadingService.registerModule(new ClickGuiModule(pingBypass));
   }
}
