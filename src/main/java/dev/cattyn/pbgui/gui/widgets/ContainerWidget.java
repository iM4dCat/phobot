package dev.cattyn.pbgui.gui.widgets;

import dev.cattyn.pbgui.gui.TroonGui;
import dev.cattyn.pbgui.gui.types.TypeContainer;

public abstract class ContainerWidget<V> extends SimpleWidget {
   private final TypeContainer<V> value;

   public ContainerWidget(TroonGui troonGui, Tree window, float x, float y, float w, float h, String id, TypeContainer<V> value) {
      super(troonGui, window, x, y, w, h, id);
      this.value = value;
   }

   public TypeContainer<V> getRawValue() {
      return this.value;
   }

   public V getValue() {
      return this.value.get();
   }

   public void setValue(V value) {
      this.value.set(value);
   }
}
