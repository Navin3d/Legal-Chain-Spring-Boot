//const path            = require("path");
//const cors            = require("cors");
//const YAML            = require('yamljs');
//const express         = require("express");
//const bodyParser      = require("body-parser");
//const logger          = require("slf3d");
//const swaggerUi       = require('swagger-ui-express');
//const expressFile     = require("express-fileupload");
//const org1Controller  = require("./controller/org1");
//const org2Controller  = require("./controller/org2");
//const { PORT }        = require("./utils/config");

import path from 'path';
import cors from 'cors';
import YAML from 'yamljs';
import express from 'express';
import bodyParser from 'body-parser';
import logger from 'slf3d';
import swaggerUi from 'swagger-ui-express';
import expressFile from 'express-fileupload';
import org1Controller from './controller/org1/index.js';
import org2Controller from './controller/org2/index.js';
import { PORT } from "./utils/config/index";

import { fileURLToPath } from 'url';
import { dirname } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const swaggerYamlPath = path.resolve(__dirname, "./utils/config/swagger.yaml");
const swaggerConfig   = YAML.load(swaggerYamlPath);

const comprehensiveApp = express();
comprehensiveApp.use(cors());
comprehensiveApp.use(expressFile());
comprehensiveApp.use(bodyParser.json());
comprehensiveApp.use('/docs', swaggerUi.serve, swaggerUi.setup(swaggerConfig));

comprehensiveApp.use("/legal", org1Controller);
comprehensiveApp.use("/civil", org2Controller);

comprehensiveApp.listen(PORT, () => {
  logger.info(`The comprehensive smartcontract API is started successfully and running in PORT: ${PORT}`);
});
