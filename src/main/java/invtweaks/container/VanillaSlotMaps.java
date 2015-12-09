package invtweaks.container;

import invtweaks.InvTweaksConst;
import invtweaks.api.container.ContainerSection;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
public class VanillaSlotMaps {
    public static Map<ContainerSection, List<Slot>> containerPlayerSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.CRAFTING_OUT, container.inventorySlots.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.inventorySlots.subList(1, 5));
        slotRefs.put(ContainerSection.ARMOR, container.inventorySlots.subList(5, 9));
        slotRefs.put(ContainerSection.INVENTORY, container.inventorySlots.subList(9, 45));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.inventorySlots.subList(9, 36));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.inventorySlots.subList(36, 45));

        return slotRefs;
    }

    @SideOnly(Side.CLIENT)
    public static boolean containerCreativeIsInventory(GuiContainerCreative.ContainerCreative container) {
        GuiScreen currentScreen = FMLClientHandler.instance().getClient().currentScreen;
        return currentScreen instanceof GuiContainerCreative && ((GuiContainerCreative) currentScreen).getSelectedTabIndex() == CreativeTabs.tabInventory.getTabIndex();
    }
    @SideOnly(Side.CLIENT)
    public static Map<ContainerSection, List<Slot>> containerCreativeSlots(GuiContainerCreative.ContainerCreative container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.ARMOR, container.inventorySlots.subList(5, 9));
        slotRefs.put(ContainerSection.INVENTORY, container.inventorySlots.subList(9, 45));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.inventorySlots.subList(9, 36));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.inventorySlots.subList(36, 45));

        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerChestDispenserSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.CHEST, container.inventorySlots.subList(0, container.inventorySlots
                .size() - InvTweaksConst.INVENTORY_SIZE));

        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerHorseSlots(ContainerHorseInventory container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        if(container.theHorse.isChested()) { // Chest slots are only added if chest is added. Saddle/armor slots always exist.
            slotRefs.put(ContainerSection.CHEST, container.inventorySlots.subList(2, container.inventorySlots.size() - InvTweaksConst.INVENTORY_SIZE));
        }

        return slotRefs;
    }

    public static boolean containerHorseIsInventory(ContainerHorseInventory container) {
        return container.theHorse.isChested();
    }

    public static Map<ContainerSection, List<Slot>> containerFurnaceSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.FURNACE_IN, container.inventorySlots.subList(0, 1));
        slotRefs.put(ContainerSection.FURNACE_FUEL, container.inventorySlots.subList(1, 2));
        slotRefs.put(ContainerSection.FURNACE_OUT, container.inventorySlots.subList(2, 3));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerWorkbenchSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.CRAFTING_OUT, container.inventorySlots.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.inventorySlots.subList(1, 10));

        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerEnchantmentSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.ENCHANTMENT, container.inventorySlots.subList(0, 1));

        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerBrewingSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        slotRefs.put(ContainerSection.BREWING_BOTTLES, container.inventorySlots.subList(0, 3));
        slotRefs.put(ContainerSection.BREWING_INGREDIENT, container.inventorySlots.subList(3, 4));

        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> unknownContainerSlots(Container container) {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        int size = container.inventorySlots.size();

        if(size >= InvTweaksConst.INVENTORY_SIZE) {
            // Assuming the container ends with the inventory, just like all vanilla containers.
            slotRefs.put(ContainerSection.CHEST,
                    container.inventorySlots.subList(0, size - InvTweaksConst.INVENTORY_SIZE));
        } else {
            slotRefs.put(ContainerSection.CHEST, container.inventorySlots.subList(0, size));
        }

        return slotRefs;
    }
}
