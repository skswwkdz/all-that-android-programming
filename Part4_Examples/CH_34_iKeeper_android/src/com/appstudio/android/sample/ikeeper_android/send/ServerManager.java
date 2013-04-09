package com.appstudio.android.sample.ikeeper_android.send;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

public class ServerManager {
	  private HttpPost preProcess(String urlStr, ArrayList<NameValuePair> nameValuePairs)  {
		  HttpPost httpPost = new HttpPost(urlStr);
		  UrlEncodedFormEntity entityRequest;
		  
		  try {
			  entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  throw new RuntimeException(e);
		  }
		  
		  httpPost.setEntity(entityRequest);
		  return httpPost;
	  }
	  
	  private String postProcess(InputStream is)  {
		  int bufferSize = 1024;
		  byte[] buffer = new byte[bufferSize];
		  int readByte = 0;
		  int totalByte = 0;
		  int count = 0;

		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bufferSize * 2);
		  
		  try {
			  while((readByte = is.read(buffer)) != -1) {
				  totalByte += readByte;
				  count ++;
				  byteArrayOutputStream.write(buffer, 0, readByte);
			  }
		  } catch (IOException e) {
			  throw new RuntimeException(e);
		  }        
	    
		  byte[] lDataBytes = byteArrayOutputStream.toByteArray();        
		  return new String(lDataBytes);
	  	}

}