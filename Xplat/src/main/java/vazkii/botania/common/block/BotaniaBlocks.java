/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.block;

import net.minecraft.core.*;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import org.jetbrains.annotations.NotNull;

import vazkii.botania.api.block.PetalApothecary;
import vazkii.botania.api.state.BotaniaStateProperties;
import vazkii.botania.api.state.enums.AlfheimPortalState;
import vazkii.botania.api.state.enums.LuminizerVariant;
import vazkii.botania.common.block.corporea.*;
import vazkii.botania.common.block.decor.*;
import vazkii.botania.common.block.decor.stairs.BotaniaStairBlock;
import vazkii.botania.common.block.dispenser.*;
import vazkii.botania.common.block.mana.*;
import vazkii.botania.common.block.red_string.*;
import vazkii.botania.common.entity.EnderAirBottleEntity;
import vazkii.botania.common.entity.VineBallEntity;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.BlockItemWithSpecialRenderer;
import vazkii.botania.common.item.block.TinyPotatoBlockItem;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.mixin.DispenserBlockAccessor;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.Locale;
import java.util.function.BiConsumer;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public final class BotaniaBlocks {
	private static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> NO_SPAWN = (state, world, pos, et) -> false;
	private static final BlockBehaviour.StatePredicate NO_SUFFOCATION = (state, world, pos) -> false;

	public static final Block whiteFlower = new BotaniaFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).strength(0).sound(SoundType.GRASS));
	public static final Block orangeFlower = new BotaniaFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block magentaFlower = new BotaniaFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block lightBlueFlower = new BotaniaFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block yellowFlower = new BotaniaFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block limeFlower = new BotaniaFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block pinkFlower = new BotaniaFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block grayFlower = new BotaniaFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block lightGrayFlower = new BotaniaFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block cyanFlower = new BotaniaFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block purpleFlower = new BotaniaFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block blueFlower = new BotaniaFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block brownFlower = new BotaniaFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block greenFlower = new BotaniaFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block redFlower = new BotaniaFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block blackFlower = new BotaniaFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(whiteFlower));

	public static final Block whiteShinyFlower = new GlimmeringFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.copy(whiteFlower).lightLevel(s -> 15));
	public static final Block orangeShinyFlower = new GlimmeringFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block magentaShinyFlower = new GlimmeringFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block lightBlueShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block yellowShinyFlower = new GlimmeringFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block limeShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block pinkShinyFlower = new GlimmeringFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block grayShinyFlower = new GlimmeringFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block lightGrayShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block cyanShinyFlower = new GlimmeringFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block purpleShinyFlower = new GlimmeringFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block blueShinyFlower = new GlimmeringFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block brownShinyFlower = new GlimmeringFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block greenShinyFlower = new GlimmeringFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block redShinyFlower = new GlimmeringFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.copy(whiteShinyFlower));
	public static final Block blackShinyFlower = new GlimmeringFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(whiteShinyFlower));

	public static final Block whiteBuriedPetals = new BuriedPetalBlock(DyeColor.WHITE, BlockBehaviour.Properties.copy(whiteFlower).sound(SoundType.MOSS).lightLevel(s -> 4));
	public static final Block orangeBuriedPetals = new BuriedPetalBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block magentaBuriedPetals = new BuriedPetalBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block lightBlueBuriedPetals = new BuriedPetalBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block yellowBuriedPetals = new BuriedPetalBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block limeBuriedPetals = new BuriedPetalBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block pinkBuriedPetals = new BuriedPetalBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block grayBuriedPetals = new BuriedPetalBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block lightGrayBuriedPetals = new BuriedPetalBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block cyanBuriedPetals = new BuriedPetalBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block purpleBuriedPetals = new BuriedPetalBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block blueBuriedPetals = new BuriedPetalBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block brownBuriedPetals = new BuriedPetalBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block greenBuriedPetals = new BuriedPetalBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block redBuriedPetals = new BuriedPetalBlock(DyeColor.RED, BlockBehaviour.Properties.copy(whiteBuriedPetals));
	public static final Block blackBuriedPetals = new BuriedPetalBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(whiteBuriedPetals));

	public static final BlockBehaviour.Properties FLOATING_PROPS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F).sound(SoundType.GRAVEL).lightLevel(s -> 15);
	public static final Block whiteFloatingFlower = new FloatingFlowerBlock(DyeColor.WHITE, FLOATING_PROPS);
	public static final Block orangeFloatingFlower = new FloatingFlowerBlock(DyeColor.ORANGE, FLOATING_PROPS);
	public static final Block magentaFloatingFlower = new FloatingFlowerBlock(DyeColor.MAGENTA, FLOATING_PROPS);
	public static final Block lightBlueFloatingFlower = new FloatingFlowerBlock(DyeColor.LIGHT_BLUE, FLOATING_PROPS);
	public static final Block yellowFloatingFlower = new FloatingFlowerBlock(DyeColor.YELLOW, FLOATING_PROPS);
	public static final Block limeFloatingFlower = new FloatingFlowerBlock(DyeColor.LIME, FLOATING_PROPS);
	public static final Block pinkFloatingFlower = new FloatingFlowerBlock(DyeColor.PINK, FLOATING_PROPS);
	public static final Block grayFloatingFlower = new FloatingFlowerBlock(DyeColor.GRAY, FLOATING_PROPS);
	public static final Block lightGrayFloatingFlower = new FloatingFlowerBlock(DyeColor.LIGHT_GRAY, FLOATING_PROPS);
	public static final Block cyanFloatingFlower = new FloatingFlowerBlock(DyeColor.CYAN, FLOATING_PROPS);
	public static final Block purpleFloatingFlower = new FloatingFlowerBlock(DyeColor.PURPLE, FLOATING_PROPS);
	public static final Block blueFloatingFlower = new FloatingFlowerBlock(DyeColor.BLUE, FLOATING_PROPS);
	public static final Block brownFloatingFlower = new FloatingFlowerBlock(DyeColor.BROWN, FLOATING_PROPS);
	public static final Block greenFloatingFlower = new FloatingFlowerBlock(DyeColor.GREEN, FLOATING_PROPS);
	public static final Block redFloatingFlower = new FloatingFlowerBlock(DyeColor.RED, FLOATING_PROPS);
	public static final Block blackFloatingFlower = new FloatingFlowerBlock(DyeColor.BLACK, FLOATING_PROPS);

	public static final Block petalBlockWhite = new PetalBlock(DyeColor.WHITE, BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).strength(0.4F).sound(SoundType.MOSS));
	public static final Block petalBlockOrange = new PetalBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.ORANGE));
	public static final Block petalBlockMagenta = new PetalBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.MAGENTA));
	public static final Block petalBlockLightBlue = new PetalBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.LIGHT_BLUE));
	public static final Block petalBlockYellow = new PetalBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.YELLOW));
	public static final Block petalBlockLime = new PetalBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.LIME));
	public static final Block petalBlockPink = new PetalBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.PINK));
	public static final Block petalBlockGray = new PetalBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.GRAY));
	public static final Block petalBlockSilver = new PetalBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.LIGHT_GRAY));
	public static final Block petalBlockCyan = new PetalBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.CYAN));
	public static final Block petalBlockPurple = new PetalBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.PURPLE));
	public static final Block petalBlockBlue = new PetalBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.BLUE));
	public static final Block petalBlockBrown = new PetalBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.BROWN));
	public static final Block petalBlockGreen = new PetalBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.GREEN));
	public static final Block petalBlockRed = new PetalBlock(DyeColor.RED, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.RED));
	public static final Block petalBlockBlack = new PetalBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(petalBlockWhite).mapColor(DyeColor.BLACK));

	public static final Block whiteMushroom = new BotaniaMushroomBlock(DyeColor.WHITE, BlockBehaviour.Properties.copy(whiteFlower).lightLevel(s -> 3));
	public static final Block orangeMushroom = new BotaniaMushroomBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block magentaMushroom = new BotaniaMushroomBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block lightBlueMushroom = new BotaniaMushroomBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block yellowMushroom = new BotaniaMushroomBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block limeMushroom = new BotaniaMushroomBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block pinkMushroom = new BotaniaMushroomBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block grayMushroom = new BotaniaMushroomBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block lightGrayMushroom = new BotaniaMushroomBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block cyanMushroom = new BotaniaMushroomBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block purpleMushroom = new BotaniaMushroomBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block blueMushroom = new BotaniaMushroomBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block brownMushroom = new BotaniaMushroomBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block greenMushroom = new BotaniaMushroomBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block redMushroom = new BotaniaMushroomBlock(DyeColor.RED, BlockBehaviour.Properties.copy(whiteMushroom));
	public static final Block blackMushroom = new BotaniaMushroomBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(whiteMushroom));

	public static final Block doubleFlowerWhite = new BotaniaDoubleFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.copy(whiteFlower));
	public static final Block doubleFlowerOrange = new BotaniaDoubleFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerMagenta = new BotaniaDoubleFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerLightBlue = new BotaniaDoubleFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerYellow = new BotaniaDoubleFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerLime = new BotaniaDoubleFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerPink = new BotaniaDoubleFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerGray = new BotaniaDoubleFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerLightGray = new BotaniaDoubleFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerCyan = new BotaniaDoubleFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerPurple = new BotaniaDoubleFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerBlue = new BotaniaDoubleFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerBrown = new BotaniaDoubleFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerGreen = new BotaniaDoubleFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerRed = new BotaniaDoubleFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.copy(doubleFlowerWhite));
	public static final Block doubleFlowerBlack = new BotaniaDoubleFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.copy(doubleFlowerWhite));

	public static final Block defaultAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.DEFAULT, BlockBehaviour.Properties.of()
			.strength(3.5F).sound(SoundType.STONE).requiresCorrectToolForDrops().mapColor(MapColor.STONE)
			.lightLevel(s -> s.getValue(PetalApothecaryBlock.FLUID) == PetalApothecary.State.LAVA ? 15 : 0));
	public static final Block deepslateAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.DEEPSLATE, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
	public static final Block livingrockAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.LIVINGROCK, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.STONE).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block mossyAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.MOSSY, BlockBehaviour.Properties.copy(defaultAltar));
	public static final Block forestAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.FOREST, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.TUFF).mapColor(MapColor.PLANT));
	public static final Block plainsAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.PLAINS, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.CALCITE).mapColor(DyeColor.WHITE));
	public static final Block mountainAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.MOUNTAIN, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE_TILES).mapColor(DyeColor.LIGHT_GRAY));
	public static final Block fungalAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.FUNGAL, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE_BRICKS).mapColor(MapColor.CRIMSON_STEM));
	public static final Block swampAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.SWAMP, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE_TILES).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block desertAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.DESERT, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(MapColor.TERRACOTTA_ORANGE));
	public static final Block taigaAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.TAIGA, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(DyeColor.BLUE));
	public static final Block mesaAltar = new PetalApothecaryBlock(PetalApothecaryBlock.Variant.MESA, BlockBehaviour.Properties.copy(defaultAltar).sound(SoundType.CALCITE).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block[] ALL_APOTHECARIES = new Block[] { defaultAltar, deepslateAltar, livingrockAltar, mossyAltar, forestAltar, plainsAltar, mountainAltar, fungalAltar, swampAltar, desertAltar, taigaAltar, mesaAltar };

	public static final Block livingrock = new BotaniaBlock(BlockBehaviour.Properties.of().strength(2, 10).sound(SoundType.STONE).mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops());
	public static final Block livingrockPolished = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block livingrockBrick = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block livingrockBrickChiseled = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block livingrockBrickCracked = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block livingrockBrickMossy = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));

	public static final Block livingwoodLog = new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2).sound(SoundType.WOOD).mapColor(state -> state.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_RED : MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodLogStripped = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLog).mapColor(MapColor.TERRACOTTA_RED));
	public static final Block livingwoodLogGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLog).lightLevel(b -> 12));
	public static final Block livingwoodLogStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogStripped).lightLevel(b -> 8));
	public static final Block livingwood = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLog).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodStripped = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogStripped));
	public static final Block livingwoodGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogGlimmering).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogStrippedGlimmering).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.copy(livingwoodLog).mapColor(MapColor.TERRACOTTA_RED));
	public static final Block livingwoodPlanksMossy = new BotaniaBlock(BlockBehaviour.Properties.copy(livingwoodPlanks));
	public static final Block livingwoodFramed = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodPlanks));
	public static final Block livingwoodPatternFramed = new BotaniaBlock(BlockBehaviour.Properties.copy(livingwoodPlanks));

	public static final Block dreamwoodLog = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLog).mapColor(MapColor.QUARTZ));
	public static final Block dreamwoodLogStripped = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodLog));
	public static final Block dreamwoodLogGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogGlimmering).mapColor(MapColor.QUARTZ));
	public static final Block dreamwoodLogStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(livingwoodLogStrippedGlimmering).mapColor(MapColor.QUARTZ));
	public static final Block dreamwood = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodLog));
	public static final Block dreamwoodStripped = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodLog));
	public static final Block dreamwoodGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodLogGlimmering));
	public static final Block dreamwoodStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodLogStrippedGlimmering));
	public static final Block dreamwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.copy(dreamwoodLog));
	public static final Block dreamwoodPlanksMossy = new BotaniaBlock(BlockBehaviour.Properties.copy(dreamwoodPlanks));
	public static final Block dreamwoodFramed = new RotatedPillarBlock(BlockBehaviour.Properties.copy(dreamwoodPlanks));
	public static final Block dreamwoodPatternFramed = new BotaniaBlock(BlockBehaviour.Properties.copy(dreamwoodPlanks));

	public static final Block manaSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.MANA, BlockBehaviour.Properties.copy(livingwood).isValidSpawn(NO_SPAWN));
	public static final Block redstoneSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.REDSTONE, BlockBehaviour.Properties.copy(livingwood).isValidSpawn(NO_SPAWN));
	public static final Block elvenSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.ELVEN, BlockBehaviour.Properties.copy(dreamwood).isValidSpawn(NO_SPAWN));
	public static final Block gaiaSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.GAIA, BlockBehaviour.Properties.copy(dreamwood).isValidSpawn(NO_SPAWN));

	public static final Block manaPool = new ManaPoolBlock(ManaPoolBlock.Variant.DEFAULT, BlockBehaviour.Properties.copy(livingrock));
	public static final Block creativePool = new ManaPoolBlock(ManaPoolBlock.Variant.CREATIVE, BlockBehaviour.Properties.copy(livingrock));
	public static final Block dilutedPool = new ManaPoolBlock(ManaPoolBlock.Variant.DILUTED, BlockBehaviour.Properties.copy(livingrock));
	public static final Block fabulousPool = new ManaPoolBlock(ManaPoolBlock.Variant.FABULOUS, BlockBehaviour.Properties.copy(livingrock));
	public static final Block alchemyCatalyst = new AlchemyCatalystBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block conjurationCatalyst = new ConjurationCatalystBlock(BlockBehaviour.Properties.copy(livingrock));

	public static final Block manasteelBlock = new BotaniaBlock(BlockBehaviour.Properties.of().strength(3, 10).mapColor(MapColor.LAPIS).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block terrasteelBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(manasteelBlock).mapColor(MapColor.EMERALD));
	public static final Block elementiumBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(manasteelBlock).mapColor(MapColor.COLOR_PINK));
	public static final Block manaDiamondBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(manasteelBlock).mapColor(MapColor.DIAMOND));
	public static final Block dragonstoneBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(manasteelBlock).mapColor(MapColor.COLOR_PINK));

	public static final Block manaGlass = new BotaniaGlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).lightLevel(s -> 15).isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block elfGlass = new BotaniaGlassBlock(BlockBehaviour.Properties.copy(manaGlass).isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block bifrost = new BifrostBlock(BlockBehaviour.Properties.of().strength(-1, 0.3F)
			.lightLevel(s -> 15).sound(SoundType.GLASS).noOcclusion().isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block bifrostPerm = new PermanentBifrostBlock(BlockBehaviour.Properties.of().strength(0.3F)
			.lightLevel(s -> 15).sound(SoundType.GLASS).noOcclusion().isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));

	public static final Block runeAltar = new RunicAltarBlock(BlockBehaviour.Properties.copy(livingrock).requiresCorrectToolForDrops());
	public static final Block enchanter = new ManaEnchanterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(3, 5).lightLevel(s -> 15).sound(SoundType.STONE));
	public static final Block brewery = new BotanicalBreweryBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block terraPlate = new TerrestrialAgglomerationPlateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(3, 10).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block alfPortal = new AlfheimPortalBlock(BlockBehaviour.Properties.copy(livingwood).strength(10).sound(SoundType.WOOD)
			.lightLevel(s -> s.getValue(BotaniaStateProperties.ALFPORTAL_STATE) != AlfheimPortalState.OFF ? 15 : 0));

	public static final Block manaPylon = new PylonBlock(PylonBlock.Variant.MANA, BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_BLUE).strength(5.5F).sound(SoundType.METAL).lightLevel(s -> 7).requiresCorrectToolForDrops());
	public static final Block naturaPylon = new PylonBlock(PylonBlock.Variant.NATURA, BlockBehaviour.Properties.copy(manaPylon).mapColor(MapColor.EMERALD));
	public static final Block gaiaPylon = new PylonBlock(PylonBlock.Variant.GAIA, BlockBehaviour.Properties.copy(manaPylon).mapColor(DyeColor.PINK));

	public static final Block distributor = new ManaSplitterBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block manaVoid = new ManaVoidBlock(BlockBehaviour.Properties.copy(livingrock).strength(2, 2000));
	public static final Block manaDetector = new ManaDetectorBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block pistonRelay = new ForceRelayBlock(BlockBehaviour.Properties.of().strength(2, 10).sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE).isValidSpawn(NO_SPAWN));
	public static final Block turntable = new SpreaderTurntableBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block tinyPlanet = new TinyPlanetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(20, 100).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
	public static final Block wildDrum = new DrumBlock(DrumBlock.Variant.WILD, BlockBehaviour.Properties.copy(livingwood));
	public static final Block gatheringDrum = new DrumBlock(DrumBlock.Variant.GATHERING, BlockBehaviour.Properties.copy(livingwood));
	public static final Block canopyDrum = new DrumBlock(DrumBlock.Variant.CANOPY, BlockBehaviour.Properties.copy(livingwood));
	public static final Block spawnerClaw = new LifeImbuerBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3).requiresCorrectToolForDrops());
	public static final Block rfGenerator = new PowerGeneratorBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block prism = new ManaPrismBlock(BlockBehaviour.Properties.copy(elfGlass).noCollission());
	public static final Block pump = new ManaPumpBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block sparkChanger = new SparkTinkererBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block manaBomb = new ManastormChargeBlock(BlockBehaviour.Properties.copy(livingwood).strength(12));
	public static final Block bellows = new BellowsBlock(BlockBehaviour.Properties.copy(livingwood));

	public static final Block openCrate = new OpenCrateBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block craftCrate = new CraftyCrateBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block forestEye = new EyeOfTheAncientsBlock(BlockBehaviour.Properties.of().strength(5, 10).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block solidVines = new SolidVineBlock(BlockBehaviour.Properties.copy(Blocks.VINE));
	public static final Block abstrusePlatform = new PlatformBlock(PlatformBlock.Variant.ABSTRUSE, BlockBehaviour.Properties.copy(livingwood).strength(2, 5).isValidSpawn(NO_SPAWN).noOcclusion().isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION));
	public static final Block spectralPlatform = new PlatformBlock(PlatformBlock.Variant.SPECTRAL, BlockBehaviour.Properties.copy(abstrusePlatform));
	public static final Block infrangiblePlatform = new PlatformBlock(PlatformBlock.Variant.INFRANGIBLE, BlockBehaviour.Properties.copy(abstrusePlatform).strength(-1, Float.MAX_VALUE).isValidSpawn(NO_SPAWN).noOcclusion());
	public static final Block tinyPotato = new TinyPotatoBlock(BlockBehaviour.Properties.of().strength(0.25F).mapColor(DyeColor.PINK));
	public static final Block enderEye = new EnderOverseerBlock(BlockBehaviour.Properties.copy(manasteelBlock));
	public static final Block redStringContainer = new RedStringContainerBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block redStringDispenser = new RedStringDispenserBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block redStringFertilizer = new RedStringNutrifierBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block redStringComparator = new RedStringComparatorBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block redStringRelay = new RedStringSpooferBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block redStringInterceptor = new RedStringInterceptorBlock(BlockBehaviour.Properties.copy(livingrock));

	public static final Block corporeaFunnel = new CorporeaFunnelBlock(BlockBehaviour.Properties.of().strength(5.5F).mapColor(DyeColor.PURPLE).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block corporeaInterceptor = new CorporeaInterceptorBlock(BlockBehaviour.Properties.copy(corporeaFunnel));
	public static final Block corporeaIndex = new CorporeaIndexBlock(BlockBehaviour.Properties.copy(corporeaFunnel).noOcclusion());
	public static final Block corporeaCrystalCube = new CorporeaCrystalCubeBlock(BlockBehaviour.Properties.copy(corporeaFunnel));
	public static final Block corporeaRetainer = new CorporeaRetainerBlock(BlockBehaviour.Properties.copy(corporeaFunnel));

	public static final Block corporeaBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(corporeaFunnel));
	public static final Block corporeaBrick = new BotaniaBlock(BlockBehaviour.Properties.copy(corporeaBlock));
	public static final SlabBlock corporeaSlab = new SlabBlock(BlockBehaviour.Properties.copy(corporeaBlock));
	public static final StairBlock corporeaStairs = new BotaniaStairBlock(corporeaBlock.defaultBlockState(), BlockBehaviour.Properties.copy(corporeaBlock));
	public static final SlabBlock corporeaBrickSlab = new SlabBlock(BlockBehaviour.Properties.copy(corporeaBrick));
	public static final StairBlock corporeaBrickStairs = new BotaniaStairBlock(corporeaBrick.defaultBlockState(), BlockBehaviour.Properties.copy(corporeaBrick));
	public static final Block corporeaBrickWall = new WallBlock(BlockBehaviour.Properties.copy(corporeaBrick));

	public static final Block incensePlate = new IncensePlateBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block hourglass = new HoveringHourglassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(2).sound(SoundType.METAL));
	public static final Block ghostRail = new SpectralRailBlock(BlockBehaviour.Properties.copy(Blocks.RAIL));
	public static final Block lightRelayDefault = new LuminizerBlock(LuminizerVariant.DEFAULT, BlockBehaviour.Properties.of().noCollission());
	public static final Block lightRelayDetector = new LuminizerBlock(LuminizerVariant.DETECTOR, BlockBehaviour.Properties.copy(lightRelayDefault));
	public static final Block lightRelayFork = new LuminizerBlock(LuminizerVariant.FORK, BlockBehaviour.Properties.copy(lightRelayDefault));
	public static final Block lightRelayToggle = new LuminizerBlock(LuminizerVariant.TOGGLE, BlockBehaviour.Properties.copy(lightRelayDefault));
	public static final Block lightLauncher = new LuminizerLauncherBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block cacophonium = new CacophoniumBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK).strength(0.8F));
	public static final Block cellBlock = new CellularBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL));
	public static final Block teruTeruBozu = new TeruTeruBozuBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE));
	public static final Block avatar = new AvatarBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block fakeAir = new FakeAirBlock(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().air().randomTicks());
	public static final Block root = new LivingRootBlock(BlockBehaviour.Properties.of().strength(1.2F).sound(SoundType.WOOD));
	public static final Block felPumpkin = new FelPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN));
	public static final Block cocoon = new CocoonBlock(BlockBehaviour.Properties.of().strength(3, 60).sound(SoundType.WOOL));
	public static final Block enchantedSoil = new EnchantedSoilBlock(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRASS).mapColor(MapColor.GRASS));
	public static final Block animatedTorch = new AnimatedTorchBlock(BlockBehaviour.Properties.of().lightLevel(s -> 7).noOcclusion());
	public static final Block starfield = new StarfieldCreatorBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.PINK).strength(5, 2000).sound(SoundType.METAL));

	public static final Block azulejo0 = new BotaniaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(2, 5).sound(SoundType.STONE).requiresCorrectToolForDrops());
	public static final Block azulejo1 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo2 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo3 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo4 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo5 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo6 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo7 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo8 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo9 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo10 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo11 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo12 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo13 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo14 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block azulejo15 = new BotaniaBlock(BlockBehaviour.Properties.copy(azulejo0));
	public static final Block manaFlame = new ManaFlameBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL).lightLevel(s -> 15).noCollission());
	public static final Block blazeBlock = new BotaniaBlock(BlockBehaviour.Properties.copy(manasteelBlock).lightLevel(s -> 15));
	public static final Block gaiaHead = new GaiaHeadBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).strength(1));
	public static final Block gaiaHeadWall = new WallGaiaHeadBlock(BlockBehaviour.Properties.copy(gaiaHead));
	public static final Block shimmerrock = new BotaniaBlock(BlockBehaviour.Properties.copy(livingrock));
	public static final Block shimmerwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.copy(livingwood));
	public static final Block dryGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.DRY, BlockBehaviour.Properties.of().strength(0.6F).randomTicks().sound(SoundType.GRASS).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN));
	public static final Block goldenGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.GOLDEN, BlockBehaviour.Properties.copy(dryGrass).mapColor(MapColor.GOLD));
	public static final Block vividGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.VIVID, BlockBehaviour.Properties.copy(dryGrass).mapColor(MapColor.PLANT));
	public static final Block scorchedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.SCORCHED, BlockBehaviour.Properties.copy(dryGrass).mapColor(MapColor.NETHER));
	public static final Block infusedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.INFUSED, BlockBehaviour.Properties.copy(dryGrass).mapColor(MapColor.COLOR_CYAN));
	public static final Block mutatedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.MUTATED, BlockBehaviour.Properties.copy(dryGrass).mapColor(MapColor.WARPED_HYPHAE));

	public static final Block motifDaybloom = new FlowerMotifBlock(MobEffects.BLINDNESS, 15, BlockBehaviour.Properties.copy(Blocks.POPPY), true);
	public static final Block motifNightshade = new FlowerMotifBlock(MobEffects.POISON, 20, BlockBehaviour.Properties.copy(Blocks.POPPY), true);
	public static final Block motifHydroangeas = new FlowerMotifBlock(MobEffects.UNLUCK, 10, BlockBehaviour.Properties.copy(Blocks.POPPY), false);

	public static void registerBlocks(BiConsumer<Block, ResourceLocation> r) {
		r.accept(whiteFlower, prefix("white" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(orangeFlower, prefix("orange" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(magentaFlower, prefix("magenta" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(lightBlueFlower, prefix("light_blue" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(yellowFlower, prefix("yellow" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(limeFlower, prefix("lime" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(pinkFlower, prefix("pink" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(grayFlower, prefix("gray" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(lightGrayFlower, prefix("light_gray" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(cyanFlower, prefix("cyan" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(purpleFlower, prefix("purple" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(blueFlower, prefix("blue" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(brownFlower, prefix("brown" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(greenFlower, prefix("green" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(redFlower, prefix("red" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(blackFlower, prefix("black" + LibBlockNames.MYSTICAL_FLOWER_SUFFIX));
		r.accept(whiteShinyFlower, prefix("white" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(orangeShinyFlower, prefix("orange" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(magentaShinyFlower, prefix("magenta" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(lightBlueShinyFlower, prefix("light_blue" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(yellowShinyFlower, prefix("yellow" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(limeShinyFlower, prefix("lime" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(pinkShinyFlower, prefix("pink" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(grayShinyFlower, prefix("gray" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(lightGrayShinyFlower, prefix("light_gray" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(cyanShinyFlower, prefix("cyan" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(purpleShinyFlower, prefix("purple" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(blueShinyFlower, prefix("blue" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(brownShinyFlower, prefix("brown" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(greenShinyFlower, prefix("green" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(redShinyFlower, prefix("red" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(blackShinyFlower, prefix("black" + LibBlockNames.SHINY_FLOWER_SUFFIX));
		r.accept(whiteBuriedPetals, prefix("white" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(orangeBuriedPetals, prefix("orange" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(magentaBuriedPetals, prefix("magenta" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(lightBlueBuriedPetals, prefix("light_blue" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(yellowBuriedPetals, prefix("yellow" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(limeBuriedPetals, prefix("lime" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(pinkBuriedPetals, prefix("pink" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(grayBuriedPetals, prefix("gray" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(lightGrayBuriedPetals, prefix("light_gray" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(cyanBuriedPetals, prefix("cyan" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(purpleBuriedPetals, prefix("purple" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(blueBuriedPetals, prefix("blue" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(brownBuriedPetals, prefix("brown" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(greenBuriedPetals, prefix("green" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(redBuriedPetals, prefix("red" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(blackBuriedPetals, prefix("black" + LibBlockNames.BURIED_PETALS_SUFFIX));
		r.accept(whiteFloatingFlower, prefix("white" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(orangeFloatingFlower, prefix("orange" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(magentaFloatingFlower, prefix("magenta" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(lightBlueFloatingFlower, prefix("light_blue" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(yellowFloatingFlower, prefix("yellow" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(limeFloatingFlower, prefix("lime" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(pinkFloatingFlower, prefix("pink" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(grayFloatingFlower, prefix("gray" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(lightGrayFloatingFlower, prefix("light_gray" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(cyanFloatingFlower, prefix("cyan" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(purpleFloatingFlower, prefix("purple" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(blueFloatingFlower, prefix("blue" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(brownFloatingFlower, prefix("brown" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(greenFloatingFlower, prefix("green" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(redFloatingFlower, prefix("red" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(blackFloatingFlower, prefix("black" + LibBlockNames.FLOATING_FLOWER_SUFFIX));
		r.accept(petalBlockWhite, prefix("white" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockOrange, prefix("orange" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockMagenta, prefix("magenta" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockLightBlue, prefix("light_blue" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockYellow, prefix("yellow" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockLime, prefix("lime" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockPink, prefix("pink" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockGray, prefix("gray" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockSilver, prefix("light_gray" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockCyan, prefix("cyan" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockPurple, prefix("purple" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockBlue, prefix("blue" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockBrown, prefix("brown" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockGreen, prefix("green" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockRed, prefix("red" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(petalBlockBlack, prefix("black" + LibBlockNames.PETAL_BLOCK_SUFFIX));
		r.accept(whiteMushroom, prefix("white" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(orangeMushroom, prefix("orange" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(magentaMushroom, prefix("magenta" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(lightBlueMushroom, prefix("light_blue" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(yellowMushroom, prefix("yellow" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(limeMushroom, prefix("lime" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(pinkMushroom, prefix("pink" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(grayMushroom, prefix("gray" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(lightGrayMushroom, prefix("light_gray" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(cyanMushroom, prefix("cyan" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(purpleMushroom, prefix("purple" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(blueMushroom, prefix("blue" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(brownMushroom, prefix("brown" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(greenMushroom, prefix("green" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(redMushroom, prefix("red" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(blackMushroom, prefix("black" + LibBlockNames.MUSHROOM_SUFFIX));
		r.accept(doubleFlowerWhite, prefix("white" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerOrange, prefix("orange" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerMagenta, prefix("magenta" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerLightBlue, prefix("light_blue" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerYellow, prefix("yellow" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerLime, prefix("lime" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerPink, prefix("pink" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerGray, prefix("gray" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerLightGray, prefix("light_gray" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerCyan, prefix("cyan" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerPurple, prefix("purple" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerBlue, prefix("blue" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerBrown, prefix("brown" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerGreen, prefix("green" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerRed, prefix("red" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(doubleFlowerBlack, prefix("black" + LibBlockNames.DOUBLE_FLOWER_SUFFIX));
		r.accept(defaultAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.DEFAULT.name().toLowerCase(Locale.ROOT)));
		r.accept(forestAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.FOREST.name().toLowerCase(Locale.ROOT)));
		r.accept(plainsAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.PLAINS.name().toLowerCase(Locale.ROOT)));
		r.accept(mountainAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.MOUNTAIN.name().toLowerCase(Locale.ROOT)));
		r.accept(fungalAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.FUNGAL.name().toLowerCase(Locale.ROOT)));
		r.accept(swampAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.SWAMP.name().toLowerCase(Locale.ROOT)));
		r.accept(desertAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.DESERT.name().toLowerCase(Locale.ROOT)));
		r.accept(taigaAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.TAIGA.name().toLowerCase(Locale.ROOT)));
		r.accept(mesaAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.MESA.name().toLowerCase(Locale.ROOT)));
		r.accept(mossyAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.MOSSY.name().toLowerCase(Locale.ROOT)));
		r.accept(livingrockAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.LIVINGROCK.name().toLowerCase(Locale.ROOT)));
		r.accept(deepslateAltar, prefix(LibBlockNames.APOTHECARY_PREFIX + PetalApothecaryBlock.Variant.DEEPSLATE.name().toLowerCase(Locale.ROOT)));
		r.accept(livingrock, prefix(LibBlockNames.LIVING_ROCK));
		r.accept(livingrockPolished, prefix(LibBlockNames.LIVING_ROCK_POLISHED));
		r.accept(livingrockBrick, prefix(LibBlockNames.LIVING_ROCK_BRICK));
		r.accept(livingrockBrickChiseled, prefix(LibBlockNames.LIVING_ROCK_BRICK_CHISELED));
		r.accept(livingrockBrickCracked, prefix(LibBlockNames.LIVING_ROCK_BRICK_CRACKED));
		r.accept(livingrockBrickMossy, prefix(LibBlockNames.LIVING_ROCK_BRICK_MOSSY));
		r.accept(livingwoodLog, prefix(LibBlockNames.LIVING_WOOD_LOG));
		r.accept(livingwoodLogStripped, prefix(LibBlockNames.LIVING_WOOD_LOG_STRIPPED));
		r.accept(livingwoodLogGlimmering, prefix(LibBlockNames.LIVING_WOOD_LOG_GLIMMERING));
		r.accept(livingwoodLogStrippedGlimmering, prefix(LibBlockNames.LIVING_WOOD_LOG_GLIMMERING_STRIPPED));
		r.accept(livingwood, prefix(LibBlockNames.LIVING_WOOD));
		r.accept(livingwoodGlimmering, prefix(LibBlockNames.LIVING_WOOD_GLIMMERING));
		r.accept(livingwoodStripped, prefix(LibBlockNames.LIVING_WOOD_STRIPPED));
		r.accept(livingwoodStrippedGlimmering, prefix(LibBlockNames.LIVING_WOOD_GLIMMERING_STRIPPED));
		r.accept(livingwoodPlanks, prefix(LibBlockNames.LIVING_WOOD_PLANKS));
		r.accept(livingwoodPlanksMossy, prefix(LibBlockNames.LIVING_WOOD_PLANKS_MOSSY));
		r.accept(livingwoodFramed, prefix(LibBlockNames.LIVING_WOOD_FRAMED));
		r.accept(livingwoodPatternFramed, prefix(LibBlockNames.LIVING_WOOD_PATTERN_FRAMED));
		r.accept(dreamwoodLog, prefix(LibBlockNames.DREAM_WOOD_LOG));
		r.accept(dreamwoodLogGlimmering, prefix(LibBlockNames.DREAM_WOOD_LOG_GLIMMERING));
		r.accept(dreamwoodLogStripped, prefix(LibBlockNames.DREAM_WOOD_LOG_STRIPPED));
		r.accept(dreamwoodLogStrippedGlimmering, prefix(LibBlockNames.DREAM_WOOD_LOG_GLIMMERING_STRIPPED));
		r.accept(dreamwood, prefix(LibBlockNames.DREAM_WOOD));
		r.accept(dreamwoodGlimmering, prefix(LibBlockNames.DREAM_WOOD_GLIMMERING));
		r.accept(dreamwoodStripped, prefix(LibBlockNames.DREAM_WOOD_STRIPPED));
		r.accept(dreamwoodStrippedGlimmering, prefix(LibBlockNames.DREAM_WOOD_GLIMMERING_STRIPPED));
		r.accept(dreamwoodPlanks, prefix(LibBlockNames.DREAM_WOOD_PLANKS));
		r.accept(dreamwoodPlanksMossy, prefix(LibBlockNames.DREAM_WOOD_PLANKS_MOSSY));
		r.accept(dreamwoodFramed, prefix(LibBlockNames.DREAM_WOOD_FRAMED));
		r.accept(dreamwoodPatternFramed, prefix(LibBlockNames.DREAM_WOOD_PATTERN_FRAMED));
		r.accept(manaSpreader, prefix(LibBlockNames.SPREADER));
		r.accept(redstoneSpreader, prefix(LibBlockNames.SPREADER_REDSTONE));
		r.accept(elvenSpreader, prefix(LibBlockNames.SPREADER_ELVEN));
		r.accept(gaiaSpreader, prefix(LibBlockNames.SPREADER_GAIA));
		r.accept(manaPool, prefix(LibBlockNames.POOL));
		r.accept(creativePool, prefix(LibBlockNames.POOL_CREATIVE));
		r.accept(dilutedPool, prefix(LibBlockNames.POOL_DILUTED));
		r.accept(fabulousPool, prefix(LibBlockNames.POOL_FABULOUS));
		r.accept(alchemyCatalyst, prefix(LibBlockNames.ALCHEMY_CATALYST));
		r.accept(conjurationCatalyst, prefix(LibBlockNames.CONJURATION_CATALYST));
		r.accept(manasteelBlock, prefix(LibBlockNames.MANASTEEL_BLOCK));
		r.accept(terrasteelBlock, prefix(LibBlockNames.TERRASTEEL_BLOCK));
		r.accept(elementiumBlock, prefix(LibBlockNames.ELEMENTIUM_BLOCK));
		r.accept(manaDiamondBlock, prefix(LibBlockNames.MANA_DIAMOND_BLOCK));
		r.accept(dragonstoneBlock, prefix(LibBlockNames.DRAGONSTONE_BLOCK));
		r.accept(manaGlass, prefix(LibBlockNames.MANA_GLASS));
		r.accept(elfGlass, prefix(LibBlockNames.ELF_GLASS));
		r.accept(bifrost, prefix(LibBlockNames.BIFROST));
		r.accept(bifrostPerm, prefix(LibBlockNames.BIFROST_PERM));
		r.accept(runeAltar, prefix(LibBlockNames.RUNE_ALTAR));
		r.accept(enchanter, prefix(LibBlockNames.ENCHANTER));
		r.accept(brewery, prefix(LibBlockNames.BREWERY));
		r.accept(terraPlate, prefix(LibBlockNames.TERRA_PLATE));
		r.accept(alfPortal, prefix(LibBlockNames.ALF_PORTAL));
		r.accept(manaPylon, prefix(LibBlockNames.PYLON));
		r.accept(naturaPylon, prefix(LibBlockNames.PYLON_NATURA));
		r.accept(gaiaPylon, prefix(LibBlockNames.PYLON_GAIA));
		r.accept(distributor, prefix(LibBlockNames.DISTRIBUTOR));
		r.accept(manaVoid, prefix(LibBlockNames.MANA_VOID));
		r.accept(manaDetector, prefix(LibBlockNames.MANA_DETECTOR));
		r.accept(pistonRelay, prefix(LibBlockNames.PISTON_RELAY));
		r.accept(turntable, prefix(LibBlockNames.TURNTABLE));
		r.accept(tinyPlanet, prefix(LibBlockNames.TINY_PLANET));
		r.accept(wildDrum, prefix(LibBlockNames.DRUM_WILD));
		r.accept(gatheringDrum, prefix(LibBlockNames.DRUM_GATHERING));
		r.accept(canopyDrum, prefix(LibBlockNames.DRUM_CANOPY));
		r.accept(spawnerClaw, prefix(LibBlockNames.SPAWNER_CLAW));
		r.accept(rfGenerator, prefix(LibBlockNames.FLUXFIELD));
		r.accept(prism, prefix(LibBlockNames.PRISM));
		r.accept(pump, prefix(LibBlockNames.PUMP));
		r.accept(sparkChanger, prefix(LibBlockNames.SPARK_CHANGER));
		r.accept(manaBomb, prefix(LibBlockNames.MANA_BOMB));
		r.accept(bellows, prefix(LibBlockNames.BELLOWS));
		r.accept(openCrate, prefix(LibBlockNames.OPEN_CRATE));
		r.accept(craftCrate, prefix(LibBlockNames.CRAFT_CRATE));
		r.accept(forestEye, prefix(LibBlockNames.FOREST_EYE));
		r.accept(solidVines, prefix(LibBlockNames.SOLID_VINE));
		r.accept(abstrusePlatform, prefix(LibBlockNames.PLATFORM_ABSTRUSE));
		r.accept(spectralPlatform, prefix(LibBlockNames.PLATFORM_SPECTRAL));
		r.accept(infrangiblePlatform, prefix(LibBlockNames.PLATFORM_INFRANGIBLE));
		r.accept(tinyPotato, prefix(LibBlockNames.TINY_POTATO));
		r.accept(enderEye, prefix(LibBlockNames.ENDER_EYE_BLOCK));
		r.accept(redStringContainer, prefix(LibBlockNames.RED_STRING_CONTAINER));
		r.accept(redStringDispenser, prefix(LibBlockNames.RED_STRING_DISPENSER));
		r.accept(redStringFertilizer, prefix(LibBlockNames.RED_STRING_FERTILIZER));
		r.accept(redStringComparator, prefix(LibBlockNames.RED_STRING_COMPARATOR));
		r.accept(redStringRelay, prefix(LibBlockNames.RED_STRING_RELAY));
		r.accept(redStringInterceptor, prefix(LibBlockNames.RED_STRING_INTERCEPTOR));
		r.accept(corporeaIndex, prefix(LibBlockNames.CORPOREA_INDEX));
		r.accept(corporeaFunnel, prefix(LibBlockNames.CORPOREA_FUNNEL));
		r.accept(corporeaInterceptor, prefix(LibBlockNames.CORPOREA_INTERCEPTOR));
		r.accept(corporeaCrystalCube, prefix(LibBlockNames.CORPOREA_CRYSTAL_CUBE));
		r.accept(corporeaRetainer, prefix(LibBlockNames.CORPOREA_RETAINER));
		r.accept(corporeaBlock, prefix(LibBlockNames.CORPOREA_BLOCK));
		r.accept(corporeaSlab, prefix(LibBlockNames.CORPOREA_SLAB));
		r.accept(corporeaStairs, prefix(LibBlockNames.CORPOREA_STAIRS));
		r.accept(corporeaBrick, prefix(LibBlockNames.CORPOREA_BRICK));
		r.accept(corporeaBrickSlab, prefix(LibBlockNames.CORPOREA_BRICK + LibBlockNames.SLAB_SUFFIX));
		r.accept(corporeaBrickStairs, prefix(LibBlockNames.CORPOREA_BRICK + LibBlockNames.STAIR_SUFFIX));
		r.accept(corporeaBrickWall, prefix(LibBlockNames.CORPOREA_BRICK + LibBlockNames.WALL_SUFFIX));
		r.accept(incensePlate, prefix(LibBlockNames.INCENSE_PLATE));
		r.accept(hourglass, prefix(LibBlockNames.HOURGLASS));
		r.accept(ghostRail, prefix(LibBlockNames.GHOST_RAIL));
		r.accept(lightRelayDefault, prefix(LibBlockNames.LIGHT_RELAY));
		r.accept(lightRelayDetector, prefix("detector" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightRelayFork, prefix("fork" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightRelayToggle, prefix("toggle" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightLauncher, prefix(LibBlockNames.LIGHT_LAUNCHER));
		r.accept(cacophonium, prefix(LibBlockNames.CACOPHONIUM));
		r.accept(cellBlock, prefix(LibBlockNames.CELL_BLOCK));
		r.accept(teruTeruBozu, prefix(LibBlockNames.TERU_TERU_BOZU));
		r.accept(avatar, prefix(LibBlockNames.AVATAR));
		r.accept(fakeAir, prefix(LibBlockNames.FAKE_AIR));
		r.accept(root, prefix(LibBlockNames.ROOT));
		r.accept(felPumpkin, prefix(LibBlockNames.FEL_PUMPKIN));
		r.accept(cocoon, prefix(LibBlockNames.COCOON));
		r.accept(enchantedSoil, prefix(LibBlockNames.ENCHANTED_SOIL));
		r.accept(animatedTorch, prefix(LibBlockNames.ANIMATED_TORCH));
		r.accept(starfield, prefix(LibBlockNames.STARFIELD));
		r.accept(azulejo0, prefix(LibBlockNames.AZULEJO_PREFIX + 0));
		r.accept(azulejo1, prefix(LibBlockNames.AZULEJO_PREFIX + 1));
		r.accept(azulejo2, prefix(LibBlockNames.AZULEJO_PREFIX + 2));
		r.accept(azulejo3, prefix(LibBlockNames.AZULEJO_PREFIX + 3));
		r.accept(azulejo4, prefix(LibBlockNames.AZULEJO_PREFIX + 4));
		r.accept(azulejo5, prefix(LibBlockNames.AZULEJO_PREFIX + 5));
		r.accept(azulejo6, prefix(LibBlockNames.AZULEJO_PREFIX + 6));
		r.accept(azulejo7, prefix(LibBlockNames.AZULEJO_PREFIX + 7));
		r.accept(azulejo8, prefix(LibBlockNames.AZULEJO_PREFIX + 8));
		r.accept(azulejo9, prefix(LibBlockNames.AZULEJO_PREFIX + 9));
		r.accept(azulejo10, prefix(LibBlockNames.AZULEJO_PREFIX + 10));
		r.accept(azulejo11, prefix(LibBlockNames.AZULEJO_PREFIX + 11));
		r.accept(azulejo12, prefix(LibBlockNames.AZULEJO_PREFIX + 12));
		r.accept(azulejo13, prefix(LibBlockNames.AZULEJO_PREFIX + 13));
		r.accept(azulejo14, prefix(LibBlockNames.AZULEJO_PREFIX + 14));
		r.accept(azulejo15, prefix(LibBlockNames.AZULEJO_PREFIX + 15));
		r.accept(manaFlame, prefix(LibBlockNames.MANA_FLAME));
		r.accept(blazeBlock, prefix(LibBlockNames.BLAZE_BLOCK));
		r.accept(gaiaHeadWall, prefix(LibBlockNames.GAIA_WALL_HEAD));
		r.accept(gaiaHead, prefix(LibBlockNames.GAIA_HEAD));
		r.accept(shimmerrock, prefix(LibBlockNames.SHIMMERROCK));
		r.accept(shimmerwoodPlanks, prefix(LibBlockNames.SHIMMERWOOD_PLANKS));
		r.accept(dryGrass, prefix(BotaniaGrassBlock.Variant.DRY.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(goldenGrass, prefix(BotaniaGrassBlock.Variant.GOLDEN.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(vividGrass, prefix(BotaniaGrassBlock.Variant.VIVID.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(scorchedGrass, prefix(BotaniaGrassBlock.Variant.SCORCHED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(infusedGrass, prefix(BotaniaGrassBlock.Variant.INFUSED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(mutatedGrass, prefix(BotaniaGrassBlock.Variant.MUTATED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(motifDaybloom, prefix(LibBlockNames.MOTIF_DAYBLOOM));
		r.accept(motifNightshade, prefix(LibBlockNames.MOTIF_NIGHTSHADE));
		r.accept(motifHydroangeas, prefix(LibBlockNames.MOTIF_HYDROANGEAS));
	}

	public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r) {
		Item.Properties props = BotaniaItems.defaultBuilder();
		r.accept(new BlockItem(whiteFlower, props), BuiltInRegistries.BLOCK.getKey(whiteFlower));
		r.accept(new BlockItem(orangeFlower, props), BuiltInRegistries.BLOCK.getKey(orangeFlower));
		r.accept(new BlockItem(magentaFlower, props), BuiltInRegistries.BLOCK.getKey(magentaFlower));
		r.accept(new BlockItem(lightBlueFlower, props), BuiltInRegistries.BLOCK.getKey(lightBlueFlower));
		r.accept(new BlockItem(yellowFlower, props), BuiltInRegistries.BLOCK.getKey(yellowFlower));
		r.accept(new BlockItem(limeFlower, props), BuiltInRegistries.BLOCK.getKey(limeFlower));
		r.accept(new BlockItem(pinkFlower, props), BuiltInRegistries.BLOCK.getKey(pinkFlower));
		r.accept(new BlockItem(grayFlower, props), BuiltInRegistries.BLOCK.getKey(grayFlower));
		r.accept(new BlockItem(lightGrayFlower, props), BuiltInRegistries.BLOCK.getKey(lightGrayFlower));
		r.accept(new BlockItem(cyanFlower, props), BuiltInRegistries.BLOCK.getKey(cyanFlower));
		r.accept(new BlockItem(purpleFlower, props), BuiltInRegistries.BLOCK.getKey(purpleFlower));
		r.accept(new BlockItem(blueFlower, props), BuiltInRegistries.BLOCK.getKey(blueFlower));
		r.accept(new BlockItem(brownFlower, props), BuiltInRegistries.BLOCK.getKey(brownFlower));
		r.accept(new BlockItem(greenFlower, props), BuiltInRegistries.BLOCK.getKey(greenFlower));
		r.accept(new BlockItem(redFlower, props), BuiltInRegistries.BLOCK.getKey(redFlower));
		r.accept(new BlockItem(blackFlower, props), BuiltInRegistries.BLOCK.getKey(blackFlower));
		r.accept(new BlockItem(whiteShinyFlower, props), BuiltInRegistries.BLOCK.getKey(whiteShinyFlower));
		r.accept(new BlockItem(orangeShinyFlower, props), BuiltInRegistries.BLOCK.getKey(orangeShinyFlower));
		r.accept(new BlockItem(magentaShinyFlower, props), BuiltInRegistries.BLOCK.getKey(magentaShinyFlower));
		r.accept(new BlockItem(lightBlueShinyFlower, props), BuiltInRegistries.BLOCK.getKey(lightBlueShinyFlower));
		r.accept(new BlockItem(yellowShinyFlower, props), BuiltInRegistries.BLOCK.getKey(yellowShinyFlower));
		r.accept(new BlockItem(limeShinyFlower, props), BuiltInRegistries.BLOCK.getKey(limeShinyFlower));
		r.accept(new BlockItem(pinkShinyFlower, props), BuiltInRegistries.BLOCK.getKey(pinkShinyFlower));
		r.accept(new BlockItem(grayShinyFlower, props), BuiltInRegistries.BLOCK.getKey(grayShinyFlower));
		r.accept(new BlockItem(lightGrayShinyFlower, props), BuiltInRegistries.BLOCK.getKey(lightGrayShinyFlower));
		r.accept(new BlockItem(cyanShinyFlower, props), BuiltInRegistries.BLOCK.getKey(cyanShinyFlower));
		r.accept(new BlockItem(purpleShinyFlower, props), BuiltInRegistries.BLOCK.getKey(purpleShinyFlower));
		r.accept(new BlockItem(blueShinyFlower, props), BuiltInRegistries.BLOCK.getKey(blueShinyFlower));
		r.accept(new BlockItem(brownShinyFlower, props), BuiltInRegistries.BLOCK.getKey(brownShinyFlower));
		r.accept(new BlockItem(greenShinyFlower, props), BuiltInRegistries.BLOCK.getKey(greenShinyFlower));
		r.accept(new BlockItem(redShinyFlower, props), BuiltInRegistries.BLOCK.getKey(redShinyFlower));
		r.accept(new BlockItem(blackShinyFlower, props), BuiltInRegistries.BLOCK.getKey(blackShinyFlower));
		r.accept(new BlockItem(whiteFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(whiteFloatingFlower));
		r.accept(new BlockItem(orangeFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(orangeFloatingFlower));
		r.accept(new BlockItem(magentaFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(magentaFloatingFlower));
		r.accept(new BlockItem(lightBlueFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(lightBlueFloatingFlower));
		r.accept(new BlockItem(yellowFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(yellowFloatingFlower));
		r.accept(new BlockItem(limeFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(limeFloatingFlower));
		r.accept(new BlockItem(pinkFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(pinkFloatingFlower));
		r.accept(new BlockItem(grayFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(grayFloatingFlower));
		r.accept(new BlockItem(lightGrayFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(lightGrayFloatingFlower));
		r.accept(new BlockItem(cyanFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(cyanFloatingFlower));
		r.accept(new BlockItem(purpleFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(purpleFloatingFlower));
		r.accept(new BlockItem(blueFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(blueFloatingFlower));
		r.accept(new BlockItem(brownFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(brownFloatingFlower));
		r.accept(new BlockItem(greenFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(greenFloatingFlower));
		r.accept(new BlockItem(redFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(redFloatingFlower));
		r.accept(new BlockItem(blackFloatingFlower, props), BuiltInRegistries.BLOCK.getKey(blackFloatingFlower));
		r.accept(new BlockItem(petalBlockWhite, props), BuiltInRegistries.BLOCK.getKey(petalBlockWhite));
		r.accept(new BlockItem(petalBlockOrange, props), BuiltInRegistries.BLOCK.getKey(petalBlockOrange));
		r.accept(new BlockItem(petalBlockMagenta, props), BuiltInRegistries.BLOCK.getKey(petalBlockMagenta));
		r.accept(new BlockItem(petalBlockLightBlue, props), BuiltInRegistries.BLOCK.getKey(petalBlockLightBlue));
		r.accept(new BlockItem(petalBlockYellow, props), BuiltInRegistries.BLOCK.getKey(petalBlockYellow));
		r.accept(new BlockItem(petalBlockLime, props), BuiltInRegistries.BLOCK.getKey(petalBlockLime));
		r.accept(new BlockItem(petalBlockPink, props), BuiltInRegistries.BLOCK.getKey(petalBlockPink));
		r.accept(new BlockItem(petalBlockGray, props), BuiltInRegistries.BLOCK.getKey(petalBlockGray));
		r.accept(new BlockItem(petalBlockSilver, props), BuiltInRegistries.BLOCK.getKey(petalBlockSilver));
		r.accept(new BlockItem(petalBlockCyan, props), BuiltInRegistries.BLOCK.getKey(petalBlockCyan));
		r.accept(new BlockItem(petalBlockPurple, props), BuiltInRegistries.BLOCK.getKey(petalBlockPurple));
		r.accept(new BlockItem(petalBlockBlue, props), BuiltInRegistries.BLOCK.getKey(petalBlockBlue));
		r.accept(new BlockItem(petalBlockBrown, props), BuiltInRegistries.BLOCK.getKey(petalBlockBrown));
		r.accept(new BlockItem(petalBlockGreen, props), BuiltInRegistries.BLOCK.getKey(petalBlockGreen));
		r.accept(new BlockItem(petalBlockRed, props), BuiltInRegistries.BLOCK.getKey(petalBlockRed));
		r.accept(new BlockItem(petalBlockBlack, props), BuiltInRegistries.BLOCK.getKey(petalBlockBlack));
		r.accept(new BlockItem(whiteMushroom, props), BuiltInRegistries.BLOCK.getKey(whiteMushroom));
		r.accept(new BlockItem(orangeMushroom, props), BuiltInRegistries.BLOCK.getKey(orangeMushroom));
		r.accept(new BlockItem(magentaMushroom, props), BuiltInRegistries.BLOCK.getKey(magentaMushroom));
		r.accept(new BlockItem(lightBlueMushroom, props), BuiltInRegistries.BLOCK.getKey(lightBlueMushroom));
		r.accept(new BlockItem(yellowMushroom, props), BuiltInRegistries.BLOCK.getKey(yellowMushroom));
		r.accept(new BlockItem(limeMushroom, props), BuiltInRegistries.BLOCK.getKey(limeMushroom));
		r.accept(new BlockItem(pinkMushroom, props), BuiltInRegistries.BLOCK.getKey(pinkMushroom));
		r.accept(new BlockItem(grayMushroom, props), BuiltInRegistries.BLOCK.getKey(grayMushroom));
		r.accept(new BlockItem(lightGrayMushroom, props), BuiltInRegistries.BLOCK.getKey(lightGrayMushroom));
		r.accept(new BlockItem(cyanMushroom, props), BuiltInRegistries.BLOCK.getKey(cyanMushroom));
		r.accept(new BlockItem(purpleMushroom, props), BuiltInRegistries.BLOCK.getKey(purpleMushroom));
		r.accept(new BlockItem(blueMushroom, props), BuiltInRegistries.BLOCK.getKey(blueMushroom));
		r.accept(new BlockItem(brownMushroom, props), BuiltInRegistries.BLOCK.getKey(brownMushroom));
		r.accept(new BlockItem(greenMushroom, props), BuiltInRegistries.BLOCK.getKey(greenMushroom));
		r.accept(new BlockItem(redMushroom, props), BuiltInRegistries.BLOCK.getKey(redMushroom));
		r.accept(new BlockItem(blackMushroom, props), BuiltInRegistries.BLOCK.getKey(blackMushroom));
		r.accept(new BlockItem(doubleFlowerWhite, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerWhite));
		r.accept(new BlockItem(doubleFlowerOrange, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerOrange));
		r.accept(new BlockItem(doubleFlowerMagenta, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerMagenta));
		r.accept(new BlockItem(doubleFlowerLightBlue, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerLightBlue));
		r.accept(new BlockItem(doubleFlowerYellow, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerYellow));
		r.accept(new BlockItem(doubleFlowerLime, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerLime));
		r.accept(new BlockItem(doubleFlowerPink, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerPink));
		r.accept(new BlockItem(doubleFlowerGray, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerGray));
		r.accept(new BlockItem(doubleFlowerLightGray, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerLightGray));
		r.accept(new BlockItem(doubleFlowerCyan, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerCyan));
		r.accept(new BlockItem(doubleFlowerPurple, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerPurple));
		r.accept(new BlockItem(doubleFlowerBlue, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerBlue));
		r.accept(new BlockItem(doubleFlowerBrown, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerBrown));
		r.accept(new BlockItem(doubleFlowerGreen, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerGreen));
		r.accept(new BlockItem(doubleFlowerRed, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerRed));
		r.accept(new BlockItem(doubleFlowerBlack, props), BuiltInRegistries.BLOCK.getKey(doubleFlowerBlack));
		r.accept(new BlockItem(defaultAltar, props), BuiltInRegistries.BLOCK.getKey(defaultAltar));
		r.accept(new BlockItem(forestAltar, props), BuiltInRegistries.BLOCK.getKey(forestAltar));
		r.accept(new BlockItem(plainsAltar, props), BuiltInRegistries.BLOCK.getKey(plainsAltar));
		r.accept(new BlockItem(mountainAltar, props), BuiltInRegistries.BLOCK.getKey(mountainAltar));
		r.accept(new BlockItem(fungalAltar, props), BuiltInRegistries.BLOCK.getKey(fungalAltar));
		r.accept(new BlockItem(swampAltar, props), BuiltInRegistries.BLOCK.getKey(swampAltar));
		r.accept(new BlockItem(desertAltar, props), BuiltInRegistries.BLOCK.getKey(desertAltar));
		r.accept(new BlockItem(taigaAltar, props), BuiltInRegistries.BLOCK.getKey(taigaAltar));
		r.accept(new BlockItem(mesaAltar, props), BuiltInRegistries.BLOCK.getKey(mesaAltar));
		r.accept(new BlockItem(mossyAltar, props), BuiltInRegistries.BLOCK.getKey(mossyAltar));
		r.accept(new BlockItem(livingrockAltar, props), BuiltInRegistries.BLOCK.getKey(livingrockAltar));
		r.accept(new BlockItem(deepslateAltar, props), BuiltInRegistries.BLOCK.getKey(deepslateAltar));
		r.accept(new BlockItem(livingrock, props), BuiltInRegistries.BLOCK.getKey(livingrock));
		r.accept(new BlockItem(livingrockPolished, props), BuiltInRegistries.BLOCK.getKey(livingrockPolished));
		r.accept(new BlockItem(livingrockBrick, props), BuiltInRegistries.BLOCK.getKey(livingrockBrick));
		r.accept(new BlockItem(livingrockBrickChiseled, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickChiseled));
		r.accept(new BlockItem(livingrockBrickCracked, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickCracked));
		r.accept(new BlockItem(livingrockBrickMossy, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickMossy));
		r.accept(new BlockItem(livingwoodLog, props), BuiltInRegistries.BLOCK.getKey(livingwoodLog));
		r.accept(new BlockItem(livingwoodLogStripped, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogStripped));
		r.accept(new BlockItem(livingwoodLogGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogGlimmering));
		r.accept(new BlockItem(livingwoodLogStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogStrippedGlimmering));
		r.accept(new BlockItem(livingwood, props), BuiltInRegistries.BLOCK.getKey(livingwood));
		r.accept(new BlockItem(livingwoodStripped, props), BuiltInRegistries.BLOCK.getKey(livingwoodStripped));
		r.accept(new BlockItem(livingwoodStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodStrippedGlimmering));
		r.accept(new BlockItem(livingwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlanks));
		r.accept(new BlockItem(livingwoodPlanksMossy, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlanksMossy));
		r.accept(new BlockItem(livingwoodFramed, props), BuiltInRegistries.BLOCK.getKey(livingwoodFramed));
		r.accept(new BlockItem(livingwoodPatternFramed, props), BuiltInRegistries.BLOCK.getKey(livingwoodPatternFramed));
		r.accept(new BlockItem(livingwoodGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodGlimmering));
		r.accept(new BlockItem(dreamwoodLog, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLog));
		r.accept(new BlockItem(dreamwoodLogGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogGlimmering));
		r.accept(new BlockItem(dreamwoodLogStripped, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogStripped));
		r.accept(new BlockItem(dreamwoodLogStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogStrippedGlimmering));
		r.accept(new BlockItem(dreamwood, props), BuiltInRegistries.BLOCK.getKey(dreamwood));
		r.accept(new BlockItem(dreamwoodStripped, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStripped));
		r.accept(new BlockItem(dreamwoodStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStrippedGlimmering));
		r.accept(new BlockItem(dreamwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlanks));
		r.accept(new BlockItem(dreamwoodPlanksMossy, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlanksMossy));
		r.accept(new BlockItem(dreamwoodFramed, props), BuiltInRegistries.BLOCK.getKey(dreamwoodFramed));
		r.accept(new BlockItem(dreamwoodPatternFramed, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPatternFramed));
		r.accept(new BlockItem(dreamwoodGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodGlimmering));
		r.accept(new BlockItem(manaSpreader, props), BuiltInRegistries.BLOCK.getKey(manaSpreader));
		r.accept(new BlockItem(redstoneSpreader, props), BuiltInRegistries.BLOCK.getKey(redstoneSpreader));
		r.accept(new BlockItem(elvenSpreader, props), BuiltInRegistries.BLOCK.getKey(elvenSpreader));
		r.accept(new BlockItem(gaiaSpreader, props), BuiltInRegistries.BLOCK.getKey(gaiaSpreader));
		r.accept(new BlockItem(manaPool, props), BuiltInRegistries.BLOCK.getKey(manaPool));
		r.accept(new BlockItem(creativePool, BotaniaItems.defaultBuilder().rarity(Rarity.EPIC)), BuiltInRegistries.BLOCK.getKey(creativePool));
		r.accept(new BlockItem(dilutedPool, props), BuiltInRegistries.BLOCK.getKey(dilutedPool));
		r.accept(new BlockItem(fabulousPool, props), BuiltInRegistries.BLOCK.getKey(fabulousPool));
		r.accept(new BlockItem(alchemyCatalyst, props), BuiltInRegistries.BLOCK.getKey(alchemyCatalyst));
		r.accept(new BlockItem(conjurationCatalyst, props), BuiltInRegistries.BLOCK.getKey(conjurationCatalyst));
		r.accept(new BlockItem(manasteelBlock, props), BuiltInRegistries.BLOCK.getKey(manasteelBlock));
		r.accept(new BlockItem(terrasteelBlock, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(terrasteelBlock));
		r.accept(new BlockItem(elementiumBlock, props), BuiltInRegistries.BLOCK.getKey(elementiumBlock));
		r.accept(new BlockItem(manaDiamondBlock, props), BuiltInRegistries.BLOCK.getKey(manaDiamondBlock));
		r.accept(new BlockItem(dragonstoneBlock, props), BuiltInRegistries.BLOCK.getKey(dragonstoneBlock));
		r.accept(new BlockItem(manaGlass, props), BuiltInRegistries.BLOCK.getKey(manaGlass));
		r.accept(new BlockItem(elfGlass, props), BuiltInRegistries.BLOCK.getKey(elfGlass));
		r.accept(new BlockItem(bifrostPerm, props), BuiltInRegistries.BLOCK.getKey(bifrostPerm));
		r.accept(new BlockItem(runeAltar, props), BuiltInRegistries.BLOCK.getKey(runeAltar));
		r.accept(new BlockItem(enchanter, props), BuiltInRegistries.BLOCK.getKey(enchanter));
		r.accept(new BlockItemWithSpecialRenderer(brewery, props), BuiltInRegistries.BLOCK.getKey(brewery));
		r.accept(new BlockItem(terraPlate, props), BuiltInRegistries.BLOCK.getKey(terraPlate));
		r.accept(new BlockItem(alfPortal, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(alfPortal));

		r.accept(new BlockItemWithSpecialRenderer(manaPylon, props), BuiltInRegistries.BLOCK.getKey(manaPylon));
		r.accept(new BlockItemWithSpecialRenderer(naturaPylon, props), BuiltInRegistries.BLOCK.getKey(naturaPylon));
		r.accept(new BlockItemWithSpecialRenderer(gaiaPylon, props), BuiltInRegistries.BLOCK.getKey(gaiaPylon));
		r.accept(new BlockItem(distributor, props), BuiltInRegistries.BLOCK.getKey(distributor));
		r.accept(new BlockItem(manaVoid, props), BuiltInRegistries.BLOCK.getKey(manaVoid));
		r.accept(new BlockItem(manaDetector, props), BuiltInRegistries.BLOCK.getKey(manaDetector));
		r.accept(new BlockItem(pistonRelay, props), BuiltInRegistries.BLOCK.getKey(pistonRelay));
		r.accept(new BlockItem(turntable, props), BuiltInRegistries.BLOCK.getKey(turntable));
		r.accept(new BlockItem(tinyPlanet, props), BuiltInRegistries.BLOCK.getKey(tinyPlanet));
		r.accept(new BlockItem(wildDrum, props), BuiltInRegistries.BLOCK.getKey(wildDrum));
		r.accept(new BlockItem(gatheringDrum, props), BuiltInRegistries.BLOCK.getKey(gatheringDrum));
		r.accept(new BlockItem(canopyDrum, props), BuiltInRegistries.BLOCK.getKey(canopyDrum));
		r.accept(new BlockItem(spawnerClaw, props), BuiltInRegistries.BLOCK.getKey(spawnerClaw));
		r.accept(new BlockItem(rfGenerator, props), BuiltInRegistries.BLOCK.getKey(rfGenerator));
		r.accept(new BlockItem(prism, props), BuiltInRegistries.BLOCK.getKey(prism));
		r.accept(new BlockItem(pump, props), BuiltInRegistries.BLOCK.getKey(pump));
		r.accept(new BlockItem(sparkChanger, props), BuiltInRegistries.BLOCK.getKey(sparkChanger));
		r.accept(new BlockItem(manaBomb, props), BuiltInRegistries.BLOCK.getKey(manaBomb));
		r.accept(new BlockItemWithSpecialRenderer(bellows, props), BuiltInRegistries.BLOCK.getKey(bellows));
		r.accept(new BlockItem(openCrate, props), BuiltInRegistries.BLOCK.getKey(openCrate));
		r.accept(new BlockItem(craftCrate, props), BuiltInRegistries.BLOCK.getKey(craftCrate));
		r.accept(new BlockItem(forestEye, props), BuiltInRegistries.BLOCK.getKey(forestEye));
		r.accept(new BlockItem(abstrusePlatform, props), BuiltInRegistries.BLOCK.getKey(abstrusePlatform));
		r.accept(new BlockItem(spectralPlatform, props), BuiltInRegistries.BLOCK.getKey(spectralPlatform));
		r.accept(new BlockItem(infrangiblePlatform, BotaniaItems.defaultBuilder().rarity(Rarity.EPIC)), BuiltInRegistries.BLOCK.getKey(infrangiblePlatform));
		r.accept(new TinyPotatoBlockItem(tinyPotato, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(tinyPotato));
		r.accept(new BlockItem(enderEye, props), BuiltInRegistries.BLOCK.getKey(enderEye));
		r.accept(new BlockItem(redStringContainer, props), BuiltInRegistries.BLOCK.getKey(redStringContainer));
		r.accept(new BlockItem(redStringDispenser, props), BuiltInRegistries.BLOCK.getKey(redStringDispenser));
		r.accept(new BlockItem(redStringFertilizer, props), BuiltInRegistries.BLOCK.getKey(redStringFertilizer));
		r.accept(new BlockItem(redStringComparator, props), BuiltInRegistries.BLOCK.getKey(redStringComparator));
		r.accept(new BlockItem(redStringRelay, props), BuiltInRegistries.BLOCK.getKey(redStringRelay));
		r.accept(new BlockItem(redStringInterceptor, props), BuiltInRegistries.BLOCK.getKey(redStringInterceptor));
		r.accept(new BlockItemWithSpecialRenderer(corporeaIndex, props), BuiltInRegistries.BLOCK.getKey(corporeaIndex));
		r.accept(new BlockItem(corporeaFunnel, props), BuiltInRegistries.BLOCK.getKey(corporeaFunnel));
		r.accept(new BlockItem(corporeaInterceptor, props), BuiltInRegistries.BLOCK.getKey(corporeaInterceptor));
		r.accept(new BlockItem(corporeaCrystalCube, props), BuiltInRegistries.BLOCK.getKey(corporeaCrystalCube));
		r.accept(new BlockItem(corporeaRetainer, props), BuiltInRegistries.BLOCK.getKey(corporeaRetainer));
		r.accept(new BlockItem(corporeaBlock, props), BuiltInRegistries.BLOCK.getKey(corporeaBlock));
		r.accept(new BlockItem(corporeaSlab, props), BuiltInRegistries.BLOCK.getKey(corporeaSlab));
		r.accept(new BlockItem(corporeaStairs, props), BuiltInRegistries.BLOCK.getKey(corporeaStairs));
		r.accept(new BlockItem(corporeaBrick, props), BuiltInRegistries.BLOCK.getKey(corporeaBrick));
		r.accept(new BlockItem(corporeaBrickSlab, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickSlab));
		r.accept(new BlockItem(corporeaBrickStairs, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickStairs));
		r.accept(new BlockItem(corporeaBrickWall, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickWall));
		r.accept(new BlockItem(incensePlate, props), BuiltInRegistries.BLOCK.getKey(incensePlate));
		r.accept(new BlockItemWithSpecialRenderer(hourglass, props), BuiltInRegistries.BLOCK.getKey(hourglass));
		r.accept(new BlockItem(ghostRail, props), BuiltInRegistries.BLOCK.getKey(ghostRail));
		r.accept(new BlockItem(lightRelayDefault, props), BuiltInRegistries.BLOCK.getKey(lightRelayDefault));
		r.accept(new BlockItem(lightRelayDetector, props), BuiltInRegistries.BLOCK.getKey(lightRelayDetector));
		r.accept(new BlockItem(lightRelayFork, props), BuiltInRegistries.BLOCK.getKey(lightRelayFork));
		r.accept(new BlockItem(lightRelayToggle, props), BuiltInRegistries.BLOCK.getKey(lightRelayToggle));
		r.accept(new BlockItem(lightLauncher, props), BuiltInRegistries.BLOCK.getKey(lightLauncher));
		r.accept(new BlockItem(cacophonium, props), BuiltInRegistries.BLOCK.getKey(cacophonium));
		r.accept(new BlockItem(cellBlock, props), BuiltInRegistries.BLOCK.getKey(cellBlock));
		r.accept(new BlockItemWithSpecialRenderer(teruTeruBozu, props), BuiltInRegistries.BLOCK.getKey(teruTeruBozu));
		r.accept(new BlockItemWithSpecialRenderer(avatar, props), BuiltInRegistries.BLOCK.getKey(avatar));
		r.accept(new BlockItem(root, props), BuiltInRegistries.BLOCK.getKey(root));
		r.accept(new BlockItem(felPumpkin, props), BuiltInRegistries.BLOCK.getKey(felPumpkin));
		r.accept(new BlockItem(cocoon, props), BuiltInRegistries.BLOCK.getKey(cocoon));
		r.accept(new BlockItem(enchantedSoil, BotaniaItems.defaultBuilder().rarity(Rarity.RARE)), BuiltInRegistries.BLOCK.getKey(enchantedSoil));
		r.accept(new BlockItem(animatedTorch, props), BuiltInRegistries.BLOCK.getKey(animatedTorch));
		r.accept(new BlockItem(starfield, props), BuiltInRegistries.BLOCK.getKey(starfield));
		r.accept(new BlockItem(azulejo0, props), BuiltInRegistries.BLOCK.getKey(azulejo0));
		r.accept(new BlockItem(azulejo1, props), BuiltInRegistries.BLOCK.getKey(azulejo1));
		r.accept(new BlockItem(azulejo2, props), BuiltInRegistries.BLOCK.getKey(azulejo2));
		r.accept(new BlockItem(azulejo3, props), BuiltInRegistries.BLOCK.getKey(azulejo3));
		r.accept(new BlockItem(azulejo4, props), BuiltInRegistries.BLOCK.getKey(azulejo4));
		r.accept(new BlockItem(azulejo5, props), BuiltInRegistries.BLOCK.getKey(azulejo5));
		r.accept(new BlockItem(azulejo6, props), BuiltInRegistries.BLOCK.getKey(azulejo6));
		r.accept(new BlockItem(azulejo7, props), BuiltInRegistries.BLOCK.getKey(azulejo7));
		r.accept(new BlockItem(azulejo8, props), BuiltInRegistries.BLOCK.getKey(azulejo8));
		r.accept(new BlockItem(azulejo9, props), BuiltInRegistries.BLOCK.getKey(azulejo9));
		r.accept(new BlockItem(azulejo10, props), BuiltInRegistries.BLOCK.getKey(azulejo10));
		r.accept(new BlockItem(azulejo11, props), BuiltInRegistries.BLOCK.getKey(azulejo11));
		r.accept(new BlockItem(azulejo12, props), BuiltInRegistries.BLOCK.getKey(azulejo12));
		r.accept(new BlockItem(azulejo13, props), BuiltInRegistries.BLOCK.getKey(azulejo13));
		r.accept(new BlockItem(azulejo14, props), BuiltInRegistries.BLOCK.getKey(azulejo14));
		r.accept(new BlockItem(azulejo15, props), BuiltInRegistries.BLOCK.getKey(azulejo15));
		r.accept(new BlazeItemBlock(blazeBlock, props), BuiltInRegistries.BLOCK.getKey(blazeBlock));
		r.accept(new StandingAndWallBlockItem(gaiaHead, gaiaHeadWall, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON), Direction.DOWN), BuiltInRegistries.BLOCK.getKey(gaiaHead));
		r.accept(new BlockItem(shimmerrock, props), BuiltInRegistries.BLOCK.getKey(shimmerrock));
		r.accept(new BlockItem(shimmerwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(shimmerwoodPlanks));
		r.accept(new BlockItem(dryGrass, props), BuiltInRegistries.BLOCK.getKey(dryGrass));
		r.accept(new BlockItem(goldenGrass, props), BuiltInRegistries.BLOCK.getKey(goldenGrass));
		r.accept(new BlockItem(vividGrass, props), BuiltInRegistries.BLOCK.getKey(vividGrass));
		r.accept(new BlockItem(scorchedGrass, props), BuiltInRegistries.BLOCK.getKey(scorchedGrass));
		r.accept(new BlockItem(infusedGrass, props), BuiltInRegistries.BLOCK.getKey(infusedGrass));
		r.accept(new BlockItem(mutatedGrass, props), BuiltInRegistries.BLOCK.getKey(mutatedGrass));
		r.accept(new BlockItem(motifDaybloom, props), BuiltInRegistries.BLOCK.getKey(motifDaybloom));
		r.accept(new BlockItem(motifNightshade, props), BuiltInRegistries.BLOCK.getKey(motifNightshade));
		r.accept(new BlockItem(motifHydroangeas, props), BuiltInRegistries.BLOCK.getKey(motifHydroangeas));
	}

	public static void addDispenserBehaviours() {
		DispenserBlock.registerBehavior(BotaniaItems.twigWand, new WandBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.dreamwoodWand, new WandBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.obedienceStick, new StickBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.poolMinecart, new ManaPoolMinecartBehavior());
		DispenserBlock.registerBehavior(BotaniaBlocks.felPumpkin, new FelPumpkinBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.spark, new ManaSparkBehavior());
		DispenserBlock.registerBehavior(BotaniaBlocks.gaiaHead, new OptionalDispenseItemBehavior() {
			@NotNull
			@Override
			protected ItemStack execute(@NotNull BlockSource source, @NotNull ItemStack stack) {
				setSuccess(ArmorItem.dispenseArmor(source, stack));
				return stack;
			}
		});

		DispenseItemBehavior behavior = new CorporeaSparkBehavior();
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSpark, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSparkMaster, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSparkCreative, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.enderAirBottle, new AbstractProjectileDispenseBehavior() {
			@NotNull
			@Override
			protected Projectile getProjectile(@NotNull Level world, @NotNull Position pos, @NotNull ItemStack stack) {
				return new EnderAirBottleEntity(pos.x(), pos.y(), pos.z(), world);
			}
		});

		behavior = DispenserBlockAccessor.getDispenserRegistry().get(Items.GLASS_BOTTLE);
		DispenserBlock.registerBehavior(Items.GLASS_BOTTLE, new EnderAirBottlingBehavior(behavior));

		behavior = new GrassSeedsBehavior();
		Item[] seedItems = {
				BotaniaItems.grassSeeds,
				BotaniaItems.podzolSeeds,
				BotaniaItems.mycelSeeds,
				BotaniaItems.drySeeds,
				BotaniaItems.goldenSeeds,
				BotaniaItems.vividSeeds,
				BotaniaItems.scorchedSeeds,
				BotaniaItems.infusedSeeds,
				BotaniaItems.mutatedSeeds,
		};
		for (Item seed : seedItems) {
			DispenserBlock.registerBehavior(seed, behavior);
		}

		DispenserBlock.registerBehavior(BotaniaItems.manasteelShears, new ShearsDispenseItemBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.elementiumShears, new ShearsDispenseItemBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.vineBall, new AbstractProjectileDispenseBehavior() {
			@NotNull
			@Override
			protected Projectile getProjectile(@NotNull Level world, @NotNull Position pos, @NotNull ItemStack stack) {
				return new VineBallEntity(pos.x(), pos.y(), pos.z(), world);
			}
		});

		SeedBehaviors.init();
	}

	public static void addAxeStripping() {
		XplatAbstractions xplat = XplatAbstractions.INSTANCE;
		xplat.addAxeStripping(livingwoodLog, livingwoodLogStripped);
		xplat.addAxeStripping(livingwoodLogGlimmering, livingwoodLogStrippedGlimmering);
		xplat.addAxeStripping(livingwood, livingwoodStripped);
		xplat.addAxeStripping(livingwoodGlimmering, livingwoodStrippedGlimmering);
		xplat.addAxeStripping(dreamwoodLog, dreamwoodLogStripped);
		xplat.addAxeStripping(dreamwoodLogGlimmering, dreamwoodLogStrippedGlimmering);
		xplat.addAxeStripping(dreamwood, dreamwoodStripped);
		xplat.addAxeStripping(dreamwoodGlimmering, dreamwoodStrippedGlimmering);

		xplat.addAxeStripping(BotaniaFluffBlocks.livingwoodStairs, BotaniaFluffBlocks.livingwoodStrippedStairs);
		xplat.addAxeStripping(BotaniaFluffBlocks.livingwoodSlab, BotaniaFluffBlocks.livingwoodStrippedSlab);
		xplat.addAxeStripping(BotaniaFluffBlocks.livingwoodWall, BotaniaFluffBlocks.livingwoodStrippedWall);
		xplat.addAxeStripping(BotaniaFluffBlocks.dreamwoodStairs, BotaniaFluffBlocks.dreamwoodStrippedStairs);
		xplat.addAxeStripping(BotaniaFluffBlocks.dreamwoodSlab, BotaniaFluffBlocks.dreamwoodStrippedSlab);
		xplat.addAxeStripping(BotaniaFluffBlocks.dreamwoodWall, BotaniaFluffBlocks.dreamwoodStrippedWall);
	}

	public static Block getFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteFlower;
			case ORANGE -> orangeFlower;
			case MAGENTA -> magentaFlower;
			case LIGHT_BLUE -> lightBlueFlower;
			case YELLOW -> yellowFlower;
			case LIME -> limeFlower;
			case PINK -> pinkFlower;
			case GRAY -> grayFlower;
			case LIGHT_GRAY -> lightGrayFlower;
			case CYAN -> cyanFlower;
			case PURPLE -> purpleFlower;
			case BLUE -> blueFlower;
			case BROWN -> brownFlower;
			case GREEN -> greenFlower;
			case RED -> redFlower;
			case BLACK -> blackFlower;
		};
	}

	public static Block getMushroom(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteMushroom;
			case ORANGE -> orangeMushroom;
			case MAGENTA -> magentaMushroom;
			case LIGHT_BLUE -> lightBlueMushroom;
			case YELLOW -> yellowMushroom;
			case LIME -> limeMushroom;
			case PINK -> pinkMushroom;
			case GRAY -> grayMushroom;
			case LIGHT_GRAY -> lightGrayMushroom;
			case CYAN -> cyanMushroom;
			case PURPLE -> purpleMushroom;
			case BLUE -> blueMushroom;
			case BROWN -> brownMushroom;
			case GREEN -> greenMushroom;
			case RED -> redMushroom;
			case BLACK -> blackMushroom;
		};
	}

	public static Block getBuriedPetal(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteBuriedPetals;
			case ORANGE -> orangeBuriedPetals;
			case MAGENTA -> magentaBuriedPetals;
			case LIGHT_BLUE -> lightBlueBuriedPetals;
			case YELLOW -> yellowBuriedPetals;
			case LIME -> limeBuriedPetals;
			case PINK -> pinkBuriedPetals;
			case GRAY -> grayBuriedPetals;
			case LIGHT_GRAY -> lightGrayBuriedPetals;
			case CYAN -> cyanBuriedPetals;
			case PURPLE -> purpleBuriedPetals;
			case BLUE -> blueBuriedPetals;
			case BROWN -> brownBuriedPetals;
			case GREEN -> greenBuriedPetals;
			case RED -> redBuriedPetals;
			case BLACK -> blackBuriedPetals;
		};
	}

	public static Block getShinyFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteShinyFlower;
			case ORANGE -> orangeShinyFlower;
			case MAGENTA -> magentaShinyFlower;
			case LIGHT_BLUE -> lightBlueShinyFlower;
			case YELLOW -> yellowShinyFlower;
			case LIME -> limeShinyFlower;
			case PINK -> pinkShinyFlower;
			case GRAY -> grayShinyFlower;
			case LIGHT_GRAY -> lightGrayShinyFlower;
			case CYAN -> cyanShinyFlower;
			case PURPLE -> purpleShinyFlower;
			case BLUE -> blueShinyFlower;
			case BROWN -> brownShinyFlower;
			case GREEN -> greenShinyFlower;
			case RED -> redShinyFlower;
			case BLACK -> blackShinyFlower;
		};
	}

	public static Block getFloatingFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteFloatingFlower;
			case ORANGE -> orangeFloatingFlower;
			case MAGENTA -> magentaFloatingFlower;
			case LIGHT_BLUE -> lightBlueFloatingFlower;
			case YELLOW -> yellowFloatingFlower;
			case LIME -> limeFloatingFlower;
			case PINK -> pinkFloatingFlower;
			case GRAY -> grayFloatingFlower;
			case LIGHT_GRAY -> lightGrayFloatingFlower;
			case CYAN -> cyanFloatingFlower;
			case PURPLE -> purpleFloatingFlower;
			case BLUE -> blueFloatingFlower;
			case BROWN -> brownFloatingFlower;
			case GREEN -> greenFloatingFlower;
			case RED -> redFloatingFlower;
			case BLACK -> blackFloatingFlower;
		};
	}

	public static Block getDoubleFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> doubleFlowerWhite;
			case ORANGE -> doubleFlowerOrange;
			case MAGENTA -> doubleFlowerMagenta;
			case LIGHT_BLUE -> doubleFlowerLightBlue;
			case YELLOW -> doubleFlowerYellow;
			case LIME -> doubleFlowerLime;
			case PINK -> doubleFlowerPink;
			case GRAY -> doubleFlowerGray;
			case LIGHT_GRAY -> doubleFlowerLightGray;
			case CYAN -> doubleFlowerCyan;
			case PURPLE -> doubleFlowerPurple;
			case BLUE -> doubleFlowerBlue;
			case BROWN -> doubleFlowerBrown;
			case GREEN -> doubleFlowerGreen;
			case RED -> doubleFlowerRed;
			case BLACK -> doubleFlowerBlack;
		};
	}

	public static Block getPetalBlock(DyeColor color) {
		return switch (color) {
			case WHITE -> petalBlockWhite;
			case ORANGE -> petalBlockOrange;
			case MAGENTA -> petalBlockMagenta;
			case LIGHT_BLUE -> petalBlockLightBlue;
			case YELLOW -> petalBlockYellow;
			case LIME -> petalBlockLime;
			case PINK -> petalBlockPink;
			case GRAY -> petalBlockGray;
			case LIGHT_GRAY -> petalBlockSilver;
			case CYAN -> petalBlockCyan;
			case PURPLE -> petalBlockPurple;
			case BLUE -> petalBlockBlue;
			case BROWN -> petalBlockBrown;
			case GREEN -> petalBlockGreen;
			case RED -> petalBlockRed;
			case BLACK -> petalBlockBlack;
		};
	}
}
