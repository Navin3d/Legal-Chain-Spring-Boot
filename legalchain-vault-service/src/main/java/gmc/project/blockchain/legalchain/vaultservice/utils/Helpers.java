package gmc.project.blockchain.legalchain.vaultservice.utils;

import java.util.Random;

public class Helpers {
	
	public Helpers() {}
	
	public static int generateRandomSixDigitNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return randomNumber;
    }

}
