package org.turkuvaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.turkuvaz.ui.theme.TMDLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDLibraryTheme {
                /*
                MavenLocal
                    1- turkuvapp build.gradle içinde plugins kısmına id("maven-publish") ekliyoruz
                    2- yine aynı dosyada en alta aşağıdaki kodları ekliyoruz
                        afterEvaluate {
                            android.libraryVariants.forEach { variant ->
                                publishing.publications.create(variant.name, MavenPublication::class.java){
                                    from(components.findByName(variant.name))
                                    groupId = "org.turkuvaz"
                                    artifactId = "turkuvapp"
                                    version = "1.0.0"
                                }
                            }
                        }
                    3- modülün localde yayınlanması için gradle tarafında publishToMavenLocal komutunu çalıştırıyoruz
                    4- Kütüphaneyi localde test edeceğimiz projenin settings.gradle dosyasında dependencyResolutionManagement altında repositories kısmına mavenLocal() kodunu ekliyoruz
                    5- Test edeceğimiz projenin app seviyesindeki build.gradle dosyasına implementation("org.turkuvaz:turkuvapp:1.0.1") ekleyip kütüphaneyi çağırabiliyoruz

                Jitpack (yukarıdaki işlemlerden sadece 2. madde gerekli)
                    1- Kütüphane modülünün oluşturulduğu projede jitpack.yml dosyasını oluşturuyoruz. Dosya içeriği aşağıdaki gibi olmalı
                        jdk:
                            - openjdk11
                    2- Proje github'a push edilmeli ve repo ismi ile artifactId aynı olmalı.
                    3-
                */
            }
        }
    }
}