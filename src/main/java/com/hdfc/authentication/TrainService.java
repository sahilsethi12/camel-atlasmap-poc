package com.hdfc.authentication;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.springframework.stereotype.Component;

@Component
public class TrainService extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		getContext().addService(new org.apache.camel.impl.saga.InMemorySagaService());

		from("direct:booktrain").log("In book train and Body and Header is ${header.id} ${header.mode} is ${body}")
				.saga().propagation(SagaPropagation.SUPPORTS).option("id", header("id")).option("body", body())

				.compensation("direct:cancelPurchase").transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
				.removeHeader("CamelHttpPath").log("Buying train ${header.id}")
				.setHeader("CamelHttpMethod", constant("POST")).setHeader("mode", constant("train"))
				.to("http4://localhost:8082/test/payment?bridgeEndpoint=true")
				.log("Payment for train ${header.id} done")
				.log("In book train Ending and Body and Header is ${header.id} ${header.mode} is ${body}");

	}
}