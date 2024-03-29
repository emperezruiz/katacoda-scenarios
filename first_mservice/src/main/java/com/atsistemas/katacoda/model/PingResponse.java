package com.atsistemas.katacoda.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PingResponse", description="response data")
public class PingResponse {
	
	@ApiModelProperty(required=true, notes="The response message")
    private String responseMessage;

    public PingResponse() {
		super();
	}

	public PingResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
	
    public String getResponseMessage() {
        return responseMessage;
    }
    
    @Override
	public String toString() {
		return " message: " + responseMessage;
	}
}
