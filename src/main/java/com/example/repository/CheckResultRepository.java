package com.example.repository;

import com.example.entity.CheckResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckResultRepository extends ElasticsearchRepository<CheckResult, String> {

    List<CheckResult> findAll();
}
