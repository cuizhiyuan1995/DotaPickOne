package ai.pipi.dotapickone.ui.Intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import ai.pipi.dotapickone.MainActivity
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.SettingsManager
import ai.pipi.dotapickone.databinding.ActivityIntroBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    // on below line we are creating a
    // variable for our view pager
    //private lateinit var viewPager: ViewPager

    // on below line we are creating a variable
    // for our slider adapter and slider list
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderList: ArrayList<SliderData>

    // on below line we are creating a variable for our
    // skip button, slider indicator text view for 3 dots
    //lateinit var skipBtn: Button
    //lateinit var indicatorSlideOneTV: TextView
    //lateinit var indicatorSlideTwoTV: TextView
    //lateinit var indicatorSlideThreeTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        //check if user has experienced tutorial
        val dataprefermanager = SettingsManager(this@IntroActivity)
        lifecycleScope.launch{
            dataprefermanager.getFirstTime().catch { e ->
                e.printStackTrace()
            }.collect{
                withContext(Dispatchers.Main){
                    Log.d(javaClass.simpleName,"tutorial completed: " + it.toString())
                    if(it){
                        val i = Intent(this@IntroActivity, MainActivity::class.java)
                        startActivity(i)
                        this@IntroActivity.finish()
                    }
                }
            }
        }



        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // on below line we are initializing all
        // our variables with their ids.
        //viewPager = findViewById(R.id.idViewPager)
        //skipBtn = findViewById(R.id.idBtnSkip)
        //indicatorSlideOneTV = findViewById(R.id.idTVSlideOne)
        //indicatorSlideTwoTV = findViewById(R.id.idTVSlideTwo)
        //indicatorSlideThreeTV = findViewById(R.id.idTVSlideThree)

        // on below line we are adding click listener for our skip button
        binding.idBtnSkip.setOnClickListener {
            // on below line we are opening a new activity
            lifecycleScope.launch{
                dataprefermanager.saveFirstTime(true)
            }
            //val i = Intent(this, MainActivity::class.java)
            //startActivity(i)
            //this.finish()
        }

        // on below line we are initializing our slider list.
        sliderList = ArrayList()

        // on below line we are adding data to our list
        sliderList.add(
            SliderData(
                this.getString(R.string.title_home),
                this.getString(R.string.intro_home),
                R.drawable.intro_home
            )
        )

        sliderList.add(
            SliderData(
                this.getString(R.string.title_dashboard),
                this.getString(R.string.intro_Winrate),
                R.drawable.intro_winratre
            )
        )

        sliderList.add(
            SliderData(
                this.getString(R.string.title_notifications),
                this.getString(R.string.intro_Settings),
                R.drawable.intro_settings
            )
        )

        // on below line we are adding slider list
        // to our adapter class.
        sliderAdapter = SliderAdapter(this, sliderList)

        // on below line we are setting adapter
        // for our view pager on below line.
        binding.idViewPager.adapter = sliderAdapter

        // on below line we are adding page change
        // listener for our view pager.
        binding.idViewPager.addOnPageChangeListener(viewListener)

    }

    // creating a method for view pager for on page change listener.
    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            // we are calling our dots method to
            // change the position of selected dots.

            // on below line we are checking position and updating text view text color.
            if (position == 0) {
                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))

            } else if (position == 1) {
                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
            } else {
                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
            }
        }

        // below method is use to check scroll state.
        override fun onPageScrollStateChanged(state: Int) {}
    }
}