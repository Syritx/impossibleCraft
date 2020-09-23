package syritx.main;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (event.getSpawnReason() == SpawnReason.CUSTOM) return;
		
		if (event.getEntityType() == EntityType.CREEPER) {
			Creeper creeper = (Creeper)event.getEntity();
			creeper.setPowered(true);
		}
		
		//------------------------------------//
		// CREATING MOB ARMOUR
		//------------------------------------//
		
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET), 
				  chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE), 
				  leggings = new ItemStack(Material.DIAMOND_LEGGINGS), 
				  boots = new ItemStack(Material.DIAMOND_BOOTS);
		
		ItemMeta helmetMeta = helmet.getItemMeta(), 
				 chestplateMeta = chestplate.getItemMeta(), 
				 leggingsMeta = leggings.getItemMeta(), 
				 bootsMeta = boots.getItemMeta();
		
		helmetMeta.addEnchant(Enchantment.THORNS, 5, true);
		chestplateMeta.addEnchant(Enchantment.THORNS, 5, true);
		leggingsMeta.addEnchant(Enchantment.THORNS, 5, true);
		bootsMeta.addEnchant(Enchantment.THORNS, 5, true);
		
		helmet.setItemMeta(helmetMeta);
		chestplate.setItemMeta(chestplateMeta);
		leggings.setItemMeta(leggingsMeta);
		boots.setItemMeta(bootsMeta);
		
		//------------------------------------//
		// CHECKING MOBS
		//------------------------------------//
		
		if (event.getEntityType() == EntityType.SKELETON) {
			Skeleton skeleton = (Skeleton)event.getEntity();
			
			// EQUIPMENT
			skeleton.getEquipment().setHelmet(helmet);
			skeleton.getEquipment().setChestplate(chestplate);
			skeleton.getEquipment().setLeggings(leggings);
			skeleton.getEquipment().setBoots(boots);
			
			// TOOLS
			ItemStack bow = new ItemStack(Material.BOW);
			
			ItemMeta bowMeta = bow.getItemMeta();
			bowMeta.addEnchant(Enchantment.ARROW_FIRE, 5, true);
			bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 15, true);
			
			bow.setItemMeta(bowMeta);
			skeleton.getEquipment().setItemInMainHand(bow);
		}
		
		if (event.getEntityType() == EntityType.ZOMBIE) {
			Zombie zombie = (Zombie)event.getEntity();
			
			// EQUIPMENT
			zombie.getEquipment().setHelmet(helmet);
			zombie.getEquipment().setChestplate(chestplate);
			zombie.getEquipment().setLeggings(leggings);
			zombie.getEquipment().setBoots(boots);
			
			// TOOLS
			ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
			
			ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
			diamondSwordMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
			diamondSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
			diamondSword.setItemMeta(diamondSwordMeta);
			
			zombie.getEquipment().setItemInMainHand(diamondSword);
		}
	}
	
	//------------------------------------//
	// EATING EVENT
	//------------------------------------//
	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		
		if (player.getHealth() > 2) {
			player.setHealth(2);
		}
	}
	
	@EventHandler
	public void breakBlock(BlockBreakEvent event) {
		
		Location location = event.getBlock().getLocation();
		World world = event.getBlock().getWorld();
		
		for (int i = 0; i < 100; i++) {
			world.strikeLightning(location);
			world.spawnEntity(location, EntityType.PRIMED_TNT);
		}
	}
}