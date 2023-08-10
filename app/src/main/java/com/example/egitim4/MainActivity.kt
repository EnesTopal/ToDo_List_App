package com.example.egitim4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.egitim4.ui.theme.Egitim4Theme

object GlobalListManager {
    var conts = mutableStateListOf<String>()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Egitim4Theme {
            AddingRow()
                Clicker()
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingRow(){
    var cont by remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier= Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                modifier = Modifier.
                    fillMaxWidth(0.75f),
                value = cont,
                onValueChange = { text ->
                    cont = text
                })
            Button(
                modifier = Modifier.
                fillMaxWidth(0.9f),

                onClick = {
                    if (cont.isNotBlank()) {
                        GlobalListManager.conts.add(cont.lines().filter { it.isNotBlank() }.joinToString("\n"))
                        cont = ""
                    }
                }
            ) {
                Text(text = "Add")
            }

        }
        Spacer(modifier= Modifier.height(10.dp))

        LazyColumn(reverseLayout = true){
            items(GlobalListManager.conts){ currentcont->
                var buttonAttributeCounter by remember {
                    mutableStateOf(0)}
                var buttonColorChange by remember {
                    mutableStateOf(Color.Black) }
                Row(
                    modifier= Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = currentcont,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.56f)
                            .padding(5.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(modifier= Modifier
                            .widthIn(120.dp,150.dp),
                            colors = ButtonDefaults.outlinedButtonColors(buttonColorChange),
                            onClick = {
                                buttonAttributeCounter= (buttonAttributeCounter+1)%4
                                buttonColorChange = changeButtonColor(buttonAttributeCounter) }) {
                            Text(
                                text=buttonMessage(buttonAttributeCounter),
                                color = Color.White
                            )
                        }

                        TextButton(onClick = { GlobalListManager.conts.remove(currentcont) }) {
                            Text("X")
                        }
                    }
                }
                Divider()
            }
        }
    }
}


fun changeButtonColor(buttonAttributeCounter:Int):Color{
    if (buttonAttributeCounter==1){
        return Color.Blue
    }
    else if (buttonAttributeCounter==2){
        return Color.Green
    }
    else if (buttonAttributeCounter==3){
        return Color.Red
    }
    else{
        return Color.Black
    }
}

fun buttonMessage(buttonAttributeCounter:Int):String{
    if (buttonAttributeCounter==1){
        return "Running"
    }
    else if (buttonAttributeCounter==2){
        return "Completed"
    }
    else if (buttonAttributeCounter==3){
        return "Failed"
    }
    else{
        return "Inactive"
    }
}

@Preview
@Composable
fun AddingRowPreview(){
    Egitim4Theme {
        AddingRow()
    }
}

@Composable
fun Clicker(){
    var counter by remember{ mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = counter.toString(),
            fontSize = 30.sp
        )
        Button(onClick = {
            counter++ })
        {
            Text(text = "Click Me")
        }
    }
}




