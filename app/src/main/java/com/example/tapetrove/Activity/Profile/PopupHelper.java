package com.example.tapetrove.Activity.Profile;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Api.Genre;
import com.example.tapetrove.Database.Peminjaman;
import com.example.tapetrove.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PopupHelper {
  public static void showPopup(Context context, View anchorView, Peminjaman peminjaman) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.history_film_popup, null);
    TextView tvNamaFilmPopup = popupView.findViewById(R.id.tvNamaFilmPopup);
    TextView tvPaymentMethodPopup = popupView.findViewById(R.id.tvPaymentMethodPopup);
    TextView tvHargaSewaPopup = popupView.findViewById(R.id.tvHargaSewaPopup);
    TextView tvtanggalRentPopup = popupView.findViewById(R.id.tvtanggalRentPopup);
    TextView tvtanggalReturnPopup = popupView.findViewById(R.id.tvtanggalReturnPopup);
    TextView tvStatusPopup = popupView.findViewById(R.id.tvStatusPopup);
    Button btTtp = popupView.findViewById(R.id.btTtp);
    String baseUrl = "https://api.themoviedb.org/3/movie/";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOGU5ZjRjODFiNGVkNDJjMjA5NjM0NTYwMTY3ZTFhOSIsInN1YiI6IjY2MjEyM2I2M2Y0ODMzMDE4Njc0MTQyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WVRXwZs6rRB8Wht30hpF-UVxsdanmbzbal6XzhuNU7U";
    String url = baseUrl + peminjaman.getId_movie();
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer " + token)
            .build();
    new Thread(() -> {
      try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
          String res = response.body().string();
          JSONObject jsonObject = new JSONObject(res);
          String title = jsonObject.getString("title");
          ((Activity) context).runOnUiThread(() -> {
            tvNamaFilmPopup.setText(title);
          });
        }
      } catch (IOException | JSONException e) {
        e.printStackTrace();
      }
    }).start();

    // Mengisi teks dengan data yang sesuai
    tvPaymentMethodPopup.setText(peminjaman.getMetode_pembayaran());
    tvHargaSewaPopup.setText(String.valueOf(peminjaman.getHarga_sewa()));
    tvtanggalRentPopup.setText(peminjaman.getTanggal_sewa());
    tvtanggalReturnPopup.setText(peminjaman.getTenggat_kembali());
    tvStatusPopup.setText(peminjaman.getStatus_peminjaman());

    PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    popupWindow.setFocusable(true);

    btTtp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        popupWindow.dismiss(); // Menutup popup
      }
    });

    int[] location = new int[2];
    anchorView.getLocationOnScreen(location);
    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
  }
}
