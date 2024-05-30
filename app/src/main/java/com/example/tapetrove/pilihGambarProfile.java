package com.example.tapetrove;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class pilihGambarProfile {
    private Context context;
    private ImageButton ibAvatar;
    private Uri imageUri;
    private String myUri = "";
    private StorageReference storageReference;

    public static final int REQUEST_CODE_GALLERY = 101;
    public static final int REQUEST_CODE_CAMERA = 102;

    public pilihGambarProfile(Context context, ImageButton ibAvatar) {
        this.context = context;
        this.ibAvatar = ibAvatar;
        storageReference = FirebaseStorage.getInstance().getReference("profile_images");
        setAvatar(getSavedImage(context));
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilih Gambar");
        builder.setItems(new String[]{"Galeri", "Kamera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openGallery();
                } else if (which == 1) {
                    openCamera();
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    public static Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
    }

    public void saveImage(Uri imageUri) {
        this.imageUri = imageUri;
        SharedPreferences sharedPreferences = context.getSharedPreferences("image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageUri.toString());
        editor.apply();

        uploadImageToFirebase();
    }

    private void uploadImageToFirebase() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadUrl = uri.toString();
                            saveImageUrlToDatabase(downloadUrl);
                            setAvatar(uri);
                        }
                    });
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveImageUrlToDatabase(String downloadUrl) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users").child(uid);
            userReference.child("profileImageUrl").setValue(downloadUrl);
        }
    }


    private void setAvatar(Uri imageUri) {
        if (imageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                ibAvatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Uri getSavedImage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("image", Context.MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("image", null);
        if (imageUriString != null) {
            return Uri.parse(imageUriString);
        } else {
            return null;
        }
    }
}