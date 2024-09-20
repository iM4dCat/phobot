package dev.cattyn.pbgui.ui;

import dev.cattyn.pbgui.GuiApi;
import me.earth.pingbypass.api.module.Category;
import me.earth.pingbypass.api.module.Module;
import me.earth.pingbypass.api.setting.Setting;
import me.earth.pingbypass.api.setting.impl.types.NumberSetting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.Window;
import dev.cattyn.pbgui.gui.types.TypeContainer;
import dev.cattyn.pbgui.gui.widgets.HeaderWidget;
import dev.cattyn.pbgui.gui.widgets.SimpleWidget;

import java.awt.*;

public class ClickGuiScreen extends Screen {
   private final TroonGui troon;
   private final RendererImpl renderer = new RendererImpl();

   public ClickGuiScreen() {
      super(Component.literal("pb-gui"));
      this.troon = new TroonGui(this.renderer);
      float x = 5.0F;

      for (Category category : GuiApi.getGui().getPingBypass().getModuleManager().getCategoryManager()) {
         Window window = this.troon.createWindow(category.getName());
         window.setHeight(350.0F);
         window.setH(350.0F);
         window.setX(x);
         window.setY(2.0F);
         GuiApi.getGui().getPingBypass().getModuleManager().getModulesByCategory(category).forEach(m -> this.createModuleButton(window, m));
         x += window.getW() + 10.0F;
      }
   }

   public void render(GuiGraphics ctx, int i, int j, float f) {
      if (this.minecraft.player == null) {
         this.renderDirtBackground(ctx);
      }

      this.renderer.setCtx(ctx);
      this.renderer.setZ(1);
      ctx.drawString(this.minecraft.font, "pb-gui classic", 2, 2, new Color(190, 190, 190).hashCode());
      this.troon.drawFrame(i, j);
   }
   public boolean isPauseScreen() {
      return false;
   }

   private void createModuleButton(Window window, Module module) {
      HeaderWidget header = (HeaderWidget)window.header(module.getName(), 0, true);
      header.setTooltip(module.getDescription());

      for (Setting<?> setting : module) {
         SimpleWidget widget = null;
         if (setting.getType() == Boolean.class) {
            widget = window.checkbox(setting.getName(), new SettingType<>((Setting<Boolean>)setting));
         } else if (setting instanceof NumberSetting<?> s) {
            if (setting.getType() != Float.class && setting.getType() != Double.class) {
               widget = window.sliderInt(setting.getName(), new SettingType<>((Setting<Integer>)s), s.getMin().intValue(), s.getMax().intValue());
            } else {
               widget = window.sliderFloat(
                  setting.getName(), new SettingType<>((Setting<Float>)s), s.getMin().floatValue(), s.getMax().floatValue()
               );
            }
         }

         if (widget != null) {
            widget.setVisibility(w -> !header.isClosed());
            widget.setTooltip(setting.getDescription());
         }
      }

      window.button("Keybind", () -> this.minecraft.setScreen(new KeybindScreen(module))).setVisibility(w -> !header.isClosed());
   }

   private class SettingType<T> extends TypeContainer<T> {
      private final Setting<T> setting;

      public SettingType(Setting<T> setting) {
         super(null);
         this.setting = setting;
      }

      @Override
      public T get() {
         return (T)this.setting.getValue();
      }

      @Override
      public void set(T value) {
         this.setting.setValue(value);
      }
   }
}
