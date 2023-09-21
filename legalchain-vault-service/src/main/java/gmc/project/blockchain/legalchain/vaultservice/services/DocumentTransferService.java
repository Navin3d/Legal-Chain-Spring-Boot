package gmc.project.blockchain.legalchain.vaultservice.services;

import gmc.project.blockchain.legalchain.vaultservice.models.ResponseModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.DocumentRequestModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.LicenseInfo;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.RequestLicenseModel;

public interface DocumentTransferService {
	public void initiate2fAuth(String documentType, String email);
	
	public ResponseModel<Object> requestDocument(DocumentRequestModel doc);
	public LicenseInfo transferDrivingLicense(RequestLicenseModel req);
}
