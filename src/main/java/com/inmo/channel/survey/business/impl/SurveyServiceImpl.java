package com.inmo.channel.survey.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmo.channel.survey.business.SurveyService;
import com.inmo.channel.survey.business.port.SurveyRepository;
import com.inmo.channel.survey.repository.model.SurveyModel;

import io.reactivex.Observable;

@Service
public class SurveyServiceImpl implements SurveyService {

  @Autowired
  private SurveyRepository surveyRepository;

  @Override
  public Observable<SurveyModel> getSurvey() {
    return surveyRepository.getSurvey();
  }
}
