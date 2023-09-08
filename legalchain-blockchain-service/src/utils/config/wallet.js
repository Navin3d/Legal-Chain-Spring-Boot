//const path = require("path");
//const logger = require("slf3d");
//const { Wallets } = require('fabric-network');

import path from 'path';
import logger from 'slf3d';
import { Wallets } from 'fabric-network';

import { fileURLToPath } from 'url';
import { dirname } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const walletPath = path.join(__dirname, '../wallet');

const getWallet = async () => {
    let wallet;
	if (walletPath) {
		wallet = await Wallets.newFileSystemWallet(walletPath);
		logger.info(`Built a file system wallet at ${walletPath}`);
	} else {
		wallet = await Wallets.newInMemoryWallet();
		logger.info('Built an in memory wallet');
	}

	return wallet;
};

export default getWallet;
