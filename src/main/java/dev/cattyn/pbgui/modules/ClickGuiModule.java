package dev.cattyn.pbgui.modules;

import dev.cattyn.pbgui.GuiApi;
import me.earth.pingbypass.PingBypass;
import me.earth.pingbypass.api.module.impl.Categories;
import me.earth.pingbypass.api.module.impl.ModuleImpl;

public class ClickGuiModule extends ModuleImpl {
   public ClickGuiModule(PingBypass pingBypass) {
      super(pingBypass, "ClickGui", Categories.CLIENT, "ui for pb");
   }

   protected void onEnable() {
      this.disable();
      this.mc.setScreen(GuiApi.getGui().getScreen());
   }
}
