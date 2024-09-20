package dev.cattyn.pbgui.gui.widgets;

import java.util.Objects;

import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.Widget;

public abstract class SimpleWidget extends Widget {
   protected final Tree window;
   protected final String id;
   protected String tooltip;

   public SimpleWidget(TroonGui troonGui, Tree window, float x, float y, float w, float h, String id) {
      super(troonGui, x, y, w, h);
      this.window = window;
      this.id = id;
   }

   @Override
   public int getId() {
      return Objects.hash(this.window.getId(), this.id);
   }

   @Override
   public float getX() {
      return this.window.getX() + super.getX();
   }

   @Override
   public float getY() {
      return this.window.getY() + super.getY();
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.hovered(mouseX, mouseY) && this.tooltip != null) {
         this.troonGui.getToolTip().setTooltip(this.tooltip);
      }
   }

   @Override
   public boolean hovered(double x, double y) {
      return x >= (double)this.getX() && x <= (double)(this.getX() + this.getW()) && y >= (double)this.getY() && y <= (double)(this.getY() + this.getH());
   }

   public String getTooltip() {
      return this.tooltip;
   }

   public void setTooltip(String tooltip) {
      this.tooltip = tooltip;
   }

   @Override
   public String toString() {
      return this.id;
   }
}
