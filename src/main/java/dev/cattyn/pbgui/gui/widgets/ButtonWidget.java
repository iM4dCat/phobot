package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;

public class ButtonWidget extends SimpleWidget {
   private Runnable runnable;
   private boolean clicked;

   public ButtonWidget(TroonGui troonGui, Tree window, float x, float y, String id, Runnable runnable) {
      super(troonGui, window, x, y, (float)troonGui.getRenderer().textWidth(id) + 4.0F, 11.0F, id);
      this.runnable = runnable;
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      if (this.hovered(mouseX, mouseY)) {
         this.rectangle(
            0.0,
            1.0,
            this.troonGui.getRenderer().textWidth(this.id) + 4.0,
            11.0,
            this.renderer.isButtonDown(0) ? this.troonGui.getStyle().getButtonActive() : this.troonGui.getStyle().getButtonFocused()
         );
      } else {
         this.rectangle(0.0, 1.0, this.troonGui.getRenderer().textWidth(this.id) + 4.0, 11.0, this.troonGui.getStyle().getButton());
      }

      this.text(this.id, 3, 2, this.troonGui.getStyle().getText(), true);
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.renderer.isButtonDown(0)) {
         if (!this.clicked) {
            this.clicked = true;
         }
      } else if (this.clicked) {
         if (this.hovered(mouseX, mouseY) && this.runnable != null) {
            this.runnable.run();
         }

         this.clicked = false;
      }

      super.think(mouseX, mouseY);
   }
}
