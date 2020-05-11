package com.raju.lamba.demo;


import java.io.IOException;
import java.util.Base64;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class KishoreTest implements RequestHandler<Object, String> {

	//public final static String SRC_BUCKET_NAME = "dev-dm-uem-response-data";
	public final static String SRC_BUCKET_NAME = "raju2";
	public final static String TARGET_BUCKET_NAME = "rajubucket";
	public final static String SRC_FILE_NAME = "eCertificate.pdf";
	public final static String TARGET_FILE_NAME = "output.pdf";
	public final static String ACCESS_KEY = "AKIAJAEBHPPVNUH27DFA";
	public final static String SECRET_KEY = "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";
	//public final static String END_POINT = "https://sts.us-east-1.amazonaws.com";
	public final static String END_POINT = "https://s3.ap-south-1.amazonaws.com";
	//public final static String REGION = "sts.us-east-1";
	public final static String REGION = "ap-south-1";

	public KishoreTest() {
	}

	@Override
	public String handleRequest(Object input, Context context) {
		try {
			context.getLogger().log("Input: " + input);
			InstanceProfileCredentialsProvider credentials = InstanceProfileCredentialsProvider
					.createAsyncRefreshingProvider(true);
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
					.withEndpointConfiguration(new EndpointConfiguration(END_POINT, REGION))
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			String encodedContent = s3client.getObjectAsString(SRC_BUCKET_NAME, SRC_FILE_NAME);
			// System.out.println("decode_content: ================>" + decode_co
						String decodeContent = decodeData(encodedContent);
						s3client.putObject(TARGET_BUCKET_NAME, "/" + TARGET_FILE_NAME, decodeContent);

						credentials.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return "";
				}

				public String decodeData(String encodedContent) throws IOException {
					//byte[] decodedBytes2 = Base64.getDecoder().decode(encodedContent);
					//String decodedString2 = new String(decodedBytes2);
					System.out.println("decoded2 ==>" + encodedContent);
					return encodedContent;
				}

			}
