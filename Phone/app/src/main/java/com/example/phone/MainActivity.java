package com.example.phone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private List<Contact> contactDatalist = new ArrayList<>();
    private ArrayAdapter<Contact> adapter;
    private Button bt_Add;
    private Button bt_Dial;
    private EditText et_Name;
    private EditText et_Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_Name = findViewById(R.id.et_name);
        et_Phone = findViewById(R.id.et_phone);
        bt_Add = findViewById(R.id.bt_add);
        bt_Dial = findViewById(R.id.bt_dial);
        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        lvContact = findViewById(R.id.lv_contact);
        adapter = new ArrayAdapter<Contact>(this, 0, 0) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater  inflater    =   (LayoutInflater)    getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView =   inflater.inflate(R.layout.contact,null);
                TextView    tvName  =   convertView.findViewById(R.id.tv_name);
                TextView    tvPhone    =   convertView.findViewById(R.id.tv_phone);
                Contact c = contactDatalist.get(position);
                tvName.setText(c.getName());
                tvPhone.setText(c.getPhone());
                return convertView;
            }
        }; lvContact.setAdapter(adapter);

    }

    private void addContact() {
        String name = et_Name.getText().toString();
        String phone = et_Phone.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter name and phone number",Toast.LENGTH_SHORT).show();
            return;
        }
        Contact c = new Contact();
        c.setName(name);
        c.setPhone(phone);
        contactDatalist.add(c);
        adapter.notifyDataSetChanged();
    }
}
