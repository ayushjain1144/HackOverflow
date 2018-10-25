//Working

// Just pass the CDF_prob value and the next predicted nowcast value

public class final_prob {
	
	private double CDF_prob;
	private double pred_nowcast;
	
	final_prob(double CDF_prob, double pred_nowcast)
	{
		this.CDF_prob = CDF_prob;
		this.pred_nowcast = pred_nowcast;
		
	}
	private double futuristic_correction()
	{
		//taking M = 6 and m = 4
		double corr = pred_nowcast/2000;
		return corr;
		
	}
	public double final_probability()
	{
		return CDF_prob + futuristic_correction();
	}

	public static void main(String[] args) {
		final_prob p = new final_prob(0.7, 100);
		System.out.println(p.final_probability());

	}

}
