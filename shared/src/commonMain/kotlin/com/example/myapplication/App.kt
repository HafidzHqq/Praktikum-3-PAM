package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import myapplication.shared.generated.resources.Res
import myapplication.shared.generated.resources.profile_picture

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen()
        }
    }
}

/**
 * Komponen layar utama profil.
 */
@Composable
fun ProfileScreen() {
    var showBio by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .safeContentPadding()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader(
            name = "Hafidz Haqiqi",
            major = "Teknik Informatika",
            studentId = "124140016"
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        ProfileContactCard()
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Komponen Button (Kriteria: UI Components) dengan Kombinasi Warna Ocean
        Button(
            onClick = { showBio = !showBio },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF0288D1), // Biru Laut (Ocean Blue)
                                Color(0xFF00897B)  // Biru Kehijauan (Teal/Aqua)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Komponen Animasi (Kriteria Bonus: AnimatedVisibility)
        AnimatedVisibility(visible = showBio) {
            BioSection()
        }
    }
}

/**
 * Komponen header untuk foto profil, nama, dan program studi.
 */
@Composable
fun ProfileHeader(name: String, major: String, studentId: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Komponen Box (Kriteria: Layout Implementation)
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            // Komponen Image (Kriteria: UI Components)
            Image(
                painter = painterResource(Res.drawable.profile_picture),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp) // Memberikan jarak antara border dan foto
                    .clip(CircleShape)
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "$major • $studentId",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Komponen card untuk menampilkan informasi kontak secara berkelompok.
 */
@Composable
fun ProfileContactCard() {
    // Komponen Card (Kriteria: UI Components)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ContactRow(icon = Icons.Default.Email, text = "haqiqihf@gmail.com")
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            ContactRow(icon = Icons.Default.Phone, text = "085840409283")
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            ContactRow(icon = Icons.Default.LocationOn, text = "Lampung, Indonesia")
        }
    }
}

/**
 * Komponen baris tunggal untuk menampilkan satu item kontak beserta ikon.
 */
@Composable
fun ContactRow(icon: ImageVector, text: String) {
    // Komponen Row (Kriteria: Layout Implementation)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Komponen section bio yang muncul dengan animasi.
 */
@Composable
fun BioSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Saya adalah mahasiswa Teknik Informatika Institut Teknologi Sumatera (ITERA) angkatan 2024. Saya memiliki ketertarikan yang besar di bidang Rekayasa Perangkat Lunak (Software Engineering) dan Kecerdasan Buatan (Artificial Intelligence).",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Justify,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
            )
        }
    }
}
