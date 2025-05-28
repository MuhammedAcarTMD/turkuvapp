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
                    2- aynı dosya içerisinde isMinifyEnabled false olmalı
                    3- yine aynı dosyada en alta aşağıdaki kodları ekliyoruz
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
                    4- modülün localde yayınlanması için gradle tarafında publishToMavenLocal komutunu çalıştırıyoruz (userPath/.m2/repository klasörüne .jar ve .aar dosyalarını export eder)
                    5- Kütüphaneyi localde test edeceğimiz projenin settings.gradle dosyasında dependencyResolutionManagement altında repositories kısmına mavenLocal() kodunu ekliyoruz
                    6- Test edeceğimiz projenin app seviyesindeki build.gradle dosyasına implementation("org.turkuvaz:turkuvapp:1.0.1") ekleyip kütüphaneyi çağırabiliyoruz

                Jitpack (yukarıdaki işlemlerden sadece 2. ve 3. madde gerekli)
                    1- Kütüphane modülünün oluşturulduğu projede jitpack.yml dosyasını oluşturuyoruz. Dosya içeriği aşağıdaki gibi olmalı
                        jdk:
                            - openjdk11
                    2- Proje artifactId ile aynı isme sahip olan repo'ya push edilmeli
                    3- Github sayfasında sağdaki Releases'a tıklayarak yeni tag ile birlikte release oluşturuyoruz
                    4- jitpack.io websitesine github hesabıyla login oluyoruz (diğer sekmede github açık ise direkt plugin(access) kurulum izni istiyor)
                    5- Sitenin sol kısmındaki Repositories altında modülü seçip get it butonuna basarak birkaç dakika analiz etmesini bekliyoruz. Analiz sonucunda log sütununa yeşil icon gelirse sorun yok/hazır anlamındadır
                    6- Test edeceğimiz projenin settings.gradle dosyasında dependencyResolutionManagement altında repositories kısmına 'maven { url = uri("https://jitpack.io") }' kodunu ekliyoruz
                    7- Yine test projesinde app seviyesindeki build.gradle dosyasına analiz sonucu jitpack sitesinin verdiği implementation'ı ekliyoruz ve artık kütüphaneyi çağırabiliyoruz
                */


            }
        }
    }
}