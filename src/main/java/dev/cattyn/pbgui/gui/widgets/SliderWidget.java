package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.types.TypeContainer;
import dev.cattyn.pbgui.gui.widgets.sliders.SliderUtil;

public abstract class SliderWidget<V extends Number> extends ContainerWidget<V> {
   protected final V min;
   protected final V max;
   protected double dragX;
   protected boolean drag;

   public SliderWidget(TroonGui troonGui, Tree window, float x, float y, float w, String id, TypeContainer<V> value, V min, V max) {
      super(troonGui, window, x, y, w, 11.0F, id, value);
      this.min = min;
      this.max = max;
      this.dragX = (double)w * SliderUtil.clamp(this.getValue().doubleValue() / (Math.abs(max.doubleValue()) + Math.abs(min.doubleValue())), 0.0, 1.0);
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      if (this.drag) {
         this.rectangle(0.0, 1.0, (double)this.w, 11.0, this.troonGui.getStyle().getSliderBgFocused());
         this.rectangle(0.0, 1.0, this.dragX, 11.0, this.troonGui.getStyle().getSliderActive());
      } else {
         this.rectangle(
            0.0,
            1.0,
            (double)this.w,
            11.0,
            this.hovered(mouseX, mouseY) ? this.troonGui.getStyle().getSliderBgFocused() : this.troonGui.getStyle().getSliderBg()
         );
         this.rectangle(
            0.0, 1.0, this.dragX, 11.0, this.hovered(mouseX, mouseY) ? this.troonGui.getStyle().getSliderFocused() : this.troonGui.getStyle().getSlider()
         );
      }

      String text = this.id + ": " + this.getValue();
      this.text(text, (int)((double)(this.w / 2.0F) - this.renderer.textWidth(text) / 2.0), 2, this.troonGui.getStyle().getText(), true);
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.renderer.isButtonDown(0)) {
         if (this.hovered(mouseX, mouseY) && this.getGui().getCurrentSlider() == -1) {
            this.drag = true;
            this.getGui().setCurrentSlider(this.getId());
         }
      } else if (this.getGui().getCurrentSlider() == this.getId()) {
         this.drag = false;
         this.getGui().setCurrentSlider(-1);
      }

      if (this.drag) {
         this.sliderLogic(mouseX, mouseY);
      }

      super.think(mouseX, mouseY);
   }

   protected abstract void sliderLogic(double var1, double var3);

   public boolean isDragging() {
      return this.drag;
   }

   public void setDrag(boolean drag) {
      this.drag = drag;
   }
}
