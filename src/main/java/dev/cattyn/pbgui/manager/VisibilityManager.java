package dev.cattyn.pbgui.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import me.earth.pingbypass.api.module.Module;

public final class VisibilityManager {
   private final Map<Module, BooleanSupplier> visibilities = new HashMap<>();

   public void register(Module module, BooleanSupplier supplier) {
      this.visibilities.put(module, supplier);
   }

   public boolean isVisible(Module module) {
      return !this.visibilities.containsKey(module) ? true : this.visibilities.get(module).getAsBoolean();
   }
}
