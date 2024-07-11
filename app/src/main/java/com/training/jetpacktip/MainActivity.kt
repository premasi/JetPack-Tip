package com.training.jetpacktip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.jetpacktip.components.InputTextField
import com.training.jetpacktip.ui.theme.JetPackTipTheme
import com.training.jetpacktip.utils.calculateTotalTip
import com.training.jetpacktip.utils.checkNull
import com.training.jetpacktip.utils.formatRupiah
import com.training.jetpacktip.widgets.RoundButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTipTheme {
                MyApp {

                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.white_2)
    ){
        Column {
//            TopHeader()
            MainContent()
        }

    }
}

@Preview
@Composable
fun TopHeader(totalBiaya: String = "1000"){

    val formatBiaya = formatRupiah(checkNull(totalBiaya))

    Surface (modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(all = 16.dp)
        .clip(shape = RoundedCornerShape(12.dp)),
        color = colorResource(R.color.beige)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Total Biaya", style = TextStyle(
                fontSize = 24.sp,
                color = colorResource(R.color.navy_blue),
                fontWeight = FontWeight.Bold
            ))
            Text("Rp. $formatBiaya", style = TextStyle(
                fontSize = 32.sp,
                color = colorResource(R.color.navy_blue),
                fontWeight = FontWeight.ExtraBold
            ))
        }

    }
}

@Composable
fun MainContent(){
    BillForm { total ->

    }

}

@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier, onValueChange: (String) -> Unit = {}){
    val totalState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalState.value) {
        totalState.value.trim().isNotEmpty()
    }

    val splitState = remember {
        mutableIntStateOf(1)
    }

    val sliderState = remember {
        mutableFloatStateOf(0f)
    }

    val sliderValue = (sliderState.value * 100).toInt()

    val keyboardController = LocalSoftwareKeyboardController.current

    val totalTipAmount = calculateTotalTip(totalState.value, sliderValue, splitState.value)

    TopHeader(
        totalState.value
    )
    Surface(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(R.color.beige))
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,

        ) {
            InputTextField(valueState = totalState,
                labelText = "Masukan jumlah uang",
                enabled = true,
                isSingleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                onAction = KeyboardActions{
                    if(!validState) return@KeyboardActions
                    onValueChange(totalState.value.trim())

                    keyboardController?.hide()
                }
            )
            if(validState){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text("Split", modifier = Modifier
                        .align(
                            alignment = Alignment.CenterVertically
                        ),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = colorResource(R.color.navy_blue)
                        )
                    )
                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.End
                    ){
                        RoundButton(
                            modifier = Modifier,
                            imageVector = Icons.Default.Clear,
                            onClick = {
                                if(splitState.intValue != 1){
                                    splitState.intValue-=1
                                }
                            }
                        )

                        Text(text = "${splitState.intValue}", modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = colorResource(R.color.navy_blue)

                            )
                        )

                        RoundButton(
                            modifier = Modifier,
                            imageVector = Icons.Default.Add,
                            onClick = {
                                splitState.intValue++
                            }
                        )

                    }

                }

                //tip row
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Text(text = "Tip", modifier = Modifier.align(
                        alignment = Alignment.CenterVertically
                    ), style = TextStyle(
                        fontSize = 16.sp, color = colorResource(R.color.navy_blue)
                    )
                    )

                    Text(text = "Rp. ${formatRupiah(totalTipAmount)}", modifier = Modifier.align(
                        alignment = Alignment.CenterVertically
                    ),style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.navy_blue),
                    ))


                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text("${sliderValue}%", style = TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(R.color.navy_blue)
                    )
                    )

                    Slider(
                        value = sliderState.value,
                        onValueChange={newValue ->
                            sliderState.value = newValue
                        },
                        steps = 10)
                }


            } else {
                Box {  }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackTipTheme {
        MyApp{

        }
    }
}