package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_TEXT_FILE = 1;
    private TextView textViewFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSelectFile = findViewById(R.id.button_select_file);
        textViewFileContent = findViewById(R.id.textview_file_content);

        buttonSelectFile.setOnClickListener(view -> openFilePicker());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_TEXT_FILE);
    }

    @Override
    //Cannot resolve symbol 'Nullable'
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_TEXT_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                try {
                    // Sao chép tệp từ bộ nhớ ngoài vào bộ nhớ nội bộ của ứng dụng
                    File internalFile = copyFileToInternalStorage(uri, "myFile.txt");

                    // Đọc tệp từ bộ nhớ nội bộ
                    String content = readTextFromInternalFile(internalFile);
                    textViewFileContent.setText(content);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Hàm sao chép tệp vào bộ nhớ nội bộ của ứng dụng
    private File copyFileToInternalStorage(Uri uri, String fileName) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        File internalFile = new File(getFilesDir(), fileName); // Lưu tệp vào bộ nhớ nội bộ của ứng dụng
        FileOutputStream outputStream = new FileOutputStream(internalFile);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();

        return internalFile;
    }

    // Hàm đọc nội dung tệp từ bộ nhớ nội bộ
    private String readTextFromInternalFile(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(file.getName())));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line).append("\n");
        }
        br.close();
        return text.toString();
    }
}