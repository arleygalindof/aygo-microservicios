package com.aygo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class TripCostCalculatorHandler implements RequestHandler<Double, TripCostCalculatorHandler.Response> {

    public static class Response {
        public double distance;
        public double total_cost;
        public String currency;

        public Response(double distance, double total_cost, String currency) {
            this.distance = distance;
            this.total_cost = total_cost;
            this.currency = currency;
        }
    }

    @Override
    public Response handleRequest(Double distancia, Context context) {
        double baseFare = 5000.0;
        double costPerKm = 1200.0;
        double totalCost = baseFare + (distancia * costPerKm);

        return new Response(distancia, totalCost, "COP");
    }
}
