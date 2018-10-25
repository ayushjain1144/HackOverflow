package com.t.quakealert;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InformActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    RatingBar rb;
    Button button;
    Intent intent;
    Database myDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        intent=this.getIntent();
        button=findViewById( R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01596-242114"));
                startActivity(intent);
            }
        });

        Toast.makeText(this,intent.getStringExtra("place name"), Toast.LENGTH_LONG).show();
        myDb = new Database(this);
        myDb.insertData("Narayana Institute of Cardiac Sciences","258/A, Bommasandra Industrial Area, Anekal Taluk Landmark: Near To BTL Institute of Technology Hosur Road, Bangalore","1000","3","Cash | Credit card | Debit card | Insurance","3.5","8030474570");

        myDb.insertData("The Perfect Smile Multi-Speciality Dental Clinic","1st Floor, Chennappa Building, Gold Coin Club Road, Landmark: Opposite VMAKS Heights, Bangalore","0","5","Cash | Credit card | Debit card | Insurance","5.0","8033923246");
        myDb.insertData("Nayan Dental Clinic","53/6, Shop Number 9, 1st Floor, A Ramakrishna Reddy Complex, T.M Abbaiaha Reddy Street, Celebrity Layout Road, Doddathogur, Landmark: Near Patalamma Temple, Bangalore","0","2","Cash | Credit card | Debit card | Insurance","3.5","9886262936");

        myDb.insertData("Springleaf Hospital","60/3, Konappana Agrahara, Hosur Main Road Landmark: Next to Andhra Bank & Opposite Infosys Convention Centre & Pillar Number 228 Electronics City, Bangalore","10","10","Cash | Credit card | Debit card | Insurance","3.5","8030630945");




        myDb.insertData("Ramakrishna Smart Hospitals","92/1 B, HP Avenue, Konappana Agrahara, Village Begur Hobli Landmark: Near HP Company Electronics City, Bangalore",
        "6","3","Cash","4.5","8030474595");

        myDb.insertData("Fortis Hospital","154/9 Landmark: Opposite IIM-B Bannerghatta Road, Bangalore","400","100","Cash | Credit card | Debit card | Insurance","4.0","8071108313");

        myDb.insertData("Jayashree Multi Speciality Hospital", "25, 26 &27, 1st Cross,'B-Block, Vishwapriya Nagar Landmark: Near Chowdeshwari Temple Begur, Bangalore", "70","19","Cash | Credit card | Debit card | Insurance","4.0","8030456358");

        myDb.insertData("Greenview Medical Center","20,21, 14th Main Road, Sector V Landmark: Opposite Agara Lake HSR Layout, Bangalore", "55","35","Cash | Online Payment | Credit card | Debit card","4.5","8030656557");

        myDb.insertData("Devi Eye Hospital", "157/A, Sector 5, Outer Ring Road Landmark: Opposite J.S.S Public School HSR Layout, Bangalore", "5","7","Cash","4.0","8033512222");

        myDb.insertData("Prashanth Hospital","Bommanahalli Circle, Hosur Main Road, Bommanahalli , Landmark: Near Bommanahalli Signal Begur Road Bommanahalli, Bangalore", "80","7","Cash | Online Payment | Credit card | Debit card","4.5","8030474595");

        myDb.insertData("Punarjani Ayurveda Multi-Speciality Hospital","14/2, Chikkanayakana Halli Village, Varathur Hubli Landmark: Near Prakriya School Sarjapur Road, Bangalore", "22","6","Cash | Credit card","3.5","8033923246");

        myDb.insertData("Blossom Multispecialty Hospital", "98, Naganathpura Village, Hosa Road, Electronic City Post Landmark: Near Karnataka Bank and Opposite Old RTO Office Electronics City, Bangalore", "40","11","Cash | Online Payment | Credit card | Debit card","4.0","8030474601");

        myDb.insertData("Nayan Dental Clinic", "53/6, Shop Number 9, 1st Floor, A Ramakrishna Reddy Complex, T.M Abbaiaha Reddy Street, Celebrity Layout Road, Doddathogur, Landmark: Near Patalamma Temple, Bangalore", "0","2","Cash | Credit card","3.5","8030656532");


        myDb.insertData("Apollo Hospitals", "154/11, Opp IIM, Bannerghatta Road, Bengaluru - 560 076, Karnataka, India.", "250","25","Cash | Online Payment | Credit card | Debit card","4.5","8026304050");

        myDb.insertData("Apple Hospital", "ITI layout Sector 7; HSR Layout; Bengaluru; Karnataka 560068; India", "13","3","Cash | Credit card | Debit card ","4.0","8034630945");

        myDb.insertData("Infosys Medical Center", "Electronics City Phase 1, Electronic City, Bengaluru, Karnataka", "22","3","Cash | Credit card | Debit card","4.5","8986304050");

        myDb.insertData("Greenview Medical Center- Obstetrician & Gynecologist Bangalore", "20,21, 14th Main Road, Sector V Landmark: Opposite Agara Lake HSR Layout, Bangalore", "55","35","Cash | Online Payment | Credit card | Debit card","4.5","8030656557");

        myDb.insertData("Doctor Levine Memorial Hospital", "17/1, Sarjapur Main Road, Bellandur Gate Landmark: Behind HDFC Bank Bellandur, Bangalore", "23","8","Cash | Credit card | Debit card ","4.0","8030474570");

        myDb.insertData("Punarjani Ayurvedic MultiSpeciality Hospital", "14/2, Chikkanayakana Halli Village, Varathur Hubli Landmark: Near Prakriya School", "22","6","Cash | Credit card ","3.5","8033923246");

        myDb.insertData("Ramakrishna Smart Hospitals Unit III", "92/1 B, HP Avenue, Konappana Agrahara, Village Begur Hobli Landmark: Near HP Company Electronics City, Bangalore", "6","3","Cash | Credit card | Debit card","4.5","8030474595");

        myDb.insertData("Springleaf Dental Clinic", "60/3, Konappana Agrahara, Hosur Main Road Landmark: Next to Andhra Bank & Opposite Infosys Convention Centre & Pillar Number 228 Electronics City, Bangalore", "10","10","Cash | Credit card | Debit card ","4.0","8030630945");

        myDb.insertData("Punarjanma Ayurvedic Wellness Center", " No 15L, Priya Building, Konnappana Agrahara, Electronic City Phase 1, Bangalore - 560100, Near Yellamma Temple And Infosys Gate No 7 ", "21","5","Cash | Credit card | Debit Card","4.3","9152335284");

        myDb.insertData("Government Primary Health Center", "Electronic City, Hosur Road, AECS Layout, A-Block, Singasandra, Bengaluru, Karnataka 560100","11","3","Cash | Debit Card","3.3","9494518424");



        myDb.insertData("Vimalalaya Hospital","NH 7, Electronic City, Near Vimalalaya, Kammasandra, Electronic City, Bengaluru, Karnataka 560100","5","12","Cash","3","8027832133");

















        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
        tv3 = findViewById(R.id.text3);
        tv4 = findViewById(R.id.text4);
        tv5 = findViewById(R.id.text5);
        tv6 = findViewById(R.id.text6);



        button = findViewById(R.id.button2);



        // String s="AShish Rajput";
  //     b = (Button) findViewById(R.id.button);
    //   b.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View view) {
                Cursor res = myDb.getAllData();



                                    StringBuffer buffer = new StringBuffer();
                                    StringBuffer buffer1 = new StringBuffer();
                                    StringBuffer buffer2 = new StringBuffer();
                                    StringBuffer buffer3 = new StringBuffer();
                                    StringBuffer buffer4 = new StringBuffer();
                                    StringBuffer buffer5 = new StringBuffer();
                                    StringBuffer buffer6 = new StringBuffer();
                                    StringBuffer buffer7 = new StringBuffer();

        while (res.moveToNext()) {
                                        buffer = new StringBuffer();
                                        buffer1 = new StringBuffer();
                                        buffer2 = new StringBuffer();
                                        buffer3 = new StringBuffer();

                                        buffer4 = new StringBuffer();
                                        buffer5 = new StringBuffer();
                                        buffer6 = new StringBuffer();
                                        buffer7 = new StringBuffer();


                                        //buffer.append(  res.getString(0) + "\n");

                                        buffer1.append( res.getString(0) + "\n");//

                                        //buffer.append( res.getString(1) + "\n");

                                        buffer2.append( res.getString(1) + "\n");

                                        //buffer.append("Number of beds: " + res.getString(2) + "\n");
                                        buffer3.append("Number of beds: " + res.getString(2) + "\n");

                                        //buffer.append("Number of doctors: " + res.getString(3) + "\n");
                                        buffer4.append("Number of doctors: " + res.getString(3) + "\n");

                                        //buffer.append("Payment modes: " + res.getString(4) + "\n");
                                        buffer5.append( res.getString(4)+ "\n");

                                        //buffer.append("Rating: " + res.getString(5) + "\n");
                                        buffer6.append("Rating: " + res.getString(5) + "\n");

                                        //buffer.append("number: " + res.getString(6) + "\n");
                                        buffer7.append(res.getString(6) + "\n");

                                      //  Long numb=Long.valueOf(buffer7.toString());

                                        String temp = intent.getStringExtra("place name");
                                        temp= temp.trim();
                                        String temp1 = res.getString(0);
                                        temp1= temp1.trim();

                                        if(temp1.equalsIgnoreCase(temp)){
                                            String s = buffer.toString();
                                          //  tv.setText(s);
                                            s = buffer1.toString();
                                            tv1.setText(s);
                                            s = buffer2.toString();
                                            tv2.setText(s);
                                            s = buffer3.toString();
                                            tv3.setText(s);
                                            s = buffer4.toString();
                                            tv4.setText(s);
                                            s = buffer5.toString();
                                            tv5.setText(s);
                                            s = buffer6.toString();
                                            tv6.setText(s);
                                            final String n;
                                            s ="tel:"+buffer7.toString();
                                            n=s;
                                            button.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View arg0) {

                                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                                    intent.setData(Uri.parse("tel:01596-242114"));
                                                    startActivity(intent);
                                                }
                                            });

                                        }

                                    }

              //                 }
            //                }
          //          );




                }

            }




