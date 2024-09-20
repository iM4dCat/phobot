package dev.cattyn.pbgui.gui;

public interface Renderer {
   void text(String var1, int var2, int var3, int var4, boolean var5);

   void rectangle(double var1, double var3, double var5, double var7, int var9);

   double textWidth(String var1);

   boolean isButtonDown(int var1);
}
