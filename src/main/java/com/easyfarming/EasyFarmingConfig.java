package com.easyfarming;

import java.awt.*;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.config.Alpha;

@ConfigGroup("farminghelper")
public interface EasyFarmingConfig extends Config
{
	@ConfigSection(
			name = "General",
			description = "The highlighted and hidden item lists",
			position = 0,
			closedByDefault = false
	)
	String generalList = "generalList";

	enum OptionEnumHouseTele
	{
		Law_air_earth_runes,
		Teleport_To_House,
		Construction_cape,
		Construction_cape_t,
		Max_cape
	}
	public interface OptionEnumTeleport {
		String name();
	}
	@ConfigItem(
			position = 10,
			keyName = "enumConfigHouseTele",
			name = "House teleport",
			description = "Desired way to teleport to house",
			section = generalList
	)
	default OptionEnumHouseTele enumConfigHouseTele() { return OptionEnumHouseTele.Law_air_earth_runes; }
	@ConfigItem(
			position = 1,
			keyName = "highlightLeftClickColor",
			name = "Left Click Color",
			description = "The color to use for highlighting objects",
			section = generalList
	)
	default Color highlightLeftClickColor() {return new Color(0, 191, 255, 128);}
	@ConfigItem(
			position = 2,
			keyName = "highlightRightClickColor",
			name = "Right Click Color",
			description = "The color to use for highlighting objects",
			section = generalList
	)
	default Color highlightRightClickColor() {return new Color(0, 191, 30, 128);}
	@ConfigItem(
			position = 3,
			keyName = "highlightUseItemColor",
			name = "'Use' item Color",
			description = "The color to use for highlighting objects",
			section = generalList
	)
	default Color highlightUseItemColor() {return new Color(255, 192, 203, 128);}
	@ConfigItem(
			position = 4,
			keyName = "highlightAlpha",
			name = "Transparency",
			description = "The transparency value for the highlight color (0-255)",
			section = generalList
	)
	default int highlightAlpha() {return 128;}

	@ConfigItem(
		keyName = "hopsIncludeWateringCan",
		name = "Watering can (hops)",
		description = "Require a watering can when a run includes hops patches",
		position = 10,
		section = generalList
	)
	default boolean hopsIncludeWateringCan() { return true; }

	enum OptionEnumCompost
	{
		Compost,
		Supercompost,
		Ultracompost,
		Bottomless
	}
	@ConfigItem(
			position = 5,
			keyName = "enumConfigCompost",
			name = "Compost",
			description = "Desired Compost",
			section = generalList
	)
	default OptionEnumCompost enumConfigCompost() { return OptionEnumCompost.Bottomless; }

	@ConfigItem(
		keyName = "booleanConfigPayForProtection",
		name = "Pay for protection",
		description = "Want a reminder to pay for protection? (This currently doesn't check for the required items, only prompts you to pay the farmer.)",
		position = 6,
		section = generalList
	)
	default boolean generalPayForProtection() { return false; }

	@ConfigSection(
			name = "Herb teleport defaults",
			description = "Choose what teleport to use for each Herb patch",
			position = 1,
			closedByDefault = true
		)
	String teleportOptionList = "teleportOptionList";

	enum OptionEnumArdougneTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Ardougne_Teleport,
		Ardougne_tele_tab,
		Ardy_Cloak,
		Skills_Necklace,
		Fishing_Skillcape,
		Jewellery_Box_Fishing_Guild,
		None
	}
	@ConfigItem(
			position = 2,
			keyName = "enumOptionEnumArdougneTeleport",
			name = "Ardougne",
			description = "Desired way to teleport to Ardougne",
			section = teleportOptionList
	)
	default OptionEnumArdougneTeleport enumOptionEnumArdougneTeleport() { return OptionEnumArdougneTeleport.Ardy_Cloak; }
	enum OptionEnumCatherbyTeleport implements OptionEnumTeleport
	{
		Portal_Nexus_Catherby,
		Portal_Nexus_Camelot,
		Camelot_Teleport,
		Camelot_Tele_Tab,
		Catherby_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 3,
			keyName = "enumOptionEnumCatherbyTeleport",
			name = "Catherby",
			description = "Desired way to teleport to Catherby",
			section = teleportOptionList
	)
	default OptionEnumCatherbyTeleport enumOptionEnumCatherbyTeleport() { return OptionEnumCatherbyTeleport.Portal_Nexus_Catherby; }

	enum OptionEnumFaladorTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Explorers_ring,
		Falador_Teleport,
		Falador_Tele_Tab,
		Draynor_Tele_Tab,
		Amulet_of_Glory,
		Spirit_Tree_Port_Sarim,
		None
	}
	@ConfigItem(
			position = 4,
			keyName = "enumOptionEnumFaladorTeleport",
			name = "Falador",
			description = "Desired way to teleport to Falador",
			section = teleportOptionList
	)
	default OptionEnumFaladorTeleport enumOptionEnumFaladorTeleport() { return OptionEnumFaladorTeleport.Explorers_ring; }

	enum OptionEnumFarmingGuildTeleport implements OptionEnumTeleport
	{
		Jewellery_box,
		Skills_Necklace,
		Spirit_Tree,
		Fairy_Ring,
		Farming_Skillcape,
		None
	}
	@ConfigItem(
			position = 5,
			keyName = "enumOptionEnumFarmingGuildTeleport",
			name = "Farming Guild",
			description = "Desired way to teleport to Farming Guild",
			section = teleportOptionList
	)
	default OptionEnumFarmingGuildTeleport enumOptionEnumFarmingGuildTeleport() { return OptionEnumFarmingGuildTeleport.Jewellery_box; }

	enum OptionEnumHarmonyTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Harmony_Tele_tab,
		None
	}
	@ConfigItem(
			position = 6,
			keyName = "enumOptionEnumHarmonyTeleport",
			name = "Harmony",
			description = "Desired way to teleport to Harmony",
			section = teleportOptionList
	)
	default OptionEnumHarmonyTeleport enumOptionEnumHarmonyTeleport() { return OptionEnumHarmonyTeleport.Portal_Nexus; }

	enum OptionEnumKourendTeleport implements OptionEnumTeleport
	{
		Xerics_Talisman,
		Mounted_Xerics,
		Hosidius_POH_Tab,
		Normal_POH_Tab,
		None
	}
	@ConfigItem(
			position = 7,
			keyName = "enumOptionEnumKourendTeleport",
			name = "Kourend",
			description = "Desired way to teleport to Kourend",
			section = teleportOptionList
	)
	default OptionEnumKourendTeleport enumOptionEnumKourendTeleport() { return OptionEnumKourendTeleport.Xerics_Talisman; }

	enum OptionEnumMorytaniaTeleport implements OptionEnumTeleport
	{
		Ectophial,
		Fairy_Ring,
		Portal_Nexus_Fenkenstrain,
		Portal_Nexus_Canifis,
		None
	}
	@ConfigItem(
			position = 8,
			keyName = "enumOptionEnumMorytaniaTeleport",
			name = "Morytania",
			description = "Desired way to teleport to Morytania",
			section = teleportOptionList
	)
	default OptionEnumMorytaniaTeleport enumOptionEnumMorytaniaTeleport() { return OptionEnumMorytaniaTeleport.Ectophial; }

	enum OptionEnumTrollStrongholdTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Stony_Basalt,
		None
	}
	@ConfigItem(
			position = 9,
			keyName = "enumOptionEnumTrollStrongholdTeleport",
			name = "Troll Stronghold",
			description = "Desired way to teleport to Troll Stronghold",
			section = teleportOptionList
	)
	default OptionEnumTrollStrongholdTeleport enumOptionEnumTrollStrongholdTeleport() { return OptionEnumTrollStrongholdTeleport.Portal_Nexus; }

	enum OptionEnumWeissTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Icy_basalt,
		None
	}
	@ConfigItem(
			position = 10,
			keyName = "enumOptionEnumWeissTeleport",
			name = "Weiss",
			description = "Desired way to teleport to Weiss",
			section = teleportOptionList
	)
	default OptionEnumWeissTeleport enumOptionEnumWeissTeleport() { return OptionEnumWeissTeleport.Portal_Nexus; }

	enum OptionEnumCivitasTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Civitas_Teleport,
		Civitas_Tele_Tab,
		Quetzal_whistle,
		Hunter_Skillcape,
		None
	}
	@ConfigItem(
		position = 11,
		keyName = "enumOptionEnumCivitasTeleport",
		name = "Civitas illa Fortis",
		description = "Desired way to teleport to Civitas illa Fortis",
		section = teleportOptionList
	)
	default OptionEnumCivitasTeleport enumOptionEnumCivitasTeleport() { return OptionEnumCivitasTeleport.Portal_Nexus; }

	@ConfigSection(
			name = "Tree teleport defaults",
			description = "Choose what teleport to use for each Herb patch",
			position = 2,
			closedByDefault = true
	)
	String treeTeleportOptionList = "treeTeleportOptionList";
	enum TreeOptionEnumFaladorTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		/** Spellbook Falador Teleport (same string id as herb run). */
		Falador_Teleport,
		Falador_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumTreeFaladorTeleport",
			name = "Falador",
			description = "Desired way to teleport to Falador",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumFaladorTeleport enumTreeFaladorTeleport() { return TreeOptionEnumFaladorTeleport.Falador_Teleport; }

	enum TreeOptionEnumFarmingGuildTeleport implements OptionEnumTeleport
	{
		Jewellery_box,
		Skills_Necklace,
		Spirit_Tree,
		Fairy_Ring,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumTreeFarmingGuildTeleport",
			name = "Farming Guild",
			description = "Desired way to teleport to Farming Guild",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumFarmingGuildTeleport enumTreeFarmingGuildTeleport() { return TreeOptionEnumFarmingGuildTeleport.Jewellery_box; }

	enum TreeOptionEnumGnomeStrongholdTeleport implements OptionEnumTeleport
	{
		Royal_seed_pod,
		Spirit_Tree,
		Necklace_of_Passage,
		None
	}
	@ConfigItem(
			position = 2,
			keyName = "enumTreeGnomeStrongoldTeleport",
			name = "Gnome Stronghold",
			description = "Desired way to teleport to Gnome Stronghold",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumGnomeStrongholdTeleport enumTreeGnomeStrongoldTeleport() { return TreeOptionEnumGnomeStrongholdTeleport.Royal_seed_pod; }

	enum TreeOptionEnumLumbridgeTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Teleport,
		Lumbridge_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 3,
			keyName = "enumTreeLumbridgeTeleport",
			name = "Lumbridge",
			description = "Desired way to teleport to Lumbridge",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumLumbridgeTeleport enumTreeLumbridgeTeleport() { return TreeOptionEnumLumbridgeTeleport.Teleport; }

	enum TreeOptionEnumTaverleyTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Teleport,
		Falador_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 4,
			keyName = "enumTreeTaverleyTeleport",
			name = "Taverley",
			description = "Desired way to teleport to Taverley",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumTaverleyTeleport enumTreeTaverleyTeleport() { return TreeOptionEnumTaverleyTeleport.Teleport; }

	enum TreeOptionEnumVarrockTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Teleport,
		Varrock_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 5,
			keyName = "enumTreeVarrockTeleport",
			name = "Varrock",
			description = "Desired way to teleport to Varrock",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumVarrockTeleport enumTreeVarrockTeleport() { return TreeOptionEnumVarrockTeleport.Teleport; }

	enum TreeOptionEnumNemusRetreatTeleport implements OptionEnumTeleport
	{
		Quetzal_whistle,
		Quetzal_Transport,
		Pendant_of_Ates,
		Fairy_Ring,
		None
	}
	@ConfigItem(
			position = 6,
			keyName = "enumTreeNemusRetreatTeleport",
			name = "Nemus Retreat",
			description = "Desired way to teleport to Nemus Retreat (Auburn Valley tree patch)",
			section = treeTeleportOptionList
	)
	default TreeOptionEnumNemusRetreatTeleport enumTreeNemusRetreatTeleport() { return TreeOptionEnumNemusRetreatTeleport.Pendant_of_Ates; }

	@ConfigSection(
			name = "Fruit tree teleport defaults",
			description = "Choose what teleport to use for each fruit tree",
			position = 3,
			closedByDefault = true
	)
	String fruitTreeTeleportOptionList = "fruitTreeTeleportOptionList";

	enum FruitTreeOptionEnumBrimhavenTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Ardougne_Teleport,
		Ardougne_Tele_Tab,
		POH_Tele_Tab,
		Brimhaven_POH_Tabet,
		Spirit_Tree_Brimhaven,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeBrimhavenTeleport",
			name = "Brimhaven",
			description = "Desired way to teleport to Brimhaven",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumBrimhavenTeleport enumFruitTreeBrimhavenTeleport() { return FruitTreeOptionEnumBrimhavenTeleport.Ardougne_Teleport; }

	enum FruitTreeOptionEnumCatherbyTeleport implements OptionEnumTeleport
	{
		Portal_Nexus_Catherby,
		Portal_Nexus_Camelot,
		Camelot_Teleport,
		Camelot_Tele_Tab,
		Catherby_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeCatherbyTeleport",
			name = "Catherby",
			description = "Desired way to teleport to Catherby",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumCatherbyTeleport enumFruitTreeCatherbyTeleport() { return FruitTreeOptionEnumCatherbyTeleport.Portal_Nexus_Catherby; }

	enum FruitTreeOptionEnumFarmingGuildTeleport implements OptionEnumTeleport
	{
		Jewellery_box,
		Skills_Necklace,
		Spirit_Tree,
		Fairy_Ring,
		Farming_Skillcape,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeFarmingGuildTeleport",
			name = "Farming Guild",
			description = "Desired way to teleport to Farming Guild",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumFarmingGuildTeleport enumFruitTreeFarmingGuildTeleport() { return FruitTreeOptionEnumFarmingGuildTeleport.Jewellery_box; }

	enum FruitTreeOptionEnumGnomeStrongholdTeleport implements OptionEnumTeleport
	{
		Royal_seed_pod,
		Spirit_Tree,
		Slayer_Ring,
		Necklace_of_Passage,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeGnomeStrongholdTeleport",
			name = "Gnome Stronghold",
			description = "Desired way to teleport to Gnome Stronghold",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumGnomeStrongholdTeleport enumFruitTreeGnomeStrongholdTeleport() { return FruitTreeOptionEnumGnomeStrongholdTeleport.Royal_seed_pod; }

	enum FruitTreeOptionEnumLletyaTeleport implements OptionEnumTeleport
	{
		Teleport_crystal,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeLletyaTeleport",
			name = "Lletya",
			description = "Desired way to teleport to Lletya",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumLletyaTeleport enumFruitTreeLletyaTeleport() { return FruitTreeOptionEnumLletyaTeleport.Teleport_crystal; }

	enum FruitTreeOptionEnumTreeGnomeVillageTeleport implements OptionEnumTeleport
	{
		Spirit_Tree,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumFruitTreeTreeGnomeVillageTeleport",
			name = "Tree Gnome Village",
			description = "Desired way to teleport to Tree Gnome Village",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumTreeGnomeVillageTeleport enumFruitTreeTreeGnomeVillageTeleport() { return FruitTreeOptionEnumTreeGnomeVillageTeleport.Spirit_Tree; }

	enum FruitTreeOptionEnumKastoriTeleport implements OptionEnumTeleport
	{
		Quetzal_Transport,
		Pendant_of_Ates,
		None
	}
	@ConfigItem(
			position = 6,
			keyName = "enumFruitTreeKastoriTeleport",
			name = "Kastori",
			description = "Desired way to teleport to Kastori (fruit tree patch)",
			section = fruitTreeTeleportOptionList
	)
	default FruitTreeOptionEnumKastoriTeleport enumFruitTreeKastoriTeleport() { return FruitTreeOptionEnumKastoriTeleport.Pendant_of_Ates; }

	@ConfigSection(
			name = "Hops teleport defaults",
			description = "Choose what teleport to use for each Hops patch",
			position = 4,
			closedByDefault = true
	)
	String hopsTeleportOptionList = "hopsTeleportOptionList";

	enum HopsOptionEnumLumbridgeTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Teleport,
		Lumbridge_Tele_Tab,
		Chronicle,
		Varrock_Teleport,
		Varrock_Tele_Tab,
		Combat_Bracelet,
		None
	}
	@ConfigItem(
			position = 1,
			keyName = "enumHopsLumbridgeTeleport",
			name = "Lumbridge",
			description = "Desired way to teleport to Lumbridge",
			section = hopsTeleportOptionList
	)
	default HopsOptionEnumLumbridgeTeleport enumHopsLumbridgeTeleport() { return HopsOptionEnumLumbridgeTeleport.Teleport; }

	enum HopsOptionEnumSeersVillageTeleport implements OptionEnumTeleport
	{
		Portal_Nexus_Camelot,
		Camelot_Teleport,
		Camelot_Tele_Tab,
		Seers_Village,
		Fairy_Ring,
		Combat_Bracelet,
		None
	}
	@ConfigItem(
			position = 2,
			keyName = "enumHopsSeersVillageTeleport",
			name = "Seers Village",
			description = "Desired way to teleport to Seers Village",
			section = hopsTeleportOptionList
	)
	default HopsOptionEnumSeersVillageTeleport enumHopsSeersVillageTeleport() { return HopsOptionEnumSeersVillageTeleport.Portal_Nexus_Camelot; }

	enum HopsOptionEnumYanilleTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Watchtower_Teleport,
		Yanille,
		Yanille_Tele_Tab,
		Normal_POH_Tele_Tab,
		None
	}
	@ConfigItem(
			position = 3,
			keyName = "enumHopsYanilleTeleport",
			name = "Yanille",
			description = "Desired way to teleport to Yanille",
			section = hopsTeleportOptionList
	)
	default HopsOptionEnumYanilleTeleport enumHopsYanilleTeleport() { return HopsOptionEnumYanilleTeleport.Portal_Nexus; }

	enum HopsOptionEnumEntranaTeleport implements OptionEnumTeleport
	{
		Explorers_Ring,
		Spirit_Tree_Port_Sarim,
		None
	}
	@ConfigItem(
			position = 4,
			keyName = "enumHopsEntranaTeleport",
			name = "Entrana",
			description = "Desired way to teleport to Entrana",
			section = hopsTeleportOptionList
	)
	default HopsOptionEnumEntranaTeleport enumHopsEntranaTeleport() { return HopsOptionEnumEntranaTeleport.Explorers_Ring; }

	enum HopsOptionEnumAldarinTeleport implements OptionEnumTeleport
	{
		Portal_Nexus,
		Quetzal_Transport,
		Fairy_Ring,
		Aldarin_Tele_Tab,
		Normal_POH_Tele_Tab,
		Pendant_of_Ates,
		None
	}
	@ConfigItem(
			position = 5,
			keyName = "enumHopsAldarinTeleport",
			name = "Aldarin",
			description = "Desired way to teleport to Aldarin",
			section = hopsTeleportOptionList
	)
	default HopsOptionEnumAldarinTeleport enumHopsAldarinTeleport() { return HopsOptionEnumAldarinTeleport.Portal_Nexus; }

	@ConfigSection(
		name = "Seed highlighting",
		description = "Highlight the specific seeds you use, in your bank and inventory",
		position = 100
	)
	String seedHighlightList = "seedHighlightList";

	@ConfigItem(
		keyName = "highlightSeedsInBank",
		name = "Highlight my seeds",
		description = "Highlight your chosen seeds in the bank and inventory so they're easy to withdraw",
		position = 0,
		section = seedHighlightList
	)
	default boolean highlightSeedsInBank() { return true; }

	@Alpha
	@ConfigItem(
		keyName = "seedHighlightColor",
		name = "Seed highlight colour",
		description = "Colour used to highlight your chosen seeds",
		position = 1,
		section = seedHighlightList
	)
	default Color seedHighlightColor() { return new Color(255, 235, 59, 160); }

	enum HerbSeedOption {
		None("None", -1),
		Avantoe("Avantoe", ItemID.AVANTOE_SEED),
		Cadantine("Cadantine", ItemID.CADANTINE_SEED),
		Dwarf_weed("Dwarf weed", ItemID.DWARF_WEED_SEED),
		Guam("Guam", ItemID.GUAM_SEED),
		Harralander("Harralander", ItemID.HARRALANDER_SEED),
		Huasca("Huasca", ItemID.HUASCA_SEED),
		Irit("Irit", ItemID.IRIT_SEED),
		Kwuarm("Kwuarm", ItemID.KWUARM_SEED),
		Lantadyme("Lantadyme", ItemID.LANTADYME_SEED),
		Marrentill("Marrentill", ItemID.MARRENTILL_SEED),
		Ranarr("Ranarr", ItemID.RANARR_SEED),
		Snapdragon("Snapdragon", ItemID.SNAPDRAGON_SEED),
		Tarromin("Tarromin", ItemID.TARROMIN_SEED),
		Toadflax("Toadflax", ItemID.TOADFLAX_SEED),
		Torstol("Torstol", ItemID.TORSTOL_SEED);
		private final String label; private final int itemId;
		HerbSeedOption(String label, int itemId) { this.label = label; this.itemId = itemId; }
		public int getItemId() { return itemId; }
		@Override public String toString() { return label; }
	}

	@ConfigItem(keyName = "herbSeedChoice", name = "Herb seed",
		description = "Which herb seed to highlight", position = 2, section = seedHighlightList)
	default HerbSeedOption herbSeed() { return HerbSeedOption.None; }

	enum AllotmentSeedOption {
		None("None", -1),
		Cabbage("Cabbage", ItemID.CABBAGE_SEED),
		Onion("Onion", ItemID.ONION_SEED),
		Potato("Potato", ItemID.POTATO_SEED),
		Snape_grass("Snape grass", ItemID.SNAPE_GRASS_SEED),
		Strawberry("Strawberry", ItemID.STRAWBERRY_SEED),
		Sweetcorn("Sweetcorn", ItemID.SWEETCORN_SEED),
		Tomato("Tomato", ItemID.TOMATO_SEED),
		Watermelon("Watermelon", ItemID.WATERMELON_SEED);
		private final String label; private final int itemId;
		AllotmentSeedOption(String label, int itemId) { this.label = label; this.itemId = itemId; }
		public int getItemId() { return itemId; }
		@Override public String toString() { return label; }
	}

	@ConfigItem(keyName = "allotmentSeedChoice", name = "Allotment seed",
		description = "Which allotment seed to highlight", position = 3, section = seedHighlightList)
	default AllotmentSeedOption allotmentSeed() { return AllotmentSeedOption.None; }

	enum FlowerSeedOption {
		None("None", -1),
		Limpwurt("Limpwurt", ItemID.LIMPWURT_SEED),
		Marigold("Marigold", ItemID.MARIGOLD_SEED),
		Nasturtium("Nasturtium", ItemID.NASTURTIUM_SEED),
		Rosemary("Rosemary", ItemID.ROSEMARY_SEED),
		White_lily("White lily", ItemID.WHITE_LILY_SEED),
		Woad("Woad", ItemID.WOAD_SEED);
		private final String label; private final int itemId;
		FlowerSeedOption(String label, int itemId) { this.label = label; this.itemId = itemId; }
		public int getItemId() { return itemId; }
		@Override public String toString() { return label; }
	}

	@ConfigItem(keyName = "flowerSeedChoice", name = "Flower seed",
		description = "Which flower seed to highlight", position = 4, section = seedHighlightList)
	default FlowerSeedOption flowerSeed() { return FlowerSeedOption.None; }

	enum HopsSeedOption {
		None("None", -1),
		Asgarnian("Asgarnian", ItemID.ASGARNIAN_HOP_SEED),
		Barley("Barley", ItemID.BARLEY_SEED),
		Hammerstone("Hammerstone", ItemID.HAMMERSTONE_HOP_SEED),
		Hemp("Hemp", ItemID.HEMP_SEED),
		Jute("Jute", ItemID.JUTE_SEED),
		Krandorian("Krandorian", ItemID.KRANDORIAN_HOP_SEED),
		Wildblood("Wildblood", ItemID.WILDBLOOD_HOP_SEED),
		Yanillian("Yanillian", ItemID.YANILLIAN_HOP_SEED);
		private final String label; private final int itemId;
		HopsSeedOption(String label, int itemId) { this.label = label; this.itemId = itemId; }
		public int getItemId() { return itemId; }
		@Override public String toString() { return label; }
	}

	@ConfigItem(keyName = "hopsSeedChoice", name = "Hops seed",
		description = "Which hops seed to highlight", position = 5, section = seedHighlightList)
	default HopsSeedOption hopsSeed() { return HopsSeedOption.None; }
}