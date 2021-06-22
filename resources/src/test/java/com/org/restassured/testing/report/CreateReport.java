package com.org.restassured.testing.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateReport {

	public static void createReport(List<ReportModel> reportModel) throws IOException
	{
		 BufferedWriter bw = new BufferedWriter(new FileWriter("report.html"));
		 StringBuilder htmlStringBuilder=new StringBuilder();
		 htmlStringBuilder.append("<h1> TestName </h1>");
		 	htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
		    htmlStringBuilder.append("<tr><td><b>Test</b></td>");
		    htmlStringBuilder.append("<td><b>IsPassed</b></td>");
		    htmlStringBuilder.append("<td><b>Request</b></td>");
		    htmlStringBuilder.append("<td><b>Response</b></td></tr>");
	    try {
	        for(int i=0; i<reportModel.size(); i++) { 		    
	        	htmlStringBuilder.append("<tr><td>"+reportModel.get(i).getTestName()+"</td>");
	 	        htmlStringBuilder.append("<td>"+reportModel.get(i).getResult()+"</td>");
	 	        htmlStringBuilder.append("<td>"+reportModel.get(i).getRequest()+"</td>");
	 	        htmlStringBuilder.append("<td>"+reportModel.get(i).getResponse()+"</td></tr>");
	 	       
	        }
	        htmlStringBuilder.append("</table></body></html>");	       
			bw.write(htmlStringBuilder.toString());
			bw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
