package com.example.ibrhm.parentlock.Operations;

/**
 * Created by ibrhm on 12.04.2017.
 */

public class StringParserf {

    public String stringParse(String time,String select)
    {
        String command = "";

        if(select.equals("karamazovkardesler"))
        {
            if(time.equals("10"))
            {
               command = "1";
            }else if(time.equals("15"))
            {
                command = "2";
            }else if(time.equals("20"))
            {
                command = "3";
            }else if(time.equals("25"))
            {
                command = "4";
            }else if(time.equals("30"))
            {
                command = "5";
            }else if(time.equals("40"))
            {
                command = "6";
            }else if(time.equals("50"))
            {
                command = "7";
            }
        }else if(select.equals("Math"))
        {
            if(time.equals("10"))
            {
                command = "8";
            }else if(time.equals("15"))
            {
                command = "9";
            }else if(time.equals("20"))
            {
                command = "10";
            }else if(time.equals("25"))
            {
                command = "11";
            }else if(time.equals("30"))
            {
                command = "12";
            }else if(time.equals("40"))
            {
                command = "13";
            }else if(time.equals("50"))
            {
                command = "14";
            }
        }else if(select.equals("Turkish"))
        {
            if(time.equals("10"))
            {
                command = "15";
            }else if(time.equals("15"))
            {
                command = "16";
            }else if(time.equals("20"))
            {
                command = "17";
            }else if(time.equals("25"))
            {
                command = "18";
            }else if(time.equals("30"))
            {
                command = "19";
            }else if(time.equals("40"))
            {
                command ="20";
            }else if(time.equals("50"))
            {
                command = "21";
            }
        }else if(select.equals("ColorGame"))
        {
            command = "22";

        }else if(select.equals("English"))
        {
            command = "23";
        }else if(select.equals("Anything"))
        {
            command = "24";
        }else if(select.equals("Slient"))
        {
            if(time.equals("10"))
            {
                command = "25";
            }else if(time.equals("20"))
            {
                command = "26";
            }else if(time.equals("30"))
            {
                command = "27";
            }else if(time.equals("60"))
            {
                command = "28";
            }else if(time.equals("120"))
            {
                command ="29";
            }else if(time.equals("180"))
            {
                command = "30";
            }else if(time.equals("300"))
            {
                command = "31";
            }

        }else if(select.equals("Locked"))
        {
            if(time.equals("10"))
            {
                command = "32";
            }else if(time.equals("20"))
            {
                command = "33";
            }else if(time.equals("30"))
            {
                command = "34";
            }else if(time.equals("60"))
            {
                command = "35";
            }else if(time.equals("120"))
            {
                command = "36";
            }else if(time.equals("180"))
            {
                command = "37";
            }else if(time.equals("300"))
            {
                command ="38";
            }
        }


            return command;

}
}
