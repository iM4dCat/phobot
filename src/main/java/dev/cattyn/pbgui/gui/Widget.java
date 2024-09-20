package dev.cattyn.pbgui.gui;

import java.awt.Color;
import java.util.function.Predicate;

public abstract class Widget implements IWidget {
   protected Predicate<Widget> visibility;
   protected final TroonGui troonGui;
   protected final Renderer renderer;
   protected float x;
   protected float y;
   protected float w;
   protected float h;

   public Widget(TroonGui troonGui, float x, float y, float w, float h) {
      this.troonGui = troonGui;
      this.renderer = troonGui.getRenderer();
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
   }

   public abstract void think(double var1, double var3);

   public abstract void paint(double var1, double var3);

   @Override
   public abstract int getId();

   @Override
   public TroonGui getGui() {
      return this.troonGui;
   }

   @Override
   public float getX() {
      return this.x;
   }

   @Override
   public void setX(float x) {
      this.x = x;
   }

   @Override
   public float getY() {
      return this.y;
   }

   @Override
   public void setY(float y) {
      this.y = y;
   }

   @Override
   public float getW() {
      return this.w;
   }

   @Override
   public void setW(float w) {
      this.w = w;
   }

   @Override
   public float getH() {
      return this.h;
   }

   @Override
   public void setH(float h) {
      this.h = h;
   }

   @Override
   public float getGlobalW() {
      return this.getX() + this.getW();
   }

   @Override
   public float getGlobalH() {
      return this.getY() + this.getH();
   }

   public Predicate<Widget> getVisibility() {
      return this.visibility;
   }

   public Widget setVisibility(Predicate<Widget> visibility) {
      this.visibility = visibility;
      return this;
   }

   public boolean hovered(double x, double y) {
      return x >= (double)this.getX() && x <= (double)(this.getX() + this.getW()) && y >= (double)this.getY() && y <= (double)(this.getY() + this.getH());
   }

   protected void text(String text, int x, int y, Color color, boolean shadow) {
      this.renderer.text(text, (int)this.getX() + x, (int)this.getY() + y, color.hashCode(), shadow);
   }

   protected void rectangle(double x, double y, double w, double h, Color color) {
      this.renderer.rectangle((double)this.getX() + x, (double)this.getY() + y, (double)this.getX() + w, (double)this.getY() + h, color.hashCode());
   }
}
