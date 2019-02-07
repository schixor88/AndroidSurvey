package com.kushagra.kushagra.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class testactivity extends AppCompatActivity
//        implements
//        AdapterView.OnItemSelectedListener
{

    //Spinner
    Spinner jatjati,dharma,m_vasa;
    RadioGroup swamitwaRg,basobasRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

//        Spinner jatjati = (Spinner)findViewById(R.id.spin_jatjati);
//        ArrayAdapter<CharSequence> adapter_s_jat = ArrayAdapter.createFromResource(this,R.array.jatjati,android.R.layout.simple_spinner_item);
//        adapter_s_jat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        jatjati.setAdapter(adapter_s_jat);
//        jatjati.setOnItemSelectedListener(this);
//
//        Spinner dharma = (Spinner)findViewById(R.id.spin_dharma);
//        ArrayAdapter<CharSequence> adapter_s_dharma = ArrayAdapter.createFromResource(this,R.array.dharma,android.R.layout.simple_spinner_item);
//        adapter_s_dharma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dharma.setAdapter(adapter_s_dharma);
//        dharma.setOnItemSelectedListener(this);
//
//        Spinner m_vasa = (Spinner)findViewById(R.id.spin_m_vasa);
//        ArrayAdapter<CharSequence> adapter_m_vasa = ArrayAdapter.createFromResource(this,R.array.vasa,android.R.layout.simple_spinner_item);
//        adapter_m_vasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        m_vasa.setAdapter(adapter_m_vasa);
//        m_vasa.setOnItemSelectedListener(this);



        swamitwaRg = findViewById(R.id.rg_swamitwa);
        swamitwaRg.check(R.id.rb_swamitwa_none);

        basobasRg = findViewById(R.id.rg_basobas);
        //basobasRg.check(R.id.rb_basobas_gone);
    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        Toast.makeText(this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
