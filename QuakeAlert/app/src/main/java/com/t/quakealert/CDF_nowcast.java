package com.t.quakealert;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.math3.analysis.interpolation.*;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class CDF_nowcast extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home);}

  double[] y = new double[191];
  double[] f = new double[191];


  CDF_nowcast(){


      AssetManager am = getActivity().getAssets();
      InputStreamReader ims = null;
      BufferedReader reader = null;
      String data = "File not available!";
      try {
          ims = new InputStreamReader(am.open("f.txt"), "UTF-8");
          reader = new BufferedReader(ims);
      } catch (IOException e) {
          e.printStackTrace();
      }
      if(ims != null){
          try {
              String mLine = reader.readLine();
              data = "";
              while(mLine != null){
                  data+= mLine;
                  mLine = reader.readLine();
              }
          } catch (IOException e) {
              e.printStackTrace();
          } finally{
              if (reader != null) {
                  try {
                      reader.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
      return data;

      try {
          //DataInputStream textFileStream = new DataInputStream(getAssets().open("y.txt"));
          BufferedReader ybr = new BufferedReader(new FileReader("y.txt"));
          //Scanner ybr = new Scanner(textFileStream);
          //DataInputStream textFileStream1 = new DataInputStream(getAssets().open("f.txt"));
          //Scanner fbr = new Scanner(textFileStream1);
          BufferedReader fbr = new BufferedReader(new FileReader("f.txt"));
          int i = 0;
          String str;

          while(( str = ybr.readLine()) != null)
          {
              y[i] = Double.parseDouble(str);
              f[i] = Double.parseDouble(fbr.readLine());
              i++;
              System.out.println(i - 1 + "," + y[i - 1] + "," + f[i - 1]);


          }

          ybr.close();
          fbr.close();
      } catch (IOException e) {
          e.printStackTrace();
      }


  }


  public double get_prob(double count)
  {

    LinearInterpolator in = new LinearInterpolator();
    PolynomialSplineFunction func = in.interpolate(y, f);
    double prob = func.value(count);
    return prob;

  }
  public static double main(String[] args)
  {

    CDF_nowcast test = new CDF_nowcast();

    double prob = test.get_prob(100);
    System.out.println("Value of prob is" + prob);
    return prob;

  }





}