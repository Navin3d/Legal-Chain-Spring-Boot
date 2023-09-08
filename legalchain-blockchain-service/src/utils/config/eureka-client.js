import eureka from 'eureka-js-client';
import { EUREKAHOST } from "../config/index.js";
const Eureka = eureka.Eureka;
const registerWithEureka = (appName, PORT) => {
    const client = new Eureka({
        instance: {
            app: appName,
            instanceId: `${appName}:${PORT}`,
            hostName: EUREKAHOST,
            ipAddr: "127.0.0.1",
            port: {
                '$': PORT,
                '@enabled': 'true',
            },
            vipAddress: appName,
            homePageUrl: `http://${EUREKAHOST}:${PORT}/patient-details/status`,
            dataCenterInfo: {
                '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
                name: 'MyOwn',
            },
            registerWithEureka: true,
            fetchRegistry: true,
        },
        //retry 10 time for 3 minute 20 seconds.
        eureka: {
            host: EUREKAHOST,
            port: 8010,
            servicePath: '/eureka/apps/',
            maxRetries: 1,
            requestRetryDelay: 2000,
        },
    })

    client.logger.level('debug')

    client.start(error => {
        console.log(error || `${appName} registered`)
    });



    function exitHandler(options, exitCode) {
        if (options.cleanup) {
        }
        if (exitCode || exitCode === 0) console.log(exitCode);
        if (options.exit) {
            client.stop();
        }
    }

    client.on('deregistered', () => {
        process.exit();
        console.log('after deregistered');
    })

    client.on('started', () => {
        console.log("Eureka host  " + EUREKAHOST);
    })

    process.on('SIGINT', exitHandler.bind(null, { exit: true }));
};
export default registerWithEureka;
