package com.accolite.au.services;

import com.accolite.au.dto.TrainingDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface TrainingService {

    TrainingDTO markAndUpdateTrainingData(TrainingDTO trainingDTO, char type);

    TrainingDTO getTrainingData(int sessionId, int studentId);

    ObjectNode getAllTrainingData(char type);
}
