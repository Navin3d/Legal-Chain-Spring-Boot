//const fs = require('fs');
//const path = require('path');
//const FabricCAServices = require('fabric-ca-client');
//const { CAHOSTORG1, CAHOSTORG2 } = require("./index");
//const logger = require("slf3d");
import logger from 'slf3d';
import fs from 'fs';
import path from 'path';
import FabricCAServices from 'fabric-ca-client';
import { CAHOSTORG1, CAHOSTORG2 } from "./index";

import { fileURLToPath } from 'url';
import { dirname } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

export const getCCORG1 = () => {
    const ccpPath = path.resolve(__dirname, '..', '..', '..', '..', 'Hyperledger-Network', 'organizations', 'peerOrganizations', 'org1.example.com', 'connection-org1.json');
    const fileExists = fs.existsSync(ccpPath);
    if (!fileExists) {
        throw new Error(`no such file or directory: ${ccpPath}`);
    }
    const contents = fs.readFileSync(ccpPath, 'utf8');

    const ccp = JSON.parse(contents);

    return ccp;
};

export const getCCORG2 = () => {
    // load the common connection configuration file
    const ccpPath = path.resolve(__dirname, '..', '..', '..', '..',  'Hyperledger-Network', 'organizations', 'peerOrganizations', 'org2.example.com', 'connection-org2.json');
    const fileExists = fs.existsSync(ccpPath);
    if (!fileExists) {
        throw new Error(`no such file or directory: ${ccpPath}`);
    }
    const contents = fs.readFileSync(ccpPath, 'utf8');

    const ccp = JSON.parse(contents);

    return ccp;
}

export const getCAClientOrg1 = () => {
    const ccp = getCCORG1();

    logger.log(`Loaded the network configuration located at 'organizations/peerOrganizations/org1.example.com/connection-org1.json'`);

    const caInfo = ccp.certificateAuthorities[CAHOSTORG1];
    const caTLSCACerts = caInfo.tlsCACerts.pem;
    const caClient = new FabricCAServices(caInfo.url, { trustedRoots: caTLSCACerts, verify: false }, caInfo.caName);

    logger.log(`Built a CA Client named ${caInfo.caName}`);
    return caClient;
};

export const getCAClientOrg2 = () => {
    const ccp  = getCCORG2();

    logger.log(`Loaded the network configuration located at 'organizations/peerOrganizations/org2.example.com/connection-org2.json'`);

    const caInfo = ccp.certificateAuthorities[CAHOSTORG2];
    const caTLSCACerts = caInfo.tlsCACerts.pem;
    const caClient = new FabricCAServices(caInfo.url, { trustedRoots: caTLSCACerts, verify: false }, caInfo.caName);

    logger.log(`Built a CA Client named ${caInfo.caName}`);
    return caClient;
};

// module.exports = {
//     getCCORG1,
//     getCCORG2,
    
//     getCAClientOrg1,
//     getCAClientOrg2,
// };
