package ai.pipi.dotapickone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Versionlog(
    navController: NavHostController
){
    val padding = 16.dp
    val fontsize = 15.sp
    Column(
        Modifier
            .fillMaxHeight()
            .background(color = Color.LightGray)
    ) {

        //version block
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "v95.3.0",fontSize = fontsize)
                Text(text = "01/12/2025",fontSize = fontsize)
                Text(text = "Bug Fix",fontSize = fontsize)
                Text(text = "fix crash, add Kez and Ringmaster",fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of version block

        Divider(color = Color.LightGray, thickness = 4.dp)

        //version block
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "v95.2.2",fontSize = fontsize)
                Text(text = "02/12/2024",fontSize = fontsize)
                Text(text = "Bug Fix",fontSize = fontsize)
                Text(text = "fix crash, update API key",fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of version block

        Divider(color = Color.LightGray, thickness = 4.dp)

        //version block
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "v95.2.1",fontSize = fontsize)
                Text(text = "08/11/2023",fontSize = fontsize)
                Text(text = "Feature",fontSize = fontsize)
                Text(text = "optimized UI structure",fontSize = fontsize, color = Color.Gray)
                Text(text = "add open source information",fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of version block

        Divider(color = Color.LightGray, thickness = 4.dp)

        //version block
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "v95.1.2",fontSize = fontsize)
                Text(text = "01/02/2023",fontSize = fontsize)
                Text(text = "Bug Fix",fontSize = fontsize)
                Text(text = "fixed crash issue caused by failing to load images",fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of version block

        Divider(color = Color.LightGray, thickness = 4.dp)

        //version block
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "v95.1.1",fontSize = fontsize)
                Text(text = "12/25/2022",fontSize = fontsize)
                Text(text = "Feature",fontSize = fontsize)
                Text(text = "first version",fontSize = fontsize)
                Text(text = "home tab selects teammates and opponent heroes",fontSize = fontsize, color = Color.Gray)
                Text(text = "winrate tab displays heroes to be chosen",fontSize = fontsize, color = Color.Gray)
                Text(text = "settings tab includes version, contact",fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of version block
    }

}
