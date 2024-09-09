package com.git.mwacha.application.analysis;

import com.git.mwacha.domain.enums.AnalysisStatus;
import com.git.mwacha.infra.api.controller.streams.ResultResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
  private static int count = 0;

  public List<ResultResponse> results(List<Long> ids) {
    count++;
    List<ResultResponse> results = new ArrayList<>();

    for (Long id : ids) {
      results.add(new ResultResponse(id, AnalysisStatus.APPROVED));
    }

    System.out.println(count);
    return results;
  }
}
