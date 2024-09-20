package dev.cattyn.pbgui.gui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class TroonGui {
   private final Renderer renderer;
   private final Style style;
   private final ToolTip toolTip;
   private final List<Window> windows = new ArrayList<>();
   private final ArrayDeque<Window> toDestroy = new ArrayDeque<>();
   private Window toMove = null;
   private int currentSlider = -1;
   private int focused = -1;
   private int currentDrag = -1;

   public TroonGui(Renderer renderer) {
      this.renderer = renderer;
      this.style = new Style();
      this.toolTip = new ToolTip(this);
   }

   public TroonGui(Renderer renderer, Style style) {
      this.renderer = renderer;
      this.style = style;
      this.toolTip = new ToolTip(this);
   }

   public void drawFrame(double mouseX, double mouseY) {
      this.toolTip.reset();

      for (int i = 0; i < this.windows.size(); i++) {
         this.windows.get(i).paint(mouseX, mouseY);
      }

      boolean skip = false;

      for (int j = this.windows.size() - 1; j >= 0; j--) {
         if (!skip) {
            Window w = this.windows.get(j);
            w.think(mouseX, mouseY);
            if (w.isInteraction()) {
               skip = true;
            }

            w.setInteraction(false);
         }
      }

      if (this.toDestroy.peek() != null) {
         this.windows.remove(this.toDestroy.poll());
      }

      if (this.toMove != null) {
         this.windows.remove(this.toMove);
         this.windows.add(this.toMove);
         this.toMove = null;
      }

      this.toolTip.paint(mouseX, mouseY);
   }

   public Window createWindow(String header) {
      return this.register(new Window(this, header));
   }

   public Window createWindow(String header, float x, float y) {
      return this.register(new Window(this, header, x, y));
   }

   public Window createWindow(String header, float x, float y, float w, float h) {
      return this.register(new Window(this, header, x, y, w, h));
   }

   private Window register(Window window) {
      this.windows.add(window);
      return window;
   }

   public Style getStyle() {
      return this.style;
   }

   public int getCurrentDrag() {
      return this.currentDrag;
   }

   public void setCurrentDrag(int currentDrag) {
      this.currentDrag = currentDrag;
   }

   public void setToMove(Window toMove) {
      this.toMove = toMove;
   }

   public void setFocused(int focused) {
      this.focused = focused;
   }

   public int getFocused() {
      return this.focused;
   }

   public int getCurrentSlider() {
      return this.currentSlider;
   }

   public void setCurrentSlider(int currentSlider) {
      this.currentSlider = currentSlider;
   }

   public ToolTip getToolTip() {
      return this.toolTip;
   }

   public Renderer getRenderer() {
      return this.renderer;
   }

   public void destroyWindow(Window window) {
      if (window != null) {
         this.toDestroy.push(window);
      }
   }
}
