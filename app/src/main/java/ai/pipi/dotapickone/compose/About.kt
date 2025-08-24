package ai.pipi.dotapickone.compose

import ai.pipi.dotapickone.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity


@Composable
fun About(
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
                        //view.findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToVersionlogFragment())
                        navController.navigate("SCREEN_VERSION")
                    }
                )
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                //.fillMaxWidth()
            ){
                Text(text = "Version",fontSize = fontsize)
                Text(text = stringResource(R.string.app_version),fontSize = fontsize, color = Color.Gray)
            }

            Icon(painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24), contentDescription = null)

        }
        //end of block

        Divider(color = Color.LightGray, thickness = 4.dp)

        //block 2


        val email = stringResource(R.string.email)
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    enabled = true,
                    indication = null,
                    onClick = {
                        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val text = email
                        val clipData = ClipData.newPlainText("text", text)
                        clipboardManager.setPrimaryClip(clipData)
                        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_LONG).show()
                    }
                )
                    ,
        ) {

            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "Email",fontSize = fontsize)
                Text(text = stringResource(R.string.email),fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of block 2

        Divider(color = Color.LightGray, thickness = 4.dp)

        //block 3
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        val openURL = Intent(Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.stratz.com/")
                        context.startActivity(openURL)
                    }
                )
        ) {
            Column(
                Modifier
                    .padding(padding)
                //.fillMaxWidth()
            ){
                Text(text = "Powered by",fontSize = fontsize)
                Text(text = stringResource(id = R.string.about_acknowledgement),fontSize = fontsize, color = Color.Gray)
            }
        }
        //end of block 3

        Divider(color = Color.LightGray, thickness = 4.dp)

        //Block 4

        Row(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        //view.findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToVersionlogFragment())
                        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                    }
                )
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                //.fillMaxWidth()
            ){
                Text(text = "Open Source",fontSize = fontsize)
            }

            Icon(painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24), contentDescription = null)

        }
        //end of block 4

    }

}