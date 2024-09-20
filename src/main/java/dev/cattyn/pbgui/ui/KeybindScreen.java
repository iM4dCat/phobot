package dev.cattyn.pbgui.ui;

import dev.cattyn.pbgui.GuiApi;
import me.earth.pingbypass.api.input.Bind;
import me.earth.pingbypass.api.input.Key;
import me.earth.pingbypass.api.input.Key.Type;
import me.earth.pingbypass.api.module.Module;
import me.earth.pingbypass.api.setting.Setting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class KeybindScreen extends Screen {
   private final Module module;
   private final Setting<Bind> bind;

   protected KeybindScreen(Module module) {
      super(Component.literal("pb-gui-keybind"));
      this.module = module;
      this.bind = module.getSetting("Bind", Bind.class).orElse(null);
   }

   public void render(GuiGraphics ctx, int i, int j, float f) {
      if (this.bind == null) {
         this.close();
      } else {
         if (this.minecraft.player == null) {
            this.renderDirtBackground(ctx);
         }

         int x = ctx.guiWidth() / 2;
         int y = ctx.guiHeight() / 2;
         ctx.drawString(this.minecraft.font, "Press ESCAPE to exit\nPress DELETE or BACKSPACE to clear", 2, 2, -1, true);
         ctx.drawString(
            this.minecraft.font, "%s - %s".formatted(this.module.getName(), this.bind.getValue().getComponent().getString()), x, y, -1
         );
      }
   }

   public boolean keyPressed(int i, int j, int k) {
      if (i != 256) {
         Key keyByCode = this.module.getPingBypass().getKeyBoardAndMouse().getKeyByCode(Type.KEYBOARD, i);
         this.bind.setValue(i != 259 && i != 261 ? new Bind(new Key[]{keyByCode}) : Bind.none());
      }

      this.close();
      return false;
   }

   public void close() {
      this.minecraft.setScreen(GuiApi.getGui().getScreen());
   }
}
