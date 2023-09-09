package ai.pipi.dotapickone.ui.home

import ai.pipi.dotapickone.Hero
import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.ui.dashboard.DashboardViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenHome(
    viewModel: MainViewModel = viewModel(),
    dashviewModel: DashboardViewModel = viewModel(),
    navController: NavHostController
){
    val hero = Hero()
    val herolist = hero.fetchData()

    //data class ImageState(val imagesrc: Int)
    val ImageStateSaver = listSaver<Int,Any>(
        save = {listOf(it)},
        restore = {it[0] as Int}
    )

    var image1 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image2 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image3 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image4 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image5 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image6 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image7 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image8 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image9 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}
    var image10 by rememberSaveable(stateSaver = ImageStateSaver){mutableStateOf(R.drawable.blankimage)}

    var r_buttoncolor by remember{mutableStateOf(Color.Green)}
    var d_buttoncolor by remember {mutableStateOf(Color.LightGray)}

    val rtap by viewModel.rtapat.observeAsState(-1)
    val dtap by viewModel.dtapat.observeAsState(-1)
    val rord by viewModel.rord.observeAsState(true)



    if(rtap > 0){
        val heroinslot = viewModel.getheroinslots()
        val position = heroinslot[rtap - 1]
        val bitmap: Int?
        if(position >= 0){
            bitmap = viewModel.getbitmapatposition(position)
            dashviewModel.addwithhero(position,rtap - 1)
            Log.d("screenhome_compose","R_imagetappedat,addto:" + position + "," + rtap.toString())
            when(rtap){
                2-> image2 = bitmap
                3-> image3 = bitmap
                4-> image4 = bitmap
                5-> image5 = bitmap
                else-> Log.d("screenhome_compose","rcounterror_or_init:" + rtap.toString())
            }
            viewModel.updateRtap(-1)
        }
    }

    if(dtap > 0){
        val heroinslot = viewModel.getheroinslots()
        val position = heroinslot[dtap - 1]
        val bitmap: Int?
        if(position >= 0){
            dashviewModel.addvshero(position,dtap - 6)
            bitmap = viewModel.getbitmapatposition(position)
            Log.d("screenhome_compose","D_imagetappedat,addto:" + position + "," + dtap.toString())
            when(dtap){
                6-> image6 = bitmap
                7-> image7 = bitmap
                8-> image8 = bitmap
                9-> image9 = bitmap
                10-> image10 = bitmap
                else -> Log.d("screenhome_compose","dcounterror_or_init:" + dtap.toString())
            }
            viewModel.updateDtap(-1)
        }

    }

    if(rord){
        r_buttoncolor = Color.Green
        d_buttoncolor = Color.LightGray
    }
    else{
        r_buttoncolor = Color.LightGray
        d_buttoncolor = Color.Red
    }

    Column(
        Modifier
            .fillMaxHeight()
            .background(color = Color.White)
    ) {

        //Radiant
        Button(
            onClick = {
                //your onclick code here
                viewModel.setradiantordire(true)
                dashviewModel.setradiantordire(true)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = r_buttoncolor)
        ) {
            Text(text = "Radiant(You)")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(
                painter = painterResource(id = image1),
                contentDescription = "imageview1",
                modifier = Modifier
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = image2),
                contentDescription = "imageview2",
                modifier = Modifier
                    .clickable {
                        viewModel.addtorcount(2)
                        dashviewModel.deletewithhero(1)
                        image2 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFF90EE90))
            )

            Image(
                painter = painterResource(id = image3),
                contentDescription = "imageview3",
                modifier = Modifier
                    .clickable {
                        viewModel.addtorcount(3)
                        dashviewModel.deletewithhero(2)
                        image3 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFF7CFC00))
            )

            Image(
                painter = painterResource(id = image4),
                contentDescription = "imageview4",
                modifier = Modifier
                    .clickable {
                        viewModel.addtorcount(4)
                        dashviewModel.deletewithhero(3)
                        image4 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFF008000))
            )

            Image(
                painter = painterResource(id = image5),
                contentDescription = "imageview5",
                modifier = Modifier
                    .clickable {
                        viewModel.addtorcount(5)
                        dashviewModel.deletewithhero(4)
                        image5 = R.drawable.blankimage
                    }
                    .weight(1f),
                // colorFilter = ColorFilter.tint(Color(0xFF006400))
            )
        }

        //Dire
        Button(
            onClick = {
                //your onclick code here
                viewModel.setradiantordire(false)
                dashviewModel.setradiantordire(false)
            },
            Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = d_buttoncolor)
        ) {
            Text(text = "Dire(Enemy)")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(bottom = 5.dp)
        ){
            Image(
                painter = painterResource(id = image6),
                contentDescription = "imageview6",
                modifier = Modifier
                    .clickable {
                        viewModel.addtodcount(6)
                        dashviewModel.deletevshero(0)
                        image6 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFFFFB6C1))
            )

            Image(
                painter = painterResource(id = image7),
                contentDescription = "imageview7",
                modifier = Modifier
                    .clickable {
                        viewModel.addtodcount(7)
                        dashviewModel.deletevshero(1)
                        image7 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFFFFC0CB))
            )

            Image(
                painter = painterResource(id = image8),
                contentDescription = "imageview8",
                modifier = Modifier
                    .clickable {
                        viewModel.addtodcount(8)
                        dashviewModel.deletevshero(2)
                        image8 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFFFF0000))
            )

            Image(
                painter = painterResource(id = image9),
                contentDescription = "imageview9",
                modifier = Modifier
                    .clickable {
                        viewModel.addtodcount(9)
                        dashviewModel.deletevshero(3)
                        image9 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFF8B0000))
            )

            Image(
                painter = painterResource(id = image10),
                contentDescription = "imageview10",
                modifier = Modifier
                    .clickable {
                        viewModel.addtodcount(10)
                        dashviewModel.deletevshero(4)
                        image10 = R.drawable.blankimage
                    }
                    .weight(1f),
                //colorFilter = ColorFilter.tint(Color(0xFF800000))
            )
        }

        Text(
            text = "choose heroes below",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        //herocards
        LazyColumn(){
            items(List(Hero().getlength()){it}.chunked(5)){rowItems->
                LazyRow(){
                    items(rowItems){item ->
                        val bitmap_src = viewModel.getbitmapatposition(item)
                        Image(
                            painter = painterResource(id = bitmap_src),
                            contentDescription = "Image " + item.toString(),
                            modifier = Modifier
                                .fillParentMaxWidth(0.2f)
                                .clickable {
                                    viewModel.imagetapped(item)
                                }
                        )

                    }
                }
            }
        }
    }

}
