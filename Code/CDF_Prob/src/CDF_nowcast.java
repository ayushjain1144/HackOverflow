import java.io.*;
import java.util.Scanner;

import org.apache.commons.math3.analysis.interpolation.*;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class CDF_nowcast
{
	double[] y = new double[191];
	double[] f = new double[191];
	
	
	CDF_nowcast(){
	try
	{
		Scanner ybr = new Scanner(new FileReader("y.txt"));
		Scanner fbr = new Scanner(new FileReader("f.txt"));
	int i = 0;
	
	while(ybr.hasNextDouble())
	{	
		y[i] = ybr.nextDouble();
		f[i] = fbr.nextDouble();
		i++;
		System.out.println(i - 1 + "," + y[i - 1] + "," + f[i - 1]);
	
	}
	
		ybr.close();
		fbr.close();


	

	}
	catch(FileNotFoundException e)
	{
		System.out.println("Error");
	}
	
	}
	
	
	public double get_prob(double count)
	{
		
		LinearInterpolator in = new LinearInterpolator();
		PolynomialSplineFunction func = in.interpolate(y, f);
		double prob = func.value(count);
		return prob;

	}
	public static void main(String[] args)
	{

		CDF_nowcast test = new CDF_nowcast();
		
		double prob = test.get_prob(100);
		System.out.println("Value of prob is" + prob);
		
	}
	
	



}