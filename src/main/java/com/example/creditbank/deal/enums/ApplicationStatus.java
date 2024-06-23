package com.example.creditbank.deal.enums;

import lombok.Getter;

@Getter
public enum ApplicationStatus {

    PREAPPROVAL("Предварительно одобрено"),
    APPROVAL("Одобрено"),
    CC_DENIED("Кредитная карта отклонена"),
    CC_APPROVED("кредитная карта одобрена"),
    PREPARE_DOCUMENTS("Подготовка документов"),
    DOCUMENTS_CREATED("Документы созданы"),
    CLIENT_DENIED("Клиенту отклонено"),
    DOCUMENT_SIGNED("Документ подписан"),
    CREDIT_ISSUED("Кредит выдан");

    private final String applicationStatus;

    ApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
