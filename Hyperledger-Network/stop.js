const { exec } = require('child_process');

const commands = [
    "rm stop.log",
    './network.sh down >> stop.log && echo "Network is down..."',
    "node clean.js >> stop.log && echo OK",
    "rm -r ../legalchain-blockchain-service/src/utils/wallet && echo OK",
    "docker volume prune -f >> stop.log && echo OK",
];

const executeCommand = (command) => {
    return new Promise((resolve, reject) => {
        exec(command, (error, stdout, stderr) => {
            if (error) {
                // console.error(`Error executing command: ${command}\n${error.message}`);
                resolve(error);
            } else {
                // console.log(`Command executed: ${command}`);
                resolve(stdout);
            }
        });
    });
}

const executeCommandsSequentially = async (commands) => {
    try {

        for (const command of commands) {
            console.log(`Executing: ${command}`);
            await executeCommand(command);
        }

        console.log('All commands executed successfully.');
    } catch (error) {
        console.error('Error executing commands:', error);
    }
}

executeCommandsSequentially(commands);
