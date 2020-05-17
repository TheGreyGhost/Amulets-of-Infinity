/* skip999
 * 5/11/2020
 * Purpose: This library will contain all blocks for the ContainedInfinity
 * 			library. This is our first mod so bugs are to be expected. Currently,
 * 			ContainedInfinity consists of skip999 and ContainedShadow
 */
package com.contInf.BlockLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.contInf.BlockLib.init.BlockInit;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

//tags forge to tell this is a mod
@Mod("continfblocklib")
/**
 * This library contains all blocks currently in the ContainedInfinity Mod Library.
 * Currently there are X blocks, which are listed below:
 * 
 * Overworld:
 * 
 * >Marble
 * >Gneiss
 * >Breccia
 * >Siltstone
 * >Rose Gold Ore
 * >Carbon-Infused Iron Ore
 * >Lignite Coal Ore
 * >Sapphire Ore
 * >Bauxite Ore
 * >Ruby Ore
 * >Sulphur Ore
 * >Alloy Forge
 * >Warding Beacon
 * 
 * Nether:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author skip999
 *
 */
@Mod.EventBusSubscriber(modid = "continfblocklib", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContInfBlockLib {
	
	//Instance of the mod for external use
	public static ContInfBlockLib instance;
	//Mod ID 
	public static final String modID = "continfblocklib";
	//Will print messages to log file
	private static final Logger logger = LogManager.getLogger(modID);
	
	
	/**
	 * Constructor: Initializes mod with forge files
	 */
	public ContInfBlockLib() {
		
		//Has loader listen for code within setups
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientRegistries);
		
		//Initilizes blocks
		BlockInit.BLOCKS.register(modEventBus);
		
		//Creates an instance of the mod
		instance = this;
		
		//registers event bus
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	/**
	 * Creates BlockItems for blocks
	 * @param event
	 */
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
				
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->   {
			final Item.Properties properties = 
					new Item.Properties().group(ContInfBlockLibItemGroup.itemGroupInstance);
			final BlockItem blockItem = new BlockItem(block,properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
		
		logger.debug("Registered BlockItems");
	}
	
	/**
	 * runs code that will be viewed by all players
	 * @param event
	 */
	private void setup(final FMLCommonSetupEvent event) {
		
		logger.info("setup method registered.");
		
	}
	
	
	/**
	 * runs code that will be viewed by only client player
	 * @param event
	 */
	private void clientRegistries(final FMLClientSetupEvent event) {
		
		logger.info("clintRegistries method registered.");
	
	}
	
	
	/**
	 * Code to run as server starting
	 * @param event
	 */
	@SubscribeEvent
	public void asServerStarting(FMLServerStartingEvent event) {
		
		logger.info("asServerStarting method registered.");
		
	}
	
	/**
	 * ItemGroup for ContainedInfinityBlockLibrart
	 * @author skip999
	 *
	 */
	public static class ContInfBlockLibItemGroup extends ItemGroup{

		//Instance of ItemGroup
		public static final ContInfBlockLibItemGroup itemGroupInstance = 
				new ContInfBlockLibItemGroup(ItemGroup.GROUPS.length, "ContInfBlockLibItemGroupTab");
				
				
		private ContInfBlockLibItemGroup(int index, String label) {
			super(index, label);
		}

		/**
		 * Uses marble as icon
		 */
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.marble.get());
		}
		
	}
	
}
