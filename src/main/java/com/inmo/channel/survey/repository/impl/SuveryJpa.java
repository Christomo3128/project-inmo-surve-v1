package com.inmo.channel.survey.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inmo.channel.survey.repository.model.SurveyModel;

public interface SuveryJpa extends JpaRepository<SurveyModel, String> {

}
