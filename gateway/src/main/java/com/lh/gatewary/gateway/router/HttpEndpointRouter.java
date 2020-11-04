package com.lh.gatewary.gateway.router;

import java.util.List;

public interface HttpEndpointRouter {

    String route(List<String> endpoint);
}
