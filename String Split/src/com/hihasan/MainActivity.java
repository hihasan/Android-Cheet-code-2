package com.hihasan;

import java.io.StringReader;

public class MainActivity
{
    public static void main(String[]args){
        String str="Cash In Tk 1,200.00 from 01848207862 successful. Fee Tk 0.00. Balance Tk 1,225.97. TrxID 6JJ6GP052S at 19/10/2019 20:01. Download bKash App: bKa.sh/8app";

        String [] data1=str.split("Tk",2);
        System.out.println("Type: "+data1[0]);
        String str2=data1[1];

        String [] data2=str2.split("from",2);
        System.out.println("Ammount: "+data2[0]);
        String str3=data2[1];

        String [] data3=str3.split("successful",2);
        System.out.println("From: "+data3[0]);
        String str4=data3[1];
//        System.out.println(str4);

        String []data4=str4.split("Balance Tk",2);
        String str5=data4[1];
        String [] data5=str5.split("\\. TrxID",2);
        System.out.println("Total Ammount:  "+data5[0]);
        String str6=data5[1];

        String [] data6=str6.split("at",2);
        System.out.println("Transaction ID: "+data6[0]);
        String str7=data6[1];

        String [] data7=str7.split("\\.",2);
        System.out.println("Date & Time: "+data7[0]);

    }
}
