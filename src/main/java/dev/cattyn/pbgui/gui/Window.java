package dev.cattyn.pbgui.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import dev.cattyn.pbgui.gui.widgets.SimpleWidget;
import dev.cattyn.pbgui.gui.widgets.Tree;

public final class Window extends Widget implements Tree {
   private final List<SimpleWidget> widgets = new ArrayList<>();
   private float height;
   private int lines;
   private boolean open = true;
   private boolean interaction;
   private boolean clicked;
   private double[] dragOffset;
   private String header;

   public Window(TroonGui troonGui, String header) {
      super(troonGui, 0.0F, 0.0F, 100.0F, 110.0F);
      this.header = header;
      this.height = 110.0F;
   }

   public Window(TroonGui troonGui, String header, float x, float y) {
      super(troonGui, x, y, 100.0F, 110.0F);
      this.header = header;
      this.height = 110.0F;
   }

   public Window(TroonGui troonGui, String header, float x, float y, float w, float h) {
      super(troonGui, x, y, w, h);
      this.header = header;
      this.height = h;
   }

   public String getHeader() {
      return this.header;
   }

   public void setHeader(String header) {
      this.header = header;
   }

   public void destroy() {
      this.troonGui.destroyWindow(this);
   }

   @Override
   public void think(double mouseX, double mouseY) {
      if (this.renderer.isButtonDown(0) || this.renderer.isButtonDown(1)) {
         if (this.hovered(mouseX, mouseY)) {
            this.interaction = true;
         }

         if (this.troonGui.getCurrentDrag() == -1) {
            if (this.hovered(mouseX, mouseY) && this.isOpen()) {
               this.troonGui.setFocused(this.getId());
               this.troonGui.setToMove(this);
            } else if (this.troonGui.getFocused() == this.getId()) {
               this.troonGui.setFocused(-1);
            }
         }
      }

      if (this.troonGui.getCurrentDrag() == this.getId()) {
         if (this.renderer.isButtonDown(0)) {
            this.setX((float)(mouseX + this.dragOffset[0]));
            this.setY((float)(mouseY + this.dragOffset[1]));
         } else {
            this.troonGui.setCurrentDrag(-1);
         }
      } else if (this.troonGui.getCurrentDrag() == -1
         && mouseX >= (double)this.getX()
         && mouseX <= (double)(this.getX() + this.getW())
         && mouseY >= (double)this.getY()
         && mouseY <= (double)(this.getY() + 10.0F)) {
         if (this.renderer.isButtonDown(0)) {
            this.dragOffset = new double[]{(double)this.getX() - mouseX, (double)this.getY() - mouseY};
            this.troonGui.setCurrentDrag(this.getId());
            this.troonGui.setToMove(this);
         } else if (this.renderer.isButtonDown(1)) {
            if (!this.clicked) {
               this.open = !this.open;
               this.setH(this.open ? this.height : 10.0F);
               this.clicked = true;
            }
         } else {
            this.clicked = false;
         }
      }

      if (this.open) {
         this.update(this, mouseX, mouseY, 0, true);
      }
   }

   @Override
   public void paint(double mouseX, double mouseY) {
      Style style = this.troonGui.getStyle();
      boolean drag = this.troonGui.getCurrentDrag() == this.getId();
      boolean focused = this.troonGui.getFocused() == this.getId();
      this.rectangle(0.0, 0.0, (double)this.getW(), 10.0, drag ? style.getHeaderActive() : style.getHeader());
      this.text(this.header, (int)((double)(this.getW() / 2.0F) - this.renderer.textWidth(this.header) / 2.0), 1, this.troonGui.getStyle().getText(), true);
      if (this.open) {
         this.rectangle(
            0.0,
            10.0,
            (double)this.getW(),
            (double)this.getH(),
            drag ? style.getBackgroundActive() : (focused ? style.getBackground() : style.getBackgroundUnfocused())
         );

         for (SimpleWidget widget : this.widgets) {
            if (widget.getVisibility() == null || widget.getVisibility().test(widget)) {
               widget.paint(mouseX, mouseY);
            }
         }
      }
   }

   @Override
   public int getId() {
      return Objects.hashCode(this.header);
   }

   @Override
   public List<SimpleWidget> getTree() {
      return this.widgets;
   }

   @Override
   public int getOffset() {
      return this.lines;
   }

   @Override
   public void setOffset(int i) {
      this.lines = i;
   }

   @Override
   public boolean isOpen() {
      return this.open;
   }

   public boolean isInteraction() {
      return this.interaction;
   }

   public void setInteraction(boolean interaction) {
      this.interaction = interaction;
   }

   public void setHeight(float height) {
      this.height = height;
   }
}
