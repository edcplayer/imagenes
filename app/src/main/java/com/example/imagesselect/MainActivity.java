package com.example.imagesselect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button btnSelectImage, btnSendWhatsApp, btnSendEmail;
    private ImageView imgPreview;
    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSendWhatsApp = findViewById(R.id.btnSendWhatsApp);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        imgPreview = findViewById(R.id.imgPreview);


        btnSelectImage.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);


            }



        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);
        }
        btnSendWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgPreview.getDrawable() == null) {
                    Toast.makeText(getApplicationContext(), "Seleccione una imagen primero", Toast.LENGTH_SHORT).show();
                } else {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("image/*");
                    sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }
            }
        });
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgPreview.getDrawable() == null) {
                    Toast.makeText(getApplicationContext(), "Seleccione una imagen primero", Toast.LENGTH_SHORT).show();
                } else {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("image/*");
                    sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    startActivity(Intent.createChooser(sendIntent, "Enviar correo electrónico"));
                }
            }
        });

    }




}