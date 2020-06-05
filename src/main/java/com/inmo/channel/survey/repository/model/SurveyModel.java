package com.inmo.channel.survey.repository.model;

import com.inmo.channel.survey.util.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Table(
    name = "legal",
    schema = Constants.PROJECT_SCHEMA
    )
public class SurveyModel {

  @Id
  @GeneratedValue
  @Column( columnDefinition = "uuid", updatable = false )
  private UUID legalId;

  private String legalAgreement;

  private String hashCodeLegal;

  private String enabled;

  private String creationUser;

  private String creationTime;

  private String updateUser;

  private String updateTime;

}
