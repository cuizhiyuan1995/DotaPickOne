package ai.pipi.dotapickone.room

import ai.pipi.dotapickone.*
import ai.pipi.dotapickone.fragment.Advan_disadvan
import ai.pipi.dotapickone.room.Herovs.Herovs
import ai.pipi.dotapickone.room.herowinrate.Herowinrate
import ai.pipi.dotapickone.room.herowith.Herowith

data class Heronames(val heronames: List<Heroname?>)
data class Herowinrates(val herowinrates: List<Herowinrate?>)

data class Herowiths(val herowiths: List<Herowith?>)
data class Herovss(val herovss: List<Herovs?>)
const val heroidcounts = 138

fun DotaAppQuery.Hero.toHeroname() = Heroname(
    stratzId = id as Int,
    displayName = displayName,
    predicted_winrate = (-1.0).toFloat(),
    withvs_index = (0.0).toFloat()
)

fun DotaAppQuery.Constants.toHeronames() = heroes?.let {
    Heronames(
        heronames = it.map {
            it?.toHeroname()
        }
    )
}

fun DotaAppQuery.WinWeek.toHerowinrate() = Herowinrate(
    stratzId = heroId as Int,
    winRate = winCount.toFloat()/matchCount
)

fun DotaAppQuery.HeroStats.toHerowinrates() = winWeek?.let {
    Herowinrates(
        herowinrates = it.map{
            it?.toHerowinrate()
        }
    )
}


//transfer graphql types to room table entity

fun DotaAppQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m135?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m136?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m137?.advan_disadvan?.advantage?.get(0)))
    return Herowiths(herowiths = herowithlist)
}

fun DotaAppQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m135?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m136?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m137?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}



fun DotaVsQueryoneQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m1?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m2?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m3?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m4?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m5?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m6?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m7?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m8?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m9?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m10?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m11?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m12?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m13?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m14?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m15?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m16?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m17?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m18?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m19?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m20?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m21?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m22?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m23?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m25?.advan_disadvan?.advantage?.get(0)))
    return Herowiths(herowiths = herowithlist)
}

fun DotaVsQueryoneQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m1?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m2?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m3?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m4?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m5?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m6?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m7?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m8?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m9?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m10?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m11?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m12?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m13?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m14?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m15?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m16?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m17?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m18?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m19?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m20?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m21?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m22?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m23?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m25?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}

fun DotaVsQuerytwoQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m26?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m27?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m28?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m29?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m30?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m31?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m32?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m33?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m34?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m35?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m36?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m37?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m38?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m39?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m40?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m41?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m42?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m43?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m44?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m45?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m46?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m47?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m48?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m49?.advan_disadvan?.advantage?.get(0)))

    return Herowiths(herowiths = herowithlist)
}

fun DotaVsQuerytwoQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m26?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m27?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m28?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m29?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m30?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m31?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m32?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m33?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m34?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m35?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m36?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m37?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m38?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m39?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m40?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m41?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m42?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m43?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m44?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m45?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m46?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m47?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m48?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m49?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}



fun DotaVsQuerythreeQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m50?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m51?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m52?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m53?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m54?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m55?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m56?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m57?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m58?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m59?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m60?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m61?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m62?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m63?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m64?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m65?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m66?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m67?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m68?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m69?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m70?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m71?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m72?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m73?.advan_disadvan?.advantage?.get(0)))

    return Herowiths(herowiths = herowithlist)
}

fun DotaVsQuerythreeQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m50?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m51?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m52?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m53?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m54?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m55?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m56?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m57?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m58?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m59?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m60?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m61?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m62?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m63?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m64?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m65?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m66?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m67?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m68?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m69?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m70?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m71?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m72?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m73?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}

fun DotaVsQueryfourQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m74?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m75?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m76?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m77?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m78?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m79?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m80?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m81?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m82?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m83?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m84?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m85?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m86?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m87?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m88?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m89?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m90?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m91?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m92?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m93?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m94?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m95?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m96?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m97?.advan_disadvan?.advantage?.get(0)))

    return Herowiths(herowiths = herowithlist)
}

fun DotaVsQueryfourQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m74?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m75?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m76?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m77?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m78?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m79?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m80?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m81?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m82?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m83?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m84?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m85?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m86?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m87?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m88?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m89?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m90?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m91?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m92?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m93?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m94?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m95?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m96?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m97?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}

fun DotaVsQueryfiveQuery.HeroStats.toHerowiths():Herowiths{
    val herowithlist = mutableListOf<Herowith>()
    herowithlist.add(toHerowith(m98?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m99?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m100?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m101?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m102?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m103?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m104?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m105?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m106?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m107?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m108?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m109?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m110?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m111?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m112?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m113?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m114?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m119?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m120?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m121?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m123?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m126?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m128?.advan_disadvan?.advantage?.get(0)))
    herowithlist.add(toHerowith(m129?.advan_disadvan?.advantage?.get(0)))
    return Herowiths(herowiths = herowithlist)
}

fun DotaVsQueryfiveQuery.HeroStats.toHerovss():Herovss{
    val herovslist = mutableListOf<Herovs>()
    herovslist.add(toHerovs(m98?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m99?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m100?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m101?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m102?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m103?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m104?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m105?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m106?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m107?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m108?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m109?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m110?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m111?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m112?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m113?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m114?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m119?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m120?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m121?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m123?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m126?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m128?.advan_disadvan?.advantage?.get(0)))
    herovslist.add(toHerovs(m129?.advan_disadvan?.advantage?.get(0)))
    return Herovss(herovss = herovslist)
}


//create an array of with winrate, list[x] means winrate with hero whose stratz id = x
private fun toHerowitharray(with: List<Advan_disadvan.With>):List<Float>{
    val returnlist = MutableList(size = heroidcounts){index -> -1.0.toFloat()}
    //Log.d("mapper","heroid1: " + with.get(0).heroId1.toString())
    for(withelement in with){
        //Log.d("mapper","heroid2: " + withelement.heroId2)
        returnlist.set(withelement.heroId2 as Int,(withelement.winCount as Int).toFloat()/(withelement.matchCount as Int).toFloat())
    }
    //sreturnlist.add(with[0].heroId1 as Int, (-1.0).toFloat())
    return returnlist
}

private fun toHerowith(advantage: Advan_disadvan.Advantage?):Herowith{
    val winratelist = toHerowitharray(advantage?.with as List<Advan_disadvan.With>)
    return Herowith(
        stratzId = advantage.heroId as Int,
        w1 = winratelist[1],
        w2 = winratelist[2],
        w3 = winratelist[3],
        w4 = winratelist[4],
        w5 = winratelist[5],
        w6 = winratelist[6],
        w7 = winratelist[7],
        w8 = winratelist[8],
        w9 = winratelist[9],
        w10 = winratelist[10],
        w11 = winratelist[11],
        w12 = winratelist[12],
        w13 = winratelist[13],
        w14 = winratelist[14],
        w15 = winratelist[15],
        w16 = winratelist[16],
        w17 = winratelist[17],
        w18 = winratelist[18],
        w19 = winratelist[19],
        w20 = winratelist[20],
        w21 = winratelist[21],
        w22 = winratelist[22],
        w23 = winratelist[23],
        w25 = winratelist[25],
        w26 = winratelist[26],
        w27 = winratelist[27],
        w28 = winratelist[28],
        w29 = winratelist[29],
        w30 = winratelist[30],
        w31 = winratelist[31],
        w32 = winratelist[32],
        w33 = winratelist[33],
        w34 = winratelist[34],
        w35 = winratelist[35],
        w36 = winratelist[36],
        w37 = winratelist[37],
        w38 = winratelist[38],
        w39 = winratelist[39],
        w40 = winratelist[40],
        w41 = winratelist[41],
        w42 = winratelist[42],
        w43 = winratelist[43],
        w44 = winratelist[44],
        w45 = winratelist[45],
        w46 = winratelist[46],
        w47 = winratelist[47],
        w48 = winratelist[48],
        w49 = winratelist[49],
        w50 = winratelist[50],
        w51 = winratelist[51],
        w52 = winratelist[52],
        w53 = winratelist[53],
        w54 = winratelist[54],
        w55 = winratelist[55],
        w56 = winratelist[56],
        w57 = winratelist[57],
        w58 = winratelist[58],
        w59 = winratelist[59],
        w60 = winratelist[60],
        w61 = winratelist[61],
        w62 = winratelist[62],
        w63 = winratelist[63],
        w64 = winratelist[64],
        w65 = winratelist[65],
        w66 = winratelist[66],
        w67 = winratelist[67],
        w68 = winratelist[68],
        w69 = winratelist[69],
        w70 = winratelist[70],
        w71 = winratelist[71],
        w72 = winratelist[72],
        w73 = winratelist[73],
        w74 = winratelist[74],
        w75 = winratelist[75],
        w76 = winratelist[76],
        w77 = winratelist[77],
        w78 = winratelist[78],
        w79 = winratelist[79],
        w80 = winratelist[80],
        w81 = winratelist[81],
        w82 = winratelist[82],
        w83 = winratelist[83],
        w84 = winratelist[84],
        w85 = winratelist[85],
        w86 = winratelist[86],
        w87 = winratelist[87],
        w88 = winratelist[88],
        w89 = winratelist[89],
        w90 = winratelist[90],
        w91 = winratelist[91],
        w92 = winratelist[92],
        w93 = winratelist[93],
        w94 = winratelist[94],
        w95 = winratelist[95],
        w96 = winratelist[96],
        w97 = winratelist[97],
        w98 = winratelist[98],
        w99 = winratelist[99],
        w100 = winratelist[100],
        w101 = winratelist[101],
        w102 = winratelist[102],
        w103 = winratelist[103],
        w104 = winratelist[104],
        w105 = winratelist[105],
        w106 = winratelist[106],
        w107 = winratelist[107],
        w108 = winratelist[108],
        w109 = winratelist[109],
        w110 = winratelist[110],
        w111 = winratelist[111],
        w112 = winratelist[112],
        w113 = winratelist[113],
        w114 = winratelist[114],
        w119 = winratelist[119],
        w120 = winratelist[120],
        w121 = winratelist[121],
        w123 = winratelist[123],
        w126 = winratelist[126],
        w128 = winratelist[128],
        w129 = winratelist[129],
        w135 = winratelist[135],
        w136 = winratelist[136],
        w137 = winratelist[137]
    )
}


//create an array of vs winrate, list[x] means winrate vs hero whose stratz id = x
private fun toHerovsarray(vs: List<Advan_disadvan.V>):List<Float>{
    val returnlist = MutableList(size = heroidcounts){index -> -1.0.toFloat()}
    //Log.d("mapper","heroid1: " + vs.get(0).heroId1.toString())
    for(vselement in vs){
        //Log.d("mapper","heroid2: " + vselement.heroId2)
        returnlist.set(vselement.heroId2 as Int,(vselement.winCount as Int).toFloat()/(vselement.matchCount as Int).toFloat())
    }
    //sreturnlist.add(with[0].heroId1 as Int, (-1.0).toFloat())
    return returnlist
}

private fun toHerovs(advantage: Advan_disadvan.Advantage?):Herovs{
    val winratelist = toHerovsarray(advantage?.vs as List<Advan_disadvan.V>)
    return Herovs(
        stratzId = advantage.heroId as Int,
        w1 = winratelist[1],
        w2 = winratelist[2],
        w3 = winratelist[3],
        w4 = winratelist[4],
        w5 = winratelist[5],
        w6 = winratelist[6],
        w7 = winratelist[7],
        w8 = winratelist[8],
        w9 = winratelist[9],
        w10 = winratelist[10],
        w11 = winratelist[11],
        w12 = winratelist[12],
        w13 = winratelist[13],
        w14 = winratelist[14],
        w15 = winratelist[15],
        w16 = winratelist[16],
        w17 = winratelist[17],
        w18 = winratelist[18],
        w19 = winratelist[19],
        w20 = winratelist[20],
        w21 = winratelist[21],
        w22 = winratelist[22],
        w23 = winratelist[23],
        w25 = winratelist[25],
        w26 = winratelist[26],
        w27 = winratelist[27],
        w28 = winratelist[28],
        w29 = winratelist[29],
        w30 = winratelist[30],
        w31 = winratelist[31],
        w32 = winratelist[32],
        w33 = winratelist[33],
        w34 = winratelist[34],
        w35 = winratelist[35],
        w36 = winratelist[36],
        w37 = winratelist[37],
        w38 = winratelist[38],
        w39 = winratelist[39],
        w40 = winratelist[40],
        w41 = winratelist[41],
        w42 = winratelist[42],
        w43 = winratelist[43],
        w44 = winratelist[44],
        w45 = winratelist[45],
        w46 = winratelist[46],
        w47 = winratelist[47],
        w48 = winratelist[48],
        w49 = winratelist[49],
        w50 = winratelist[50],
        w51 = winratelist[51],
        w52 = winratelist[52],
        w53 = winratelist[53],
        w54 = winratelist[54],
        w55 = winratelist[55],
        w56 = winratelist[56],
        w57 = winratelist[57],
        w58 = winratelist[58],
        w59 = winratelist[59],
        w60 = winratelist[60],
        w61 = winratelist[61],
        w62 = winratelist[62],
        w63 = winratelist[63],
        w64 = winratelist[64],
        w65 = winratelist[65],
        w66 = winratelist[66],
        w67 = winratelist[67],
        w68 = winratelist[68],
        w69 = winratelist[69],
        w70 = winratelist[70],
        w71 = winratelist[71],
        w72 = winratelist[72],
        w73 = winratelist[73],
        w74 = winratelist[74],
        w75 = winratelist[75],
        w76 = winratelist[76],
        w77 = winratelist[77],
        w78 = winratelist[78],
        w79 = winratelist[79],
        w80 = winratelist[80],
        w81 = winratelist[81],
        w82 = winratelist[82],
        w83 = winratelist[83],
        w84 = winratelist[84],
        w85 = winratelist[85],
        w86 = winratelist[86],
        w87 = winratelist[87],
        w88 = winratelist[88],
        w89 = winratelist[89],
        w90 = winratelist[90],
        w91 = winratelist[91],
        w92 = winratelist[92],
        w93 = winratelist[93],
        w94 = winratelist[94],
        w95 = winratelist[95],
        w96 = winratelist[96],
        w97 = winratelist[97],
        w98 = winratelist[98],
        w99 = winratelist[99],
        w100 = winratelist[100],
        w101 = winratelist[101],
        w102 = winratelist[102],
        w103 = winratelist[103],
        w104 = winratelist[104],
        w105 = winratelist[105],
        w106 = winratelist[106],
        w107 = winratelist[107],
        w108 = winratelist[108],
        w109 = winratelist[109],
        w110 = winratelist[110],
        w111 = winratelist[111],
        w112 = winratelist[112],
        w113 = winratelist[113],
        w114 = winratelist[114],
        w119 = winratelist[119],
        w120 = winratelist[120],
        w121 = winratelist[121],
        w123 = winratelist[123],
        w126 = winratelist[126],
        w128 = winratelist[128],
        w129 = winratelist[129],
        w135 = winratelist[135],
        w136 = winratelist[136],
        w137 = winratelist[137]
    )
}


//transfer herowith to list[winrate of each couple]
fun Herowith.tolist():List<Float>{
    val returnlist = mutableListOf<Float>()
    returnlist.add(w1)
    returnlist.add(w2)
    returnlist.add(w3)
    returnlist.add(w4)
    returnlist.add(w5)
    returnlist.add(w6)
    returnlist.add(w7)
    returnlist.add(w8)
    returnlist.add(w9)
    returnlist.add(w10)
    returnlist.add(w11)
    returnlist.add(w12)
    returnlist.add(w13)
    returnlist.add(w14)
    returnlist.add(w15)
    returnlist.add(w16)
    returnlist.add(w17)
    returnlist.add(w18)
    returnlist.add(w19)
    returnlist.add(w20)
    returnlist.add(w21)
    returnlist.add(w22)
    returnlist.add(w23)
    returnlist.add(w25)
    returnlist.add(w26)
    returnlist.add(w27)
    returnlist.add(w28)
    returnlist.add(w29)
    returnlist.add(w30)
    returnlist.add(w31)
    returnlist.add(w32)
    returnlist.add(w33)
    returnlist.add(w34)
    returnlist.add(w35)
    returnlist.add(w36)
    returnlist.add(w37)
    returnlist.add(w38)
    returnlist.add(w39)
    returnlist.add(w40)
    returnlist.add(w41)
    returnlist.add(w42)
    returnlist.add(w43)
    returnlist.add(w44)
    returnlist.add(w45)
    returnlist.add(w46)
    returnlist.add(w47)
    returnlist.add(w48)
    returnlist.add(w49)
    returnlist.add(w50)
    returnlist.add(w51)
    returnlist.add(w52)
    returnlist.add(w53)
    returnlist.add(w54)
    returnlist.add(w55)
    returnlist.add(w56)
    returnlist.add(w57)
    returnlist.add(w58)
    returnlist.add(w59)
    returnlist.add(w60)
    returnlist.add(w61)
    returnlist.add(w62)
    returnlist.add(w63)
    returnlist.add(w64)
    returnlist.add(w65)
    returnlist.add(w66)
    returnlist.add(w67)
    returnlist.add(w68)
    returnlist.add(w69)
    returnlist.add(w70)
    returnlist.add(w71)
    returnlist.add(w72)
    returnlist.add(w73)
    returnlist.add(w74)
    returnlist.add(w75)
    returnlist.add(w76)
    returnlist.add(w77)
    returnlist.add(w78)
    returnlist.add(w79)
    returnlist.add(w80)
    returnlist.add(w81)
    returnlist.add(w82)
    returnlist.add(w83)
    returnlist.add(w84)
    returnlist.add(w85)
    returnlist.add(w86)
    returnlist.add(w87)
    returnlist.add(w88)
    returnlist.add(w89)
    returnlist.add(w90)
    returnlist.add(w91)
    returnlist.add(w92)
    returnlist.add(w93)
    returnlist.add(w94)
    returnlist.add(w95)
    returnlist.add(w96)
    returnlist.add(w97)
    returnlist.add(w98)
    returnlist.add(w99)
    returnlist.add(w100)
    returnlist.add(w101)
    returnlist.add(w102)
    returnlist.add(w103)
    returnlist.add(w104)
    returnlist.add(w105)
    returnlist.add(w106)
    returnlist.add(w107)
    returnlist.add(w108)
    returnlist.add(w109)
    returnlist.add(w110)
    returnlist.add(w111)
    returnlist.add(w112)
    returnlist.add(w113)
    returnlist.add(w114)
    returnlist.add(w119)
    returnlist.add(w120)
    returnlist.add(w121)
    returnlist.add(w123)
    returnlist.add(w126)
    returnlist.add(w128)
    returnlist.add(w129)
    returnlist.add(w135)
    returnlist.add(w136)
    returnlist.add(w137)
    return returnlist
}

fun Herovs.tolist():List<Float>{
    val returnlist = mutableListOf<Float>()
    returnlist.add(w1)
    returnlist.add(w2)
    returnlist.add(w3)
    returnlist.add(w4)
    returnlist.add(w5)
    returnlist.add(w6)
    returnlist.add(w7)
    returnlist.add(w8)
    returnlist.add(w9)
    returnlist.add(w10)
    returnlist.add(w11)
    returnlist.add(w12)
    returnlist.add(w13)
    returnlist.add(w14)
    returnlist.add(w15)
    returnlist.add(w16)
    returnlist.add(w17)
    returnlist.add(w18)
    returnlist.add(w19)
    returnlist.add(w20)
    returnlist.add(w21)
    returnlist.add(w22)
    returnlist.add(w23)
    returnlist.add(w25)
    returnlist.add(w26)
    returnlist.add(w27)
    returnlist.add(w28)
    returnlist.add(w29)
    returnlist.add(w30)
    returnlist.add(w31)
    returnlist.add(w32)
    returnlist.add(w33)
    returnlist.add(w34)
    returnlist.add(w35)
    returnlist.add(w36)
    returnlist.add(w37)
    returnlist.add(w38)
    returnlist.add(w39)
    returnlist.add(w40)
    returnlist.add(w41)
    returnlist.add(w42)
    returnlist.add(w43)
    returnlist.add(w44)
    returnlist.add(w45)
    returnlist.add(w46)
    returnlist.add(w47)
    returnlist.add(w48)
    returnlist.add(w49)
    returnlist.add(w50)
    returnlist.add(w51)
    returnlist.add(w52)
    returnlist.add(w53)
    returnlist.add(w54)
    returnlist.add(w55)
    returnlist.add(w56)
    returnlist.add(w57)
    returnlist.add(w58)
    returnlist.add(w59)
    returnlist.add(w60)
    returnlist.add(w61)
    returnlist.add(w62)
    returnlist.add(w63)
    returnlist.add(w64)
    returnlist.add(w65)
    returnlist.add(w66)
    returnlist.add(w67)
    returnlist.add(w68)
    returnlist.add(w69)
    returnlist.add(w70)
    returnlist.add(w71)
    returnlist.add(w72)
    returnlist.add(w73)
    returnlist.add(w74)
    returnlist.add(w75)
    returnlist.add(w76)
    returnlist.add(w77)
    returnlist.add(w78)
    returnlist.add(w79)
    returnlist.add(w80)
    returnlist.add(w81)
    returnlist.add(w82)
    returnlist.add(w83)
    returnlist.add(w84)
    returnlist.add(w85)
    returnlist.add(w86)
    returnlist.add(w87)
    returnlist.add(w88)
    returnlist.add(w89)
    returnlist.add(w90)
    returnlist.add(w91)
    returnlist.add(w92)
    returnlist.add(w93)
    returnlist.add(w94)
    returnlist.add(w95)
    returnlist.add(w96)
    returnlist.add(w97)
    returnlist.add(w98)
    returnlist.add(w99)
    returnlist.add(w100)
    returnlist.add(w101)
    returnlist.add(w102)
    returnlist.add(w103)
    returnlist.add(w104)
    returnlist.add(w105)
    returnlist.add(w106)
    returnlist.add(w107)
    returnlist.add(w108)
    returnlist.add(w109)
    returnlist.add(w110)
    returnlist.add(w111)
    returnlist.add(w112)
    returnlist.add(w113)
    returnlist.add(w114)
    returnlist.add(w119)
    returnlist.add(w120)
    returnlist.add(w121)
    returnlist.add(w123)
    returnlist.add(w126)
    returnlist.add(w128)
    returnlist.add(w129)
    returnlist.add(w135)
    returnlist.add(w136)
    returnlist.add(w137)
    return returnlist
}
