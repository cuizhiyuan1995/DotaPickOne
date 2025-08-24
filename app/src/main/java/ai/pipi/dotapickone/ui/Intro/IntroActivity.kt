package ai.pipi.dotapickone.ui.Intro

import ai.pipi.dotapickone.MainActivity
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.SettingsManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityIntroBinding
    // on below line we are creating a
    // variable for our view pager
    //private lateinit var viewPager: ViewPager

    // on below line we are creating a variable
    // for our slider adapter and slider list
    //private lateinit var sliderAdapter: SliderAdapter
    //private lateinit var sliderList: ArrayList<SliderData>

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
                    else{
                        setContent {
                            IntroCompose()
                        }
                    }
                }
            }
        }



        //binding = ActivityIntroBinding.inflate(layoutInflater)
        //setContentView(binding.root)


        // on below line we are initializing all
        // our variables with their ids.
        //viewPager = findViewById(R.id.idViewPager)
        //skipBtn = findViewById(R.id.idBtnSkip)
        //indicatorSlideOneTV = findViewById(R.id.idTVSlideOne)
        //indicatorSlideTwoTV = findViewById(R.id.idTVSlideTwo)
        //indicatorSlideThreeTV = findViewById(R.id.idTVSlideThree)

        // on below line we are adding click listener for our skip button
//        binding.idBtnSkip.setOnClickListener {
//            // on below line we are opening a new activity
//            lifecycleScope.launch{
//                dataprefermanager.saveFirstTime(true)
//            }
//            //val i = Intent(this, MainActivity::class.java)
//            //startActivity(i)
//            //this.finish()
//        }

        // on below line we are initializing our slider list.
        //sliderList = ArrayList()

        // on below line we are adding data to our list
//        sliderList.add(
//            SliderData(
//                this.getString(R.string.title_home),
//                this.getString(R.string.intro_home),
//                R.drawable.intro_home
//            )
//        )
//
//        sliderList.add(
//            SliderData(
//                this.getString(R.string.title_dashboard),
//                this.getString(R.string.intro_Winrate),
//                R.drawable.intro_winratre
//            )
//        )
//
//        sliderList.add(
//            SliderData(
//                this.getString(R.string.title_notifications),
//                this.getString(R.string.intro_Settings),
//                R.drawable.intro_settings
//            )
//        )

        // on below line we are adding slider list
        // to our adapter class.
        //sliderAdapter = SliderAdapter(this, sliderList)

        // on below line we are setting adapter
        // for our view pager on below line.
        //binding.idViewPager.adapter = sliderAdapter

        // on below line we are adding page change
        // listener for our view pager.
        //binding.idViewPager.addOnPageChangeListener(viewListener)

    }

    // creating a method for view pager for on page change listener.
//    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
//        override fun onPageScrolled(
//            position: Int,
//            positionOffset: Float,
//            positionOffsetPixels: Int
//        ) {
//        }
//
//        override fun onPageSelected(position: Int) {
//            // we are calling our dots method to
//            // change the position of selected dots.
//
//            // on below line we are checking position and updating text view text color.
//            if (position == 0) {
//                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
//                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//
//            } else if (position == 1) {
//                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
//                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//            } else {
//                binding.idTVSlideOne.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//                binding.idTVSlideTwo.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.light_gray))
//                binding.idTVSlideThree.setTextColor(ContextCompat.getColor(this@IntroActivity, R.color.white))
//            }
//        }
//
//        // below method is use to check scroll state.
//        override fun onPageScrollStateChanged(state: Int) {}
//    }
}



@Composable
fun IntroCompose() {
    ViewPagerCompose()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerCompose(){
    val pageCount = 3 // Total number of pages

    var currentPage by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    Column() {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            state = pagerState
        ) { page ->
            PageContent(page)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(colorResource(id = R.color.purple_200)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier
                .width(70.dp)
                .height(IntrinsicSize.Min))
            Row(modifier = Modifier
                .align(Alignment.CenterVertically)){
                repeat(pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(5.dp)
                    )
                }
            }
            val context = LocalContext.current as Activity
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    val dataprefermanager = SettingsManager(context)
                    coroutineScope.launch{
                        dataprefermanager.saveFirstTime(true)
                    }
                    //val i = Intent(context, MainActivity::class.java)
                    //context.startActivity(i)
                    //context.finish()
                },
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(id = R.string.intro_skip),
                    color = Color.White
                )
            }
        }
    }

}

@Composable
fun PageContent(page: Int) {
    var title_src by remember { mutableStateOf(0) }
    var image_src by remember { mutableStateOf(0) }
    var description_src by remember { mutableStateOf(0) }
    when(page){
        0->{
            title_src = R.string.title_home
            image_src = R.drawable.intro_home
            description_src = R.string.intro_home
        }
        1->{
            title_src = R.string.title_dashboard
            image_src = R.drawable.intro_winratre
            description_src = R.string.intro_Winrate
        }
        2->{
            title_src = R.string.title_notifications
            image_src = R.drawable.intro_settings
            description_src = R.string.intro_Settings
        }
    }
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.purple_200))
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = title_src),
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painterResource(id = image_src),
            contentDescription = "intro_home",
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .weight(0.5f)
        )
        Text(
            text = stringResource(id = description_src),
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.Start)
        )
    }
}
