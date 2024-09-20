package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.types.TypeContainer;

public class CheckBoxWidget extends ContainerWidget<Boolean> {
   private boolean clicked;

   public CheckBoxWidget(TroonGui troonGui, Tree window, float x, float y, String id, TypeContainer<Boolean> value) {
      super(troonGui, window, x, y, (float)troonGui.getRenderer().textWidth(id) + 3.0F, 11.0F, id, value);
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      if (this.btnHovered(mouseX, mouseY)) {
         this.rectangle(
            0.0, 1.0, 10.0, 11.0, this.getValue() ? this.troonGui.getStyle().getCheckboxActiveFocused() : this.troonGui.getStyle().getCheckboxInactiveFocused()
         );
      } else {
         this.rectangle(0.0, 1.0, 10.0, 11.0, this.getValue() ? this.troonGui.getStyle().getCheckboxActive() : this.troonGui.getStyle().getCheckboxInactive());
      }

      this.text(this.id, 12, 2, this.troonGui.getStyle().getText(), true);
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.renderer.isButtonDown(0)) {
         this.clicked = true;
      } else {
         if (this.clicked && this.btnHovered(mouseX, mouseY)) {
            this.setValue(Boolean.valueOf(!this.getValue()));
         }

         this.clicked = false;
      }

      super.think(mouseX, mouseY);
   }

   private boolean btnHovered(double mouseX, double mouseY) {
      return mouseX >= (double)this.getX() && mouseX <= (double)(this.getX() + 9.0F) && mouseY >= (double)this.getY() && mouseY <= (double)(this.getY() + 9.0F);
   }
}
