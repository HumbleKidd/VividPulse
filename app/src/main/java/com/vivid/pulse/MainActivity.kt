package com.vivid.pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VividPulseTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun VividPulseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF00C9A7),
            secondary = Color(0xFFFF6B6B),
            tertiary = Color(0xFF4ECDC4),
            background = Color(0xFFF8F9FA),
            surface = Color.White
        ),
        content = content
    )
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") }, selected = true, onClick = { navController.navigate("home") })
                NavigationBarItem(icon = { Icon(Icons.Default.PlayArrow, null) }, label = { Text("Cinema") }, selected = false, onClick = { navController.navigate("cinema") })
                NavigationBarItem(icon = { Icon(Icons.Default.Lightbulb, null) }, label = { Text("Tools") }, selected = false, onClick = { navController.navigate("tools") })
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = "home", Modifier.padding(padding)) {
            composable("home") { HomeScreen() }
            composable("cinema") { CinemaScreen() }
            composable("tools") { ToolsScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    val gradient = Brush.verticalGradient(colors = listOf(Color(0xFF00C9A7), Color(0xFF4ECDC4), Color(0xFFFFE66D)))
    Box(modifier = Modifier.fillMaxSize().background(gradient), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("Vivid Pulse", fontSize = 42.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Text("Native Android • No Web • Pure Power", color = Color.White.copy(alpha = 0.9f), textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))
            Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Surprise!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00C9A7))
                    Spacer(Modifier.height(8.dp))
                    Text("This is a real native Kotlin + Jetpack Compose app built just for you. Install the APK, open it, and enjoy the vibrant experience. Perfect for Android phones, tablets, and your projectors.")
                    Spacer(Modifier.height(16.dp))
                    Text("Pure APK — no PWA, no web wrapper. Ready from GitHub Releases.", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun CinemaScreen() {
    val videos = listOf("Local Movie 1", "Projector Demo", "Jamaica Vibe Reel", "Business Pitch", "HY300 Showcase")
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Cinema Mode", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Ready for your Android projector", color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(videos) { title ->
                Card(onClick = {}, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F9FF))) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PlayCircle, null, tint = Color(0xFF00C9A7), modifier = Modifier.size(40.dp))
                        Spacer(Modifier.width(12.dp))
                        Text(title, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

@Composable
fun ToolsScreen() {
    var note by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Quick Tools", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.Lightbulb, null)
            Spacer(Modifier.width(8.dp))
            Text("Flashlight (Native)")
        }
        OutlinedTextField(value = note, onValueChange = { note = it }, label = { Text("Quick Note") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
        Text("Notes stay local — pure native, no cloud.", style = MaterialTheme.typography.bodySmall)
    }
}
