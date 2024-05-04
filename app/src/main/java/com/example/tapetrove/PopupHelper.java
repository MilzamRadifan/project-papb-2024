package com.example.tapetrove;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupHelper {
    public static void showPopup(Context context, View anchorView, Film film) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.film_popup, null);

        TextView tvPaymentMethodPopup = popupView.findViewById(R.id.tvPaymentMethodPopup);
        TextView tvHargaSewaPopup = popupView.findViewById(R.id.tvHargaSewaPopup);
        TextView tvtanggalRentPopup = popupView.findViewById(R.id.tvtanggalRentPopup);
        TextView tvtanggalReturnPopup = popupView.findViewById(R.id.tvtanggalReturnPopup);
        TextView tvStatusPopup = popupView.findViewById(R.id.tvStatusPopup);
        Button btTtp = popupView.findViewById(R.id.btTtp);

        tvPaymentMethodPopup.setText(film.paymentMethod);
        tvHargaSewaPopup.setText(film.hargaSewa);
        tvtanggalRentPopup.setText(film.tanggalRent);
        tvtanggalReturnPopup.setText(film.tanggalReturn);
        tvStatusPopup.setText(film.status);

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
