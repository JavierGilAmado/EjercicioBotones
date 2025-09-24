package com.example.actividadclickboton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividadclickboton.ui.theme.ActividadClickBotonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActividadClickBotonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Fondo(
                        modifier = Modifier. padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Fondo(modifier: Modifier = Modifier) {
    val fondo = painterResource(R.drawable.suelo)
    val img1 = painterResource(R.drawable.planta_bebe)
    val img2 = painterResource(R.drawable.planta_mediana)
    val img3 = painterResource(R.drawable.planta_con_flor)
    val img4 = painterResource(R.drawable.planta_flor_fruta)
    val img5 = painterResource(R.drawable.planta_madura)
    val imagenes = arrayOf(img1, img2, img3, img4, img5)

    var indice by remember { mutableStateOf(0) }
    var dinero by remember { mutableStateOf(0) }
    var costeNivel by remember { mutableStateOf(10) }
    var nivel by remember { mutableStateOf(1) }


    Box(modifier = modifier) {
        // Fondo
        Image(
            painter = fondo,
            contentDescription = "Tierra de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Imagen de planta
        Image(
            painter = imagenes[indice],
            contentDescription = "Planta",
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
                .padding(10.dp, 10.dp, 10.dp, 120.dp),
            contentScale = ContentScale.Crop
        )

        // Contenedor de los dos botones en la parte inferior
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        ) {
            // Botón Regar
            Button(
                onClick = {
                    indice = (indice + 1) % imagenes.size
                    if (indice == 0) {
                        dinero += 5
                    }
                },
                modifier = Modifier
                    .weight(1f) // ocupa la mitad del ancho
                    .height(80.dp)
                    .padding(end = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.Black
                )
            ) {
                Text("Regar")
            }

            // Segundo botón
            Button(
                onClick = { /* Acción del segundo botón */ },
                modifier = Modifier
                    .weight(1f) // ocupa la mitad del ancho
                    .height(80.dp)
                    .padding(start = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                )
            ) {
                Text("Subir nivel ($costeNivel dinero)")
            }
        }

        // Texto de dinero
        Text(
            text = "DINERO : $dinero",
            modifier = Modifier.offset(x = 250.dp, y = 20.dp),
            fontSize = 20.sp
        )
        Text(
            text = "Nivel : $nivel",
            modifier = Modifier.offset(x = 20.dp, y = 20.dp),
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActividadClickBotonTheme {
        Fondo()
    }
}