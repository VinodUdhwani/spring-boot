package in.co.codeplanet.spring_boot.utility;

import java.util.Random;

public class Otp {

    public String generateOtp(){
        Random random=new Random();
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=1;i<=4;i++)
        stringBuffer.append(random.nextInt(0,9));
        return stringBuffer.toString();
    }
}
