package com.picjavard.salade.cryptotest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Piramidy Gizy on 06.09.2017.
 */

public class First_method extends Activity implements OnClickListener{
    private EditText EMsg;
    private EditText EKey;
    private TextView EndMsg;
    private Button btn_encrypt;
    private Button btn_decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);

        EMsg=(EditText) findViewById(R.id.edit_msg);
        EKey=(EditText) findViewById(R.id.edit_key);
        EndMsg=(TextView) findViewById(R.id.end_msg);
        btn_encrypt=(Button) findViewById(R.id.button_encrypt);
        btn_decrypt=(Button) findViewById(R.id.button_decrypt);

        btn_encrypt.setOnClickListener(this);
        btn_decrypt.setOnClickListener(this);
    }
    public void onClick(View v) {
        // прячем клавиатуру. v - это кнопка
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        switch (v.getId()) {
            case R.id.button_encrypt:
                EndMsg.setText(encrypt());
                break;
            case R.id.button_decrypt:
                EndMsg.setText(decrypt());
                break;
            default:
                break;
        }
    }
    protected String encrypt(){
        String alfabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ";
        String msg = EMsg.getText().toString();
        String key = "";
        String encryptMsg = "";
        for(int i = 0; i < msg.length(); i++){
            key+=EKey.getText().charAt(i % EKey.getText().length());
            encryptMsg+=alfabet.charAt(((alfabet.indexOf(key.charAt(i))+alfabet.indexOf(EMsg.getText().charAt(i)))%33));
        }
        return encryptMsg;
    }
    protected String decrypt(){
        String alfabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ";
        String msg = EMsg.getText().toString();
        String key = "";
        String encryptMsg = "";
        for(int i = 0; i < msg.length(); i++){
            key+=EKey.getText().charAt(i % EKey.getText().length());
            encryptMsg+=alfabet.charAt(((alfabet.indexOf(EMsg.getText().charAt(i))-alfabet.indexOf(key.charAt(i))+33)%33));
        }
        return encryptMsg;
    }
}
