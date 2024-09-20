package dev.cattyn.pbgui.gui.types;

public class TypeContainer<V> {
   private V value;

   public TypeContainer(V value) {
      this.value = value;
   }

   public V get() {
      return this.value;
   }

   public void set(V value) {
      this.value = value;
   }
}
