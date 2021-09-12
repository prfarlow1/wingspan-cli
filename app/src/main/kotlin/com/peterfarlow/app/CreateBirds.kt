package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.peterfarlow.*
import com.peterfarlow.app.App.Companion.serializer
import kotlinx.serialization.encodeToString
import okio.buffer
import okio.sink
import java.nio.file.Paths

class CreateBirds : CliktCommand() {

    override fun run() {
        val downyWoodpcker = Bird(
            "Downy Woodpecker",
            3,
            Habitat.forest,
            "any-ISC".toFoodCost(),
            NestType.CAVITY,
            30,
            2,
            PowerType.WHITE,
        )
        val treeSwallow = Bird(
            "Tree Swallow",
            3,
            Habitat.wetland,
            "all-IC".toFoodCost(),
            NestType.CAVITY,
            38,
            4,
            PowerType.BROWN,
        )
        val annasHummingbird = Bird(
            "Anna's Hummingbird",
            4,
            Habitat.all,
            JustWild,
            NestType.BOWL,
            13,
            2,
            PowerType.BROWN,
        )
        val commonGrackle = Bird(
            "Common Grackle",
            3,
            Habitat.all,
            "all-SW".toFoodCost(),
            NestType.BOWL,
            43,
            3,
            PowerType.BROWN,
        )
        val grasshopperSparrow = Bird(
            "Grasshopper  Sparrow",
            2,
            Habitat.grassland,
            "any-IS".toFoodCost(),
            NestType.GROUND,
            20,
            2,
            PowerType.BROWN,
        )
        val bronzedCowbird = Bird(
            "Bronzed Cowbird",
            5,
            Habitat.grassland,
            "all-IS".toFoodCost(),
            NestType.NONE,
            36,
            0,
            PowerType.PINK,
        )
        val sgk = Bird(
            "White-Crowned Sparrow",
            2,
            Habitat.all,
            "all-IS".toFoodCost(),
            NestType.GROUND,
            25,
            5,
            PowerType.BROWN,
            "",
        )
        val skljgbrs = Bird(
            "Chihuahuan Raven",
            4,
            Habitat.grassland,
            "all-RWW".toFoodCost(),
            NestType.PLATFORM,
            112,
            3,
            PowerType.BROWN,
        )
        val sorgilhrs = Bird(
            "Ring-Billed Gull",
            4,
            Habitat.wetland,
            "all-WW".toFoodCost(),
            NestType.GROUND,
            112,
            4,
            PowerType.BROWN
        )
        val kingrail = Bird(
            "King Rail",
            4,
            Habitat.wetland,
            "all-IFW".toFoodCost(),
            NestType.PLATFORM,
            51,
            4,
            PowerType.WHITE
        )
        val hoodedWarbler = Bird(
            "Hooded Warbler",
            7,
            Habitat.forest,
            "all-II".toFoodCost(),
            NestType.BOWL,
            18,
            3,
            PowerType.NONE
        )
        val junipertitmouse = Bird(
            "Juniper Titmouse",
            4,
            Habitat.forest,
            "all-IS".toFoodCost(),
            NestType.CAVITY,
            23,
            3,
            PowerType.BROWN
        )
        val chat = Bird(
            "Yellow-Breasted Chat",
            5,
            Habitat.all,
            "all-ICC".toFoodCost(),
            NestType.BOWL,
            25,
            3,
            PowerType.BROWN
        )
        val tvult = Bird(
            "Turkey Vulture",
            1,
            Habitat.all,
            FoodCost.none,
            NestType.CAVITY,
            170,
            1,
            PowerType.PINK
        )
        val rceoodpeck = Bird(
            "Red-Cockaded Woodpecker",
            4,
            Habitat.forest,
            "all-IC".toFoodCost(),
            NestType.CAVITY,
            36,
            2,
            PowerType.WHITE
        )
        val purpmartin = Bird(
            "Purple Martin",
            2,
            Habitat.grasslandWetland,
            "all-I".toFoodCost(),
            NestType.CAVITY,
            46,
            3,
            PowerType.BROWN
        )
        val redvireo = Bird(
            "Red-Eyed Vireo",
            3,
            Habitat.forest,
            "any-IC".toFoodCost(),
            NestType.STAR,
            25,
            2,
            PowerType.WHITE
        )
        val crane = Bird(
            "Sandhill Crane",
            5,
            Habitat.grasslandWetland,
            "all-SSW".toFoodCost(),
            NestType.GROUND,
            196,
            1,
            PowerType.BROWN
        )
        val bittern = Bird(
            "American Bittern",
            7,
            Habitat.wetland,
            "all-IIR".toFoodCost(),
            NestType.PLATFORM,
            107,
            2,
            PowerType.BROWN
        )
        val vireo2 = Bird(
            "Bell's Vireo",
            4,
            Habitat.forestGrassland,
            "all-II".toFoodCost(),
            NestType.STAR,
            18,
            2,
            PowerType.WHITE
        )
        val spotted = Bird(
            "Spotted Towee",
            0,
            Habitat.forestGrassland,
            "any-ISC".toFoodCost(),
            NestType.GROUND,
            28,
            4,
            PowerType.BROWN
        )
        val nut = Bird(
            "Clark's Nutcracker",
            5,
            Habitat.forest,
            "all-SSW".toFoodCost(),
            NestType.PLATFORM,
            61,
            2,
            PowerType.BROWN
        )
        val ybc = Bird(
            "Yellow-Billed Cuckoo",
            5,
            Habitat.forest,
            "all-IIW".toFoodCost(),
            NestType.PLATFORM,
            46,
            2,
            PowerType.PINK
        )
        val birds = arrayOf(
            ybc,
            nut,
            spotted,
            vireo2,
            bittern,
            crane,
            redvireo,
            purpmartin,
            rceoodpeck,
            tvult,
            chat,
            junipertitmouse,
            hoodedWarbler,
            kingrail,
            sorgilhrs,
            skljgbrs,
            downyWoodpcker, treeSwallow, annasHummingbird, commonGrackle, grasshopperSparrow, bronzedCowbird, sgk
        )
        echo("creating ${birds.size} birds")
        echo("forest birds: ${birds.count { it.habitat.contains(Habitat.FOREST) }}")
        echo("grassland birds: ${birds.count { it.habitat.contains(Habitat.GRASSLAND) }}")
        echo("wetland birds: ${birds.count { it.habitat.contains(Habitat.WETLAND) }}")

        Paths.get("game").toFile().mkdir()
        val file = Paths.get("game/birds").toFile().apply {
            if (exists()) {
                delete()
            } else {
                createNewFile()
            }
        }
        val encoding = serializer.encodeToString(birds)
        file.sink().buffer().use { sink ->
            sink.writeUtf8(encoding)
        }
    }
}