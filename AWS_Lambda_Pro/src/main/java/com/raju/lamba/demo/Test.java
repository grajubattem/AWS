package com.raju.lamba.demo;

import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

public class Test {

	public static void main(String[] args) throws IOException {
		InstanceProfileCredentialsProvider credentials = InstanceProfileCredentialsProvider
				.createAsyncRefreshingProvider(true);
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJAEBHPPVNUH27DFA",
				"Ordf/jWxtBJUkB10V43/M8cjyKK/pe5zpLcemx92");
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(
						new EndpointConfiguration("https://s3.ap-south-1.amazonaws.com", "ap-south-1"))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

		List<Bucket> buckets = s3client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
		
		String body = s3client.getObjectAsString("raju2", "test1.txt");
        System.out.println("Body: " + body);
		
		
		credentials.close();
	}
}
