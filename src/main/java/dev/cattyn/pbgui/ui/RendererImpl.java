package dev.cattyn.pbgui.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;
import dev.cattyn.pbgui.gui.Renderer;

public class RendererImpl implements Renderer {
   private GuiGraphics ctx;
   private final Minecraft mc = Minecraft.getInstance();
   private int z;

   @Override
   public void text(String text, int x, int y, int color, boolean shadow) {
      this.ctx.pose().translate(0.0F, 0.0F, (float)this.z);
      this.ctx.drawString(this.mc.font, text, x, y, color, shadow);
   }

   @Override
   public void rectangle(double x, double y, double w, double h, int color) {
      this.ctx.fill((int)x, (int)y, (int)w, (int)h, this.z, color);
      this.z++;
   }

   @Override
   public double textWidth(String text) {
      return (double)this.mc.font.width(text);
   }

   @Override
   public boolean isButtonDown(int i) {
      return GLFW.glfwGetMouseButton(this.mc.getWindow().getWindow(), i) == 1;
   }

   public void setCtx(GuiGraphics ctx) {
      this.ctx = ctx;
   }

   public void setZ(int z) {
      this.z = z;
   }
}
