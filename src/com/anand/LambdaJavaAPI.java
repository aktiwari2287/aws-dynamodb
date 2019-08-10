package com.anand;

import java.util.Collections;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

public class LambdaJavaAPI implements RequestHandler<Object, GatewayResponse> {

	@Override
	public GatewayResponse handleRequest(Object object, Context context) {
		save(object.toString());
		String message = "Hello from Anand......."+ object;
		System.out.println(message);

		GatewayResponse response = new GatewayResponse(message, 200,
				Collections.singletonMap("X-Powered-By", "Anand..."), false);
		return response;
	}

	private void save(String s) {
		DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
		Item item = new Item().withPrimaryKey("id", String.valueOf(Math.random()%10*100)).withString("title",s);
		dynamoDB.getTable("items").putItem(item);

	}
	public static void main(String[] args) {
		System.out.println(Math.random()%10*100);
	}
}
