package ai.pipi.dotapickone.ui.dashboard

import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.room.Heroname
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun ScreenDash(
    viewModel: MainViewModel = viewModel(),
    dashviewModel: DashboardViewModel = viewModel(),
    navController: NavHostController
) {

    val heronames by dashviewModel.heroname_live.observeAsState()
    val sortinfo by dashviewModel.sortInfo.observeAsState()
    var list_removechosen by remember {mutableStateOf(listOf<Heroname>())}
    var sortcolumn by remember{mutableStateOf(SortInfo(sortColumn = SortColumn_value.Winrate,ascending = false))}

    if(heronames != null){
        Log.d("ScreenDash","enter submitlist")
        //Log.d(javaClass.simpleName,it.toString())
        val list_removechosen_temp = mutableListOf<Heroname>()
        for(heroname in heronames!!){
            if(!dashviewModel.checkchosen(heroname.stratzId)){
                list_removechosen_temp.add(heroname)
            }
        }
        //adapter.submitList(list_removechosen)
        list_removechosen = list_removechosen_temp
        Log.d("ScreenDash","scroll to top")
    }

    if(sortinfo != null){
        Log.d("ScreenDash","enter observe sort")
        sortcolumn = sortinfo as SortInfo
    }

    Column(
        Modifier
            .fillMaxHeight()
            .background(color = Color.White)
    ){
        val listState = rememberLazyListState()
        // Remember a CoroutineScope to be able to launch
        val coroutineScope = rememberCoroutineScope()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = "Hero",
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        Log.d(javaClass.simpleName, "heroname_clicked")
                        dashviewModel.sortInfoClick(SortColumn_value.Heroname)

                        //init_heroname_observer()
                        Log.d("ScreenDash","enter submitlist")
                        //Log.d(javaClass.simpleName,it.toString())
                        val list_removechosen_temp = mutableListOf<Heroname>()
                        for(heroname in heronames!!){
                            if(!dashviewModel.checkchosen(heroname.stratzId)){
                                list_removechosen_temp.add(heroname)
                            }
                        }
                        //adapter.submitList(list_removechosen)
                        list_removechosen = list_removechosen_temp
                        Log.d("ScreenDash","scroll to top")

                        coroutineScope.launch {
                            // Animate scroll to the 10th item
                            listState.animateScrollToItem(index = 0)
                        }
                    }
            ){
                Text(
                    text = "Name",
                    textAlign = TextAlign.Start,
                )
                if(sortcolumn.sortColumn == SortColumn_value.Heroname){
                    if(sortcolumn.ascending == true){
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscName")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
                            contentDescription = "name_arrow_up",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                    else{
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescName")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                            contentDescription = "name_arrow_down",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        Log.d(javaClass.simpleName, "winrate_clicked")
                        dashviewModel.sortInfoClick(SortColumn_value.Winrate)

                        //init_heroname_observer()
                        Log.d("ScreenDash","enter submitlist")
                        //Log.d(javaClass.simpleName,it.toString())
                        val list_removechosen_temp = mutableListOf<Heroname>()
                        for(heroname in heronames!!){
                            if(!dashviewModel.checkchosen(heroname.stratzId)){
                                list_removechosen_temp.add(heroname)
                            }
                        }
                        //adapter.submitList(list_removechosen)
                        list_removechosen = list_removechosen_temp
                        Log.d("ScreenDash","scroll to top")

                        coroutineScope.launch {
                            // Animate scroll to the 10th item
                            listState.animateScrollToItem(index = 0)
                        }
                    }
            ) {
                Text(
                    text = "Winrate",
                    textAlign = TextAlign.Start,
                )
                if(sortcolumn.sortColumn == SortColumn_value.Winrate){
                    if(sortcolumn.ascending == true){
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscWin")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
                            contentDescription = "winrate_arrow_up",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                    else{
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescWin")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                            contentDescription = "winrate_arrow_down",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        Log.d(javaClass.simpleName, "rate_clicked")
                        dashviewModel.sortInfoClick(SortColumn_value.Rate)

                        //init_heroname_observer()
                        Log.d("ScreenDash","enter submitlist")
                        //Log.d(javaClass.simpleName,it.toString())
                        val list_removechosen_temp = mutableListOf<Heroname>()
                        for(heroname in heronames!!){
                            if(!dashviewModel.checkchosen(heroname.stratzId)){
                                list_removechosen_temp.add(heroname)
                            }
                        }
                        //adapter.submitList(list_removechosen)
                        list_removechosen = list_removechosen_temp
                        Log.d("ScreenDash","scroll to top")

                        coroutineScope.launch {
                            // Animate scroll to the 10th item
                            listState.animateScrollToItem(index = 0)
                        }
                    }
            ) {
                Text(
                    text = "Rate",
                    textAlign = TextAlign.Start,
                )

                if(sortcolumn.sortColumn == SortColumn_value.Rate){
                    if(sortcolumn.ascending == true){
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscRate")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
                            contentDescription = "rate_arrow_up",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                    else{
                        Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescRate")
                        Image(
                            painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                            contentDescription = "rate_arrow_down",
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                }
            }
        }

        //heroes
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(8.dp)
        ){
            items(list_removechosen){ item->
                Row(
                    horizontalArrangement = Arrangement.Start
                ){
                    val position = dashviewModel.transferSIDtoAlpha(item.stratzId)
                    val bitmap_src = position?.let { viewModel.getbitmapatposition(it) }
                    bitmap_src?.let { painterResource(id = it) }?.let {
                        Box(modifier = Modifier
                            .weight(1f)
                        ){
                            Image(
                                painter = it,
                                contentDescription = "Image " + item.toString(),
                                modifier = Modifier
                                    .height(40.dp)
                            )
                        }

                    }
                    item.displayName?.let {
                        Text(
                            text = it,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    val displaywinrate = String.format("%.2f", item.predicted_winrate * 100.0) + "%"
                    var norm_displaywinrate = (item.predicted_winrate-0.3f)/0.4f
                    norm_displaywinrate = norm_displaywinrate.coerceIn(0f,1f)
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp)
                    ) {
                        Text(
                            text = displaywinrate
                        )
                        if(item.predicted_winrate > 0.5f){
                            Box(){
                                LinearProgressIndicator(
                                    progress = norm_displaywinrate, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier.fillMaxWidth(), // Customize the color if needed
                                    color = Color.Green,
                                    backgroundColor = Color.LightGray
                                )
                                LinearProgressIndicator(
                                    progress = 0.5f, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier
                                        .fillMaxWidth(), // Customize the color if needed
                                    color = Color.LightGray

                                )
                            }
                        }
                        else{
                            Box(){
                                LinearProgressIndicator(
                                    progress = 0.5f, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier
                                        .fillMaxWidth(), // Customize the color if needed
                                    color = Color.Red,
                                    backgroundColor = Color.LightGray
                                )
                                LinearProgressIndicator(
                                    progress = norm_displaywinrate, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier.fillMaxWidth(), // Customize the color if needed
                                    color = Color.LightGray
                                )
                            }
                        }

                    }

                    val displayrate = String.format("%.2f", item.withvs_index * 100.0) + "%"
                    var norm_displayrate = (item.withvs_index+0.4f)/0.8f
                    norm_displayrate = norm_displayrate.coerceIn(0f,1f)
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp)
                    ) {
                        Text(
                            text = displayrate
                        )
                        if(item.withvs_index > 0f){
                            Box(){
                                LinearProgressIndicator(
                                    progress = norm_displayrate, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier.fillMaxWidth(), // Customize the color if needed
                                    color = Color.Green,
                                    backgroundColor = Color.LightGray
                                )
                                LinearProgressIndicator(
                                    progress = 0.5f, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier
                                        .fillMaxWidth(), // Customize the color if needed
                                    color = Color.LightGray

                                )
                            }
                        }
                        else{
                            Box(){
                                LinearProgressIndicator(
                                    progress = 0.5f, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier
                                        .fillMaxWidth(), // Customize the color if needed
                                    color = Color.Red,
                                    backgroundColor = Color.LightGray
                                )
                                LinearProgressIndicator(
                                    progress = norm_displayrate, // Set the progress value between 0.0f and 1.0f
                                    modifier = Modifier.fillMaxWidth(), // Customize the color if needed
                                    color = Color.LightGray
                                )
                            }
                        }

                    }

                }
                Divider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}