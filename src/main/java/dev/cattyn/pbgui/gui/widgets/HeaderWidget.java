package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;

public class HeaderWidget extends SimpleWidget {
   private int percent;
   private boolean closable;
   private boolean closed;
   private boolean clicked;

   public HeaderWidget(TroonGui troonGui, Tree window, float x, float y, String id, int percent, boolean closable) {
      super(troonGui, window, x, y, window.getW() - 4.0F, 11.0F, id);
      this.setPercent(percent);
      this.closable = closable;
      this.closed = closable;
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      StringBuilder sb = new StringBuilder(this.id);
      if (this.closable) {
         sb.append(" ").append(this.closed ? ">" : "v");
      }

      double betterPercent = (double)((float)this.percent / 100.0F);
      double textLength = this.renderer.textWidth(sb.toString());
      double dividedLength = textLength / 2.0;
      this.text(
         sb.toString(),
         (int)Math.min(Math.max((double)this.w * betterPercent - dividedLength, 0.0), (double)this.w - textLength),
         2,
         this.troonGui.getStyle().getText(),
         true
      );
      double f = Math.min((double)this.w * betterPercent - dividedLength - 2.0, (double)this.w - textLength - 2.0);
      if (f > 0.0) {
         this.rectangle(0.0, 6.0, f, 7.0, this.troonGui.getStyle().getHeaderWidget());
      }

      double s = Math.max((double)this.w * betterPercent + dividedLength + 2.0, textLength + 2.0);
      if (s < (double)this.w) {
         this.rectangle(s, 6.0, (double)this.w, 7.0, this.troonGui.getStyle().getHeaderWidget());
      }
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.renderer.isButtonDown(1)) {
         this.clicked = true;
      } else {
         if (this.clicked && this.hovered(mouseX, mouseY)) {
            this.closed = !this.closed;
         }

         this.clicked = false;
      }

      super.think(mouseX, mouseY);
   }

   public void setPercent(int percent) {
      this.percent = Math.min(Math.max(percent, 0), 100);
   }

   public int getPercent() {
      return this.percent;
   }

   public boolean isClosed() {
      return this.closed;
   }
}
