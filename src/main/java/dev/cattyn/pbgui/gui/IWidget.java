package dev.cattyn.pbgui.gui;

public interface IWidget {
   TroonGui getGui();

   int getId();

   float getX();

   void setX(float var1);

   float getY();

   void setY(float var1);

   float getW();

   void setW(float var1);

   float getH();

   void setH(float var1);

   float getGlobalW();

   float getGlobalH();
}
