package com.inmo.channel.survey.business.port;

import com.inmo.channel.survey.repository.model.SurveyModel;

import io.reactivex.Observable;

public interface SurveyRepository {

  Observable<SurveyModel> getSurvey();

}
