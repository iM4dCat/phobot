package dev.cattyn.pbgui.gui.widgets.sliders;

public final class SliderUtil {
   private SliderUtil() {
      throw new IllegalAccessError();
   }

   public static double clamp(double val, double min, double max) {
      return Math.min(Math.max(val, min), max);
   }

   public static double round(double val, int places) {
      return (double)Math.round(val * Math.pow(10.0, (double)places)) / Math.pow(10.0, (double)places);
   }
}
