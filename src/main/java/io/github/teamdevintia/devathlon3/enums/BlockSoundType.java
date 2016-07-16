package io.github.teamdevintia.devathlon3.enums;

import org.bukkit.Material;
import org.bukkit.Sound;

/**
 * @author Shad0wCore
 */
enum BlockSoundType {

    WOODSOUND(Sound.BLOCK_WOOD_BREAK),
    STONESOUND(Sound.BLOCK_STONE_BREAK),
    SNOWSOUND(Sound.BLOCK_SNOW_BREAK),
    ANVILSOUND(Sound.BLOCK_ANVIL_BREAK),
    CLOTHSOUND(Sound.BLOCK_CLOTH_BREAK),
    GLASSSOUND(Sound.BLOCK_GLASS_BREAK),
    GRASSSOUND(Sound.BLOCK_GRASS_BREAK),
    GRAVELSOUND(Sound.BLOCK_GRAVEL_BREAK),
    SANDSOUND(Sound.BLOCK_SAND_BREAK),
    SLIMESOUND(Sound.BLOCK_SLIME_BREAK);

    private Sound sound;

    BlockSoundType(Sound sound) {
        this.sound = sound;
    }

    public static Sound fetch(int id) {
        return fetch(Material.getMaterial(id));
    }

    public static Sound fetch(Material material) {
        switch (material) {

            case WOOD:
            case WOOD_BUTTON:
            case WOOD_DOOR:
            case WOOD_DOUBLE_STEP:
            case WOOD_PLATE:
            case WOOD_STAIRS:
            case WOOD_STEP:
            case WOODEN_DOOR:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case DARK_OAK_DOOR:
            case JUNGLE_DOOR:
            case SPRUCE_DOOR:
            case TRAP_DOOR:
            case FENCE:
            case FENCE_GATE:
            case ACACIA_FENCE:
            case BIRCH_FENCE:
            case DARK_OAK_FENCE:
            case JUNGLE_FENCE:
            case LADDER:
            case SPRUCE_FENCE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case LOG:
            case LOG_2:
            case WORKBENCH:
            case CHEST:
            case TRAPPED_CHEST:
            case JUKEBOX:
            case NOTE_BLOCK:
            case TORCH:
            case MELON_STEM:
            case REDSTONE_TORCH_OFF:
            case REDSTONE_TORCH_ON:
            case BED_BLOCK:
            case WALL_SIGN:
            case SIGN_POST:
            case STANDING_BANNER:
            case WALL_BANNER:
            case SIGN:
            case PUMPKIN:
            case JACK_O_LANTERN:
            case PUMPKIN_STEM:
            case PISTON_EXTENSION:
            case CHORUS_FLOWER:
            case BOOKSHELF:
            case CHORUS_PLANT:
            case CHORUS_FRUIT:
            case PISTON_BASE:
            case PISTON_STICKY_BASE:
            case TRIPWIRE_HOOK:
            case HUGE_MUSHROOM_1:
            case HUGE_MUSHROOM_2:
                return WOODSOUND.getSound();
            case ANVIL:
                return ANVILSOUND.getSound();
            case STONE:
            case LEVER:
            case STONE_BUTTON:
            case STONE_PLATE:
            case STONE_SLAB2:
            case ENDER_STONE:
            case DOUBLE_STONE_SLAB2:
            case COBBLESTONE:
            case GLOWSTONE:
            case MOSSY_COBBLESTONE:
            case RED_SANDSTONE:
            case REDSTONE:
            case SANDSTONE:
            case COBBLESTONE_STAIRS:
            case SANDSTONE_STAIRS:
            case REDSTONE_WIRE:
            case REDSTONE_ORE:
            case REDSTONE_COMPARATOR_ON:
            case REDSTONE_COMPARATOR_OFF:
            case REDSTONE_COMPARATOR:
            case REDSTONE_BLOCK:
            case RED_SANDSTONE_STAIRS:
            case GLOWING_REDSTONE_ORE:
            case IRON_DOOR_BLOCK:
            case IRON_TRAPDOOR:
            case IRON_PLATE:
            case IRON_ORE:
            case IRON_FENCE:
            case IRON_DOOR:
            case IRON_BLOCK:
            case FURNACE:
            case BURNING_FURNACE:
            case MONSTER_EGG:
            case SKULL:
            case END_ROD:
            case END_BRICKS:
            case ENDER_CHEST:
            case END_GATEWAY:
            case ENDER_PORTAL_FRAME:
            case ENDER_PORTAL:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case GOLD_BLOCK:
            case GOLD_PLATE:
            case GOLD_ORE:
            case COAL_BLOCK:
            case COAL_ORE:
            case DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case NETHER_BRICK:
            case NETHER_BRICK_STAIRS:
            case NETHER_FENCE:
            case NETHER_WARTS:
            case NETHERRACK:
            case STAINED_CLAY:
            case HARD_CLAY:
            case CLAY:
            case CLAY_BRICK:
            case PRISMARINE:
            case PRISMARINE_CRYSTALS:
            case SEA_LANTERN:
            case ACTIVATOR_RAIL:
            case DETECTOR_RAIL:
            case RAILS:
            case POWERED_RAIL:
            case QUARTZ_BLOCK:
            case QUARTZ_ORE:
            case QUARTZ_STAIRS:
            case BREWING_STAND:
            case BRICK_STAIRS:
            case PURPUR_STAIRS:
            case SMOOTH_STAIRS:
            case CAULDRON:
            case COBBLE_WALL:
            case DAYLIGHT_DETECTOR:
            case DIODE:
            case DIODE_BLOCK_OFF:
            case DIODE_BLOCK_ON:
            case ENCHANTMENT_TABLE:
            case EMERALD_BLOCK:
            case EMERALD_ORE:
            case COMMAND_REPEATING:
            case BRICK:
            case COMMAND_CHAIN:
            case COMMAND:
            case HOPPER:
            case DAYLIGHT_DETECTOR_INVERTED:
            case FLOWER_POT:
            case MELON_BLOCK:
            case MOB_SPAWNER:
            case PURPUR_BLOCK:
            case PURPUR_DOUBLE_SLAB:
            case DISPENSER:
            case DROPPER:
            case PURPUR_PILLAR:
            case PURPUR_SLAB:
            case SMOOTH_BRICK:
            case STRUCTURE_BLOCK:
            case WEB:
            case TRIPWIRE:
            case OBSIDIAN:
            case PISTON_MOVING_PIECE:
            case NETHER_STALK:
                return STONESOUND.getSound();
            case REDSTONE_LAMP_ON:
            case REDSTONE_LAMP_OFF:
            case GLASS:
            case STAINED_GLASS:
            case THIN_GLASS:
            case STAINED_GLASS_PANE:
            case BEACON:
                return GLASSSOUND.getSound();
            case SNOW:
            case SNOW_BLOCK:
                return SNOWSOUND.getSound();
            case GRASS:
            case GRASS_PATH:
            case LONG_GRASS:
            case SAPLING:
            case SEEDS:
            case MELON_SEEDS:
            case BEETROOT_SEEDS:
            case PUMPKIN_SEEDS:
            case LEAVES:
            case LEAVES_2:
            case HAY_BLOCK:
            case BEETROOT_BLOCK:
            case BEETROOT:
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
            case CROPS:
            case DEAD_BUSH:
            case RED_ROSE:
            case YELLOW_FLOWER:
            case VINE:
            case WATER_LILY:
            case DOUBLE_PLANT:
                return GRASSSOUND.getSound();
            case WOOL:
            case CARPET:
                return CLOTHSOUND.getSound();
            case SAND:
            case SOUL_SAND:
                return SANDSOUND.getSound();
            case GRAVEL:
            case DIRT:
                return GRAVELSOUND.getSound();
            case SLIME_BLOCK:
                return SLIMESOUND.getSound();
            default:
                return WOODSOUND.getSound();
        }

    }

    public Sound getSound() {
        return this.sound;
    }

}