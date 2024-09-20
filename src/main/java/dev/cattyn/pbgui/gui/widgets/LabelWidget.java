package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;

public class LabelWidget extends SimpleWidget {
   public LabelWidget(TroonGui troonGui, Tree window, float x, float y, String id) {
      super(troonGui, window, x, y, (float)troonGui.getRenderer().textWidth(id), 11.0F, id);
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      this.text(this.id, 0, 2, this.troonGui.getStyle().getText(), true);
   }
}
