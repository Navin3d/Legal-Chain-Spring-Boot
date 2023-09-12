import express from "express";
import contractController from './contract.controller';
import walletController from './wallet.controller';

const org1Router           = express.Router();

org1Router.get  ("/contract/get/:userId"                     ,  contractController.getAssetByUserIdORG1);
org1Router.get  ("/contract/get/:userId/:documentId"         ,  contractController.getAssetByAssetIdORG1);
org1Router.post ("/contract/get/many"                        ,  contractController.getAssetByAssetIdsORG1);
org1Router.post ("/contract"                                 ,  contractController.saveAssetORG1);

org1Router.post ("/wallet/admin"                             ,  walletController.createWalletAdmin);
org1Router.post ("/wallet/user"                              ,  walletController.createWalletUser);

export default org1Router;
