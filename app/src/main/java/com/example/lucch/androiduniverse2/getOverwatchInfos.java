package com.example.lucch.androiduniverse2;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class getOverwatchInfos {

   private final String USER_AGENT = "Mozilla/5.0";

   public static void main(String[] args) throws Exception {
   
      getOverwatchInfos http = new getOverwatchInfos();
   
      System.out.print(http.getUserInfos("MatrisisDeus-2188", "heroes"));
      System.out.print(http.getTopTwenty("CompetitiveRank", "3"));
      
   }

   public String getTopTwenty(String type, String plateforme) throws Exception {
      /*
      type :
      - CompetitiveRank
      - Kad
      - EliminationsPM
      - Score
      
      plateforme :
      - PC : 3
      - PS : 2
      - Xbox : 1
      */
      
      String url = "https://overwatchtracker.com/api/leaderboards/?board=" + type + "&mode=1&platform=" + plateforme;
   
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
   
   	// optional default is GET
      con.setRequestMethod("GET");
   
   	//add request header
      con.setRequestProperty("User-Agent", USER_AGENT);
   
      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);
   
      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
   
      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
      }
      in.close();
   
   	//print result
      return response.toString();
   
   }

	// HTTP GET request
   public String getUserInfos(String btag, String infos) throws Exception {
   
   /*
   infos :
   
   - blob
   - stats
   - achievements
   - heroes
   */
   
      String url = "https://owapi.net/api/v3/u/" + btag + "/" + infos;
   
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
   
   	// optional default is GET
      con.setRequestMethod("GET");
   
   	//add request header
      con.setRequestProperty("User-Agent", USER_AGENT);
   
      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);
   
      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
   
      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
      }
      in.close();
   
   	//print result
      return response.toString();
   
   }

}