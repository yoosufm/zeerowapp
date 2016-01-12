package com.zeerow.qa.util.api.common;

/**
 * Created by yoosuf on 7/13/2015.
 */
public enum ApplicationErrorsMessage {
    EC001(1,"001","Nickname is required","","",""),
    EC002(2,"002","Email already exist in system","","",""),
    EC003(3,"003","User could not be registered","","",""),
    EC004(4,"004","User not found","","",""),
    EC005(5,"005","Log insertion failed","","",""),
    EC006(6,"006","User id required","","",""),
    EC007(7,"007","Valid Email is required","","",""),
    EC008(8,"008","Social is required","","",""),
    EC009(9,"009","Password is required","","",""),
    EC010(10,"010","The field is_rec_marketing_email must be either TRUE OR FALSE","","",""),
    EC011(11,"011","User_type value is not correct","","",""),
    EC012(12,"012","api_token has not been sent","","",""),
    EC013(13,"013","Platform field is required","","",""),
    EC014(14,"014","udid field is required","","",""),
    EC015(15,"015","Input data not found","","",""),
    EC016(16,"016","Required input data key name is not correct or not sent","","",""),
    EC017(17,"017","Required input value found empty","","",""),
    EC018(18,"018","API token generation failed","","",""),
    EC019(19,"019","User's token update failed","","",""),
    EC020(20,"020","Valid image is required","","",""),
    EC021(21,"021","Image size exceed maximum allowed limit","","",""),
    EC022(22,"022","This nickname already exists","중복된 닉네임이 존재합니다.","重复的绰号。","重複されたニックネームが存在します。"),
    EC023(23,"023","API token is not valid","","",""),
    EC024(24,"024","API token has benn expired already","","",""),
    EC025(25,"025","Account is not activated yet","","",""),
    EC026(26,"026","PIN is not valid","","",""),
    EC027(27,"027","Language code is not valid","","",""),
    EC028(28,"028","Please use a nickname in English between 2~16 characters","닉네임은 영문2~16자로 입력해주세요. ","在2至16的英文昵称,请输入。","ニックネームは英文2~16者で入力してください。"),
    EC029(29,"029","Please use an alphanumeric and atleast one special character in password between 8~12 characters","비밀번호는 영문 대소문자, 숫자 조합 8~12자로 입력해주세요. ","在yg称times和密码。","暗証番号は、英大文字・小文字、数字の8~12者で入力してください。"),
    EC030(30,"030","Nickname is not registered","","",""),
    EC031(31,"031","Nickname and Password do not match","","",""),
    EC032(32,"032","Email is not registered. Please check again","","",""),
    EC999(999,"999","Exception occurred or unknown error","","","");


    public int code;
    public String errorMessageEn;
    public String errorMessageKo;
    public String errorMessageCh;
    public String errorMessageJp;
    public String errorCode;

    private ApplicationErrorsMessage(int code, String errorCode, String errorMessageEn, String errorMessageKo, String errorMessageCh, String errorMessageJp) {
        this.code = code;
        this.errorMessageEn = errorMessageEn;
        this.errorMessageKo = errorMessageKo;
        this.errorMessageCh = errorMessageCh;
        this.errorMessageJp = errorMessageJp;
        this.errorCode = errorCode;
    }

    public static ApplicationErrorsMessage fromInt(int code) {
        switch(code) {
            case 1	: return EC001 ;
            case 2	: return EC002 ;
            case 3	: return EC003 ;
            case 4	: return EC004 ;
            case 5	: return EC005 ;
            case 6	: return EC006 ;
            case 7	: return EC007 ;
            case 8	: return EC008 ;
            case 9	: return EC009 ;
            case 10	: return EC010 ;
            case 11	: return EC011 ;
            case 12	: return EC012 ;
            case 13	: return EC013 ;
            case 14	: return EC014 ;
            case 15	: return EC015 ;
            case 16	: return EC016 ;
            case 17	: return EC017 ;
            case 18	: return EC018 ;
            case 19	: return EC019 ;
            case 20	: return EC020 ;
            case 21	: return EC021 ;
            case 22	: return EC022 ;
            case 23	: return EC023 ;
            case 24	: return EC024 ;
            case 25	: return EC025 ;
            case 26	: return EC026 ;
            case 27	: return EC027 ;
            case 28	: return EC028 ;
            case 29	: return EC029 ;
            case 30 : return EC030;
            case 31 : return EC031;
            case 32 : return EC032;
            case 999 : return EC999 ;
        }

        // we had some exception handling for this
        // as the contract for these was between 2 independent applications
        // liable to change between versions (mostly adding new stuff)
        // but keeping it simple here.
        return null;
    }

}
