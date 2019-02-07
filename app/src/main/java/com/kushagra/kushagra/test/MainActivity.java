package com.kushagra.kushagra.test;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kushagra.kushagra.test.adapter.DetailAdapter;
import com.kushagra.kushagra.test.database.DBHelper;
import com.kushagra.kushagra.test.model.Details;
import com.kushagra.kushagra.test.model.User;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.rey.material.widget.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements LocationListener, AdapterView.OnItemSelectedListener {

    LocationManager locationManager;

    EditText et_pradeshno,et_jilla,et_nagarpalika,et_ward,et_bastiname,et_tolename,et_sadakname;

    EditText et_id,et_lat, et_lng, et_alt;

    EditText et_ghardhaniname,et_ghardhanisex,et_ghardhaniphone;

    Button btn_add, btn_update, btn_delete, btn_export;

    ListView lstDetail;

    String spin_jati, spin_vasa, spin_dharrma;

    RadioGroup rgbasai_abadhi,rg_basobas;
    RadioGroup rg_ghar_xa;
    RadioGroup rg_arughar_xa;
    RadioGroup rg_elec_connection;
    RadioGroup rg_fuel_type;
    RadioGroup rg_license;
    RadioGroup rg_birami_yesno;
    RadioGroup rg_pregnent_test_yesno;
    RadioGroup rg_pregnent_help;


    FirebaseDatabase database;

    DatabaseReference table_user;

    List<Details> details = new ArrayList<>();

    DBHelper db;

    String directory_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AADIR/";

    SQLiteToExcel sqliteToExcel;

    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Data Space");

        //db
        db = new DBHelper(this);

        //file export


        final File file = new File(directory_path);
        if (!file.exists()) {
            Log.v("File Created", String.valueOf(file.mkdirs()));

        }

//        if (!directory.exists()) {
//            directory.mkdir();
//        }

        //Spinners
        Spinner jatjati = (Spinner)findViewById(R.id.spin_jatjati);
        ArrayAdapter<CharSequence> adapter_s_jat = ArrayAdapter.createFromResource(this,R.array.jatjati,android.R.layout.simple_spinner_item);
        adapter_s_jat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jatjati.setAdapter(adapter_s_jat);
        jatjati.setOnItemSelectedListener(this);

        Spinner dharma = (Spinner)findViewById(R.id.spin_dharma);
        ArrayAdapter<CharSequence> adapter_s_dharma = ArrayAdapter.createFromResource(this,R.array.dharma,android.R.layout.simple_spinner_item);
        adapter_s_dharma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dharma.setAdapter(adapter_s_dharma);
        dharma.setOnItemSelectedListener(this);

        Spinner m_vasa = (Spinner)findViewById(R.id.spin_m_vasa);
        ArrayAdapter<CharSequence> adapter_m_vasa = ArrayAdapter.createFromResource(this,R.array.vasa,android.R.layout.simple_spinner_item);
        adapter_m_vasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_vasa.setAdapter(adapter_m_vasa);
        m_vasa.setOnItemSelectedListener(this);

        //

        rgbasai_abadhi = (RadioGroup)findViewById(R.id.rg_basai_abadhi);
        rgbasai_abadhi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout offline = (LinearLayout)findViewById(R.id.offline);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_basai_less6:

                        offline.setVisibility(View.GONE);
                        break;

                    case  R.id.rb_basai_more6:
                        offline.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        rg_basobas = (RadioGroup)findViewById(R.id.rg_basobas);
        rg_basobas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_karan = (RadioGroup)findViewById(R.id.rg_sarnu_reason);
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_basobas_raithaney:
                        rg_karan.setVisibility(View.GONE);
                        break;
                    case  R.id.rb_basobas_aft2040:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case  R.id.rb_basobas_aft2050:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case  R.id.rb_basobas_aft2060:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case  R.id.rb_basobas_aft2070:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                }

            }
        });

        rg_ghar_xa = (RadioGroup)findViewById(R.id.rg_ghar_xa);
        rg_ghar_xa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            RadioGroup rg_ghar_if_xa = (RadioGroup)findViewById(R.id.rg_ghar_if_xa);
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_ghar_xa:
                        rg_ghar_if_xa.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_ghar_xaina:
                        rg_ghar_if_xa.setVisibility(View.GONE);
                        break;



                }
            }
        });

        rg_arughar_xa =(RadioGroup)findViewById(R.id.rg_aru_ghar_xa);
        rg_arughar_xa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_aru_ghar_ifxa = (RadioGroup)findViewById(R.id.rg_aru_ghar_if_xa);
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_aru_ghar_xa:
                        rg_aru_ghar_ifxa.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_aru_ghar_xaina:
                        rg_aru_ghar_ifxa.setVisibility(View.GONE);
                        break;

                }
            }
        });

        rg_elec_connection = (RadioGroup)findViewById(R.id.rg_elec_con);
        rg_elec_connection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_elec_con_ifno = (RadioGroup)findViewById(R.id.rg_if_no_elec_con);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_elec_con_yes:
                        rg_elec_con_ifno.setVisibility(View.GONE);
                        break;

                    case R.id.rb_elec_con_no:
                        rg_elec_con_ifno.setVisibility(View.VISIBLE);
                        break;

                }

            }
        });

        rg_fuel_type = (RadioGroup)findViewById(R.id.rg_fuel_type);
        rg_fuel_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_if_daura_use = findViewById(R.id.rg_if_daura_use);
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_fuel_daura:
                        rg_if_daura_use.setVisibility(View.VISIBLE);
                        break;

                    default:
                        rg_if_daura_use.setVisibility(View.GONE);
                        break;
                }
            }
        });

        rg_license = (RadioGroup)findViewById(R.id.rg_license);
        rg_license.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout linearLayout_license_xavane = (LinearLayout)findViewById(R.id.linear_layout_license_xavane);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_license_yes:
                        linearLayout_license_xavane.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_license_no:
                        linearLayout_license_xavane.setVisibility(View.GONE);
                        break;
                }


            }
        });

        rg_birami_yesno = (RadioGroup)findViewById(R.id.rg_birami_yesno);
        rg_birami_yesno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout linearLayout_ifbirami_xavane = (LinearLayout)findViewById(R.id.linear_layout_if_biramixa_vane);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_birami_yes:
                       linearLayout_ifbirami_xavane.setVisibility(View.VISIBLE);
                       break;

                    case R.id.rb_birami_no:
                        linearLayout_ifbirami_xavane.setVisibility(View.GONE);
                        break;
                }

            }
        });

        rg_pregnent_test_yesno = (RadioGroup)findViewById(R.id.rg_preg_test_yesno);
        rg_pregnent_test_yesno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup if_pregtest_no_why = (RadioGroup) findViewById(R.id.if_pregtest_no_why);
            RadioGroup if_pregtest_yes_count = (RadioGroup) findViewById(R.id.if_pregtest_yes_count);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_pregnent_tes_yes:
                        if_pregtest_no_why.setVisibility(View.GONE);
                        if_pregtest_yes_count.setVisibility(View.VISIBLE);
                        break;


                    case R.id.rb_pregnent_test_no:
                        if_pregtest_no_why.setVisibility(View.VISIBLE);
                        if_pregtest_yes_count.setVisibility(View.GONE);
                        break;
                }

            }
        });

        rg_pregnent_help = (RadioGroup)findViewById(R.id.rg_pregnent_help);
        rg_pregnent_help.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RelativeLayout relativeLayout_why_pregnent_nohelp = (RelativeLayout)findViewById(R.id.rl_pregnent_whydelivery_nohelp);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_delivery_nohelp:
                        relativeLayout_why_pregnent_nohelp.setVisibility(View.VISIBLE);
                        break;


                        default:
                            relativeLayout_why_pregnent_nohelp.setVisibility(View.GONE);
                            break;
                }

            }
        });










        initializeGhardhaniBibaran();


        //init1
        et_nagarpalika =(EditText)findViewById(R.id.et_nagarpalika);
        et_pradeshno =(EditText)findViewById(R.id.et_pradesh_no);
        et_jilla =(EditText)findViewById(R.id.et_jilla);
        et_nagarpalika =(EditText)findViewById(R.id.et_nagarpalika);
        et_ward =(EditText)findViewById(R.id.et_ward);
        et_tolename =(EditText)findViewById(R.id.et_tolename);
        et_bastiname =(EditText)findViewById(R.id.et_bastiname);
        et_sadakname =(EditText)findViewById(R.id.et_sadakname);

        //initialize2
        et_lat = (EditText)findViewById(R.id.et_lats);
        et_lng = (EditText)findViewById(R.id.et_lngs);
        et_alt = (EditText)findViewById(R.id.et_alts);
        et_id = (EditText)findViewById(R.id.et_id);
        et_id.setVisibility(View.INVISIBLE);


        btn_add = (Button)findViewById(R.id.btn_add);
        //btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_export = (Button)findViewById(R.id.btn_export);

        lstDetail=(ListView)findViewById(R.id.list);




        //gps init
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        getLocationGPS();

        refreshData();

        //btn actions
        addDatatoDatabase();

        //testONLY
        Button btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        deleteDatafromDatabase();



//
//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Details details = new Details(
//                        Integer.parseInt(et_id.getText().toString()),
//                        et_lat.getText().toString(),
//                        et_lng.getText().toString(),
//                        et_alt.getText().toString(),
//                        et_pradeshno.getText().toString(),
//                        et_jilla.getText().toString(),
//                        et_nagarpalika.getText().toString(),
//                        et_ward.getText().toString(),
//                        et_bastiname.getText().toString(),
//                        et_tolename.getText().toString(),
//                        et_sadakname.getText().toString(),
//                        spin_jati,
//                        spin_vasa,
//                        spin_dharrma
//                );
//
//                db.updateDetail(details);
//                refreshData();
//
//            }
//        });


        Permissions.check(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, null, new PermissionHandler() {
            @Override
            public void onGranted() {


                        exportSQLtoExcel();

                            }
                        });

            }

    private void initializeGhardhaniBibaran() {
        et_ghardhaniname = (EditText)findViewById(R.id.et_ghardhaniname);
        et_ghardhanisex = (EditText)findViewById(R.id.et_ghardhanisex);
        et_ghardhaniphone = (EditText)findViewById(R.id.et_ghardhaniphone);


    }

    private void exportSQLtoExcel() {
        btn_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "KUSHAGRA", directory_path);

                //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                final String filename_e = "data"+date+".xls";


                sqliteToExcel.exportAllTables(filename_e, new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {

                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();

                        emailFile();

                    }

                    private void emailFile() {

                        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

                        if (info == null)
                        {
                            Toast.makeText(MainActivity.this, "No Internet cannot email!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(info.isConnected())
                            {
                                Toast.makeText(MainActivity.this, "Connection!", Toast.LENGTH_SHORT).show();
                                String filename=filename_e;
                                File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AADIR/",filename);
                                Uri path = Uri.fromFile(filelocation);
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setType("vnd.android.cursor.dir/email");
                                String to[] ={"mail.majimestudios@gmail.com"};
                                emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
                                emailIntent .putExtra(Intent.EXTRA_STREAM, path);
                                emailIntent .putExtra(Intent.EXTRA_SUBJECT, "DataSentByApp");
                                startActivity(Intent.createChooser(emailIntent , "SmartCity"));
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Connection Error!", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void deleteDatafromDatabase() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Details details = new Details(
                        Integer.parseInt(et_id.getText().toString()),
                        et_lat.getText().toString(),
                        et_lng.getText().toString(),
                        et_alt.getText().toString(),
                        et_pradeshno.getText().toString(),
                        et_jilla.getText().toString(),
                        et_nagarpalika.getText().toString(),
                        et_ward.getText().toString(),
                        et_bastiname.getText().toString(),
                        et_tolename.getText().toString(),
                        et_sadakname.getText().toString(),
                        spin_jati,
                        spin_vasa,
                        spin_dharrma,
                        et_ghardhaniname.getText().toString(),
                        et_ghardhanisex.getText().toString(),
                        et_ghardhaniphone.getText().toString()
                );


                db.deleteDetail(details);
                refreshData();
            }
        });

    }

    private void addDatatoDatabase() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String test="";
                test=et_ghardhaniname.getText().toString()+""
                        +et_ghardhaniphone.getText().toString()+""
                        +et_ghardhanisex.getText().toString();
                Toast.makeText(MainActivity.this,""+test , Toast.LENGTH_LONG).show();

                Details details = new Details(
                        Integer.parseInt(et_id.getText().toString()),
                        et_lat.getText().toString(),
                        et_lng.getText().toString(),
                        et_alt.getText().toString(),
                        et_pradeshno.getText().toString(),
                        et_jilla.getText().toString(),
                        et_nagarpalika.getText().toString(),
                        et_ward.getText().toString(),
                        et_bastiname.getText().toString(),
                        et_tolename.getText().toString(),
                        et_sadakname.getText().toString(),
                        spin_jati,
                        spin_vasa,
                        spin_dharrma,
                        et_ghardhaniname.getText().toString(),
                        et_ghardhanisex.getText().toString(),
                        et_ghardhaniphone.getText().toString()
                );

                db.addDetail(details);
                refreshData();



            }

        });
    }

    private void getLocationGPS() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
            this.onLocationChanged(null);
        }
    }


    private void refreshData(){
        details=db.getAllDetails();
        DetailAdapter adapter = new DetailAdapter(MainActivity.this,details,et_id,et_lat,et_lng,et_alt,et_pradeshno,et_jilla,et_nagarpalika,et_ward,et_bastiname,et_tolename,et_sadakname,spin_jati,spin_vasa,spin_dharrma);
        lstDetail.setAdapter(adapter);

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location==null){

            final ProgressDialog loading = new ProgressDialog(this);
            loading.setTitle("Loading!");
            loading.setMessage("Fetching Location, getting GPS");
            loading.show();


        }

        else {

            if (location.hasAltitude()) {
                double alt_d = location.getAltitude();
                String alt = Double.toString(alt_d);
                et_alt.setText(alt);
            } else {
                et_alt.setText("No Altitude");
            }

            double lat_d = location.getLatitude();
            String lat = Double.toString(lat_d);
            et_lat.setText(lat);

            double lng_d = location.getLongitude();
            String lng = Double.toString(lng_d);
            et_lng.setText(lng);



        }


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        switch (adapterView.getId()){

            case R.id.spin_dharma:

                String dharma = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Dharma "+dharma, Toast.LENGTH_SHORT).show();
                spin_dharrma = dharma;
                Log.d("TAG1",spin_dharrma);
                break;

            case R.id.spin_jatjati:

                String jatjati = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Jatjati "+jatjati, Toast.LENGTH_SHORT).show();
                spin_jati = jatjati;
                Log.d("TAG2",spin_jati);
                break;

            case R.id.spin_m_vasa:

                String vasa = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Vasa "+vasa, Toast.LENGTH_SHORT).show();
                spin_vasa =vasa;
                Log.d("TAG3",spin_vasa);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        spin_vasa = "";
        spin_jati = "";
        spin_dharrma = "";


    }
}
