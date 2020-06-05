package com.inmo.channel.survey.business;

import com.inmo.channel.survey.repository.model.SurveyModel;

import io.reactivex.Observable;

public interface SurveyService {

  Observable<SurveyModel> getSurvey();

}
