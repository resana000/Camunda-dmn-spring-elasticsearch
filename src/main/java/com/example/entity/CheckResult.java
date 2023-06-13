package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(indexName = "check_result", type = "check_result")
@NoArgsConstructor
public class CheckResult implements Serializable {

    @Id
    private String id;

    @CreationTimestamp
    private LocalDateTime creationTime;

    private String payload;

    @Field(type = FieldType.Text)
    private List<String> failedChecks = new ArrayList<>();


    public CheckResult(String payload, List<String> failedChecks) {
        this.payload = payload;
        this.failedChecks = failedChecks;
    }
}
