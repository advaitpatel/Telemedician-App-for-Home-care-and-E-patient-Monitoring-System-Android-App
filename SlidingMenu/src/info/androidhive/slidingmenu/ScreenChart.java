package info.androidhive.slidingmenu;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScreenChart extends Activity
{
	private View mChart;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_chart);

		// Getting reference to the button btn_chart
		Button btnChart = (Button) findViewById(R.id.btn_chart);

		// Defining click event listener for the button btn_chart
		OnClickListener clickListener = new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				// Draw the pie Chart
				openChart();
			}
		};

		// Setting event click listener for the button btn_chart of the
		// MainActivity layout
		btnChart.setOnClickListener(clickListener);
	}

	private void openChart()
	{

		// Pie Chart Section Names
		String[] code = new String[] { "Appointments", "Notifications",
				"Emergencies", "Home Visits", "Find Services", "Asked Questions" , "Donations" };

		// Pie Chart Section Value
		double[] distribution = { 6, 17, 20, 4, 33, 9, 3 };

		// Color of each Pie Chart Sections
		int[] colors = { Color.parseColor("#2683C2"), Color.parseColor("#FAA519"), Color.GREEN, Color.parseColor("#13B586"),
				Color.parseColor("#400000"), Color.parseColor("#33FFCC"), Color.parseColor("#EB543B") };

		
		
		// Instantiating CategorySeries to plot Pie Chart
		CategorySeries distributionSeries = new CategorySeries("Your Statastics in Vision Medicare since you logged in");
		
		for (int i = 0; i < distribution.length; i++)
		{
			// Adding a slice with its values and name to the Pie Chart
			distributionSeries.add(code[i], distribution[i]);
		}

		
		
		// Instantiating a renderer for the Pie Chart
		DefaultRenderer defaultRenderer = new DefaultRenderer();
		for (int i = 0; i < distribution.length; i++) 
		{
			SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setDisplayChartValues(true);
			
			//Adding colors to the chart
			defaultRenderer.setBackgroundColor(Color.parseColor("#660033"));
			defaultRenderer.setApplyBackgroundColor(true);
			
			// Adding a renderer for a slice
			defaultRenderer.addSeriesRenderer(seriesRenderer);
		}

		defaultRenderer.setChartTitle("Your Statastics in Vision Medicare since you logged in");
		
		defaultRenderer.setChartTitleTextSize(27);
		
		//it will set size of labels in chart
		defaultRenderer.setLabelsTextSize(15);
		
		//It will set hieghts of legends
		defaultRenderer.setLegendHeight(0);
		
		defaultRenderer.setLegendTextSize(25);
		defaultRenderer.setZoomButtonsVisible(false);

		// Creating an intent to plot bar chart using dataset and
		// multipleRenderer
		// Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
		 //distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");

		// Start Activity
		// startActivity(intent);

		// this part is used to display graph on the xml
		LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
		
		// remove any views before u paint the chart
		chartContainer.removeAllViews();
		
		// drawing pie chart
		mChart = ChartFactory.getPieChartView(getBaseContext(),distributionSeries, defaultRenderer);
		
		// adding the view to the linearlayout
		chartContainer.addView(mChart);

	}

	
}