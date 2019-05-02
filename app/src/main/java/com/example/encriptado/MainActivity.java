package com.example.encriptado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtOriginal, txtEncode, txtDecode, txtEncode1, txtDecode1t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOriginal = findViewById(R.id.txt1);
        txtEncode = findViewById(R.id.txt2);
        txtDecode = findViewById(R.id.txt3);
        txtEncode1 = findViewById(R.id.txt4);
        txtDecode1t = findViewById(R.id.txt5);

        txtOriginal.setText("123");
    }
    public void crypt(View v){
        try {
            String original = txtOriginal.getText().toString();

            RSA rsa = new RSA();
            rsa.setContext(getBaseContext());
            rsa.genKeyPair(1024);

            rsa.saveToDiskPrivateKey("rsa.pri");
            rsa.saveToDiskPublicKey("rsa.pub");

            String encode_text = rsa.Encrypt(original);

            txtEncode.setText(encode_text);

            RSA rsa2 = new RSA();
            rsa2.setContext(getBaseContext());

            rsa2.openFromDiskPrivateKey("rsa.pri");
            rsa2.openFromDiskPublicKey("rsa.pub");

            String decode_text = rsa2.Decrypt(encode_text);

            txtDecode.setText(decode_text);
            txtEncode1.setText(encryption(txtOriginal.getText().toString()));
            txtDecode1t.setText(decryption(txtEncode1.getText().toString()));
        }catch (Exception e){

        }
    }
    public String encryption(String strNormalText){
        String seedValue = "YourSecKey";
        String normalTextEnc="";
        try {
            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }
    public String decryption(String strEncryptedText){
        String seedValue = "YourSecKey";
        String strDecryptedText="";
        try {
            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }
}
