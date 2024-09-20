package dev.cattyn.pbgui;

import dev.cattyn.pbgui.manager.VisibilityManager;
import dev.cattyn.pbgui.ui.ClickGuiScreen;
import me.earth.pingbypass.PingBypass;
import net.minecraft.client.Minecraft;

import java.util.concurrent.atomic.AtomicReference;

public class Gui {
   public static final String NAME = "pb-gui";
   private final PingBypass pingBypass;
   private final Minecraft minecraft;
   private final VisibilityManager visibility;
   private final AtomicReference<Object> screen = new AtomicReference<>();

   public PingBypass getPingBypass() {
      return this.pingBypass;
   }

   public Minecraft getMinecraft() {
      return this.minecraft;
   }

   public VisibilityManager getVisibility() {
      return this.visibility;
   }

   public Gui(PingBypass pingBypass, Minecraft minecraft, VisibilityManager visibility) {
      this.pingBypass = pingBypass;
      this.minecraft = minecraft;
      this.visibility = visibility;
   }

   public ClickGuiScreen getScreen() {
      Object value = this.screen.get();
      if (value == null) {
         synchronized (this.screen) {
            value = this.screen.get();
            if (value == null) {
               ClickGuiScreen actualValue = new ClickGuiScreen();
               value = actualValue == null ? this.screen : actualValue;
               this.screen.set(value);
            }
         }
      }

      return (ClickGuiScreen)(value == this.screen ? null : value);
   }
}
