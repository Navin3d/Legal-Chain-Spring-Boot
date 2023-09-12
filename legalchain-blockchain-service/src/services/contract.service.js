//const logger = require('slf3d');
//const getWallet = require('../utils/config/wallet');

import logger from 'slf3d';
import request from "superagent";
import { Gateway } from 'fabric-network';
import getWallet from '../utils/config/wallet';
import { CHANNELNAME, CHAINCODENAME } from '../utils/config';

const date = new Date();
const gateway = new Gateway();
const apiClient = request.agent();

const vaultService = {
    pinFile: (fileModel) => {
      return apiClient.post('http://localhost:8080/record/pin').send(fileModel);
    },
};
  

const prettyJSONString = (inputString) => {
    return JSON.stringify(JSON.parse(inputString), null, 2);
}


const saveAsset = async (ccp, userId, documentId, documentString) => {
    let result = false;
    try {
        const wallet = await getWallet();

        console.log(userId, documentId, documentString);

        await gateway.connect(ccp, {
            wallet,
            identity: userId,
            discovery: { enabled: true, asLocalhost: true }
        });

        const network = await gateway.getNetwork(CHANNELNAME);
        const contract = network.getContract(CHAINCODENAME);

        logger.log(`Submit Transaction: CreateAssert ${documentId}, change the appraisedValue to 350`);
        result = await contract.submitTransaction('CreateAssert', userId, documentId, documentString);
        logger.log('Result: committed');

        const doc = {
            id: documentId,
            insertedAt: `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, "0")}-${(date.getDate()).toString().padStart(2, "0")}` + "T00:00:00",
            ownedBy: userId,
        };

        await vaultService.pinFile(doc);
    } catch (e) {
        logger.error(e.message);
        throw e;
    } finally {
        gateway.disconnect();
    }

    return result;

}

const getAllAsset = async (ccp, userId) => {
    let asset = null;
    try {
        const wallet = await getWallet();

        await gateway.connect(ccp, {
            wallet,
            identity: userId,
            discovery: { enabled: true, asLocalhost: true }
        });

        const network = await gateway.getNetwork(CHANNELNAME);
        const contract = network.getContract(CHAINCODENAME);

        logger.log(`Evaluate Transaction: ReadAllAsset, function returns all assets.`);
        asset = await contract.evaluateTransaction('ReadAllAsset');
        logger.log(`Successfuly fetched the assets`);
    } catch (e) {
        logger.error(e.message);
        throw e;
    } finally {
        gateway.disconnect();
    }

    return prettyJSONString(asset.toString());

}

const getAssetByAssetId = async (ccp, userId, assetId) => {
    let asset = null;
    try {
        const wallet          = await getWallet();

        await gateway.connect(ccp, {
            wallet,
            identity: userId,
            discovery: { enabled: true, asLocalhost: true }
        });

        const network          = await gateway.getNetwork(CHANNELNAME);
        const contract         = network.getContract(CHAINCODENAME);

        logger.log(`Evaluate Transaction: ReadAsset, function returns ${assetId} attributes`);
        asset                  = await contract.evaluateTransaction('ReadAsset', assetId);
        asset                  = JSON.parse(asset);
        const file             = JSON.parse(asset["DocumentData"]);
        asset["DocumentData"]  = file
        logger.log(`Successfuly fetched the assets`);
    } catch (e) {
        console.log(e);
        throw e;
    } finally {
        gateway.disconnect();
    }

    return asset;
}

const getAssetByUserId = async (ccp, userId) => {
    const assetsString = await getAllAsset(ccp, userId);
    const assets = JSON.parse(assetsString);
    let filteredAssets = assets.map(assset => {
        if(assset["UserId"] == userId) {
            const file             = JSON.parse(assset["DocumentData"]);
            assset["DocumentData"] = file
            return assset;
        }
    });
    return filteredAssets;
}

const getAssets = async (ccp, userId, assetIds) => {
    let assets = [];
    try {
        const wallet          = await getWallet();

        await gateway.connect(ccp, {
            wallet,
            identity: userId,
            discovery: { enabled: true, asLocalhost: true }
        });

        const network          = await gateway.getNetwork(CHANNELNAME);
        const contract         = network.getContract(CHAINCODENAME);

        for(const assetId of assetIds) {
            logger.log(`Evaluate Transaction: ReadAsset, function returns ${assetId} attributes`);
            let asset              = await contract.evaluateTransaction('ReadAsset', assetId);
            asset                  = JSON.parse(asset);
            const file             = JSON.parse(asset["DocumentData"]);
            asset["DocumentData"]  = file;
            assets.push(asset);
        }
        logger.log(`Successfuly fetched the assets`);
    } catch (e) {
        console.log(e);
        throw e;
    } finally {
        gateway.disconnect();
    }
    return assets;
}

export default {
    getAssetByAssetId,
    getAssetByUserId,
    getAssets,
    saveAsset,
};
