package ai.pipi.dotapickone.ui.dashboard

import ai.pipi.dotapickone.DotaAppQuery
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import ai.pipi.dotapickone.room.AppDatabase
import ai.pipi.dotapickone.room.Heroname
import ai.pipi.dotapickone.room.HeronameDao
import ai.pipi.dotapickone.room.Herovs.HerovsDao
import ai.pipi.dotapickone.room.herowinrate.HerowinrateDao
import ai.pipi.dotapickone.room.herowith.HerowithDao
import ai.pipi.dotapickone.room.tolist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.kotlinx.multik.api.d1array
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.api.ones
import org.jetbrains.kotlinx.multik.ndarray.data.D1Array
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import kotlin.collections.set


enum class SortColumn_value {
    Heroname,
    Winrate,
    Rate
}
data class SortInfo(val sortColumn: SortColumn_value, val ascending: Boolean)

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
            value = ""
    }
    val text: LiveData<String> = _text
    private val heroid_name = MutableLiveData<MutableList<DotaAppQuery.Hero>>()
    private var radiantordire: MutableLiveData<Boolean> = MutableLiveData(true)
    private var radiantherolist = MutableList(5){0}
    private var direherolist = MutableList(5){0}
    private var rlist_sorder = mutableListOf<Int>()
    private var dlist_sorder = mutableListOf<Int>()
    private lateinit var stratzorder : MutableMap<Int,Int>      //key is stratzid, value is the order of that stratz id
    private lateinit var stratztoalpah : MutableMap<Int,Int>    //key is stratzid, value is its alphabetic order


    //database variables
    private lateinit var db : AppDatabase
    private lateinit var heronameDao : HeronameDao
    private lateinit var herowithDao : HerowithDao
    private lateinit var herovsDao : HerovsDao
    private lateinit var herowinrateDao : HerowinrateDao
    private lateinit var heroname_live : LiveData<List<Heroname>>


    //matrix variables
    //private val herowithmatrix = mutableListOf<List<Float>>()
    //private lateinit var herowithmultik : D2Array<Float>
    //private val herovsmatrix = mutableListOf<List<Float>>()
    //private lateinit var herovsmultik : D2Array<Float>
    //private var herowinratevector = listOf<Float>()
    private lateinit var herowinratemultik : D1Array<Float>
    private val onemultik = mk.ones<Float>(123,123)
    private val onelinemultik = mk.ones<Float>(123)
    private lateinit var coordinateindex : D2Array<Float>
    private lateinit var counterindex : D2Array<Float>

    //dashboard sort
    private var sortInfo = MutableLiveData(
        SortInfo(SortColumn_value.Winrate, false))

    //loading
    private val finishloading = MutableLiveData<Boolean>()


    fun startinit() {
        Log.d(javaClass.simpleName,"dashviewmodel start init")

        //init database
        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            db = AppDatabase.getDatabase(getApplication())
            heronameDao = db.heronameDao()
            herowithDao = db.herowithDao()
            herovsDao = db.herovsDao()
            herowinrateDao = db.herowinrateDao()
            initmatrices()
            initstratzorder()
            getfinallist()

            heroname_live = heronameDao.getHeroSortByDescWin_live()

            finishloading.postValue(true)


            //initiate heroname submit list livedata
//            heroname_live.addSource(heronameDao.getHeroSortByAscName_live()){
//                Log.d(javaClass.simpleName,"NameAsc_entered")
//                if(sortInfo.value?.sortColumn == SortColumn_value.Heroname){
//                    if(sortInfo.value?.ascending == true){
//                        heroname_live.value = it
//                    }
//                }
//            }
//
//            heroname_live.addSource(heronameDao.getHeroSortByDescName_live()){
//                Log.d(javaClass.simpleName,"NameDesc_entered")
//                if(sortInfo.value?.sortColumn == SortColumn_value.Heroname){
//                    if(sortInfo.value?.ascending == false){
//                        heroname_live.value = it
//                    }
//                }
//            }
//
//            heroname_live.addSource(heronameDao.getHeroSortByAscWin_live()){
//                Log.d(javaClass.simpleName,"WinAsc_entered")
//                if(sortInfo.value?.sortColumn == SortColumn_value.Winrate){
//                    if(sortInfo.value?.ascending == true){
//                        heroname_live.value = it
//                    }
//                }
//            }
//
//            heroname_live.addSource(heronameDao.getHeroSortByDescWin_live()){
//                Log.d(javaClass.simpleName,"WinDesc_entered")
//                if(sortInfo.value?.sortColumn == SortColumn_value.Winrate){
//                    if(sortInfo.value?.ascending == false){
//                        heroname_live.value = it
//                    }
//                }
//            }

//            heroname_live.addSource(sortInfo){
//                when(it){
//                    SortInfo(SortColumn_value.Heroname,true)->{
//                        heroname_live.value = heronameDao.getHeroSortByAscName_live().value
//                    }
//                    SortInfo(SortColumn_value.Heroname,false)->{
//                        heroname_live.value = heronameDao.getHeroSortByDescName_live().value
//                    }
//                    SortInfo(SortColumn_value.Winrate,true)->{
//                        heroname_live.value = heronameDao.getHeroSortByAscWin_live().value
//                    }
//                    SortInfo(SortColumn_value.Winrate,false)->{
//                        heroname_live.value = heronameDao.getHeroSortByDescWin_live().value
//                    }
//                    else->{
//                        Log.d(javaClass.simpleName,"addsource_sortinfo_error")
//                    }
//                }
//            }
        }
    }

    //functions
    fun set_heroid_name(heroidname: MutableList<DotaAppQuery.Hero>){
        heroid_name.postValue(heroidname)
    }

    fun observe_heroidname():LiveData<MutableList<DotaAppQuery.Hero>>{
        return heroid_name
    }

    fun setradiantordire(rord:Boolean){
        radiantordire.postValue(rord)
    }

    fun observe_heroname():LiveData<List<Heroname>>{
        return heroname_live
    }

    fun observe_sortinfo():LiveData<SortInfo>{
        return sortInfo
    }

    fun observe_finishloading():LiveData<Boolean>{
        return finishloading
    }



    fun addwithhero(position: Int,teamposition:Int)=
        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO){
        //position is name alphabetic position, teamposition is location in homefragment
            val templist = radiantherolist
            Log.d(javaClass.simpleName,"withhero_before: " + templist)
            //val db = AppDatabase.getDatabase(getApplication())
            //val heronameDao = db.heronameDao()
            val herolist = heronameDao.getHeroSortByAscName()
            val stratzid = herolist[position].stratzId
            Log.d(javaClass.simpleName,"alphabetic position: " + position)
            Log.d(javaClass.simpleName,"stratz position: " + stratzid)
            templist[teamposition] = stratzid
            Log.d(javaClass.simpleName,"withhero_after: " + templist)
            radiantherolist = templist
            setrlist_sorder()
            getfinallist()
    }
    fun deletewithhero(teamposition: Int){
        val templist = radiantherolist
        Log.d(javaClass.simpleName,"withhero_before: " + templist)
        templist[teamposition] = 0
        Log.d(javaClass.simpleName,"withhero_after: " + templist)
        radiantherolist=templist
        setrlist_sorder()
        getfinallist()
    }

    fun addvshero(position: Int,teamposition:Int)=
        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO){
        //position is name alphabetic position, teamposition is location in homefragment
            val templist = direherolist
            Log.d(javaClass.simpleName,"vshero_before: " + templist)
            //val db = AppDatabase.getDatabase(getApplication())
            //val heronameDao = db.heronameDao()
            val herolist = heronameDao.getHeroSortByAscName()
            val stratzid = herolist.get(position).stratzId
            Log.d(javaClass.simpleName,"alphabetic position: " + position)
            Log.d(javaClass.simpleName,"stratz position: " + stratzid)
            templist.set(teamposition, stratzid)
            Log.d(javaClass.simpleName, "vshero_after: " + templist)
            direherolist = templist
            setdlist_sorder()
            getfinallist()
    }
    fun deletevshero(teamposition: Int){
        val templist = direherolist
        Log.d(javaClass.simpleName,"vshero_before: " + templist)
        templist[teamposition] = 0
        Log.d(javaClass.simpleName,"vshero_after: " + templist)
        direherolist = templist
        setdlist_sorder()
        getfinallist()
    }





    //transfer herowith and herovs table into matrix
    private fun initmatrices(){

        //calculate Ratio of two heros as teammate
        val herowiths = herowithDao.getAll()
        val herowithmatrix = mutableListOf<List<Float>>()
        for(herowith in herowiths){
            herowithmatrix.add(herowith.tolist())
        }
        Log.d(javaClass.simpleName,"withmatrixcomplete,size: " + herowithmatrix.size)
        var herowithmultik = mk.ndarray(herowithmatrix)
        herowithmultik = onemultik/herowithmultik - onemultik
        Log.d(javaClass.simpleName,"withmultikcomplete,size: " + herowithmultik.shape.get(0) + "," + herowithmultik.shape.get(1) + ")")

        //calculate Ratio of two heros as opponent
        val herovss = herovsDao.getAll()
        val herovsmatrix = mutableListOf<List<Float>>()
        for(herovs in herovss){
            herovsmatrix.add(herovs.tolist())
        }
        Log.d(javaClass.simpleName,"vsmatrixcomplete,size: " + herovsmatrix.size)
        var herovsmultik = mk.ndarray(herovsmatrix)
        //println(herovsmultik.get(0..123,1))
        herovsmultik = onemultik/herovsmultik - onemultik
        Log.d(javaClass.simpleName,"vsmultikcomplete,shape:(" + herovsmultik.shape.get(0) + "," + herovsmultik.shape.get(1) + ")")

        //calculate Ratio of one hero
        val herowinratevector = herowinrateDao.getwinratelist()
        Log.d(javaClass.simpleName,"winratevectorcomplete,size: " + herowinratevector.size)
        herowinratemultik = mk.ndarray(herowinratevector)
        herowinratemultik = onelinemultik/herowinratemultik - onelinemultik
        Log.d(javaClass.simpleName,"winratemultikcomplete,size: " + herowinratemultik.shape.get(0))

        //calculate Ratio_i * Ratio_j matrix
        val winratearray = Array(123){herowinratevector.toFloatArray()}
        val winratecolarray = Array(123) { i -> FloatArray(123){herowinratevector.get(i)} }
        var winratemultik = mk.ndarray(winratearray)
        var winratecolmultik = mk.ndarray(winratecolarray)
        winratemultik = onemultik/winratemultik - onemultik             //every row is herowinrate ratio
        winratecolmultik = onemultik/winratecolmultik - onemultik       //every column is herowinrate ratio
        val heromulhero = winratemultik * winratecolmultik

        //coordinateindex = Ratio_2hero/Ratio_i*Ratio_j
        coordinateindex = herowithmultik / heromulhero

        //calculate Ratio_i/Ratio_j matrix
        val herodivhero = winratecolmultik / winratemultik

        //counterindex = Ratio_2hero/Ratio_i/Ratio_j,   C_ij means i as choose hero, j means opponent hero
        counterindex = herovsmultik / herodivhero
    }

    private fun initstratzorder(){
        var herolist = heronameDao.getAll()
        stratzorder = mutableMapOf()
        for((count, heroname) in herolist.withIndex()){
            stratzorder[heroname.stratzId] = count
        }
        Log.d(javaClass.simpleName,"StratzOrder: " + stratzorder)

        herolist = heronameDao.getHeroSortByAscName()
        stratztoalpah = mutableMapOf()
        for((count, heroname) in herolist.withIndex()){
            stratztoalpah[heroname.stratzId] = count
        }
        Log.d(javaClass.simpleName,"StratztoAlpha: " + stratztoalpah)
    }

    fun transferSIDtoAlpha(stratzId:Int): Int? {
        return stratztoalpah[stratzId]
    }

    private fun setrlist_sorder(){
        Log.d(javaClass.simpleName,"enter_rlist")
        val returnlist = mutableListOf<Int>()
        val rlist_sid = radiantherolist
        for (id in rlist_sid) {
            if (id != 0) {
                stratzorder[id]?.let { it1 -> returnlist.add(it1) }
            }
        }
        rlist_sorder = returnlist
        Log.d(javaClass.simpleName,"radiant stratz order list: " + rlist_sorder)
    }

    private fun setdlist_sorder(){
        Log.d(javaClass.simpleName,"enter_dlist")
        val returnlist = mutableListOf<Int>()
        val dlist_sid = direherolist
        for (id in dlist_sid) {
            if (id != 0) {
                stratzorder[id]?.let { it1 -> returnlist.add(it1) }
            }
        }
        dlist_sorder = returnlist
        Log.d(javaClass.simpleName,"dire stratz order list: " + dlist_sorder)
    }

    private fun getfinallist(){
        //R_total = RaRbRc/RfRgRh * CabCac * DafDagDah
        var result = herowinratemultik.copy()
        var result_noindex = herowinratemultik.copy()
        for(sorder in rlist_sorder){
            val temp = herowinratemultik[sorder]
            val Ratio_sorder = mk.d1array(123){temp}
            //println(Ratio_sorder)
            result = result * Ratio_sorder
            result_noindex = result_noindex * Ratio_sorder
            result = result * coordinateindex[0..123,sorder]
        }
        for(sorder in dlist_sorder){
            val temp = herowinratemultik[sorder]
            val Ratio_sorder = mk.d1array(123){temp}
            //println(Ratio_sorder)
            result = result / Ratio_sorder
            result_noindex = result_noindex / Ratio_sorder
            result = result * counterindex[0..123,sorder]
        }
        //change losewin ration to winrate,calculate index store in result_noindex
        result_noindex = (result_noindex - result)/(result + onelinemultik)
        result = onelinemultik/(result + onelinemultik)
        Log.d(javaClass.simpleName,"final list: " + result.toList())
        Log.d(javaClass.simpleName,"final index list: " + result_noindex.toList())
        updateprediction_index(result.toList(),result_noindex.toList())
    }

    //add a column to heroname table
    //add a column 'with/vs' cooefficient to heroname table
    private fun updateprediction_index(list1: List<Float>,list2: List<Float>)=
        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO){
        val heronames = heronameDao.getAll()
            for((count, heroname) in heronames.withIndex()){
                heroname.predicted_winrate = list1[count]
                heroname.withvs_index = list2[count]
                Log.d(javaClass.simpleName,"updatewinrate${count}: " + heroname.predicted_winrate)
                Log.d(javaClass.simpleName,"updateindex${count}: " + heroname.withvs_index)
                heronameDao.updateHeroname(heroname)
        }
    }

//    //add a column 'with/vs' cooefficient to heroname table
//    private fun updateindex(list: List<Float>)=
//        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO){
//            val heronames = heronameDao.getAll()
//            for((count,heroname) in heronames.withIndex()){
//                heroname.withvs_index = list[count]
//                Log.d(javaClass.simpleName,"updateindex${count}: " + heroname.withvs_index)
//                heronameDao.updateHeroname(heroname)
//            }
//        }


    //handle dashboard sort
    fun sortInfoClick(input_sortColumn: SortColumn_value) {
        // XXX User has changed sort info
        Log.d(javaClass.simpleName,"sortinfobefore:" + sortInfo.value.toString())
        if(sortInfo.value != null){
            if(input_sortColumn != sortInfo.value!!.sortColumn){        //tap other tab
                if (input_sortColumn == SortColumn_value.Heroname){
                    sortInfo.setValue(SortInfo(input_sortColumn,true))
                }
                else{
                    sortInfo.setValue(SortInfo(input_sortColumn,false))
                }
            }
            else{
                val upanddown = !sortInfo.value!!.ascending             //tap same tab
                sortInfo.setValue(SortInfo(input_sortColumn,upanddown))
            }
        }
        Log.d(javaClass.simpleName,"sortinfoafter:" + sortInfo.value.toString())

        when(sortInfo.value){
            SortInfo(SortColumn_value.Heroname,true)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_AscName")
                heroname_live = heronameDao.getHeroSortByAscName_live()
            }
            SortInfo(SortColumn_value.Heroname,false)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_DescName")
                heroname_live = heronameDao.getHeroSortByDescName_live()
            }
            SortInfo(SortColumn_value.Winrate,true)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_AscWin")
                heroname_live = heronameDao.getHeroSortByAscWin_live()
            }
            SortInfo(SortColumn_value.Winrate,false)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_DescWin")
                heroname_live = heronameDao.getHeroSortByDescWin_live()
            }
            SortInfo(SortColumn_value.Rate,true)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_AscRate")
                heroname_live = heronameDao.getHeroSortByAscRate_live()
            }
            SortInfo(SortColumn_value.Rate,false)->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_DescRate")
                heroname_live = heronameDao.getHeroSortByDescRate_live()
            }
            else->{
                Log.d(javaClass.simpleName,"sorinfoclick_changeheronamelive_error")
            }
        }
    }

    //don't display chosen hero in dashboard list, true means chosen, false means not chosen
    fun checkchosen(stratzId: Int):Boolean{
        if(radiantherolist.contains(stratzId) or direherolist.contains(stratzId)){
            Log.d(javaClass.simpleName,stratzId.toString() + " is chosen")
            return true
        }
        else{
            return false
        }
    }



}