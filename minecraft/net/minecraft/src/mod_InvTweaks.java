package net.minecraft.src;

import java.util.logging.Logger;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

/**
 * @author Jimeo Wan (jimeo.wan at gmail.com)
 * Website: {@link http://wan.ka.free.fr/?invtweaks}
 * Source code: {@link https://github.com/jimeowan/inventory-tweaks}
 * 
 */
public class mod_InvTweaks extends BaseMod {

    @SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger("mod_InvTweaks");
    
	private InvTweaks instance;
    
    public mod_InvTweaks() {
    	
    	Minecraft mc = ModLoader.getMinecraftInstance();
    	
    	// Register key
    	KeyBinding sortKey = new KeyBinding(
    			"Sort inventory", Keyboard.KEY_R); /* KeyBinding */
    	ModLoader.RegisterKey(this, sortKey, false);
    	
    	// Register in game hooks
    	ModLoader.SetInGameHook(this, true, true);
    	ModLoader.SetInGUIHook(this, true, false);

    	// Instantiate mod core
    	instance = new InvTweaks(mc);
    }
    
	@Override
	public String Version() {
		return "1.11 (1.7.3)";
	}
    
    public final void KeyboardEvent(KeyBinding keyBinding)
    {
    	instance.onSortingKeyPressed();
    }
    
    public boolean OnTickInGame(Minecraft minecraft)
    {
    	instance.onTickInGame();
    	return true; 
    }

    public boolean OnTickInGUI(Minecraft minecraft, GuiScreen guiScreen)
    {
    	instance.onTickInGUI(guiScreen);
    	return true;
    }
    
    public void OnItemPickup(EntityPlayer entityplayer, ItemStack stack)
    {
    	instance.onItemPickup(stack);
    }
    
}