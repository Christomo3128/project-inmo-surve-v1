package com.inmo.channel.expose.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {

	private String legalId;
	
	private String legalAgreement;

	private String hashCodeLegal;

	private String enabled;

	private String creationUser;

	private String creationTime;

	private String updateUser;

	private String updateTime;

}
