package client.controllers;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartPresenter {

	public LineChartPresenter(Stage aStage, String aChartTitle, String aXTitle, String aYTitle, ArrayList<String[]> someData) {
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
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle(chartTitle);
        
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

        
        lineChart.getData().add(series);
        
        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);
        stage.show();
    }//end display single series
	
	private Stage stage;
	private String chartTitle;
	private String xTitle;
	private String yTitle;
	private ArrayList<String[]> data;
}//end class



//Copy-paste code from the page of Oracle
//@SuppressWarnings({ "rawtypes", "unchecked" })
//public void displayMultiSeries(Stage stage) {
//    stage.setTitle("Line Chart Sample");
//    final CategoryAxis xAxis = new CategoryAxis();
//    final NumberAxis yAxis = new NumberAxis();
//     xAxis.setLabel("Month");
//    final LineChart<String,Number> lineChart = 
//            new LineChart<String,Number>(xAxis,yAxis);
//   
//    lineChart.setTitle("Stock Monitoring, 2010");
//                      
//    XYChart.Series series1 = new XYChart.Series();
//    series1.setName("Portfolio 1");
//    
//    series1.getData().add(new XYChart.Data("Jan", 23));
//    series1.getData().add(new XYChart.Data("Feb", 14));
//    series1.getData().add(new XYChart.Data("Mar", 15));
//    series1.getData().add(new XYChart.Data("Apr", 24));
//    series1.getData().add(new XYChart.Data("May", 34));
//    series1.getData().add(new XYChart.Data("Jun", 36));
//    series1.getData().add(new XYChart.Data("Jul", 22));
//    series1.getData().add(new XYChart.Data("Aug", 45));
//    series1.getData().add(new XYChart.Data("Sep", 43));
//    series1.getData().add(new XYChart.Data("Oct", 17));
//    series1.getData().add(new XYChart.Data("Nov", 29));
//    series1.getData().add(new XYChart.Data("Dec", 25));
//    
//    XYChart.Series series2 = new XYChart.Series();
//    series2.setName("Portfolio 2");
//    series2.getData().add(new XYChart.Data("Jan", 33));
//    series2.getData().add(new XYChart.Data("Feb", 34));
//    series2.getData().add(new XYChart.Data("Mar", 25));
//    series2.getData().add(new XYChart.Data("Apr", 44));
//    series2.getData().add(new XYChart.Data("May", 39));
//    series2.getData().add(new XYChart.Data("Jun", 16));
//    series2.getData().add(new XYChart.Data("Jul", 55));
//    series2.getData().add(new XYChart.Data("Aug", 54));
//    series2.getData().add(new XYChart.Data("Sep", 48));
//    series2.getData().add(new XYChart.Data("Oct", 27));
//    series2.getData().add(new XYChart.Data("Nov", 37));
//    series2.getData().add(new XYChart.Data("Dec", 29));
//    
//    XYChart.Series series3 = new XYChart.Series();
//    series3.setName("Portfolio 3");
//    series3.getData().add(new XYChart.Data("Jan", 44));
//    series3.getData().add(new XYChart.Data("Feb", 35));
//    series3.getData().add(new XYChart.Data("Mar", 36));
//    series3.getData().add(new XYChart.Data("Apr", 33));
//    series3.getData().add(new XYChart.Data("May", 31));
//    series3.getData().add(new XYChart.Data("Jun", 26));
//    series3.getData().add(new XYChart.Data("Jul", 22));
//    series3.getData().add(new XYChart.Data("Aug", 25));
//    series3.getData().add(new XYChart.Data("Sep", 43));
//    series3.getData().add(new XYChart.Data("Oct", 44));
//    series3.getData().add(new XYChart.Data("Nov", 45));
//    series3.getData().add(new XYChart.Data("Dec", 44));
//    
//    Scene scene  = new Scene(lineChart,800,600);       
//    lineChart.getData().addAll(series1, series2, series3);
//   
//    stage.setScene(scene);
//    stage.show();
//}//end multi-series
