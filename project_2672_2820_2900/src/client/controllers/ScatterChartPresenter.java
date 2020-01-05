package client.controllers;



import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterChartPresenter {

	public ScatterChartPresenter(Stage aStage, String aChartTitle, String aXTitle, String aYTitle, ArrayList<String[]> someData) {
		this.stage = aStage;
		this.chartTitle = aChartTitle;
		this.xTitle = aXTitle;
		this.yTitle = aYTitle;
		this.data = someData;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayChartSingleSeries() {
		//https://docs.oracle.com/javafx/2/charts/line-chart.htm

		stage.setTitle("Line Chart Sample");
		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel(xTitle);
		yAxis.setLabel(yTitle);

		//creating the chart
		final ScatterChart<Number,Number> scatterChart = 
				new ScatterChart<Number,Number>(xAxis,yAxis);

		scatterChart.setTitle(chartTitle);

		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName(yTitle + " over " + xTitle);
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        //populating the series with data
        for(String[] nextRow: data) {
        	double x = Double.parseDouble(nextRow[0]);
        	double y = Double.parseDouble(nextRow[1]);
        	if (x>maxX)
        		maxX = x;
        	if (x < minX)
        		minX = x;
        	if(y>maxY)
        		maxY = y;
        	if(y<minY)
        		minY = y;
        	series.getData().add(new XYChart.Data(x, y));
        }//end for over all rows
        
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit((int)(0.1*(maxX-minX)));
        xAxis.setLowerBound(minX - xAxis.getTickUnit());
        xAxis.setUpperBound(maxX + xAxis.getTickUnit());

        yAxis.setAutoRanging(false);
        yAxis.setTickUnit((int)(0.1*(maxX-minX)));
        yAxis.setLowerBound(minY-yAxis.getTickUnit());
        yAxis.setUpperBound(maxY+yAxis.getTickUnit());

		scatterChart.getData().add(series);
		
		Scene scene  = new Scene(scatterChart,800,600);
		stage.setScene(scene);
		stage.show();
	}//end display single series

	private Stage stage;
	private String chartTitle;
	private String xTitle;
	private String yTitle;
	private ArrayList<String[]> data;
}//end class

