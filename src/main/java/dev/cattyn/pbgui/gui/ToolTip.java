package dev.cattyn.pbgui.gui;

import java.awt.Color;

public final class ToolTip extends Widget {
   private String tooltip;

   public ToolTip(TroonGui troonGui) {
      super(troonGui, 0.0F, 0.0F, 0.0F, 0.0F);
   }

   public void reset() {
      this.tooltip = null;
   }

   public void setTooltip(String tooltip) {
      this.tooltip = tooltip;
   }

   @Override
   public void think(double mouseX, double mouseY) {
      throw new IllegalAccessError();
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      if (this.tooltip != null) {
         this.rectangle(mouseX + 9.0, mouseY, mouseX + 10.0 + this.renderer.textWidth(this.tooltip), mouseY + 9.0, new Color(0, 0, 0, 210));
         this.text(this.tooltip, (int)mouseX + 10, (int)mouseY, this.troonGui.getStyle().getText(), true);
      }
   }

   @Override
   public int getId() {
      return 0;
   }
}
