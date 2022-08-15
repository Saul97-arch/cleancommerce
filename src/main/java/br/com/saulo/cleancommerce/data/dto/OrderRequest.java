package br.com.saulo.cleancommerce.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;

public class OrderRequest {
    @JsonProperty("customerId")
    Long customerId;

    public OrderRequest() {}

    public OrderRequest(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
