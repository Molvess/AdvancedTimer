package com.molvessinc.advancedtimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private long baslangicZamani = 0L;
    private long gecenZaman = 0L;
    private boolean calisiyorMu = false;
    private String gosterimModu = "SANIYE";

    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView tvSayac;
    private AppCompatButton btnBaslat;
    private AppCompatButton btnDurdur;

    // Kronometre motoru
    private Runnable kronometreGorevi = new Runnable() {
        @Override
        public void run() {
            if (calisiyorMu) {
                long suAn = SystemClock.uptimeMillis();
                long toplamGecenSure = gecenZaman + (suAn - baslangicZamani);
                tvSayac.setText(zamaniFormatla(toplamGecenSure));

                handler.postDelayed(this, 10);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navView = findViewById(R.id.navView);
        TextView btnMenu = findViewById(R.id.btnMenu);

        tvSayac = findViewById(R.id.tvSayac);
        btnBaslat = findViewById(R.id.btnBaslat);
        btnDurdur = findViewById(R.id.btnDurdur);

        tvSayac.setText(zamaniFormatla(0L));
        butonMetinleriniGuncelle();

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        btnBaslat.setOnClickListener(v -> {
            if (!calisiyorMu) {
                baslangicZamani = SystemClock.uptimeMillis();
                calisiyorMu = true;
                handler.post(kronometreGorevi);
                butonMetinleriniGuncelle();
            }
        });

        btnDurdur.setOnClickListener(v -> {
            if (calisiyorMu) {
                gecenZaman += SystemClock.uptimeMillis() - baslangicZamani;
                calisiyorMu = false;
                handler.removeCallbacks(kronometreGorevi);
                butonMetinleriniGuncelle();
            } else {
                gecenZaman = 0L;
                tvSayac.setText(zamaniFormatla(0L));
                butonMetinleriniGuncelle();
            }
        });

        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_salise) gosterimModu = "SALISE";
            else if (itemId == R.id.nav_saniye) gosterimModu = "SANIYE";
            else if (itemId == R.id.nav_dakika) gosterimModu = "DAKIKA";
            else if (itemId == R.id.nav_saat) gosterimModu = "SAAT";
            else if (itemId == R.id.nav_gun) gosterimModu = "GUN";
            else gosterimModu = "SANIYE";

            long anlikZaman = calisiyorMu ? gecenZaman + (SystemClock.uptimeMillis() - baslangicZamani) : gecenZaman;
            tvSayac.setText(zamaniFormatla(anlikZaman));

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void butonMetinleriniGuncelle() {
        if (calisiyorMu) {
            btnBaslat.setText("ÇALIŞIYOR...");
            btnBaslat.setEnabled(false);
            btnDurdur.setText("DURDUR");
        } else {
            btnBaslat.setEnabled(true);
            if (gecenZaman > 0) {
                btnBaslat.setText("DEVAM ET");
                btnDurdur.setText("SIFIRLA");
            } else {
                btnBaslat.setText("BAŞLAT");
                btnDurdur.setText("SIFIRLA");
            }
        }
    }

    private String zamaniFormatla(long milisaniye) {
        int saniye = (int) (milisaniye / 1000);
        int dakika = saniye / 60;
        int saat = dakika / 60;
        int gun = saat / 24;

        long salise = (milisaniye % 1000) / 10;
        int gSaniye = saniye % 60;
        int gDakika = dakika % 60;
        int gSaat = saat % 24;

        switch (gosterimModu) {
            case "SALISE":
                return String.format("%02d:%02d:%02d.%02d", gSaat, gDakika, gSaniye, salise);
            case "SANIYE":
                return String.format("%02d:%02d:%02d", gSaat, gDakika, gSaniye);
            case "DAKIKA":
                return String.format("%02d:%02d", gSaat, gDakika);
            case "SAAT":
                return String.format("%02d:00", gSaat);
            case "GUN":
                return String.format("%d Gün %02d Sa", gun, gSaat);
            default:
                return "00:00:00";
        }
    }
}