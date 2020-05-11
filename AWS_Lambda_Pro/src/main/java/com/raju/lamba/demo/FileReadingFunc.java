package com.raju.lamba.demo;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class FileReadingFunc implements RequestHandler<Object, String> {

	public FileReadingFunc() {
	}

	@Override
	public String handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		InstanceProfileCredentialsProvider credentials = InstanceProfileCredentialsProvider
				.createAsyncRefreshingProvider(true);
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJAEBHPPVNUH27DFA",
				"Ordf/jWxtBJUkB10V43/M8cjyKK/pe5zpLcemx92");
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(
						new EndpointConfiguration("https://s3.ap-south-1.amazonaws.com", "ap-south-1"))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

		String body = s3client.getObjectAsString("raju2", "test1.txt");
		System.out.println("Body1: ================>" + body);
		
		String body2 = s3client.getObjectAsString("raju2", "eCertificate.pdf");
		//System.out.println("Body2: ================>" + body2);
		s3client.putObject("raju2", "/output.txt", body2);
		try {
			credentials.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Hello World - " + input;
	}

}