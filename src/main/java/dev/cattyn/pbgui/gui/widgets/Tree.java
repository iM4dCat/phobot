package dev.cattyn.pbgui.gui.widgets;

import java.util.List;

import dev.cattyn.pbgui.gui.IWidget;
import dev.cattyn.pbgui.gui.Widget;
import dev.cattyn.pbgui.gui.types.TypeContainer;
import dev.cattyn.pbgui.gui.widgets.sliders.FloatSliderWidget;
import dev.cattyn.pbgui.gui.widgets.sliders.IntegerSliderWidget;

public interface Tree extends IWidget {
   List<SimpleWidget> getTree();

   int getOffset();

   void setOffset(int var1);

   boolean isOpen();

   default void update(Tree tree, double mouseX, double mouseY, int lines, boolean open) {
      if (tree != null && !tree.getTree().isEmpty()) {
         if (open) {
            boolean dragging = false;

            for (int i = 0; i < tree.getTree().size(); i++) {
               Widget widget = tree.getTree().get(i);
               widget.setY((float)(lines * 12 + 12));
               if (widget.getVisibility() == null || widget.getVisibility().test(widget)) {
                  lines++;
                  widget.think(mouseX, mouseY);
               }
            }
         }
      }
   }

   default void paint(Tree tree, double mouseX, double mouseY) {
      if (tree != null && !tree.getTree().isEmpty()) {
         for (Widget widget : tree.getTree()) {
            if (widget.getVisibility() == null || widget.getVisibility().test(widget)) {
               if (widget instanceof Tree) {
                  this.paint((Tree)widget, mouseX, mouseY);
               }

               widget.paint(mouseX, mouseY);
            }
         }
      }
   }

   default void tooltip(String tooltip) {
      if (!this.getTree().isEmpty()) {
         SimpleWidget widget = this.getTree().get(this.getTree().size() - 1);
         widget.setTooltip(tooltip);
      }
   }

   default SimpleWidget text(String id) {
      return this.text(id, 2.0F);
   }

   default SimpleWidget text(String id, float x) {
      return this.register(new LabelWidget(this.getGui(), this, x, (float)(this.getOffset() * 12 + 12), id));
   }

   default SimpleWidget checkbox(String id, TypeContainer<Boolean> container) {
      return this.checkbox(id, container, 2.0F);
   }

   default SimpleWidget checkbox(String id, TypeContainer<Boolean> container, float x) {
      return this.register(new CheckBoxWidget(this.getGui(), this, x, (float)(this.getOffset() * 12 + 12), id, container));
   }

   default SimpleWidget button(String id) {
      return this.button(id, 2.0F, null);
   }

   default SimpleWidget button(String id, Runnable runnable) {
      return this.button(id, 2.0F, runnable);
   }

   default SimpleWidget button(String id, float x) {
      return this.button(id, x, null);
   }

   default SimpleWidget button(String id, float x, Runnable runnable) {
      return this.register(new ButtonWidget(this.getGui(), this, x, (float)(this.getOffset() * 12 + 12), id, runnable));
   }

   default SimpleWidget header(String id) {
      return this.header(id, 50);
   }

   default SimpleWidget header(String id, int percent) {
      return this.header(id, percent, false);
   }

   default SimpleWidget header(String id, int percent, boolean closable) {
      return this.register(new HeaderWidget(this.getGui(), this, 2.0F, (float)(this.getOffset() * 12 + 12), id, percent, closable));
   }

   default SimpleWidget sliderInt(String id, TypeContainer<Integer> value, int min, int max) {
      return this.register(new IntegerSliderWidget(this.getGui(), this, 2.0F, (float)(this.getOffset() * 12 + 12), id, value, min, max));
   }

   default SimpleWidget sliderInt(String id, TypeContainer<Integer> value, int min, int max, float width) {
      return this.register(new IntegerSliderWidget(this.getGui(), this, 2.0F, (float)(this.getOffset() * 12 + 12), width, id, value, min, max));
   }

   default SimpleWidget sliderFloat(String id, TypeContainer<Float> value, float min, float max) {
      return this.sliderFloat(id, value, min, max, 2);
   }

   default SimpleWidget sliderFloat(String id, TypeContainer<Float> value, float min, float max, float width) {
      return this.sliderFloat(id, value, min, max, width, 2);
   }

   default SimpleWidget sliderFloat(String id, TypeContainer<Float> value, float min, float max, int places) {
      return this.register(new FloatSliderWidget(this.getGui(), this, 2.0F, (float)(this.getOffset() * 12 + 12), id, value, min, max, places));
   }

   default SimpleWidget sliderFloat(String id, TypeContainer<Float> value, float min, float max, float width, int places) {
      return this.register(new FloatSliderWidget(this.getGui(), this, 2.0F, (float)(this.getOffset() * 12 + 12), width, id, value, min, max, places));
   }

   default SimpleWidget register(SimpleWidget widget) {
      this.getTree().add(widget);
      return widget;
   }
}
