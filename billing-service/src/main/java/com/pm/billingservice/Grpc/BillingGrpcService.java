package com.pm.billingservice.Grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.billingServiceGrpc.billingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends billingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        log.info("createBillingAccount fucntion called {}", request.toString());
        //some business logic eg create something , add or remove stuff

        //now sending the response back in the list form ,StreamObserver<BillingResponse>
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }


}
