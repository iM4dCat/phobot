package dev.cattyn.pbgui.gui.widgets.sliders;

import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.types.TypeContainer;
import dev.cattyn.pbgui.gui.widgets.SliderWidget;
import dev.cattyn.pbgui.gui.widgets.Tree;

public class FloatSliderWidget extends SliderWidget<Float> {
   private final int places;

   public FloatSliderWidget(TroonGui troonGui, Tree window, float x, float y, float w, String id, TypeContainer<Float> value, Float min, Float max, int places) {
      super(troonGui, window, x, y, w, id, value, min, max);
      this.places = places;
   }

   public FloatSliderWidget(TroonGui troonGui, Tree window, float x, float y, String id, TypeContainer<Float> value, Float min, Float max, int places) {
      super(troonGui, window, x, y, window.getW() - x * 2.0F, id, value, min, max);
      this.places = places;
   }

   @Override
   protected void sliderLogic(double mouseX, double mouseY) {
      this.dragX = SliderUtil.clamp(mouseX - (double)this.getX(), 0.0, (double)this.w);
      this.setValue(
         Float.valueOf(
            (float)SliderUtil.round(this.dragX / (double)this.w * (double)Math.abs(this.max - this.min) + (double)this.min.floatValue(), this.places)
         )
      );
   }
}
