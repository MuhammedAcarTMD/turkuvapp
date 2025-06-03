package org.turkuvaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

                Jitpack (yukarıdaki işlemlerden sadece 2. madde gerekli)
                    1- Kütüphane modülünün oluşturulduğu projede jitpack.yml dosyasını oluşturuyoruz. Dosya içeriği aşağıdaki gibi olmalı
                        jdk:
                            - openjdk11
                    2- Proje artifactId ile aynı isme sahip olan repo'ya push edilmeli
                    3- Github sayfasında sağdaki Releases'a tıklayarak yeni tag ile birlikte release oluşturuyoruz
                    4- jitpack.io websitesine github hesabıyla login oluyoruz (diğer sekmede github açık ise direkt plugin(access) kurulum izni istiyor)
                    5- Sitenin sol kısmındaki Repositories altında modülü seçip get it butonuna basarak birkaç dakika analiz etmesini bekliyoruz. Analiz sonucunda log sütununa yeşil icon gelirse sorun yok/hazır anlamındadır
                    6- Test edeceğimiz projenin settings.gradle dosyasında dependencyResolutionManagement altında repositories kısmına 'maven { url = uri("https://jitpack.io") }' kodunu ekliyoruz
                    7- Yine test projesinde app seviyesindeki build.gradle dosyasına analiz sonucu jitpack sitesinin verdiği implementation'ı ekliyoruz ve artık kütüphaneyi çağırabiliyoruz



                Maven Central
                    1- https://central.sonatype.com sitesine github hesabı ile login olunur, bu bize direkt onaylanmış io.github.user_name namespace'ini verir
                    2- Key işlemleri için kullanacağımız gpg uygulamasını bilgisayara https://gnupg.org/download/ adresinden indirip kurmamız gerekiyor
                    3- Gpg kurulumu bittikten sonra "gpg --gen-key" komutunu terminalde çalıştırıyoruz
                    4- Bu komut bizden full_name, email ve password(kaybedilmemeli) isteyecek ve sonunda bize fingerprint(sha gibi) verecek
                    5- Fingerprint'in son 8 hanesi Key ID olarak kullanılacak (key id ve password kaybedilmemeli)
                    6- Oluşturulan fingerprint'i sunucuya kaydetmek için "gpg --keyserver keyserver.ubuntu.com --send-keys [fingerprint]" komutunu çalıştırıyoruz
                    7- Key dosyası "gpg --export-secret-keys -o ~/.gnupg/key.kbx" komutu ile export ediyoruz
                    8- Modülün build.gradle dosyasında plugins kısmına 'id("com.vanniktech.maven.publish") version "0.29.0"' kodunu ekliyoruz
                    9- gradle.properties dosyasına aşağıdaki kodları eklemeliyiz ve ayrıca gradle.properties dosyası .gitignore'a eklenmeli çünkü içindeki bilgiler git repo'da olmamalı
                        SONATYPE_HOST=CENTRAL_PORTAL
                        RELEASE_SIGNING_ENABLED=true

                        GROUP=io.github.your_github_account
                        POM_ARTIFACT_ID=library_name
                        VERSION_NAME=X.X.X

                        POM_NAME=library_name
                        POM_DESCRIPTION=This library provides ....
                        POM_URL=Your github repo url

                        POM_LICENSE_NAME=The Apache Software License, Version 2.0
                        POM_LICENSE_URL=https://www.apache.org/licenses/LICENSE-2.0.txt
                        POM_LICENSE_DIST=repo

                        POM_SCM_URL=Your github repo url
                        POM_SCM_CONNECTION=scm:git:git://github.com/YOUR_GIT_USER/REPO_NAME.git
                        POM_SCM_DEV_CONNECTION=scm:git:ssh://git@github.com/YOUR_GIT_USER/REPO_NAME.git

                        POM_DEVELOPER_ID=YOUR_GIT_USER
                        POM_DEVELOPER_NAME=Your Name
                        POM_DEVELOPER_URL=https://github.com/YOUR_GIT_USER/
                        POM_DEVELOPER_EMAIL=your email

                        mavenCentralUsername=YOUR_MAVEN_CENTRAL_USERNAME
                        mavenCentralPassword=YOUR_MAVEN_CENTRAL_PASSWORD

                        signing.keyId=YOUR_SIGNING_KEY_ID
                        signing.password=YOUR_SIGNING_PASSWORD
                        signing.secretKeyRingFile=PATH/TO/YOUR/KEY_FILE

                    10- MavenLocal'a publish alıp kontrol edilir(sadece test amaçlı)
                    11- MavenCentral'a publish için "./gradlew :turkuvapp:assemble" komutunu çalıştırıp eğer sorun yoksa sırasıyla "./gradlew :turkuvapp:signReleasePublication" ve "./gradlew :turkuvapp:publishMavenPublicationToMavenCentralRepository" komutlarını çalıştırıyoruz.
                    12- Artık maven central panelinde modülümüzü görebilir ve publish butonu ile yayına alabiliriz

                */

                /*
                NOTES
                - key güvenliği nasıl sağlanır
                */
            }
        }
    }
}