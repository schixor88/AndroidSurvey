package com.kushagra.kushagra.test;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kushagra.kushagra.test.adapter.DetailAdapter;
import com.kushagra.kushagra.test.database.DBHelper;
import com.kushagra.kushagra.test.helper.CSVWriter;
import com.kushagra.kushagra.test.model.Details;
import com.kushagra.kushagra.test.model.Details2;
import com.kushagra.kushagra.test.model.FamilyMemberData;
import com.kushagra.kushagra.test.model.HouseholdData;
import com.kushagra.kushagra.test.model.User;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.rey.material.widget.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements LocationListener, AdapterView.OnItemSelectedListener {

    LocationManager locationManager;

    EditText et_pradeshno, et_jilla, et_nagarpalika, et_ward, et_bastiname, et_tolename, et_sadakname;

    EditText et_id, et_lat, et_lng, et_alt;

    EditText et_ghardhaniname, et_ghardhanisex, et_ghardhaniphone;

    Button btn_add, btn_update, btn_delete, btn_export;

    ListView lstDetail;

    String spin_jati, spin_vasa, spin_dharrma;

    RadioGroup rgbasai_abadhi, rg_basobas;
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

    //Pariwarik babaran, Details 2
    Details2 details2;

    //Topic 2
    HouseholdData houseData;

    //Topic 3
    LinearLayout layout_individualData;
    Spinner spinFamilyCount;

    View[] familyMember = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    FamilyMemberData[] familyData = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        details2 = new Details2();
        houseData = new HouseholdData();

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
        Spinner jatjati = (Spinner) findViewById(R.id.spin_jatjati);
        ArrayAdapter<CharSequence> adapter_s_jat = ArrayAdapter.createFromResource(this, R.array.jatjati, android.R.layout.simple_spinner_item);
        adapter_s_jat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jatjati.setAdapter(adapter_s_jat);
        jatjati.setOnItemSelectedListener(this);

        Spinner dharma = (Spinner) findViewById(R.id.spin_dharma);
        ArrayAdapter<CharSequence> adapter_s_dharma = ArrayAdapter.createFromResource(this, R.array.dharma, android.R.layout.simple_spinner_item);
        adapter_s_dharma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dharma.setAdapter(adapter_s_dharma);
        dharma.setOnItemSelectedListener(this);

        Spinner m_vasa = (Spinner) findViewById(R.id.spin_m_vasa);
        ArrayAdapter<CharSequence> adapter_m_vasa = ArrayAdapter.createFromResource(this, R.array.vasa, android.R.layout.simple_spinner_item);
        adapter_m_vasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_vasa.setAdapter(adapter_m_vasa);
        m_vasa.setOnItemSelectedListener(this);

        //

        rgbasai_abadhi = (RadioGroup) findViewById(R.id.rg_basai_abadhi);
        rgbasai_abadhi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout offline = (LinearLayout) findViewById(R.id.offline);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selected = findViewById(i);
                String value = selected.getText().toString();
                details2.setBasai_abadhi(value);

                switch (i) {
                    case R.id.rb_basai_less6:

                        offline.setVisibility(View.GONE);
                        break;

                    case R.id.rb_basai_more6:
                        offline.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        rg_basobas = (RadioGroup) findViewById(R.id.rg_basobas);
        rg_basobas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_karan = (RadioGroup) findViewById(R.id.rg_sarnu_reason);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selected = findViewById(i);
                String value = selected.getText().toString();
                details2.setPariwar_basobas_avastha(value);

                switch (i) {
                    case R.id.rb_basobas_raithaney:
                        rg_karan.setVisibility(View.GONE);
                        break;
                    case R.id.rb_basobas_aft2040:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_basobas_aft2050:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_basobas_aft2060:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_basobas_aft2070:
                        rg_karan.setVisibility(View.VISIBLE);
                        break;
                }

            }
        });

        rg_ghar_xa = (RadioGroup) findViewById(R.id.rg_ghar_xa);
        rg_ghar_xa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            RadioGroup rg_ghar_if_xa = (RadioGroup) findViewById(R.id.rg_ghar_if_xa);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selected = findViewById(i);
                String value = selected.getText().toString();
                details2.setNagarpalika_ma_ghar_bhayeko(value);

                switch (i) {
                    case R.id.rb_ghar_xa:
                        rg_ghar_if_xa.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_ghar_xaina:
                        rg_ghar_if_xa.setVisibility(View.GONE);
                        break;


                }
            }
        });

        rg_arughar_xa = (RadioGroup) findViewById(R.id.rg_aru_ghar_xa);
        rg_arughar_xa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_aru_ghar_ifxa = (RadioGroup) findViewById(R.id.rg_aru_ghar_if_xa);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selected = findViewById(i);
                String value = selected.getText().toString();
                details2.setNagarpalika_ma_anya_ghar_bhayeko(value);

                switch (i) {

                    case R.id.rb_aru_ghar_xa:
                        rg_aru_ghar_ifxa.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_aru_ghar_xaina:
                        rg_aru_ghar_ifxa.setVisibility(View.GONE);
                        break;

                }
            }
        });

        rg_elec_connection = (RadioGroup) findViewById(R.id.rg_elec_con);
        rg_elec_connection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_elec_con_ifno = (RadioGroup) findViewById(R.id.rg_if_no_elec_con);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

                    case R.id.rb_elec_con_yes:
                        rg_elec_con_ifno.setVisibility(View.GONE);
                        details2.setElectricity_jadan_bhayeko("1");
                        break;

                    case R.id.rb_elec_con_no:
                        rg_elec_con_ifno.setVisibility(View.VISIBLE);
                        details2.setElectricity_jadan_bhayeko("0");
                        break;
                }

            }
        });

        rg_fuel_type = (RadioGroup) findViewById(R.id.rg_fuel_type);
        rg_fuel_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup rg_if_daura_use = findViewById(R.id.rg_if_daura_use);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selected = findViewById(i);
                String value = selected.getText().toString();
                details2.setPakaune_fuel(value);
                switch (i) {

                    case R.id.rb_fuel_daura:
                        rg_if_daura_use.setVisibility(View.VISIBLE);
                        break;

                    default:
                        rg_if_daura_use.setVisibility(View.GONE);
                        break;
                }
            }
        });

        rg_license = (RadioGroup) findViewById(R.id.rg_license);
        rg_license.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout linearLayout_license_xavane = (LinearLayout) findViewById(R.id.linear_layout_license_xavane);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.rb_license_yes:
                        linearLayout_license_xavane.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_license_no:
                        linearLayout_license_xavane.setVisibility(View.GONE);
                        break;
                }


            }
        });

        rg_birami_yesno = (RadioGroup) findViewById(R.id.rg_birami_yesno);
        rg_birami_yesno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            LinearLayout linearLayout_ifbirami_xavane = (LinearLayout) findViewById(R.id.linear_layout_if_biramixa_vane);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

                    case R.id.rb_birami_yes:
                        linearLayout_ifbirami_xavane.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rb_birami_no:
                        linearLayout_ifbirami_xavane.setVisibility(View.GONE);
                        break;
                }

            }
        });

        rg_pregnent_test_yesno = (RadioGroup) findViewById(R.id.rg_preg_test_yesno);
        rg_pregnent_test_yesno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioGroup if_pregtest_no_why = (RadioGroup) findViewById(R.id.if_pregtest_no_why);
            RadioGroup if_pregtest_yes_count = (RadioGroup) findViewById(R.id.if_pregtest_yes_count);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

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

        rg_pregnent_help = (RadioGroup) findViewById(R.id.rg_pregnent_help);
        rg_pregnent_help.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RelativeLayout relativeLayout_why_pregnent_nohelp = (RelativeLayout) findViewById(R.id.rl_pregnent_whydelivery_nohelp);

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

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
        et_nagarpalika = (EditText) findViewById(R.id.et_nagarpalika);
        et_pradeshno = (EditText) findViewById(R.id.et_pradesh_no);
        et_jilla = (EditText) findViewById(R.id.et_jilla);
        et_nagarpalika = (EditText) findViewById(R.id.et_nagarpalika);
        et_ward = (EditText) findViewById(R.id.et_ward);
        et_tolename = (EditText) findViewById(R.id.et_tolename);
        et_bastiname = (EditText) findViewById(R.id.et_bastiname);
        et_sadakname = (EditText) findViewById(R.id.et_sadakname);

        //initialize2
        et_lat = (EditText) findViewById(R.id.et_lats);
        et_lng = (EditText) findViewById(R.id.et_lngs);
        et_alt = (EditText) findViewById(R.id.et_alts);
        et_id = (EditText) findViewById(R.id.et_id);
        et_id.setVisibility(View.INVISIBLE);


        btn_add = (Button) findViewById(R.id.btn_add);
        //btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_export = (Button) findViewById(R.id.btn_export);

        lstDetail = (ListView) findViewById(R.id.list);


        //gps init
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        getLocationGPS();

        refreshData();

        //btn actions
        addDatatoDatabase();

        //testONLY
        Button btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
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


        handleDetails2RadioValues();
        // For topic 2, listen radio group event
        handleHouseHoldRadioGroups();
        // For topic 3, dymanic rendering of layout
        renderFamilyInputDataLayout();

    }

    private void initializeGhardhaniBibaran() {
        et_ghardhaniname = (EditText) findViewById(R.id.et_ghardhaniname);
        et_ghardhanisex = (EditText) findViewById(R.id.et_ghardhanisex);
        et_ghardhaniphone = (EditText) findViewById(R.id.et_ghardhaniphone);
    }

    private void exportDB() {

        DBHelper dbhelper = new DBHelper(getApplicationContext());
        File exportDir = null;
        if (Build.VERSION.SDK_INT > 19) {
            exportDir = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOCUMENTS);
        } else {
            exportDir = new File(Environment.getExternalStorageDirectory(), "");
        }
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(directory_path, "data" + date + ".csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM Details", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            int columnCount = curCSV.getColumnCount();
            while (curCSV.moveToNext()) {
                //Which column you want to exprort
//                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2)};
                String arrStr[] = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    arrStr[i] = curCSV.getString(i);
                }
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Toast.makeText(this, "Successfully exported to " + file.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }

    private void exportSQLtoExcel() {
        btn_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                exportDB();

                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "KUSHAGRA", directory_path);

                //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                final String filename_e = "data" + date + ".csv";
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

                        if (info == null) {
                            Toast.makeText(MainActivity.this, "No Internet cannot email!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (info.isConnected()) {
                                Toast.makeText(MainActivity.this, "Connection!", Toast.LENGTH_SHORT).show();
                                String filename = filename_e;
                                File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AADIR/", filename);
                                Uri path = Uri.fromFile(filelocation);
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setType("vnd.android.cursor.dir/email");
                                String to[] = {"mail.majimestudios@gmail.com"};
                                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                                emailIntent.putExtra(Intent.EXTRA_STREAM, path);
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "DataSentByApp");
                                startActivity(Intent.createChooser(emailIntent, "SmartCity"));
                            } else {
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

                //Details2
                getDetails2EditTextValues();
                getDetials2CheckboxValues();

                // Household
                getHouseHoldCheckboxValues();
                getHouseHoldEditTextValues();

                // For 15 family members
                getCheckBoxValues0(0);
                getCheckBoxValues1(1);
                getCheckBoxValues2(2);
                getCheckBoxValues3(3);
                getCheckBoxValues4(4);
                getCheckBoxValues5(5);
                getCheckBoxValues6(6);
                getCheckBoxValues7(7);
                getCheckBoxValues8(8);
                getCheckBoxValues9(9);

                getEditTextValues0(0);
                getEditTextValues1(1);
                getEditTextValues2(2);
                getEditTextValues3(3);
                getEditTextValues4(4);
                getEditTextValues5(5);
                getEditTextValues6(6);
                getEditTextValues7(7);
                getEditTextValues8(8);
                getEditTextValues9(9);

                String test = "";
                test = et_ghardhaniname.getText().toString() + ""
                        + et_ghardhaniphone.getText().toString() + ""
                        + et_ghardhanisex.getText().toString();
                Toast.makeText(MainActivity.this, "" + test, Toast.LENGTH_LONG).show();

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

                db.addDetail(details, details2, houseData, familyData);

                refreshData();


            }

        });
    }

    private void getLocationGPS() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 120);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            this.onLocationChanged(null);
        }
    }


    private void refreshData() {
        details = db.getAllDetails();
        DetailAdapter adapter = new DetailAdapter(MainActivity.this, details, et_id, et_lat, et_lng, et_alt, et_pradeshno, et_jilla, et_nagarpalika, et_ward, et_bastiname, et_tolename, et_sadakname, spin_jati, spin_vasa, spin_dharrma);
        lstDetail.setAdapter(adapter);

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {

            final ProgressDialog loading = new ProgressDialog(this);
            loading.setTitle("Loading!");
            loading.setMessage("Fetching Location, getting GPS");
            loading.show();


        } else {

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


        switch (adapterView.getId()) {

            case R.id.spin_dharma:

                String dharma = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Dharma " + dharma, Toast.LENGTH_SHORT).show();
                spin_dharrma = dharma;
                Log.d("TAG1", spin_dharrma);
                break;

            case R.id.spin_jatjati:

                String jatjati = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Jatjati " + jatjati, Toast.LENGTH_SHORT).show();
                spin_jati = jatjati;
                Log.d("TAG2", spin_jati);
                break;

            case R.id.spin_m_vasa:

                String vasa = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(this, "Vasa " + vasa, Toast.LENGTH_SHORT).show();
                spin_vasa = vasa;
                Log.d("TAG3", spin_vasa);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        spin_vasa = "";
        spin_jati = "";
        spin_dharrma = "";
    }

    private void getDetails2EditTextValues() {
        EditText tenant_family_count, informer_name, informer_gender, informer_age, informer_email, thar, kitchen_nirman_barsa,
                gas_cylinder_tikne_din, old_chulo_ko_lagi_daaura_kg, modern_chulo_ko_lagi_daaura_kg, mattitel_permonth_litre,
                induction_heater_hoursperday, oven_hoursperday, paani_tatauna_cylinderperyear, paani_tatauna_wood_kgpermonth_old_chulo,
                paani_tatauna_wood_kgpermonth_modern, paani_tatauna_mattitel_permonth, paani_tatauna_induction_hoursperday,
                paani_tatauna_oven_hoursperday, fan_count, fan_watt, fan_hoursperday, aircondition_count, aircondition_watt,
                aircondition_hoursperday, bijuli_unit, washingmachine_count, fridge_count, solar_count, vacuum_count, inverter_count,
                generator_count, dishwasher_count, cable_count, other_house_gadget_count, radio_count, television_count, telephone_count,
                cellphone_count, smartphone_count, computer_laptop_count, internet_count, other_communication_count, internet_speed_mbps,
                cycle_count, bike_count, lightweight_vehicle_count, heavyweight_vehicle_count, public_transport_vehicle_count,
                license_bhayeko_count, license_count_2_wheeler, license_count_4_wheeler,
                healthpost_jana_hidera_time, healthpost_jana_gaadi_time, vaccine_nagareko_karan,
                under_5yr_death_gender, under_5yr_death_age, under_5yr_death_karan;

        tenant_family_count = findViewById(R.id.d2_et_tenant_family_count);
        informer_name = findViewById(R.id.d2_et_informer_name);
        informer_gender = findViewById(R.id.d2_et_informer_gender);
        informer_age = findViewById(R.id.d2_et_informer_age);
        informer_email = findViewById(R.id.d2_et_informer_email);
        thar = findViewById(R.id.d2_et_thar);
        kitchen_nirman_barsa = findViewById(R.id.d2_et_kitchen_nirman_barsa);
        gas_cylinder_tikne_din = findViewById(R.id.d2_et_gas_cylinder_tikne_din);
        old_chulo_ko_lagi_daaura_kg = findViewById(R.id.d2_et_old_chulo_ko_lagi_daaura_kg);
        modern_chulo_ko_lagi_daaura_kg = findViewById(R.id.d2_et_modern_chulo_ko_lagi_daaura_kg);
        mattitel_permonth_litre = findViewById(R.id.d2_et_mattitel_permonth_litre);
        induction_heater_hoursperday = findViewById(R.id.d2_et_induction_heater_hoursperday);
        oven_hoursperday = findViewById(R.id.d2_et_oven_hoursperday);
        paani_tatauna_cylinderperyear = findViewById(R.id.d2_et_paani_tatauna_cylinderperyear);
        paani_tatauna_wood_kgpermonth_old_chulo = findViewById(R.id.d2_et_paani_tatauna_wood_kgpermonth_old_chulo);
        paani_tatauna_wood_kgpermonth_modern = findViewById(R.id.d2_et_paani_tatauna_wood_kgpermonth_modern);
        paani_tatauna_mattitel_permonth = findViewById(R.id.d2_et_paani_tatauna_mattitel_permonth);
        paani_tatauna_induction_hoursperday = findViewById(R.id.d2_et_paani_tatauna_induction_hoursperday);
        paani_tatauna_oven_hoursperday = findViewById(R.id.d2_et_paani_tatauna_oven_hoursperday);
        fan_count = findViewById(R.id.d2_et_fan_count);
        fan_watt = findViewById(R.id.d2_et_fan_watt);
        fan_hoursperday = findViewById(R.id.d2_et_fan_hoursperday);
        aircondition_count = findViewById(R.id.d2_et_aircondition_count);
        aircondition_watt = findViewById(R.id.d2_et_aircondition_watt);
        aircondition_hoursperday = findViewById(R.id.d2_et_aircondition_hoursperday);
        bijuli_unit = findViewById(R.id.d2_et_bijuli_unit);
        washingmachine_count = findViewById(R.id.d2_et_washingmachine_count);
        fridge_count = findViewById(R.id.d2_et_fridge_count);
        solar_count = findViewById(R.id.d2_et_solar_count);
        vacuum_count = findViewById(R.id.d2_et_vacuum_count);
        inverter_count = findViewById(R.id.d2_et_inverter_count);
        generator_count = findViewById(R.id.d2_et_generator_count);
        dishwasher_count = findViewById(R.id.d2_et_dishwasher_count);
        cable_count = findViewById(R.id.d2_et_cable_count);
        other_house_gadget_count = findViewById(R.id.d2_et_other_house_gadget_count);
        radio_count = findViewById(R.id.d2_et_radio_count);
        television_count = findViewById(R.id.d2_et_television_count);
        telephone_count = findViewById(R.id.d2_et_telephone_count);
        cellphone_count = findViewById(R.id.d2_et_cellphone_count);
        smartphone_count = findViewById(R.id.d2_et_smartphone_count);
        computer_laptop_count = findViewById(R.id.d2_et_computer_laptop_count);
        internet_count = findViewById(R.id.d2_et_internet_count);
        other_communication_count = findViewById(R.id.d2_et_other_communication_count);
        internet_speed_mbps = findViewById(R.id.d2_et_internet_speed_mbps);
        cycle_count = findViewById(R.id.d2_et_cycle_count);
        bike_count = findViewById(R.id.d2_et_bike_count);
        lightweight_vehicle_count = findViewById(R.id.d2_et_lightweight_vehicle_count);
        heavyweight_vehicle_count = findViewById(R.id.d2_et_heavyweight_vehicle_count);
        public_transport_vehicle_count = findViewById(R.id.d2_et_public_transport_vehicle_count);
        license_bhayeko_count = findViewById(R.id.d2_et_license_bhayeko_count);
        license_count_2_wheeler = findViewById(R.id.d2_et_license_count_2_wheeler);
        license_count_4_wheeler = findViewById(R.id.d2_et_license_count_4_wheeler);
        healthpost_jana_hidera_time = findViewById(R.id.d2_et_healthpost_jana_hidera_time);
        healthpost_jana_gaadi_time = findViewById(R.id.d2_et_healthpost_jana_gaadi_time);
        vaccine_nagareko_karan = findViewById(R.id.d2_et_vaccine_nagareko_karan);
        under_5yr_death_gender = findViewById(R.id.d2_et_under_5yr_death_gender);
        under_5yr_death_age = findViewById(R.id.d2_et_under_5yr_death_age);
        under_5yr_death_karan = findViewById(R.id.d2_et_under_5yr_death_karan);

        details2.setTenant_family_count(tenant_family_count.getText().toString());
        details2.setInformer_name(informer_name.getText().toString());
        details2.setInformer_gender(informer_gender.getText().toString());
        details2.setInformer_age(informer_age.getText().toString());
        details2.setInformer_email(informer_email.getText().toString());
        details2.setThar(thar.getText().toString());
        details2.setKitchen_nirman_barsa(kitchen_nirman_barsa.getText().toString());
        details2.setGas_cylinder_tikne_din(gas_cylinder_tikne_din.getText().toString());
        details2.setOld_chulo_ko_lagi_daaura_kg(old_chulo_ko_lagi_daaura_kg.getText().toString());
        details2.setModern_chulo_ko_lagi_daaura_kg(modern_chulo_ko_lagi_daaura_kg.getText().toString());
        details2.setMattitel_permonth_litre(mattitel_permonth_litre.getText().toString());
        details2.setInduction_heater_hoursperday(induction_heater_hoursperday.getText().toString());
        details2.setOven_hoursperday(oven_hoursperday.getText().toString());
        details2.setPaani_tatauna_cylinderperyear(paani_tatauna_cylinderperyear.getText().toString());
        details2.setPaani_tatauna_wood_kgpermonth_old_chulo(paani_tatauna_wood_kgpermonth_old_chulo.getText().toString());
        details2.setPaani_tatauna_wood_kgpermonth_modern(paani_tatauna_wood_kgpermonth_modern.getText().toString());
        details2.setPaani_tatauna_mattitel_permonth(paani_tatauna_mattitel_permonth.getText().toString());
        details2.setPaani_tatauna_induction_hoursperday(paani_tatauna_induction_hoursperday.getText().toString());
        details2.setPaani_tatauna_oven_hoursperday(paani_tatauna_oven_hoursperday.getText().toString());
        details2.setFan_count(fan_count.getText().toString());
        details2.setFan_watt(fan_watt.getText().toString());
        details2.setFan_hoursperday(fan_hoursperday.getText().toString());
        details2.setAircondition_count(aircondition_count.getText().toString());
        details2.setAircondition_watt(aircondition_watt.getText().toString());
        details2.setAircondition_hoursperday(aircondition_hoursperday.getText().toString());
        details2.setBijuli_unit(bijuli_unit.getText().toString());
        details2.setWashingmachine_count(washingmachine_count.getText().toString());
        details2.setFridge_count(fridge_count.getText().toString());
        details2.setSolar_count(solar_count.getText().toString());
        details2.setVacuum_count(vacuum_count.getText().toString());
        details2.setInverter_count(inverter_count.getText().toString());
        details2.setGenerator_count(generator_count.getText().toString());
        details2.setDishwasher_count(dishwasher_count.getText().toString());
        details2.setCable_count(cable_count.getText().toString());
        details2.setOther_house_gadget_count(other_house_gadget_count.getText().toString());
        details2.setRadio_count(radio_count.getText().toString());
        details2.setTelevision_count(television_count.getText().toString());
        details2.setTelephone_count(telephone_count.getText().toString());
        details2.setCellphone_count(cellphone_count.getText().toString());
        details2.setSmartphone_count(smartphone_count.getText().toString());
        details2.setComputer_laptop_count(computer_laptop_count.getText().toString());
        details2.setInternet_count(internet_count.getText().toString());
        details2.setOther_communication_count(other_communication_count.getText().toString());
        details2.setInternet_speed_mbps(internet_speed_mbps.getText().toString());
        details2.setCycle_count(cycle_count.getText().toString());
        details2.setBike_count(bike_count.getText().toString());
        details2.setLightweight_vehicle_count(lightweight_vehicle_count.getText().toString());
        details2.setHeavyweight_vehicle_count(heavyweight_vehicle_count.getText().toString());
        details2.setPublic_transport_vehicle_count(public_transport_vehicle_count.getText().toString());
        details2.setLicense_bhayeko_count(license_bhayeko_count.getText().toString());
        details2.setLicense_count_2_wheeler(license_count_2_wheeler.getText().toString());
        details2.setLicense_count_4_wheeler(license_count_4_wheeler.getText().toString());
        details2.setHealthpost_jana_hidera_time(healthpost_jana_hidera_time.getText().toString());
        details2.setHealthpost_jana_gaadi_time(healthpost_jana_gaadi_time.getText().toString());
        details2.setVaccine_nagareko_karan(vaccine_nagareko_karan.getText().toString());
        details2.setUnder_5yr_death_gender(under_5yr_death_gender.getText().toString());
        details2.setUnder_5yr_death_age(under_5yr_death_age.getText().toString());
        details2.setUnder_5yr_death_karan(under_5yr_death_karan.getText().toString());


    }

    private void handleDetails2RadioValues() {
        RadioGroup swamitto, basai_abadhi, jatiya_samuha, pariwar_basobas_avastha, basai_sarnu_karan, basai_avadhi,
                nagarpalika_ma_ghar_bhayeko, ghar_jagga_swamitto_kisim, nagarpalika_ma_anya_ghar_bhayeko, anya_ghar_k_ko_lagi,
                kitchen_alaggai_bhayeko, kitchen_nirman_avastha, batti_main_source, electricity_jadan_bhayeko,
                electricity_jadan_kina_nabhayeko, culo_ko_prakar, pakaune_fuel, daaura_ko_source, bijuli_load_ampere,
                family_ma_yatayat_license_bhayeko, one_year_ma_birami, birami_huda_kata_lageko,
                two_year_le_vaccine_lagayeko, two_year_ma_pregnant, pregnant_ko_health_checkup,
                pregnant_ko_check_nagarne_karan, pregnant_ko_check_gareko_count, pregnant_le_icon_pill_linegareko,
                pregnant_le_juka_medicine_linegareko, pregnant_le_vitamin_linegareko, baccha_janmaaune_sthan, baccha_janmaauna_sahayog_garne,
                baccha_janmaauna_help_nalinukaran, last12Monthma_under_5yr_ko_death, under_5yr_ko_development_measure_huncha,
                age6month_to_6yr_vitamin_A, age1yr_to_6yr_juka_medicine, winter_ma_warm_cloth, jhuul_ko_babyastha,
                regular_health_checkup_peryear, past12month_ma_death_bhayeko, yog_ra_adhyatmik_kendra_ma_janegareko,
                khanepaani_source, paani_meter_jadan_gareyeko, paani_sufficient_hune, paani_lina_jane_aaune_time,
                khanepaani_quality, pani_purify_garne_gareko, rain_water_collect_gareko, rainwater_ko_use,
                ghaar_ma_dhaal_attach_bhayeko, dhaal_attach_bhayeko_type, waste_water_management,
                decompose_nodecompose_separate_garne;

        swamitto = findViewById(R.id.rg_swamitwa);
//        basai_abadhi = findViewById(R.id.rg_basai_abadhi);
        jatiya_samuha = findViewById(R.id.rg_jatiya_samuha);
//        pariwar_basobas_avastha = findViewById(R.id.rg_basobas);
//        basai_sarnu_karan = findViewById(R.id.rg_sarnu_reason);
        basai_avadhi = findViewById(R.id.rg_basai_time);
//        nagarpalika_ma_ghar_bhayeko = findViewById(R.id.rg_ghar_xa);
//        ghar_jagga_swamitto_kisim = findViewById(R.id.rg_ghar_if_xa);
//        nagarpalika_ma_anya_ghar_bhayeko = findViewById(R.id.rg_aru_ghar_xa);
//        anya_ghar_k_ko_lagi = findViewById(R.id.rg_aru_ghar_if_xa);
        kitchen_alaggai_bhayeko = findViewById(R.id.rg_vanxa_xa);
        kitchen_nirman_avastha = findViewById(R.id.rg_make_condn);
        batti_main_source = findViewById(R.id.rg_light);
//        electricity_jadan_bhayeko = findViewById(R.id.rg_elec_con);
//        electricity_jadan_kina_nabhayeko = findViewById(R.id.rg_if_no_elec_con);
        culo_ko_prakar = findViewById(R.id.rg_chulo_type);
//        pakaune_fuel = findViewById(R.id.rg_fuel_type);
//        daaura_ko_source = findViewById(R.id.rg_if_daura_use);
        bijuli_load_ampere = findViewById(R.id.rg_elec_aapurti);
//        family_ma_yatayat_license_bhayeko = findViewById(R.id.rg_license);
//        one_year_ma_birami = findViewById(R.id.rg_birami_yesno);
        birami_huda_kata_lageko = findViewById(R.id.rg_birami_yes_1sttreat);
        two_year_le_vaccine_lagayeko = findViewById(R.id.rg_vaccine_yesno);
        two_year_ma_pregnant = findViewById(R.id.rg_pregnent_yesno);
//        pregnant_ko_health_checkup = findViewById(R.id.rg_preg_test_yesno);
//        pregnant_ko_check_nagarne_karan = findViewById(R.id.if_pregtest_no_why);
//        pregnant_ko_check_gareko_count = findViewById(R.id.if_pregtest_yes_count);
        pregnant_le_icon_pill_linegareko = findViewById(R.id.rg_pregnent_ironyesno);
        pregnant_le_juka_medicine_linegareko = findViewById(R.id.rg_pregnent_juka);
        pregnant_le_vitamin_linegareko = findViewById(R.id.rg_pregnent_vitamin);
        baccha_janmaaune_sthan = findViewById(R.id.rg_pregnent_deliveryplace);
//        baccha_janmaauna_sahayog_garne = findViewById(R.id.rg_pregnent_help);
        baccha_janmaauna_help_nalinukaran = findViewById(R.id.rg_pregnent_whynohelp);
        last12Monthma_under_5yr_ko_death = findViewById(R.id.rg_12mnth_kiddie_yesno);
        under_5yr_ko_development_measure_huncha = findViewById(R.id.rg_5yrs_posan_condn);
        age6month_to_6yr_vitamin_A = findViewById(R.id.rg_6mnthdekhi_6yrs_vitaminA);
        age1yr_to_6yr_juka_medicine = findViewById(R.id.rg_1to6yrs_juka);
        winter_ma_warm_cloth = findViewById(R.id.rg_jadoma_odney_ochauney);
        jhuul_ko_babyastha = findViewById(R.id.rg_jhulko_byawsta_yesno);
        regular_health_checkup_peryear = findViewById(R.id.rg_basra_swastacheck_count);
        past12month_ma_death_bhayeko = findViewById(R.id.rg_in12mthn_death);
        yog_ra_adhyatmik_kendra_ma_janegareko = findViewById(R.id.rg_exceptHospital_yog_etc);
        khanepaani_source = findViewById(R.id.rg_khanepani_srowt);
        paani_meter_jadan_gareyeko = findViewById(R.id.rg_paniko_meter_yesno);
        paani_sufficient_hune = findViewById(R.id.rg_pani_paryapta_yesno);
        paani_lina_jane_aaune_time = findViewById(R.id.rg_khanepani_lyaunalagne_time);
        khanepaani_quality = findViewById(R.id.rg_khanepani_gunastar);
        pani_purify_garne_gareko = findViewById(R.id.rg_khanepani_suddhikaran);
        rain_water_collect_gareko = findViewById(R.id.rg_aakhaspani_sankalan_yesno);
        rainwater_ko_use = findViewById(R.id.rg_aakhaspani_sankalan_ifyes);
        ghaar_ma_dhaal_attach_bhayeko = findViewById(R.id.rg_gharma_dhal_yesno);
        dhaal_attach_bhayeko_type = findViewById(R.id.rg_ghar_dhal_ifyes);
        waste_water_management = findViewById(R.id.rg_fohorpani_byawastha);
        decompose_nodecompose_separate_garne = findViewById(R.id.rg_kuhiney_fohor_chuttuney_yesno);


    }

    private void getDetials2CheckboxValues() {
        Integer paani_tataune_upakaranIDs[] = {R.id.cb_heat_water_gas, R.id.cb_heat_water_param, R.id.cb_heat_water_sudhars,
                R.id.cb_heat_water_kerosene, R.id.cb_heat_water_induct, R.id.cb_heat_water_elec, R.id.cb_heat_water_solar,
                R.id.cb_heat_water_heat_normal};
        Integer room_heating_upakaranIDs[] = {R.id.cb_room_heat_gas, R.id.cb_room_heat_elech, R.id.cb_room_heat_kerosenes,
                R.id.cb_room_heat_wood};
        Integer room_cooling_upakaranIDs[] = {R.id.cb_room_cool_fan, R.id.cb_room_cool_cooler, R.id.cb_room_cool_none};
        Integer solid_waste_managementIDs[] = {R.id.cb_fhoro_compoundma_jalauney, R.id.cb_fohor_thuparney_container,
                R.id.cb_fohor_lina_aauxa, R.id.cb_fohor_compost, R.id.cb_fohor_sadakma, R.id.cb_fohor_sarbajanik,
                R.id.cb_fohor_nadi};

        CheckBox[] pani_tataune = new CheckBox[paani_tataune_upakaranIDs.length];
        StringBuilder sbPaani = new StringBuilder();
        for (int i = 0; i < paani_tataune_upakaranIDs.length; i++) {
            pani_tataune[i] = findViewById(paani_tataune_upakaranIDs[i]);
            if (pani_tataune[i].isChecked()) {
                sbPaani.append(pani_tataune[i].getText().toString());
                sbPaani.append(" , ");
            }
        }
        details2.setPaani_tataune_upakaran(sbPaani.toString());

        CheckBox[] room_tataune = new CheckBox[room_heating_upakaranIDs.length];
        StringBuilder sbRoomHeat = new StringBuilder();
        for (int i = 0; i < room_heating_upakaranIDs.length; i++) {
            room_tataune[i] = findViewById(room_heating_upakaranIDs[i]);
            if (room_tataune[i].isChecked()) {
                sbRoomHeat.append(room_tataune[i].getText().toString());
                sbRoomHeat.append(" , ");
            }
        }
        details2.setRoom_heating_upakaran(sbRoomHeat.toString());

        CheckBox[] room_cool = new CheckBox[room_cooling_upakaranIDs.length];
        StringBuilder sbRoomCool = new StringBuilder();
        for (int i = 0; i < room_cooling_upakaranIDs.length; i++) {
            room_cool[i] = findViewById(room_cooling_upakaranIDs[i]);
            if (room_cool[i].isChecked()) {
                sbRoomCool.append(room_cool[i].getText().toString());
                sbRoomCool.append(" , ");
            }
        }
        details2.setRoom_cooling_upakaran(sbRoomCool.toString());

        CheckBox[] wasteMgmt = new CheckBox[solid_waste_managementIDs.length];
        StringBuilder sbWaste = new StringBuilder();
        for (int i = 0; i < solid_waste_managementIDs.length; i++) {
            wasteMgmt[i] = findViewById(solid_waste_managementIDs[i]);
            if (wasteMgmt[i].isChecked()) {
                sbWaste.append(wasteMgmt[i].getText().toString());
                sbWaste.append(" , ");
            }
        }
        details2.setSolid_waste_management(sbWaste.toString());

    }

    private void handleHouseHoldRadioGroups() {

        //toilet
        RadioGroup rgHasToilet = findViewById(R.id.t2_toilet_rg_hasToilet);
        findViewById(R.id.t2_toilet_layout_toiletTypes).setVisibility(View.GONE);
        rgHasToilet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_toilet_rb_hasToilet_y) {
                    houseData.setHasToilet("1");
                    houseData.setHasNoToilet("0");
                    findViewById(R.id.t2_toilet_layout_toiletTypes).setVisibility(View.VISIBLE);
                } else {
                    houseData.setHasNoToilet("1");
                    houseData.setHasToilet("0");
                    findViewById(R.id.t2_toilet_layout_toiletTypes).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgToiletTypes = findViewById(R.id.t2_toilet_rg_toiletType);
        rgToiletTypes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedRB = findViewById(i);
                String value = selectedRB.getText().toString();
                houseData.setToiletType(value);
            }
        });

        RadioGroup rgToiletWaste = findViewById(R.id.t2_toilet_rg_wasteMgmt);
        rgToiletWaste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedRB = findViewById(i);
                String value = selectedRB.getText().toString();
                houseData.setToiletWasteMgmt(value);
            }
        });

        //finance
        RadioGroup rgPrimaryIncome = findViewById(R.id.t2_finance_rg_primaryIncomeSource);
        rgPrimaryIncome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setPrimaryIncomeSource(value);
            }
        });

        RadioGroup rgOwnsLand = findViewById(R.id.t2_finance_rg_ownsLand);
        findViewById(R.id.t2_finance_layout_ownsLand_y).setVisibility(View.GONE);
        rgOwnsLand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_finance_rb_ownsLand_y) {
                    houseData.setOwnsLand("1");
                    houseData.setOwnsNoLand("0");
                    findViewById(R.id.t2_finance_layout_ownsLand_y).setVisibility(View.VISIBLE);
                } else {
                    houseData.setOwnsLand("0");
                    houseData.setOwnsNoLand("1");
                    findViewById(R.id.t2_finance_layout_ownsLand_y).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgLandLocation = findViewById(R.id.t2_finance_rg_ownedLandLocation);
        rgLandLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setLandLocation(value);
            }
        });

        RadioGroup rgLandOwnedBy = findViewById(R.id.t2_finance_rg_landOwnedBy);
        rgLandOwnedBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setLandOwnedBy(value);
            }
        });

        RadioGroup rgLandUsedAs = findViewById(R.id.t2_finance_rg_landUsedAs);
        rgLandUsedAs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setLandUsedAs(value);
            }
        });

        RadioGroup rgLandOnLease = findViewById(R.id.t2_finance_rg_landOnLease);
        findViewById(R.id.t2_finance_layout_landOnLease_y).setVisibility(View.GONE);
        rgLandOnLease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_finance_rb_landOnLease_y) {
                    houseData.setLandOnLease("1");
                    houseData.setLandOnNoLease("0");
                    findViewById(R.id.t2_finance_layout_landOnLease_y).setVisibility(View.VISIBLE);
                } else {
                    houseData.setLandOnNoLease("1");
                    houseData.setLandOnLease("0");
                    findViewById(R.id.t2_finance_layout_landOnLease_y).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgLeaseLandUsedAs = findViewById(R.id.t2_finance_rg_LeaseLandUsedAs);
        rgLeaseLandUsedAs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setLandOnLeaseUsedAs(value);
            }
        });

        //agriculutre
        RadioGroup rbSellsProduct = findViewById(R.id.t2_agro_rg_sellsProducts);
        findViewById(R.id.t2_agro_layout_sellProductList).setVisibility(View.GONE);
        rbSellsProduct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_agro_rb_sellsProducts_y) {
                    houseData.setSellsProductions("1");
                    houseData.setNotSellsProductions("0");
                    findViewById(R.id.t2_agro_layout_sellProductList).setVisibility(View.VISIBLE);
                } else {
                    houseData.setNotSellsProductions("1");
                    houseData.setSellsProductions("0");
                    findViewById(R.id.t2_agro_layout_sellProductList).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgSellsProductTo = findViewById(R.id.t2_agro_rg_sellProductTo);
        rgSellsProductTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setSellProductionTo(value);
            }
        });

        RadioGroup rgPlantsFruit = findViewById(R.id.t2_agro_rg_fruitPlant);
        rgPlantsFruit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_agro_rb_fruitPlant_y) {
                    houseData.setHasFruitPlants("1");
                    houseData.setHasNoFruitPlants("0");
                } else {
                    houseData.setHasNoFruitPlants("1");
                    houseData.setHasFruitPlants("0");
                }
            }
        });

        RadioGroup rgWorksAbroad = findViewById(R.id.t2_income_rg_worksAbroad);
        findViewById(R.id.t2_income_layout_worksAbroad_y).setVisibility(View.GONE);
        rgWorksAbroad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_income_rg_worksAbroad_y) {
                    findViewById(R.id.t2_income_layout_worksAbroad_y).setVisibility(View.VISIBLE);
                    houseData.setWorksAbroad("1");
                    houseData.setNotWorkAbroad("0");
                } else {
                    findViewById(R.id.t2_income_layout_worksAbroad_y).setVisibility(View.GONE);
                    houseData.setWorksAbroad("0");
                    houseData.setNotWorkAbroad("1");
                }
            }
        });

        RadioGroup rgProductSustainFor = findViewById(R.id.t2_income_rg_productSustainFor);
        rgProductSustainFor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setProductionsSustainableFor(value);
            }
        });

        RadioGroup rgIncomeSufficient = findViewById(R.id.t2_income_rg_incomeSufficient);
        findViewById(R.id.t2_income_layout_incomeSufficient_n).setVisibility(View.GONE);
        rgIncomeSufficient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_income_rb_incomeSufficient_n) {
                    houseData.setIncomeIsSufficient("0");
                    houseData.setIncomeIsNotSufficient("1");
                    findViewById(R.id.t2_income_layout_incomeSufficient_n).setVisibility(View.VISIBLE);
                } else {
                    houseData.setIncomeIsSufficient("1");
                    houseData.setIncomeIsNotSufficient("0");
                    findViewById(R.id.t2_income_layout_incomeSufficient_n).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgHasLoan = findViewById(R.id.t2_income_rg_loan);
        findViewById(R.id.t2_income_layout_loan_y).setVisibility(View.GONE);
        rgHasLoan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_income_rb_loan_y) {
                    houseData.setHasTakenLoan("1");
                    houseData.setHasNotTakenLoan("0");
                    findViewById(R.id.t2_income_layout_loan_y).setVisibility(View.VISIBLE);
                } else {
                    houseData.setHasNotTakenLoan("1");
                    houseData.setHasTakenLoan("0");
                    findViewById(R.id.t2_income_layout_loan_y).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgLoanPurpose = findViewById(R.id.t2_income_rg_takenLoanFor);
        rgLoanPurpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setTakenLoanFor(value);
            }
        });

        RadioGroup rgLoanClear = findViewById(R.id.t2_income_rg_durationToClearLoan);
        rgLoanClear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setDurationToClearLoan(value);
            }
        });

        //prakop
        RadioGroup rgKnowSamit = findViewById(R.id.t2_prakop_rg_knowsSamhit);
        rgKnowSamit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_knowsSamhit_y) {
                    houseData.setKnowsSamhit("1");
                    houseData.setNotKnowSamhit("0");
                } else {
                    houseData.setNotKnowSamhit("1");
                    houseData.setKnowsSamhit("0");
                }
            }
        });

        RadioGroup rgBuildSamhit = findViewById(R.id.t2_prakop_rg_builtAsPerSamhit);
        rgBuildSamhit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_builtAsPerSamhit_y) {
                    houseData.setBuiltAsPerSamhit("1");
                    houseData.setNotBuiltAsPerSamhit("0");
                    houseData.setNotKnowBuiltAsPerSamhit("0");
                } else if (i == R.id.t2_prakop_rb_builtAsPerSamhit_n) {
                    houseData.setBuiltAsPerSamhit("0");
                    houseData.setNotBuiltAsPerSamhit("1");
                    houseData.setNotKnowBuiltAsPerSamhit("0");
                } else {
                    houseData.setBuiltAsPerSamhit("0");
                    houseData.setNotBuiltAsPerSamhit("0");
                    houseData.setNotKnowBuiltAsPerSamhit("1");
                }
            }
        });

        RadioGroup rgPermissionBlueprint = findViewById(R.id.t2_prakop_rg_hasPermissionBlueprint);
        rgPermissionBlueprint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_hasPermissionBlueprint_y) {
                    houseData.setHasPermissionBlueprint("1");
                    houseData.setHasNoPermissionBlueprint("0");
                } else {
                    houseData.setHasPermissionBlueprint("0");
                    houseData.setHasNoPermissionBlueprint("1");
                }
            }
        });

        RadioGroup rgSafeZoneNear = findViewById(R.id.t2_prakop_rg_safeZoneNear);
        rgSafeZoneNear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_safeZoneNear_y) {
                    houseData.setHasSafeZoneNearHouse("1");
                    houseData.setHasNoSafeZoneNearHouse("0");
                } else {
                    houseData.setHasSafeZoneNearHouse("0");
                    houseData.setHasNoSafeZoneNearHouse("1");
                }
            }
        });

        RadioGroup rgSusceptCalamity = findViewById(R.id.t2_prakop_rg_susceptToCalamity);
        rgSusceptCalamity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_susceptToCalamity_y) {
                    houseData.setHouseSusceptToCalamity("1");
                    houseData.setHouseNoSusceptToCalamity("0");
                } else {
                    houseData.setHouseSusceptToCalamity("0");
                    houseData.setHouseNoSusceptToCalamity("1");
                }
            }
        });

        RadioGroup rgInfoEarthquake = findViewById(R.id.t2_prakop_rg_infoAboutEarthquake);
        rgInfoEarthquake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_infoAboutEarthquake_y) {
                    houseData.setInfoAboutEarthquake("1");
                    houseData.setNoInfoAboutEarthquake("0");
                } else {
                    houseData.setInfoAboutEarthquake("0");
                    houseData.setNoInfoAboutEarthquake("1");
                }
            }
        });

        RadioGroup rgSafeZoneInHome = findViewById(R.id.t2_prakop_rg_safeZoneInHouse);
        rgSafeZoneInHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_safeZoneInHouse_y) {
                    houseData.setKnowSafeZoneInHome("1");
                    houseData.setKnowsNoSafeZoneInHome("0");
                } else {
                    houseData.setKnowsNoSafeZoneInHome("1");
                    houseData.setKnowSafeZoneInHome("0");
                }
            }
        });

        RadioGroup rgSuppliesSOE = findViewById(R.id.t2_prakop_rg_supplyForSOE);
        rgSuppliesSOE.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_supplyForSOE_y) {
                    houseData.setHasSuppliesForSOE("1");
                    houseData.setHasNoSuppliesForSOE("0");
                } else {
                    houseData.setHasNoSuppliesForSOE("1");
                    houseData.setHasSuppliesForSOE("0");
                }
            }
        });

        RadioGroup rgLastMajorDisaster = findViewById(R.id.t2_prakop_rg_lastMajorDisaster);
        rgLastMajorDisaster.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setLastMajorDisaster(value);
            }
        });

        RadioGroup rgAssetInsurance = findViewById(R.id.t2_prakop_rg_assetInsurance);
        findViewById(R.id.t2_prakop_layout_assetInsurance_y).setVisibility(View.GONE);
        rgAssetInsurance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_assetInsurance_y) {
                    findViewById(R.id.t2_prakop_layout_assetInsurance_y).setVisibility(View.VISIBLE);
                    houseData.setHasAssetInsurance("1");
                    houseData.setHasNoAssetInsurance("0");
                } else {
                    findViewById(R.id.t2_prakop_layout_assetInsurance_y).setVisibility(View.GONE);
                    houseData.setHasAssetInsurance("0");
                    houseData.setHasNoAssetInsurance("1");
                }
            }
        });

        RadioGroup rgViolenceVictim = findViewById(R.id.t2_prakop_rg_violenceViction);
        findViewById(R.id.t2_prakop_layout_violenceViction_y).setVisibility(View.GONE);
        rgViolenceVictim.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_violenceViction_y) {
                    findViewById(R.id.t2_prakop_layout_violenceViction_y).setVisibility(View.VISIBLE);
                    houseData.setIsViolenceVictim("1");
                    houseData.setIsNotViolenceVictim("0");
                } else {
                    findViewById(R.id.t2_prakop_layout_violenceViction_y).setVisibility(View.GONE);
                    houseData.setIsViolenceVictim("0");
                    houseData.setIsNotViolenceVictim("1");
                }
            }
        });

        RadioGroup rgSued = findViewById(R.id.t2_prakop_rg_sued);
        rgSued.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_sued_y) {
                    houseData.setIsSued("1");
                    houseData.setIsNotSued("0");
                } else {
                    houseData.setIsSued("0");
                    houseData.setIsNotSued("1");
                }
            }
        });

        RadioGroup rgShifted = findViewById(R.id.t2_prakop_rg_shifted);
        rgShifted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_shifted_y) {
                    houseData.setIsShifted("1");
                    houseData.setIsNotShifted("0");
                } else {
                    houseData.setIsShifted("0");
                    houseData.setIsNotShifted("1");
                }
            }
        });

        RadioGroup rgAbused = findViewById(R.id.t2_prakop_rg_sexuallyAbused);
        rgAbused.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_sexuallyAbused_y) {
                    houseData.setIsSexuallyAbuse("1");
                    houseData.setIsNotSexuallyAbused("0");
                } else {
                    houseData.setIsSexuallyAbuse("0");
                    houseData.setIsNotSexuallyAbused("1");
                }
            }
        });

        RadioGroup rgMissing = findViewById(R.id.t2_prakop_rg_personMissing);
        rgMissing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_personMissing_y) {
                    houseData.setMemberMissing("1");
                    houseData.setMemberNotMissing("0");
                } else {
                    houseData.setMemberMissing("0");
                    houseData.setMemberNotMissing("1");
                }
            }
        });

        RadioGroup rgFeelSafe = findViewById(R.id.t2_prakop_rg_safeInMunicipal);
        findViewById(R.id.t2_prakop_layout_safeInMunicipal_y).setVisibility(View.GONE);
        findViewById(R.id.t2_prakop_layout_safeInMunicipal_n).setVisibility(View.GONE);
        rgFeelSafe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_prakop_rb_safeInMunicipal_y) {
                    findViewById(R.id.t2_prakop_layout_safeInMunicipal_y).setVisibility(View.VISIBLE);
                    findViewById(R.id.t2_prakop_layout_safeInMunicipal_n).setVisibility(View.GONE);
                    houseData.setFeelsSafeInMunicipal("1");
                    houseData.setFeelsNoSafeInMunicipal("0");
                } else {
                    findViewById(R.id.t2_prakop_layout_safeInMunicipal_y).setVisibility(View.GONE);
                    findViewById(R.id.t2_prakop_layout_safeInMunicipal_n).setVisibility(View.VISIBLE);
                    houseData.setFeelsSafeInMunicipal("0");
                    houseData.setFeelsNoSafeInMunicipal("1");
                }
            }
        });

        RadioGroup rgFeelSafeReason = findViewById(R.id.t2_prakop_rg_feelSafeReason);
        rgFeelSafeReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setReasonFeelsSafe(value);
            }
        });

        RadioGroup rgNotFeelSafeReason = findViewById(R.id.t2_prakop_rg_notFeelSafeReason);
        rgNotFeelSafeReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setReasonNoFeelSafe(value);
            }
        });

        RadioGroup rgWork16FromHome = findViewById(R.id.t2_social_rg_work16FromHome);
        findViewById(R.id.t2_social_layout_work16FromHome_y).setVisibility(View.GONE);
        rgWork16FromHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_social_rb_work16FromHome_y) {
                    findViewById(R.id.t2_social_layout_work16FromHome_y).setVisibility(View.VISIBLE);
                    houseData.setHomeMemberUptoAge16Works("1");
                    houseData.setNoHomeMemberUptoAge16Works("0");
                } else {
                    findViewById(R.id.t2_social_layout_work16FromHome_y).setVisibility(View.GONE);
                    houseData.setHomeMemberUptoAge16Works("0");
                    houseData.setNoHomeMemberUptoAge16Works("1");
                }
            }
        });

        RadioGroup rgWork16InHome = findViewById(R.id.t2_social_rg_work16InHome);
        findViewById(R.id.t2_social_layout_work16InHome_y).setVisibility(View.GONE);
        rgWork16InHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_social_rb_work16InHome_y) {
                    findViewById(R.id.t2_social_layout_work16InHome_y).setVisibility(View.VISIBLE);
                    houseData.setMemberUptoAge16HiredWork("1");
                    houseData.setNoMemberUptoAge16HiredWork("0");
                } else {
                    findViewById(R.id.t2_social_layout_work16InHome_y).setVisibility(View.GONE);
                    houseData.setMemberUptoAge16HiredWork("0");
                    houseData.setNoMemberUptoAge16HiredWork("1");
                }
            }
        });

        RadioGroup rgChildInfluenced = findViewById(R.id.t2_social_rg_childInfluenced);
        findViewById(R.id.t2_social_layout_childInfluenced_y).setVisibility(View.GONE);
        rgChildInfluenced.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_social_rb_childInfluenced_y) {
                    findViewById(R.id.t2_social_layout_childInfluenced_y).setVisibility(View.VISIBLE);
                    houseData.setChildrenIsOnBadInfluence("1");
                    houseData.setChildrenNotOnBadInfluence("0");
                } else {
                    findViewById(R.id.t2_social_layout_childInfluenced_y).setVisibility(View.GONE);
                    houseData.setChildrenIsOnBadInfluence("0");
                    houseData.setChildrenNotOnBadInfluence("1");
                }
            }
        });

        RadioGroup rgFamilyDecicion = findViewById(R.id.t2_social_rg_familyDecision);
        rgFamilyDecicion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setWayOfMakingFamilyDecision(value);
            }
        });

        RadioGroup rgInvoleInDev = findViewById(R.id.t2_social_rg_involveInDev);
        rgInvoleInDev.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t2_social_rb_involveInDev_y) {
                    houseData.setMemberHasInvolvedOnDevelopment("1");
                    houseData.setMemberHasNotInvolvedOnDevelopment("0");
                } else {
                    houseData.setMemberHasInvolvedOnDevelopment("0");
                    houseData.setMemberHasNotInvolvedOnDevelopment("1");
                }
            }
        });

        RadioGroup rgDevPriority = findViewById(R.id.t2_social_rg_devPriority);
        rgDevPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelected = findViewById(i);
                String value = rbSelected.getText().toString();
                houseData.setOpinionOnDevelopmentPriority(value);
            }
        });
    }

    private void getHouseHoldEditTextValues() {
        // Khetipati
        EditText editAnna, editDhan, editMakai, editKodo, editGhau, editFapar, editOthers1Name, editOthers1Muri, editOthers2Name, editOthers2Muri;
        EditText editOil, editDaal, editTarkari, editFreshTarkari, editAalu, editMasala, editFalful, editKandamul, editOthers3Name, editOthers3KG, editOthers4Name, editOthers4KG;

        editAnna = findViewById(R.id.t2_agro_edit_annaBali);
        editDhan = findViewById(R.id.t2_agro_edit_dhan);
        editMakai = findViewById(R.id.t2_agro_edit_makai);
        editKodo = findViewById(R.id.t2_agro_edit_kodo);
        editGhau = findViewById(R.id.t2_agro_edit_ghau);
        editFapar = findViewById(R.id.t2_agro_edit_fapar);
        editOthers1Name = findViewById(R.id.t2_agro_edit_others1Name);
        editOthers1Muri = findViewById(R.id.t2_agro_edit_others1Amount);
        editOthers2Name = findViewById(R.id.t2_agro_edit_others2Name);
        editOthers2Muri = findViewById(R.id.t2_agro_edit_others2Amount);

        editOil = findViewById(R.id.t2_agro_edit_oil);
        editDaal = findViewById(R.id.t2_agro_edit_daal);
        editTarkari = findViewById(R.id.t2_agro_edit_tarkari);
        editFreshTarkari = findViewById(R.id.t2_agro_edit_freshTarkari);
        editAalu = findViewById(R.id.t2_agro_edit_aalu);
        editMasala = findViewById(R.id.t2_agro_edit_masala);
        editFalful = findViewById(R.id.t2_agro_edit_falful);
        editKandamul = findViewById(R.id.t2_agro_edit_kandamul);
        editOthers3Name = findViewById(R.id.t2_agro_edit_others3Name);
        editOthers3KG = findViewById(R.id.t2_agro_edit_others3Amount);
        editOthers4Name = findViewById(R.id.t2_agro_edit_others4Name);
        editOthers4KG = findViewById(R.id.t2_agro_edit_others4Amount);

        houseData.setAnnabali_muri(editAnna.getText().toString());
        houseData.setDhan_muri(editDhan.getText().toString());
        houseData.setMakai_muri(editMakai.getText().toString());
        houseData.setKodo_muri(editKodo.getText().toString());
        houseData.setGhau_muri(editGhau.getText().toString());
        houseData.setFapar_muri(editFapar.getText().toString());
        houseData.setOthers_muri(editOthers1Name.getText().toString() + " : " + editOthers1Muri.getText().toString() + "\n" +
                editOthers2Name.getText().toString() + " : " + editOthers2Muri.getText().toString());

        houseData.setOil_kg(editOil.getText().toString());
        houseData.setDaal_kg(editDaal.getText().toString());
        houseData.setTarkari_kg(editTarkari.getText().toString());
        houseData.setFreshTarkari_kg(editFreshTarkari.getText().toString());
        houseData.setAalu_kg(editAalu.getText().toString());
        houseData.setMasala_kg(editMasala.getText().toString());
        houseData.setFalful_kg(editFalful.getText().toString());
        houseData.setKandamul_kg(editKandamul.getText().toString());
        houseData.setOthers_kg(editOthers3Name.getText().toString() + " : " + editOthers3KG.getText().toString() + "\n" +
                editOthers4Name.getText().toString() + " : " + editOthers4KG.getText().toString());

        // Pashu Panchi
        EditText cow_sthaniya, cow_unnat, buffalo_sthaniya, buffalo_unnat, goat_sthaniya, goat_unnat, sheep_sthaniya, sheep_unnat, pig_sthaniya, pig_unnat, other_animalName, other_animal_sthaniya, other_animal_unnat;
        EditText hen_sthaniya, hen_unnat, pigeon_count, other_birdName, other_birdCount;
        EditText fish_count, beehive_count, other_pasupanchiName, otherPasupanchiCount;
        EditText milkCurd_litre, ghee_kg, otherDairy_kg, meat_kg, compost_quintal, urine_litre, wool_kg, egg_crate, fish_kg, honey_kg, other_productName, other_productKG;

        cow_sthaniya = findViewById(R.id.t2_agro_edit_cowSthaniya);
        cow_unnat = findViewById(R.id.t2_agro_edit_cowUnnat);
        buffalo_sthaniya = findViewById(R.id.t2_agro_edit_buffaloSthaniya);
        buffalo_unnat = findViewById(R.id.t2_agro_edit_buffaloUnnat);
        goat_sthaniya = findViewById(R.id.t2_agro_edit_goatSthaniya);
        goat_unnat = findViewById(R.id.t2_agro_edit_goatUnnat);
        sheep_sthaniya = findViewById(R.id.t2_agro_edit_sheepSthaniya);
        sheep_unnat = findViewById(R.id.t2_agro_edit_sheepUnnat);
        pig_sthaniya = findViewById(R.id.t2_agro_edit_pigSthaniya);
        pig_unnat = findViewById(R.id.t2_agro_edit_pigUnnat);
        other_animalName = findViewById(R.id.t2_agro_edit_othersAnimalName);
        other_animal_sthaniya = findViewById(R.id.t2_agro_edit_othersAnimalSthaniya);
        other_animal_unnat = findViewById(R.id.t2_agro_edit_othersAnimalUnnat);
        hen_sthaniya = findViewById(R.id.t2_agro_edit_henSthaniya);
        hen_unnat = findViewById(R.id.t2_agro_edit_henUnnat);
        pigeon_count = findViewById(R.id.t2_agro_edit_pigeonCount);
        other_birdName = findViewById(R.id.t2_agro_edit_othersBirdName);
        other_birdCount = findViewById(R.id.t2_agro_edit_othersBirdCount);

        houseData.setCow_sthaniya(cow_sthaniya.getText().toString());
        houseData.setCow_unnat(cow_unnat.getText().toString());
        houseData.setBuffalo_sthaniya(buffalo_sthaniya.getText().toString());
        houseData.setBuffalo_unnat(buffalo_unnat.getText().toString());
        houseData.setGoat_sthaniya(goat_sthaniya.getText().toString());
        houseData.setGoat_unnat(goat_unnat.getText().toString());
        houseData.setSheep_sthaniya(sheep_sthaniya.getText().toString());
        houseData.setSheep_unnat(sheep_unnat.getText().toString());
        houseData.setPig_sthaniya(pig_sthaniya.getText().toString());
        houseData.setPig_unnat(pig_unnat.getText().toString());
        houseData.setOther_animal(other_animalName.getText().toString() +
                "\nSthaniya: " + other_animal_sthaniya.getText().toString() +
                "\nUnnat: " + other_animal_unnat.getText().toString());
        houseData.setHen_sthaniya(hen_sthaniya.getText().toString());
        houseData.setHen_unnat(hen_unnat.getText().toString());
        houseData.setPigeon_count(pigeon_count.getText().toString());
        houseData.setOther_birds(other_birdName.getText().toString() + " : " + other_birdCount.getText().toString());

        fish_count = findViewById(R.id.t2_agro_edit_fishCount);
        beehive_count = findViewById(R.id.t2_agro_edit_beesCount);
        other_pasupanchiName = findViewById(R.id.t2_agro_edit_otherOthersName);
        otherPasupanchiCount = findViewById(R.id.t2_agro_edit_otherOthersCount);

        houseData.setFish_count(fish_count.getText().toString());
        houseData.setBeehive_count(beehive_count.getText().toString());
        houseData.setOther_pasupanchi(other_pasupanchiName.getText().toString() + " : " + otherPasupanchiCount.getText().toString());

        milkCurd_litre = findViewById(R.id.t2_agro_edit_milkCurd);
        ghee_kg = findViewById(R.id.t2_agro_edit_ghee);
        otherDairy_kg = findViewById(R.id.t2_agro_edit_otherDairy);
        meat_kg = findViewById(R.id.t2_agro_edit_meat);
        compost_quintal = findViewById(R.id.t2_agro_edit_compost);
        urine_litre = findViewById(R.id.t2_agro_edit_urine);
        wool_kg = findViewById(R.id.t2_agro_edit_wool);
        egg_crate = findViewById(R.id.t2_agro_edit_eggs);
        fish_kg = findViewById(R.id.t2_agro_edit_fish);
        honey_kg = findViewById(R.id.t2_agro_edit_honey);
        other_productName = findViewById(R.id.t2_agro_edit_otherProduceName);
        other_productKG = findViewById(R.id.t2_agro_edit_otherProduceAmount);

        houseData.setMilkCurd_litre(milkCurd_litre.getText().toString());
        houseData.setGhee_kg(ghee_kg.getText().toString());
        houseData.setOtherDairy_kg(otherDairy_kg.getText().toString());
        houseData.setMeat_kg(meat_kg.getText().toString());
        houseData.setCompost_quintal(compost_quintal.getText().toString());
        houseData.setUrine_litre(urine_litre.getText().toString());
        houseData.setWool_kg(wool_kg.getText().toString());
        houseData.setEgg_crate(egg_crate.getText().toString());
        houseData.setFish_kg(fish_kg.getText().toString());
        houseData.setHoney_kg(honey_kg.getText().toString());
        houseData.setOther_production_kg(other_productName.getText().toString() + " : " + other_productKG.getText().toString());

        // Income Expense
        EditText incomeAgriculture, incomeBusiness, incomeSalaryPension, incomeSocialAllowance, incomeForeignEmp, incomeWages, incomeRent, incomeInterestInvest, incomeOthers;
        EditText expenseFood, expenseCloth, expenseEducation, expenseHealth, expenseEntertain, expenseRent, expenseAgriculture, expenseInstallment, expenseFuel, expenseTranport, expenseOthers;

        incomeAgriculture = findViewById(R.id.t2_income_edit_agriculture);
        incomeBusiness = findViewById(R.id.t2_income_edit_business);
        incomeSalaryPension = findViewById(R.id.t2_income_edit_salary);
        incomeSocialAllowance = findViewById(R.id.t2_income_edit_socialAllowance);
        incomeForeignEmp = findViewById(R.id.t2_income_edit_abroadJob);
        incomeWages = findViewById(R.id.t2_income_edit_wages);
        incomeRent = findViewById(R.id.t2_income_edit_rent);
        incomeInterestInvest = findViewById(R.id.t2_income_edit_bankInterest);
        incomeOthers = findViewById(R.id.t2_income_edit_others);

        expenseFood = findViewById(R.id.t2_expense_edit_food);
        expenseCloth = findViewById(R.id.t2_expense_edit_cloth);
        expenseEducation = findViewById(R.id.t2_expense_edit_education);
        expenseHealth = findViewById(R.id.t2_expense_edit_health);
        expenseEntertain = findViewById(R.id.t2_expense_edit_entertainment);
        expenseRent = findViewById(R.id.t2_expense_edit_rent);
        expenseAgriculture = findViewById(R.id.t2_expense_edit_agriculture);
        expenseInstallment = findViewById(R.id.t2_expense_edit_installmentFee);
        expenseFuel = findViewById(R.id.t2_expense_edit_machines);
        expenseTranport = findViewById(R.id.t2_expense_edit_transport);
        expenseOthers = findViewById(R.id.t2_expense_edit_others);

        houseData.setIncomeAgriculture(incomeAgriculture.getText().toString());
        houseData.setIncomeBusiness(incomeBusiness.getText().toString());
        houseData.setIncomeSalaryPension(incomeSalaryPension.getText().toString());
        houseData.setIncomeSocialAllowance(incomeSocialAllowance.getText().toString());
        houseData.setIncomeForeignEmp(incomeForeignEmp.getText().toString());
        houseData.setIncomeWages(incomeWages.getText().toString());
        houseData.setIncomeRent(incomeRent.getText().toString());
        houseData.setIncomeInterestInvest(incomeInterestInvest.getText().toString());
        houseData.setIncomeOthers(incomeOthers.getText().toString());

        houseData.setExpenseFood(expenseFood.getText().toString());
        houseData.setExpenseCloth(expenseCloth.getText().toString());
        houseData.setExpenseEducation(expenseEducation.getText().toString());
        houseData.setExpenseHealth(expenseHealth.getText().toString());
        houseData.setExpenseEntertain(expenseEntertain.getText().toString());
        houseData.setExpenseRent(expenseRent.getText().toString());
        houseData.setExpenseAgriculture(expenseAgriculture.getText().toString());
        houseData.setExpenseInstallment(expenseInstallment.getText().toString());
        houseData.setExpenseFuel(expenseFuel.getText().toString());
        houseData.setExpenseTranport(expenseTranport.getText().toString());
        houseData.setExpenseOthers(expenseOthers.getText().toString());

        // Prakop
        EditText deathCount, injuredCount, missingCount, assetLossCount;
        EditText unsafeWardNo, unsafeRegion;

        deathCount = findViewById(R.id.t2_prakop_edit_deathCount);
        injuredCount = findViewById(R.id.t2_prakop_edit_injuryCount);
        missingCount = findViewById(R.id.t2_prakop_edit_missingCount);
        assetLossCount = findViewById(R.id.t2_prakop_edit_assetLoss);

        unsafeWardNo = findViewById(R.id.t2_prakop_edit_unsafeWard);
        unsafeRegion = findViewById(R.id.t2_prakop_edit_unsafeRegion);

        houseData.setDeathCount(deathCount.getText().toString());
        houseData.setInjuryCount(injuredCount.getText().toString());
        houseData.setMissingCount(missingCount.getText().toString());
        houseData.setAssetLossAmountRs(assetLossCount.getText().toString());
        houseData.setWardNoWhereNotFeelSafe(unsafeWardNo.getText().toString());
        houseData.setRegionNameWhereNotFeelSafe(unsafeRegion.getText().toString());

        // Social
        EditText workingBoyCount, workingGirlCount, hiredBoyCount, hiredGirlCount;

        workingBoyCount = findViewById(R.id.t2_social_edit_workingBoyCount);
        workingGirlCount = findViewById(R.id.t2_social_edit_workingGirlCount);
        hiredBoyCount = findViewById(R.id.t2_social_edit_hiredBoyCount);
        hiredGirlCount = findViewById(R.id.t2_social_edit_hiredGirlCount);

        houseData.setUptoAge16WoringBoyCount(workingBoyCount.getText().toString());
        houseData.setUptoAge16WorkingGirlCount(workingGirlCount.getText().toString());
        houseData.setUptoAge16HiredBoyCount(hiredBoyCount.getText().toString());
        houseData.setUptoAge16HiredGirlCount(hiredGirlCount.getText().toString());
    }

    private void getHouseHoldCheckboxValues() {
        //electricity
        CheckBox[] onlineServiceCheckBoxes;
        Integer[] onlineServiceChkId = {R.id.t2_onlineServices_1, R.id.t2_onlineServices_2, R.id.t2_onlineServices_3, R.id.t2_onlineServices_4,
                R.id.t2_onlineServices_5, R.id.t2_onlineServices_6, R.id.t2_onlineServices_7, R.id.t2_onlineServices_8,
                R.id.t2_onlineServices_9, R.id.t2_onlineServices_10, R.id.t2_onlineServices_11, R.id.t2_onlineServices_12,
                R.id.t2_onlineServices_13, R.id.t2_onlineServices_14};
        onlineServiceCheckBoxes = new CheckBox[onlineServiceChkId.length];
        StringBuilder onlineService = new StringBuilder();
        for (int i = 0; i < onlineServiceChkId.length; i++) {
            onlineServiceCheckBoxes[i] = findViewById(onlineServiceChkId[i]);
            if (onlineServiceCheckBoxes[i].isChecked()) {
                onlineService.append(onlineServiceCheckBoxes[i].getText().toString());
                onlineService.append(" , ");
            }
        }
        houseData.setOnlineServices(onlineService.toString());

        //agriculture
        CheckBox[] agroProductChkBoxes;
        Integer[] agroProductChkIds = {R.id.t2_agro_chk_agroProduct_1, R.id.t2_agro_chk_agroProduct_2, R.id.t2_agro_chk_agroProduct_3, R.id.t2_agro_chk_agroProduct_4,
                R.id.t2_agro_chk_agroProduct_5, R.id.t2_agro_chk_agroProduct_6, R.id.t2_agro_chk_agroProduct_7, R.id.t2_agro_chk_agroProduct_8,};
        agroProductChkBoxes = new CheckBox[agroProductChkIds.length];
        StringBuilder agroProducts = new StringBuilder();
        for (int i = 0; i < agroProductChkBoxes.length; i++) {
            agroProductChkBoxes[i] = findViewById(agroProductChkIds[i]);
            if (agroProductChkBoxes[i].isChecked()) {
                agroProducts.append(agroProductChkBoxes[i].getText().toString());
                agroProducts.append(" , ");
            }
        }
        houseData.setPrimaryAgroProductions(agroProducts.toString());

        CheckBox[] sellProductChkBoxes;
        Integer[] sellProductIds = {R.id.t2_agro_chk_sellProduct_1, R.id.t2_agro_chk_sellProduct_2, R.id.t2_agro_chk_sellProduct_3, R.id.t2_agro_chk_sellProduct_4, R.id.t2_agro_chk_sellProduct_5,
                R.id.t2_agro_chk_sellProduct_6, R.id.t2_agro_chk_sellProduct_7, R.id.t2_agro_chk_sellProduct_8, R.id.t2_agro_chk_sellProduct_9, R.id.t2_agro_chk_sellProduct_10};
        sellProductChkBoxes = new CheckBox[sellProductIds.length];
        StringBuilder sellProductList = new StringBuilder();
        for (int i = 0; i < sellProductIds.length; i++) {
            sellProductChkBoxes[i] = findViewById(sellProductIds[i]);
            if (sellProductChkBoxes[i].isChecked()) {
                sellProductList.append(sellProductChkBoxes[i].getText().toString());
                sellProductList.append(" , ");
            }
        }
        houseData.setSellProductionList(sellProductList.toString());

        //income expense
        CheckBox[] remittanceExpenseChkBoxes;
        Integer[] remittanceExpenseIds = {R.id.t2_expense_chk_remittance_1, R.id.t2_expense_chk_remittance_2, R.id.t2_expense_chk_remittance_3, R.id.t2_expense_chk_remittance_4,
                R.id.t2_expense_chk_remittance_5, R.id.t2_expense_chk_remittance_6, R.id.t2_expense_chk_remittance_7, R.id.t2_expense_chk_remittance_8};
        remittanceExpenseChkBoxes = new CheckBox[remittanceExpenseIds.length];
        StringBuilder remittanceExpenseList = new StringBuilder();
        for (int i = 0; i < remittanceExpenseIds.length; i++) {
            remittanceExpenseChkBoxes[i] = findViewById(remittanceExpenseIds[i]);
            if (remittanceExpenseChkBoxes[i].isChecked()) {
                remittanceExpenseList.append(remittanceExpenseChkBoxes[i].getText().toString());
                remittanceExpenseList.append(" , ");
            }
        }
        houseData.setRemittanceSpentOn(remittanceExpenseList.toString());

        CheckBox[] investmentsChkBoxes;
        Integer[] investmentsIds = {R.id.t2_agro_chk_investments_1, R.id.t2_agro_chk_investments_2, R.id.t2_agro_chk_investments_3, R.id.t2_agro_chk_investments_4, R.id.t2_agro_chk_investments_5};
        investmentsChkBoxes = new CheckBox[investmentsIds.length];
        StringBuilder investmentsList = new StringBuilder();
        for (int i = 0; i < investmentsIds.length; i++) {
            investmentsChkBoxes[i] = findViewById(investmentsIds[i]);
            if (investmentsChkBoxes[i].isChecked()) {
                investmentsList.append(investmentsChkBoxes[i].getText().toString());
                investmentsList.append(" , ");
            }
        }
        houseData.setLastYearInvestments(investmentsList.toString());

        CheckBox[] cashSourceChkBoxes;
        Integer[] cashSourceIds = {R.id.t2_agro_chk_addCash_1, R.id.t2_agro_chk_addCash_2, R.id.t2_agro_chk_addCash_3, R.id.t2_agro_chk_addCash_4, R.id.t2_agro_chk_addCash_5};
        cashSourceChkBoxes = new CheckBox[cashSourceIds.length];
        StringBuilder cashSourceList = new StringBuilder();
        for (int i = 0; i < cashSourceIds.length; i++) {
            cashSourceChkBoxes[i] = findViewById(cashSourceIds[i]);
            if (cashSourceChkBoxes[i].isChecked()) {
                cashSourceList.append(cashSourceChkBoxes[i].getText().toString());
                cashSourceList.append(" , ");
            }
        }
        houseData.setAdditionalCashSource(cashSourceList.toString());

        CheckBox[] loanSourceChkBoxes;
        Integer[] loanSourceIds = {R.id.t2_income_chk_loanSource_1, R.id.t2_income_chk_loanSource_2, R.id.t2_income_chk_loanSource_3, R.id.t2_income_chk_loanSource_4, R.id.t2_income_chk_loanSource_5};
        loanSourceChkBoxes = new CheckBox[loanSourceIds.length];
        StringBuilder loanSourceList = new StringBuilder();
        for (int i = 0; i < loanSourceIds.length; i++) {
            loanSourceChkBoxes[i] = findViewById(loanSourceIds[i]);
            if (loanSourceChkBoxes[i].isChecked()) {
                loanSourceList.append(loanSourceChkBoxes[i].getText().toString());
                loanSourceList.append(" , ");
            }
        }
        houseData.setLoanSource(loanSourceList.toString());

        //prakop
        CheckBox[] prakopChkBoxes;
        Integer[] prakopIds = {R.id.t2_prakop_chk_prakopAffected_1, R.id.t2_prakop_chk_prakopAffected_2, R.id.t2_prakop_chk_prakopAffected_3, R.id.t2_prakop_chk_prakopAffected_4, R.id.t2_prakop_chk_prakopAffected_5,
                R.id.t2_prakop_chk_prakopAffected_6, R.id.t2_prakop_chk_prakopAffected_7, R.id.t2_prakop_chk_prakopAffected_8, R.id.t2_prakop_chk_prakopAffected_9, R.id.t2_prakop_chk_prakopAffected_10};
        prakopChkBoxes = new CheckBox[prakopIds.length];
        StringBuilder prakopList = new StringBuilder();
        for (int i = 0; i < prakopIds.length; i++) {
            prakopChkBoxes[i] = findViewById(prakopIds[i]);
            if (prakopChkBoxes[i].isChecked()) {
                prakopList.append(prakopChkBoxes[i].getText().toString());
                prakopList.append(" , ");
            }
        }
        houseData.setAffectedByCalamitiesList(prakopList.toString());

        CheckBox[] assetInsuranceChkBoxes;
        Integer[] assetInsuranceIds = {R.id.t2_prakop_chk_insurance_1, R.id.t2_prakop_chk_insurance_2, R.id.t2_prakop_chk_insurance_3, R.id.t2_prakop_chk_insurance_4, R.id.t2_prakop_chk_insurance_5};
        assetInsuranceChkBoxes = new CheckBox[assetInsuranceIds.length];
        StringBuilder assetInsuranceList = new StringBuilder();
        for (int i = 0; i < assetInsuranceIds.length; i++) {
            assetInsuranceChkBoxes[i] = findViewById(assetInsuranceIds[i]);
            if (assetInsuranceChkBoxes[i].isChecked()) {
                assetInsuranceList.append(assetInsuranceChkBoxes[i].getText().toString());
                assetInsuranceList.append(" , ");
            }
        }
        houseData.setAffectedByCalamitiesList(assetInsuranceList.toString());

        CheckBox[] minimizeDisasterChkBoxes;
        Integer[] minimizeDisasterIds = {R.id.t2_prakop_chk_minDisaster_1, R.id.t2_prakop_chk_minDisaster_2, R.id.t2_prakop_chk_minDisaster_3,
                R.id.t2_prakop_chk_minDisaster_4, R.id.t2_prakop_chk_minDisaster_5, R.id.t2_prakop_chk_minDisaster_6};
        minimizeDisasterChkBoxes = new CheckBox[minimizeDisasterIds.length];
        StringBuilder minimizeDisasterList = new StringBuilder();
        for (int i = 0; i < minimizeDisasterIds.length; i++) {
            minimizeDisasterChkBoxes[i] = findViewById(minimizeDisasterIds[i]);
            if (minimizeDisasterChkBoxes[i].isChecked()) {
                minimizeDisasterList.append(minimizeDisasterChkBoxes[i].getText().toString());
                minimizeDisasterList.append(" , ");
            }
        }
        houseData.setStepsTakenToMinimizeDisasterEffects(minimizeDisasterList.toString());

        //social
        CheckBox[] childInfluenceChkBoxes;
        Integer[] childInfluenceIds = {R.id.t2_social_chk_influence_1, R.id.t2_social_chk_influence_2, R.id.t2_social_chk_influence_3};
        childInfluenceChkBoxes = new CheckBox[childInfluenceIds.length];
        StringBuilder childInfluenceList = new StringBuilder();
        for (int i = 0; i < childInfluenceIds.length; i++) {
            childInfluenceChkBoxes[i] = findViewById(childInfluenceIds[i]);
            if (childInfluenceChkBoxes[i].isChecked()) {
                childInfluenceList.append(childInfluenceChkBoxes[i].getText().toString());
                childInfluenceList.append(" , ");
            }
        }
        houseData.setChildrenBadInfluenceOn(childInfluenceList.toString());

        CheckBox[] femaleAssetChkBoxes;
        Integer[] femaleAssetIds = {R.id.t2_social_chk_femaleAsset_1, R.id.t2_social_chk_femaleAsset_2, R.id.t2_social_chk_femaleAsset_3,
                R.id.t2_social_chk_femaleAsset_4, R.id.t2_social_chk_femaleAsset_5, R.id.t2_social_chk_femaleAsset_6};
        femaleAssetChkBoxes = new CheckBox[femaleAssetIds.length];
        StringBuilder femaleAssetList = new StringBuilder();
        for (int i = 0; i < femaleAssetIds.length; i++) {
            femaleAssetChkBoxes[i] = findViewById(femaleAssetIds[i]);
            if (femaleAssetChkBoxes[i].isChecked()) {
                femaleAssetList.append(femaleAssetChkBoxes[i].getText().toString());
                femaleAssetList.append(" , ");
            }
        }
        houseData.setAssetsOnFemaleName(femaleAssetList.toString());

        CheckBox[] socialAllowanceChkBoxes;
        Integer[] socialAllowanceIds = {R.id.t2_socail_chk_allowance_1, R.id.t2_socail_chk_allowance_2, R.id.t2_socail_chk_allowance_3,
                R.id.t2_socail_chk_allowance_4, R.id.t2_socail_chk_allowance_5, R.id.t2_socail_chk_allowance_6};
        socialAllowanceChkBoxes = new CheckBox[socialAllowanceIds.length];
        StringBuilder socialAllowanceList = new StringBuilder();
        for (int i = 0; i < socialAllowanceIds.length; i++) {
            socialAllowanceChkBoxes[i] = findViewById(socialAllowanceIds[i]);
            if (socialAllowanceChkBoxes[i].isChecked()) {
                socialAllowanceList.append(socialAllowanceChkBoxes[i].getText().toString());
                socialAllowanceList.append(" , ");
            }
        }
        houseData.setAllowanceListTakenByFamily(socialAllowanceList.toString());
    }

    private void renderFamilyInputDataLayout() {

        // Initialize all layout first
        int MAX_FAMILY_COUNT = 10;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < MAX_FAMILY_COUNT; i++) {
            familyMember[i] = inflater.inflate(R.layout.t3_individual_data, null);
            familyData[i] = new FamilyMemberData();  //initialize empty values
        }

        // Show Single layout at first
        renderFamilyDataWithCount(1);

        layout_individualData = findViewById(R.id.t3_individualDataLayout);//linear layout holding all members
        spinFamilyCount = findViewById(R.id.t3_familyCount);

        spinFamilyCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Render as per user selection
                renderFamilyDataWithCount(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        listenChangeFamilyData0(0);        // and hide layout for each views
        listenChangeFamilyData1(1);
        listenChangeFamilyData2(2);
        listenChangeFamilyData3(3);
        listenChangeFamilyData4(4);
        listenChangeFamilyData5(5);
        listenChangeFamilyData6(6);
        listenChangeFamilyData7(7);
        listenChangeFamilyData8(8);
        listenChangeFamilyData9(9);

        handleValueChanges0(0);       //for radio buttons
        handleValueChanges1(1);       //for radio buttons
        handleValueChanges2(2);       //for radio buttons
        handleValueChanges3(3);
        handleValueChanges4(4);
        handleValueChanges5(5);
        handleValueChanges6(6);
        handleValueChanges7(7);
        handleValueChanges8(8);
        handleValueChanges9(9);
    }

    private void handleValueChanges0(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges1(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges2(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges3(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges4(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges5(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges6(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges7(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges8(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void handleValueChanges9(final int familyIndex) {
        RadioGroup rgGender = familyMember[familyIndex].findViewById(R.id.t3_rg_gender);
        RadioGroup rgRelationToOwner = familyMember[familyIndex].findViewById(R.id.t3_rg_relationToOwner);

        RadioGroup rgEmail = familyMember[familyIndex].findViewById(R.id.t3_rg_email);
        RadioGroup rgLeaveHouse = familyMember[familyIndex].findViewById(R.id.t3_rg_leftHouse);
        RadioGroup rgLeaveHouseReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveHouse_reason);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setGender(value);
            }
        });

        rgRelationToOwner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRelationToOwner(value);
            }
        });


        rgEmail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rad_email_y) {
                    familyData[familyIndex].setHasEmail("1");
                    familyData[familyIndex].setHasNoEmail("0");
                } else {
                    familyData[familyIndex].setHasEmail("0");
                    familyData[familyIndex].setHasNoEmail("1");
                }
            }
        });

        rgLeaveHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_radio_leftHouse_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.VISIBLE);

                    familyData[familyIndex].setHasLeftHome6Month("1");
                    familyData[familyIndex].setHasNotLeftHome6Month("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);

                    familyData[familyIndex].setHasLeftHome6Month("0");
                    familyData[familyIndex].setHasNotLeftHome6Month("1");
                    familyData[familyIndex].setLeaveHome_place("0");
                    familyData[familyIndex].setLeaveHome_reason("0");
                    EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);
                    editLeaveHouseLocation.setText("");
                }
            }
        });

        rgLeaveHouseReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveHome_reason(reason);
            }
        });

        //education
        RadioGroup rgEducation = familyMember[familyIndex].findViewById(R.id.t3_rg_education);
        RadioGroup rgSchoolType = familyMember[familyIndex].findViewById(R.id.t3_rg_school_type);
        RadioGroup rgSchoolLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_school_level);
        final RadioGroup rgSchoolLeaveReason = familyMember[familyIndex].findViewById(R.id.t3_rg_leaveSchool_reason);

        rgEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String education = selectedValue.getText().toString();
                familyData[familyIndex].setEducation(education);
            }
        });

        rgSchoolType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String type = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolType(type);

                if (i == R.id.t3_rb_leaveSchool) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);
                    familyData[familyIndex].setLeaveSchool_reason("0");
                }
            }
        });

        rgSchoolLeaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String reason = selectedValue.getText().toString();
                familyData[familyIndex].setLeaveSchool_reason(reason);
            }
        });

        rgSchoolLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String level = selectedValue.getText().toString();
                familyData[familyIndex].setSchoolLevel(level);
            }
        });

        //rojgari
        RadioGroup rgJobs = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobs);
        RadioGroup rgJobAbroadCountries = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_jobCountry);
        RadioGroup rgRemittance = familyMember[familyIndex].findViewById(R.id.t3_rojgari_rg_remittance);

        rgJobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_job_abroad) {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
                }

                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String jobType = selectedValue.getText().toString();
                familyData[familyIndex].setIncomeSource(jobType);
            }
        });

        rgJobAbroadCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String country = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_country(country);
            }
        });

        rgRemittance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setAbroad_moneyTransfer(value);
            }
        });

        RadioGroup rgBankAC = familyMember[familyIndex].findViewById(R.id.t3_rg_bankAC);
        RadioGroup rgATM = familyMember[familyIndex].findViewById(R.id.t3_rg_atm);
        RadioGroup rgOnlineBank = familyMember[familyIndex].findViewById(R.id.t3_rg_onlineBanking);
        RadioGroup rgDepositRegular = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular);
        RadioGroup rgDepositTo = familyMember[familyIndex].findViewById(R.id.t3_rg_depositregular_to);

        rgBankAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_bankAC_y) {
                    familyData[familyIndex].setHasBankAC("1");
                    familyData[familyIndex].setHasNoBankAC("0");
                } else {
                    familyData[familyIndex].setHasNoBankAC("1");
                    familyData[familyIndex].setHasBankAC("0");
                }
            }
        });

        rgATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_atm_y) {
                    familyData[familyIndex].setHasATM("1");
                    familyData[familyIndex].setHasNoATM("0");
                } else {
                    familyData[familyIndex].setHasNoATM("1");
                    familyData[familyIndex].setHasATM("0");
                }
            }
        });

        rgOnlineBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rg_onlineBanking_y) {
                    familyData[familyIndex].setUseOnlineBanking("1");
                    familyData[familyIndex].setNotUseOnlineBanking("0");
                } else {
                    familyData[familyIndex].setNotUseOnlineBanking("1");
                    familyData[familyIndex].setUseOnlineBanking("0");
                }
            }
        });

        rgDepositRegular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_depositregular_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setRegularDeposit("1");
                    familyData[familyIndex].setNotRegularDeposit("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);
                    familyData[familyIndex].setNotRegularDeposit("1");
                    familyData[familyIndex].setRegularDeposit("0");
                    familyData[familyIndex].setRegularDeposit_to("0");
                }
            }
        });

        rgDepositTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setRegularDeposit_to(value);
            }
        });

        //health
        RadioGroup rgHealthStatus = familyMember[familyIndex].findViewById(R.id.t3_health_rg_healthstatus);
        rgHealthStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabled) {
                    familyData[familyIndex].setIsDisabled("1");
                    familyData[familyIndex].setIsHealthy("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setIsHealthy("1");
                    familyData[familyIndex].setIsDisabled("0");
                    familyData[familyIndex].setDisabilityType("0");
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgDisabilityType = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitytype);
        rgDisabilityType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setDisabilityType(value);
            }
        });

        RadioGroup rgDisabilityCard = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disabilitycard);
        rgDisabilityCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_health_rb_disabilitycard_y) {
                    familyData[familyIndex].setHasDisabilityCard("1");
                    familyData[familyIndex].setHasNoDisabilityCard("0");
                } else {
                    familyData[familyIndex].setHasDisabilityCard("0");
                    familyData[familyIndex].setHasNoDisabilityCard("1");
                }
            }
        });

        RadioGroup rgDisease12Month = familyMember[familyIndex].findViewById(R.id.t3_health_rg_disease12month);
        rgDisease12Month.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_health_rb_disease12month_y) {
                    familyData[familyIndex].setHasDisease12Month("1");
                    familyData[familyIndex].setNoDisease12Month("0");
                } else {
                    familyData[familyIndex].setHasDisease12Month("0");
                    familyData[familyIndex].setNoDisease12Month("1");
                }
            }
        });

        RadioGroup rgLongtermDisease = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_disease);
        rgLongtermDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.t3_rb_longterm_disease_y) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasLongTermDisease("1");
                    familyData[familyIndex].setNoLongTermDisease("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
                    familyData[familyIndex].setNoLongTermDisease("1");
                    familyData[familyIndex].setHasLongTermDisease("0");
                    familyData[familyIndex].setLongTermDiseaseName("0");
                }
            }
        });


        RadioGroup rgLongTermDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_health_rg_longterm_diseaselist);
        rgLongTermDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setLongTermDiseaseName(value);
            }
        });

        RadioGroup rgCommunicableDisease = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_disease);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_communicable_disease_y) {
                    familyData[familyIndex].setHasCommunicableDisease("1");
                    familyData[familyIndex].setNoCommunicableDisease("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.VISIBLE);
                } else {
                    familyData[familyIndex].setNoCommunicableDisease("1");
                    familyData[familyIndex].setHasCommunicableDisease("0");
                    familyData[familyIndex].setCommunicableDiseaseName("0");
                    familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);
                }
            }
        });

        RadioGroup rgCommunicableDiseaseName = familyMember[familyIndex].findViewById(R.id.t3_rg_communicable_diseaselist);
        rgCommunicableDiseaseName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setCommunicableDiseaseName(value);
            }
        });


        //social
        RadioGroup rgSocialIdentity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_identity);
        rgSocialIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialIdentity(value);

                if (i == R.id.t3_rb_social_security) {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.VISIBLE);
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
                    familyData[familyIndex].setSocialSecurity_type("0");
                }
            }
        });

        RadioGroup rgSocialSecurity = familyMember[familyIndex].findViewById(R.id.t3_rg_social_security);
        rgSocialSecurity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setSocialSecurity_type(value);
                familyData[familyIndex].setSocialIdentity("0");
            }
        });

        RadioGroup rgMaritalStatus = familyMember[familyIndex].findViewById(R.id.t3_rg_marital_status);
        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setMaritalStatus(value);
            }
        });

        RadioGroup rgHasTraining = familyMember[familyIndex].findViewById(R.id.t3_rg_received_training);
        rgHasTraining.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_rb_has_received_training) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setHasReceivedTraining("1");
                    familyData[familyIndex].setNotReceivedTraining("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
                    familyData[familyIndex].setNotReceivedTraining("1");
                    familyData[familyIndex].setHasReceivedTraining("0");
                }
            }
        });

        RadioGroup rgPoliticalInvolvement = familyMember[familyIndex].findViewById(R.id.t3_social_rg_politicalinvolvement);
        rgPoliticalInvolvement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.t3_social_rb_has_politicalinvolvement) {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.VISIBLE);
                    familyData[familyIndex].setIsPoliticalInfluencer("1");
                    familyData[familyIndex].setIsNotPoliticalInfluencer("0");
                } else {
                    familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);
                    familyData[familyIndex].setIsNotPoliticalInfluencer("1");
                    familyData[familyIndex].setIsPoliticalInfluencer("0");
                    familyData[familyIndex].setPoliticalInfluencerLevel("0");
                }
            }
        });

        RadioGroup rgPoliticalLevel = familyMember[familyIndex].findViewById(R.id.t3_rg_political_level);
        rgPoliticalLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setPoliticalInfluencerLevel(value);
            }
        });

        RadioGroup rgTransportJob = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_job);
        rgTransportJob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelWork(value);
            }
        });
        RadioGroup rgTransportBusiness = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_business);
        rgTransportBusiness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelBusiness(value);
            }
        });
        RadioGroup rgTransportSchool = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_school);
        rgTransportSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelEducation(value);
            }
        });
        RadioGroup rgTransportOther = familyMember[familyIndex].findViewById(R.id.t3_rg_transport_other);
        rgTransportOther.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedValue = familyMember[familyIndex].findViewById(i);
                String value = selectedValue.getText().toString();
                familyData[familyIndex].setTravelOthers(value);
            }
        });
    }

    private void getEditTextValues0(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues1(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues2(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues3(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues4(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues5(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues6(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues7(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues8(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEditTextValues9(int familyIndex) {
        EditText editName = familyMember[familyIndex].findViewById(R.id.t3_edit_name);
        EditText editCast = familyMember[familyIndex].findViewById(R.id.t3_edit_cast);
        EditText editAge = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        EditText editBirthplace = familyMember[familyIndex].findViewById(R.id.t3_edit_birthplace);

        EditText editLeaveHouseLocation = familyMember[familyIndex].findViewById(R.id.t3_edit_leftHouse_location);

        EditText editSkill = familyMember[familyIndex].findViewById(R.id.t3_skill_extra);

        try {
            familyData[familyIndex].setName(editName.getText().toString());
            familyData[familyIndex].setCast(editCast.getText().toString());
            familyData[familyIndex].setAge(editAge.getText().toString());
            familyData[familyIndex].setBirthplace(editBirthplace.getText().toString());

            if (editLeaveHouseLocation.getText().toString().equals("")) {
                familyData[familyIndex].setLeaveHome_place("0");
            } else {
                familyData[familyIndex].setLeaveHome_place(editLeaveHouseLocation.getText().toString());
            }

            if (!editSkill.getText().toString().equals("")) {
                familyData[familyIndex].setSkills(familyData[familyIndex].getSkills() + editSkill.getText().toString() + " , ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: General Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCheckBoxValues0(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues1(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues2(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues3(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues4(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues5(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues6(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues7(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues8(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void getCheckBoxValues9(int familyIndex) {
        CheckBox[] chkBoxes;
        Integer[] chkBoxesId = {R.id.t3_skill_1, R.id.t3_skill_2, R.id.t3_skill_3, R.id.t3_skill_4, R.id.t3_skill_5,
                R.id.t3_skill_6, R.id.t3_skill_7, R.id.t3_skill_8, R.id.t3_skill_9, R.id.t3_skill_10,
                R.id.t3_skill_11, R.id.t3_skill_12, R.id.t3_skill_13, R.id.t3_skill_14, R.id.t3_skill_15,};

        chkBoxes = new CheckBox[chkBoxesId.length];
        StringBuilder sbSkills = new StringBuilder();
        for (int i = 0; i < chkBoxesId.length; i++) {
            chkBoxes[i] = familyMember[familyIndex].findViewById(chkBoxesId[i]);
            if (chkBoxes[i].isChecked()) {
                sbSkills.append(chkBoxes[i].getText().toString());
                sbSkills.append(" , ");
            }
        }
        familyData[familyIndex].setSkills(sbSkills.toString());

        CheckBox[] vaccineChkBoxes;
        Integer[] vaccineChkId = {R.id.t3_vaccine_0};
        vaccineChkBoxes = new CheckBox[vaccineChkId.length];
        StringBuilder sbVaccines = new StringBuilder();
        for (int i = 0; i < vaccineChkId.length; i++) {
            vaccineChkBoxes[i] = familyMember[familyIndex].findViewById(vaccineChkId[i]);
            if (vaccineChkBoxes[i].isChecked()) {
                sbVaccines.append(vaccineChkBoxes[i].getText().toString());
                sbVaccines.append(" , ");
            }
        }
        familyData[familyIndex].setUsesVaccine(sbVaccines.toString());

        CheckBox[] socialChkBoxes;
        Integer[] socialChkId = {
                R.id.t3_social_inv_1, R.id.t3_social_inv_2, R.id.t3_social_inv_3, R.id.t3_social_inv_4,
                R.id.t3_social_inv_5, R.id.t3_social_inv_6, R.id.t3_social_inv_7, R.id.t3_social_inv_8,
                R.id.t3_social_inv_9, R.id.t3_social_inv_10, R.id.t3_social_inv_11, R.id.t3_social_inv_12};
        socialChkBoxes = new CheckBox[socialChkId.length];
        StringBuilder sbSocialInv = new StringBuilder();
        for (int i = 0; i < socialChkId.length; i++) {
            socialChkBoxes[i] = familyMember[familyIndex].findViewById(socialChkId[i]);
            if (socialChkBoxes[i].isChecked()) {
                sbSocialInv.append(socialChkBoxes[i].getText().toString());
                sbSocialInv.append(" , ");
            }
        }
        familyData[familyIndex].setSocialInvolvements(sbSocialInv.toString());

        CheckBox[] trainChkBoxes;
        Integer[] trainChkId = {R.id.t3_train_1, R.id.t3_train_2, R.id.t3_train_3, R.id.t3_train_4, R.id.t3_train_5};
        trainChkBoxes = new CheckBox[trainChkId.length];
        StringBuilder sbTrainInv = new StringBuilder();
        for (int i = 0; i < trainChkId.length; i++) {
            trainChkBoxes[i] = familyMember[familyIndex].findViewById(trainChkId[i]);
            if (trainChkBoxes[i].isChecked()) {
                sbTrainInv.append(trainChkBoxes[i].getText().toString());
                sbTrainInv.append(" , ");
            }
        }
        familyData[familyIndex].setTrainingList(sbTrainInv.toString());
    }

    private void listenChangeFamilyData0(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData1(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData2(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData3(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData4(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData5(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData6(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData7(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData8(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void listenChangeFamilyData9(final int familyIndex) {

        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveHome).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifLeaveSchool).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_jobabroad).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_ifDepositregular).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_health_layout_disabilitytype).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_longterm_diseases).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_health_layout_communicable_diseases).setVisibility(View.GONE);

        familyMember[familyIndex].findViewById(R.id.t3_social_layout_socialsecurity).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_trainings).setVisibility(View.GONE);
        familyMember[familyIndex].findViewById(R.id.t3_layout_involvement_type).setVisibility(View.GONE);

        EditText age0 = familyMember[familyIndex].findViewById(R.id.t3_edit_age);
        age0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int age = Integer.parseInt(charSequence.toString());
                    if (age > 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.GONE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.GONE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_type).setVisibility(View.VISIBLE);
                        familyMember[familyIndex].findViewById(R.id.t3_layout_school_level).setVisibility(View.VISIBLE);
                    }

                    if (age >= 15) {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_rojgari_layout_age15).setVisibility(View.GONE);
                    }

                    if (age >= 16) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_maritalstatus).setVisibility(View.GONE);
                    }

                    if (age >= 21) {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.VISIBLE);
                    } else {
                        familyMember[familyIndex].findViewById(R.id.t3_social_layout_political_involvement).setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void renderFamilyDataWithCount(int count) {

        layout_individualData = findViewById(R.id.t3_individualDataLayout);
        layout_individualData.removeAllViews();

        for (int i = 0; i < count; i++) {
            TextView tvIndex = familyMember[i].findViewById(R.id.t3_familyIndex);
            tvIndex.setText(i + 1 + "");
            layout_individualData.addView(familyMember[i], layout_individualData.getChildCount());
        }
    }

}
