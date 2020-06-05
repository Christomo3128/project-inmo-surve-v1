package com.inmo.channel.expose.web;

import static org.springframework.http.HttpStatus.OK;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inmo.channel.expose.web.model.SurveyRequest;
import com.inmo.channel.expose.web.model.SurveyResponse;
import com.inmo.channel.survey.business.SurveyService;

import io.reactivex.Observable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "Survey")
@RequestMapping("/channel/inmo/survey/v1")
@Slf4j
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method to get getSurveyAll.
	 *
	 * @param request {@link XXXRequest}
	 * @return {@link SurveyResponse}
	 */
	@GetMapping(value = "/survey", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_STREAM_JSON_VALUE })
	@ApiOperation(value = "Listado de Parametros por Tipo.", response = SurveyResponse.class, httpMethod = "GET", tags = {
			"Parameter" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Proceso Satisfactorio."),
			@ApiResponse(code = 400, message = "El cliente envi&oacute; datos incorrectos."),
			@ApiResponse(code = 500, message = "Error en el proceso.") })
	@ResponseStatus(OK)
	public Observable<SurveyResponse> getSurveyAll() {
		log.info("Starting SurveyController.getSurveyAll");
		return surveyService.getSurvey()
				.map(p -> modelMapper.map(p, SurveyResponse.class))
				.doOnComplete(() -> log.info("Ending SurveyController.getSurveyAll"))
				.doOnError(e -> log.error("Error in SurveyController.getSurveyAll: ", e));
	}

}
