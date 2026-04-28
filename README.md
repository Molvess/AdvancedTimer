# ⏱️ Minimalist Kronometre (Advanced Timer)

Android için geliştirilmiş, temiz arayüze ve yüksek hassasiyete sahip minimalist bir kronometre uygulaması. Göz yormayan tasarımı ve pratik kullanımıyla zamanı en doğru şekilde takip etmenizi sağlar.

## ✨ Özellikler

* **Minimalist Tasarım:** Ekranda sadece ihtiyacınız olanlar var. Karmaşık butonlar, göz yoran çerçeveler ve gereksiz detaylar yok. *Action Bar* (Tepe Çubuğu) gizlenerek tam ekran ferahlığı sağlandı.
* **Kusursuz Zamanlama:** Arka planda `SystemClock.uptimeMillis()` kullanılarak cihazın donanımsal saati baz alınır. Cihaz kassa bile zaman asla şaşmaz.
* **Dinamik Gösterim (Hamburger Menü):** Sol menüden (☰) ihtiyacınıza göre zaman hassasiyetini anlık olarak seçebilirsiniz:
  * Salise `(00:00:00.00)`
  * Saniye `(00:00:00)`
  * Dakika `(00:00)`
  * Saat `(00:00)`
  * Gün `(X Gün XX Sa)`
* **Akıllı Buton Sistemi:** "Az ama öz" felsefesiyle sadece 2 buton kullanılmıştır. Butonlar duruma göre *BAŞLAT*, *DURDUR*, *DEVAM ET* ve *SIFIRLA* olarak akıllıca şekil alır.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java
* **Arayüz:** XML (Yuvarlatılmış köşeler ve Material Design bileşenleri)
* **Zamanlayıcı Motoru:** Handler & Looper
* **Geliştirme Ortamı:** Android Studio

## 🚀 Kurulum ve Çalıştırma

### Geliştiriciler İçin:
1. Bu repoyu bilgisayarınıza klonlayın:
   ```bash
   git clone [https://github.com/Molvess/AdvancedTimer.git](https://github.com/Molvess/AdvancedTimer.git)
