package com.example.codelandapp

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun ImageUploadScreen(navController: NavHostController){

    var uploadedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    var preview by remember {
        mutableStateOf(false)
    }
    val uploadPhoto = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(), onResult = { uri ->
        uri?.let {
            preview = false
            uploadedImage = uri
        }
    })
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Upload a Image", fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(30.dp))
        val stroke = Stroke(width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .height(550.dp)
                .drawBehind {
                    drawRoundRect(color = Color.Black, style = stroke)
                },
            contentAlignment = Alignment.Center
        ) {
            if (uploadedImage == null)
                Image(painter = painterResource(id = R.drawable.add_photo), contentDescription = "Upload Photo", alignment = Alignment.Center)
            else if (preview)
                AsyncImage(
                    model = uploadedImage,
                    contentDescription = "Uploaded Image",
                    contentScale = ContentScale.Fit
            )
            else
                Text(text = "Image Uploaded \nClick on Preview to see the Image", textAlign = TextAlign.Center)
        }
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    modifier = Modifier
                        .size(120.dp, 35.dp),
                    onClick = {
                              uploadPhoto.launch(
                                  PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                              )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange))
                ) {
                    Text(text = "Upload")
                }

                Button(
                    modifier = Modifier
                        .size(120.dp, 35.dp),
                    onClick = {
                              preview = true
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "Preview")
                }
            }
        }
    }
}