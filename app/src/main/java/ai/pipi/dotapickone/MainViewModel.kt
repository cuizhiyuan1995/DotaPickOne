package ai.pipi.dotapickone

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel: ViewModel() {
    private var bitmaps :MutableList<Int> = mutableListOf()
    private var _radiantordire: MutableLiveData<Boolean> = MutableLiveData(true)
    private var rcount : PriorityQueue<Int> = PriorityQueue<Int>()
    private var dcount : PriorityQueue<Int> = PriorityQueue<Int>()
    private var _rtapat = MutableLiveData(-1)
    private var _dtapat = MutableLiveData(-1)
    private var tappedposition : Int = 0
    private lateinit var blankbitmap: Bitmap
    private var heroinslot : MutableList<Int> = mutableListOf()
    private var lastfetchdate : Int = -1

    val rtapat: LiveData<Int>
        get() = _rtapat

    val dtapat: LiveData<Int>
        get() = _dtapat

    val rord: LiveData<Boolean>
        get() = _radiantordire

    init{
        rcount.add(2)
        rcount.add(3)
        rcount.add(4)
        rcount.add(5)
        dcount.add(6)
        dcount.add(7)
        dcount.add(8)
        dcount.add(9)
        dcount.add(10)
        heroinslot = mutableListOf(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1)
    }


    fun addbitmap(bitmap:Int){
        bitmaps.add(bitmap)
    }

    fun getallbitmaps():MutableList<Int>{
        return bitmaps
    }

    fun getbitmapssize():Int{
        return bitmaps.size
    }

    fun getbitmapatposition(position:Int): Int{
        return bitmaps[position]
    }

    fun setradiantordire(rord:Boolean){
        _radiantordire.postValue(rord)
        Log.d(javaClass.simpleName,"radiant tapped")
    }

    fun gettappedposition():Int{
        return tappedposition
    }

    fun addtorcount(slot:Int){
        if(slot !in rcount){
            rcount.add(slot)
            heroinslot.set(slot - 1, -1)
            Log.d(javaClass.simpleName,"heroinslot: " + heroinslot.toString())
        }
    }
    fun addtodcount(slot:Int){
        if(slot !in dcount){
            dcount.add(slot)
            heroinslot.set(slot - 1, -1)
            Log.d(javaClass.simpleName,"heroinslot: " + heroinslot.toString())
        }
    }

    fun addblankbitmap(image:Bitmap){
        blankbitmap = image
    }

    fun getheroinslots():MutableList<Int>{
        return heroinslot
    }

    fun getblankimage():Bitmap{
        return blankbitmap
    }

    fun observeRtap(): LiveData<Int>{
        return _rtapat
    }
    fun observeDtap(): LiveData<Int>{
        return _dtapat
    }

    fun updateRtap(newValue: Int) {
        _rtapat.value = newValue
    }

    fun updateDtap(newValue: Int) {
        _dtapat.value = newValue
    }


    fun observerord(): LiveData<Boolean>{
        return _radiantordire
    }

    fun setlastfetchdate(date:Int){
        lastfetchdate = date
    }

    fun getlastfetchdate():Int{
        return lastfetchdate
    }

    fun imagetapped(position: Int){
        Log.d(javaClass.simpleName,"rcount:" + rcount.toString())
        Log.d(javaClass.simpleName,"dcount:" + dcount.toString())
        if(heroinslot.contains(position)){
            Log.d(javaClass.simpleName,"imagetappedat: " + position + ",but it has been tapped")
        }
        else{
            Log.d(javaClass.simpleName,"imagetappedat: " + position)
            if(_radiantordire.value == true){
                if(rcount.isNotEmpty()){
                    tappedposition = position
                    heroinslot[rcount.peek() - 1] = position
                    Log.d(javaClass.simpleName,"heroinslot: " + heroinslot.toString())
                    _rtapat.postValue(rcount.poll())
                }
            }
            else{
                if(dcount.isNotEmpty()){
                    tappedposition = position
                    heroinslot[dcount.peek() - 1] = position
                    Log.d(javaClass.simpleName,"heroinslot: " + heroinslot.toString())
                    _dtapat.postValue(dcount.poll())
                }
            }
        }

    }

}