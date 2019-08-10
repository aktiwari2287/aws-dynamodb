package com.anand;

import java.util.Collections;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaJavaAPI implements RequestHandler<Object, GatewayResponse> {

	@Override
	public GatewayResponse handleRequest(Object object, Context context) {
		save();
		String message = "Hello from TechPrimers";
		System.out.println(message);

		GatewayResponse response = new GatewayResponse(message, 200,
				Collections.singletonMap("X-Powered-By", "TechPrimers"), false);
		return response;
	}

	private void save() {
		DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
		Item item = new Item().withPrimaryKey("id", "101").withString("title", "Hello, Anand");
		dynamoDB.getTable("items").putItem(item);

	}
}
