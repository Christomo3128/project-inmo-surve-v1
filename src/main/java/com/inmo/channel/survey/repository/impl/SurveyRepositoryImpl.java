package com.inmo.channel.survey.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmo.channel.survey.business.port.SurveyRepository;
import com.inmo.channel.survey.repository.model.SurveyModel;
import com.inmo.channel.survey.util.exceptions.DatabaseComponent;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SurveyRepositoryImpl implements SurveyRepository {

  @Autowired
  private SuveryJpa suveryJpa;

  @Override
  public Observable<SurveyModel> getSurvey() {
    return Observable.fromCallable(()-> 
    		suveryJpa.findAll())
    		.flatMap(Observable::fromIterable)
            .doOnComplete(() -> log.info("Finished ParameterRepositoryImpl.getParametersByType"))
            .doOnError(ex -> log.error("Error on ParameterRepositoryImpl.getParametersByType: ", ex))
            .onErrorResumeNext(ex -> {
              log.error("Error DatabaseComponent");
              return Observable.error(DatabaseComponent::new);
            });
  }

}
