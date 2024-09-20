package dev.cattyn.pbgui;

import lombok.Getter;

public final class GuiApi {
   @Getter
   private static Gui gui;

   private GuiApi() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

    static void setGui(Gui gui) {
      GuiApi.gui = gui;
   }
}
