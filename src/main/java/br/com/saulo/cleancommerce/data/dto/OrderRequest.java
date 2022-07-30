package br.com.saulo.cleancommerce.data.dto;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
    Long customerId;
}
