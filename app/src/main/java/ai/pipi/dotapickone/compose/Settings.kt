package ai.pipi.dotapickone.compose

import ai.pipi.dotapickone.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Settings(
    navController: NavHostController
){
    val padding = 16.dp
    val fontsize = 20.sp

    Column(
        Modifier
            .fillMaxHeight()
            .background(color = Color.LightGray)
    ) {
        val context = LocalContext.current
        val view = LocalView.current
        //block
        Row(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        //view.findNavController().navigate(NotificationsFragmentDirections.actionNavigationNotificationsToAboutFragment())
                        navController.navigate("SCREEN_ABOUT")
                    }
                )
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Icon(painterResource(id = R.drawable.ic_baseline_info_24), contentDescription = null,Modifier.padding(5.dp))

            Text(text = "about",fontSize = fontsize)


            Spacer(Modifier.weight(1f))

            Icon(painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24), contentDescription = null)

        }
        //end of block

        //Divider(color = Color.LightGray, thickness = 4.dp)
    }
}
