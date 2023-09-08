import express from "express";
import contractController from './contract.controller';
import walletController from './wallet.controller';

const org2Router           = express.Router();

org2Router.get  ("/contract/get/:userId"                ,  contractController.getAssetByUserIdORG2);
org2Router.get  ("/contract/get/:userId/:documentId"    ,  contractController.getAssetByAssetIdORG2);
org2Router.post ("/contract"                            ,  contractController.saveAssetORG2);

org2Router.post ("/wallet/admin"                        ,  walletController.createWalletAdmin);
org2Router.post ("/wallet/user"                         ,  walletController.createWalletUser);


export default org2Router;
