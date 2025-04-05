/* Copyright 2008 Misys PLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License. 
 */
package com.misyshealthcare.connect.ihe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * @author Jim Firby
 * @version 2.0 - Nov 28, 2005
 */
public class HttpChannel {

	/* Logger for problems during HTTP exchanges */
  private static final Logger log = Logger.getLogger(HttpChannel.class);

	/* The connection to make */
	private IConnectionDescription connection = null;
	
	/**
	 * Create a new channel that will communicate with the system
	 * with the given connection description.  
	 * 
	 * @param connection The connection to communicate with
	 */
	public HttpChannel(IConnectionDescription connection) {
		this.connection = connection;
	}
	
	/**
	 * Retrieve an HTTP resource.
	 * 
	 * @return The URL path to the page we want
	 * @throws IOException When the GET cannot be made because of a communication error
	 */
	public InputStream get(String path) throws IOException {
		// Get an HttpClient
		HttpClient client = ConnectionFactory.getHttpConnection(connection);
		String urlPath = getUrlPath(path);
		// Build the HTTP GET method
		GetMethod method = new GetMethod(urlPath);
		// Send it
		try {
      // Execute the method.
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        log.error("Http GET failed: " + method.getStatusLine());
      }
		} catch (HttpException e) {
			log.error("Http error doing GET on '" + urlPath + "'", e);
			throw e;
		} catch (IOException e) {
			log.error("IO error doing GET on '" + urlPath + "'", e);
			throw e;
		}
		// Read the reply
		InputStream response = null;
		try {
			response = method.getResponseBodyAsStream();
		} catch (IOException e) {
			// Can't read response
			log.error("Cannot read GET body from '" + urlPath + "'", e);
			method.releaseConnection();
			throw e;
		}
		// Close the connection
		//method.releaseConnection();
		// Done
		return response;
	}
		
	/**
	 * Parse the path so we can remove the host and port (so TLS will work too).
	 * This method is public to support MESA test logging.
	 * 
	 * @param path The URL path to be parsed
	 * @return The parsed out path to the resource, minus host and port
	 */
	public static String getUrlPath(String path) {
		// Parse the path so we can remove the host and port (so TLS will work too)
		URL url;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// If not a valid URL, then just accept as it
			return path;
		}
		// Get out the connection path
		String urlPath = url.getPath();
		String urlQuery = url.getQuery();
		if ((urlQuery != null) && !urlQuery.trim().equals("")) {
			urlPath = urlPath + "?" + urlQuery;
		}
		String urlFragment = url.getRef();
		if ((urlFragment != null) && !urlFragment.trim().equals("")) {
			urlPath = urlPath + "#" + urlFragment;
		}
		// Done
		return urlPath;
	}
	
	public static String getUrlHost(String path) {
		// Parse the path so we can remove the host and port (so TLS will work too)
		URL url;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// If not a valid URL, then just accept as it
			return path;
		}
		// Done
		return url.getHost();
	}

	public static int getUrlPort(String path) {
		// Parse the path so we can remove the host and port (so TLS will work too)
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// If not a valid URL, then just accept as it
			return -1;
		}
		// Done
		int port = url.getPort();
		if (port < 0)	port = url.getDefaultPort();
		return port;
	}

	public static String getUrlProtocol(String path) {
		// Parse the path so we can remove the host and port (so TLS will work too)
		URL url;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// If not a valid URL, then just accept as it
			return path;
		}
		// Done
		return url.getProtocol();
	}

}
