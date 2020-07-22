package com.example.emergency;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.box.androidsdk.content.BoxApiFile;
import com.box.androidsdk.content.BoxConfig;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxFile;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequestsFile;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cameraScan extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_scan);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mImageView = (ImageView) findViewById(R.id.result);
        Log.i("error","test");

    }

    public void onClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("error", "IOException");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                         this.getApplicationContext().getPackageName() + ".provider",
                       photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(new File(mCurrentPhotoPath)));
                mImageView.setImageBitmap(mImageBitmap);
                mImageBitmap = getResizedBitmap(mImageBitmap, 500);

                File path = this.getFilesDir(); //internal storage path
                String absolute = this.getFilesDir().getAbsolutePath();
                file = new File(path, "myPDF" + ".pdf"); //create File Object

                Document document= new Document();
                PdfWriter.getInstance(document,new FileOutputStream(file));
                document.open();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
                Image image = Image.getInstance(stream.toByteArray());
                document.add(new Paragraph("Your Heading for the Image Goes Here"));
                document.add(image);
                document.close();

            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }

            if (file != null) {
                InputStream uploadStream = null;
                try {
                    uploadStream = new FileInputStream(
                            file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String uploadName = "MYIMAGE" + ".pdf";
                BoxConfig.CLIENT_ID = "eamauh3g5ff0dggp0geew0c2jme1vpwg";
                BoxConfig.CLIENT_SECRET = "iMBBMDrz5quVYyIxxnFh9yJmEJtzJm2u";
                BoxConfig.REDIRECT_URL = "https://localhost";
                BoxSession session = new BoxSession(cameraScan.this);
                session.authenticate();
                Log.i("auth", "Authenticated");
                BoxApiFile fileApi = new BoxApiFile(session);
                Log.i("Path", "Done");
                String destinationFolderId = "0";
                try {
                    BoxRequestsFile.UploadFile request = fileApi.getUploadRequest(uploadStream, uploadName, destinationFolderId);
                    final BoxFile uploadFileInfo = request.send();
                    Log.i("Success", "Uploaded " + uploadFileInfo.getName());
                } catch (BoxException e) {
                    e.printStackTrace();
                    Log.w("Fail", "Failed");
                }
            }

        }




    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }



}