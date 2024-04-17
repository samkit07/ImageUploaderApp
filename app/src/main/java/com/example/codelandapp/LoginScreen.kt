package com.example.codelandapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController){
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val enableBtn = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(128.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.codeland),
                contentDescription = "codeland"
            )

            Image(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 40.dp),
                painter = painterResource(id = R.drawable.codelandtree),
                contentDescription = "codelandtree"
            )
            Text(text = "Biomedical Kiosk", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Text(modifier = Modifier
            .align(Alignment.End)
            .padding(end = 100.dp), text = "for HCE's", fontSize = 10.sp, color = colorResource(id = R.color.orange))

        Spacer(modifier = Modifier.height(26.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username.value, onValueChange = {
                                        username.value = it
            },
            label = { Text(text = "Username") },
            visualTransformation = VisualTransformation.None,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value, onValueChange = {
                                        password.value = it
            },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = username.value.isNotEmpty() && password.value.isNotEmpty(),
            onClick = { navController.navigate("Upload") },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange))
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier.wrapContentHeight()) {
            Text(text = "For Assistance & Login Details Contact: ", style = MaterialTheme.typography.bodySmall)
            Text(text = "English, Kannada & Telugu :  7406333800", style = MaterialTheme.typography.bodySmall)
            Text(text = "English, Kannada & Hindi    :  9071386515", style = MaterialTheme.typography.bodySmall)
        }
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "v1.7 @ 2023 Codeland Infosolutions Pvt Ltd.",style = MaterialTheme.typography.bodyMedium)
        }
    }
}

//@Preview
//@Composable
//fun LoginPreview(){
//    LoginScreen()
//}