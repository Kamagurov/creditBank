

# StatementStatusHistoryDto


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**StatusEnum**](#StatusEnum) |  |  |
|**time** | **OffsetDateTime** |  |  |
|**changeType** | [**ChangeTypeEnum**](#ChangeTypeEnum) |  |  |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| PENDING | &quot;PENDING&quot; |
| APPROVED | &quot;APPROVED&quot; |
| REJECTED | &quot;REJECTED&quot; |



## Enum: ChangeTypeEnum

| Name | Value |
|---- | -----|
| CREATED | &quot;CREATED&quot; |
| UPDATED | &quot;UPDATED&quot; |
| DELETED | &quot;DELETED&quot; |



