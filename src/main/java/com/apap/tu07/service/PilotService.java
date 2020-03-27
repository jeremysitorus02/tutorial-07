package com.apap.tu07.service;

import java.util.Optional;

import com.apap.tu07.model.PilotModel;

/**
 * PilotService
 */
public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);

    PilotModel addPilot(PilotModel pilot);

    void deletePilotByLicenseNumber(String licenseNumber);

    Optional<PilotModel> getPilotDetailById(long id);

	void updatePilot(long pilotId, PilotModel pilot);

	void deletePilot(PilotModel pilot);
}