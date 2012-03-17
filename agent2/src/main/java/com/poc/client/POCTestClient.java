package com.poc.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * http://kkovacs.eu/cassandra-vs-mongodb-vs-couchdb-vs-redis
 * http://www.mongodb.org/display/DOCS/Updating#Updating-%24set
 * http://www.mongodb.org/display/DOCS/Updating+Data+in+Mongo
 * http://stackoverflow
 * .com/questions/4185105/ways-to-implement-data-versioning-in-mongodb
 * http://strangeowl.blogspot.com/2010/09/versioning-objects-and-mongodb.html
 * 
 * @author Kaniska_Mandal
 */

public class POCTestClient {

	//private final static String addCustomerXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		//+ "<account id=\"AC1234\" firstname=\"Joe\" lastname=\"Smith\" zipcode=\"2345\">"
	//+ "<location id=\"4678\" country=\"USA\"></location>"
	//+ "<opportunity id=\"8678\" country=\"USA\"></opportunity>"
	//+ "</account>";
	
	private static String addCustomerXML;	
	
	private final static String addRevenueXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<revenue id=\"RV1234\" accountId=\"Joe\" amount=\"23456\">"
			+ "<salesrep name=\"Peter Smith\" territory=\"NA\"></salesrep>"
			+ "</revenue>";

	public static void main(String[] args) {
		try {
			addCustomerXML = readFileAsString("input.xml");
			System.out.print("I am done beore the string ~~~~~~~~~~~~~     " + addCustomerXML);
			testAddCustomer();
			System.out.print("I am done");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws HttpException
	 */
	private static void testAddCustomer() throws IOException, HttpException {

		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8088/dynamic-data-collection/job2/entities/add");
			//HttpPost postRequest = new HttpPost(
			//			"http://localhost:8080/dynamic-data-collection/concept/ebli/add");
			postRequest.setHeader("Accept", "application/xml");

			//StringEntity input = new StringEntity(addRevenueXML);
			StringEntity input = new StringEntity(addCustomerXML);
			input.setContentType("application/xml");
			input.setContentEncoding("UTF-8");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("New Response headers ---");
			System.out.println("Response ---" + response.getEntity());
			
			org.apache.http.Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i].toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
    /** @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path handling could be better, and buffer sizes are hardcoded
    */ 
    private static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = null;
        try {
        	 reader = new BufferedReader(
                     new FileReader(filePath));
             char[] buf = new char[1024];
             int numRead=0;
			while((numRead=reader.read(buf)) != -1){
			    String readData = String.valueOf(buf, 0, numRead);
			    fileData.append(readData);
			    buf = new char[1024];
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
        return fileData.toString();
    }


}
