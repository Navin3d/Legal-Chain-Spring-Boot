import logger from 'slf3d';
import { v4 } from "uuid";
import contarctService from '../../services/contract.service';
import { getCCORG1 } from "../../utils/config/ca-client";
import { uploadFile } from "../../services/ipfs.service";
import { IPFSURL } from "../../utils/config/ipfs-client";

const ccp = getCCORG1();

const saveAssetORG1 = async (req, res) => {
    const { userId, documentId = v4(), tittle, description } = req.body;
    const { file } = req.files;
    const fileHash = await uploadFile(file);
    const document = {
        tittle,
        description,
        hash: IPFSURL + fileHash,
        timeStamp: new Date(),
    }
    const documentString = JSON.stringify(document);

    if(!(userId && documentId))
        return res.status(400).json({
            message : "Failed to create asset...",
            status  : 0,
            error   : "userId and documentId is mandatory in request-body...",
        });

    try {
        const result = await contarctService.saveAsset(ccp, userId, documentId, documentString);
        return res.status(201).json({
            message : "Created asset successfully...",
            status  : 1,
            data    : result,
        });
    } catch(err) {
        logger.error(err.message);

        return res.status(500).json({
            message : "Failed to create asset...",
            status  : 0,
            error   : err.message,
        });
    }

}

const getAssetByAssetIdORG1 = async (req, res) => {
    const { userId, documentId } = req.params;

    if(!(userId && documentId))
        return res.status(400).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : "userId and documentId is mandatory in request-body...",
        });

    try {
        const result = await contarctService.getAssetByAssetId(ccp, userId, documentId);
        return res.status(200).json({
            message : "Fetched asset successfully...",
            status  : 1,
            data    : result,
        });
    } catch(err) {
        logger.error(err.message);

        return res.status(500).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : err.message,
        });
    }

}

const getAssetByUserIdORG1 = async (req, res) => {
    const { userId } = req.params;

    if(!userId)
        return res.status(400).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : "userId is mandatory in request-body...",
        });

    try {
        const result = await contarctService.getAssetByUserId(ccp, userId);
        return res.status(200).json({
            message : "Fetched asset successfully...",
            status  : 1,
            data    : result,
        });
    } catch(err) {
        logger.error(err.message);

        return res.status(500).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : err.message,
        });
    }
}

const getAssetByAssetIdsORG1 = async (req, res) => {
    const { userId, documentIds } = req.body;

    if(!(userId && documentIds))
        return res.status(400).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : "userId and documentId is mandatory in request-body...",
        });

    try {
        const result = await contarctService.getAssets(ccp, userId, documentIds);
        return res.status(200).json({
            message : "Fetched asset successfully...",
            status  : 1,
            data    : result,
        });
    } catch(err) {
        logger.error(err.message);

        return res.status(500).json({
            message : "Failed to fetch asset...",
            status  : 0,
            error   : err.message,
        });
    }
}

export default {
    saveAssetORG1,
    getAssetByAssetIdORG1,
    getAssetByUserIdORG1,
    getAssetByAssetIdsORG1,
}
