package com.example.healthbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthbuddy.models.putPDF;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class medRecords extends AppCompatActivity {

    TextView textView;
    Button btn;
    StorageReference storageReference;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_records);

        textView = findViewById(R.id.text1);
        btn = findViewById(R.id.Uploadpdf);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Upload PDF");

        btn.setEnabled(false);

        textView.setOnClickListener(v -> selectPDF());
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "PDF File Select"), 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            btn.setEnabled(true);
            textView.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/") + 1));   // Name of the Uploaded File.

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UploadPDFfileFirebase(data.getData());
                }
            });
        }
    }

    private void UploadPDFfileFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File is loading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("Upload" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(taskSnapshot -> {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;
                    Uri uri = uriTask.getResult();

                    putPDF putPDF = new putPDF(textView.getText().toString(), uri.toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                    Toast.makeText(medRecords.this, "File is uploaded", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }).addOnProgressListener(snapshot -> {
            double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
            progressDialog.setMessage("File Uploaded..." + (int) progress + "%");

        });
    }

    public void retrievePDF(View view) {
        startActivity(new Intent(getApplicationContext(),RetrievePDF.class));
    }
}
