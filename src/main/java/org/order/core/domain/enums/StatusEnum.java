package org.order.core.domain.enums;

public enum StatusEnum {
    PENDENTE("PENDENTE"),

    CALCULADO("CALCULADO"),

    FALHOU("FALHOU");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
