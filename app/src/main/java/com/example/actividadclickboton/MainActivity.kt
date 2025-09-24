package com.example.actividadclickboton

import android.media.MediaPlayer
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividadclickboton.ui.theme.ActividadClickBotonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicaDeFondo() // Llamada fun musica
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

        // Imagen de principal
        Image(
            painter = imagenes[indice],
            contentDescription = "Planta",
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
                .padding(10.dp, 10.dp, 10.dp, 120.dp),
            contentScale = ContentScale.Crop
        )

        // Fila con los dos botones
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
                    .weight(1f)
                    .height(80.dp)
                    .padding(end = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Regar",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 30.sp
                    )
            }

            // Botón Subir Nivel
            Button(
                onClick = {
                    if (dinero >= costeNivel) { // Si hay suficiente dinero
                        dinero -= costeNivel   // Se descuenta el dinero
                        nivel++                // Se sube un nivel
                        costeNivel += 10       // Se incrementa el coste para el siguiente nivel
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .padding(start = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Subir nivel ($costeNivel dinero)",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 21.sp,
                    modifier = Modifier.align(Alignment.CenterVertically) // o TopEnd, según quieras
                )
            }
        }

        // Texto de dinero y nivel
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


@Composable
fun MusicaDeFondo() {
    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(context, R.raw.green_wizard_gnome_song)

    DisposableEffect(Unit) {
        mediaPlayer.isLooping = true // Para que la música se repita
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActividadClickBotonTheme {
        Fondo()
    }
}