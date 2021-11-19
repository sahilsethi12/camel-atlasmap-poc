package com.hdfc.authentication;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.springframework.stereotype.Component;

@Component
public class PlaneService extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		

		from("direct:bookplane").log("In book Plane and Body and Header is ${header.id}  is ${body}").
		saga()
        .propagation(SagaPropagation.SUPPORTS)
        .option("id", header("id"))
        .option("body", body())
        .compensation("direct:cancelPurchase")
        .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
        .removeHeader("CamelHttpPath")
        .log("Buying flight ${header.id}")
        .setHeader("CamelHttpMethod", constant("POST"))
        .setHeader("mode",constant("plane"))
        .log("In book plane Ending and Body and Header is ${header.id} ${header.mode} is ${body}")
        .to("http4://localhost:8082/test/payment?bridgeEndpoint=true")
        .log("Payment for flight ${header.id} done");


	}
}