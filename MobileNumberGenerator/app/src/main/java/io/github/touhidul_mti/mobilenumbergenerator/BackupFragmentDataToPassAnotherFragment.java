package io.github.touhidul_mti.mobilenumbergenerator;

/**
 * Created by Touhidul_MTI on 17-Mar-16.
 */
public class BackupFragmentDataToPassAnotherFragment {
    static String mobileNumber;
    public static void setMobileNumber(String number){
        mobileNumber = number;
    }
    public static String getMobileNumber(){
        return mobileNumber;
    }
}
