#include "ChainableLED.h"
#include "WiFi.h"
#include "HTTPClient.h"
#include <Arduino_JSON.h>
#include "ESPAsyncWebServer.h"
#include "AsyncTCP.h"
#include <HTTPSServer.hpp>
#include <HTTPServer.hpp>
#include <SSLCert.hpp>
#include <HTTPRequest.hpp>
#include <HTTPResponse.hpp>
#include "Wire.h"
#include "Adafruit_Sensor.h"
#include "Adafruit_BME280.h"
using namespace httpsserver;

//Defines the num of LEDs used, The undefined 
//will be lost control.
#define NUM_LEDS  1

const char* ssid = "Dorian";
const char* password = "zderzder";
String serverName = "http://localhost:8081";

unsigned long lastTime = 0;
unsigned long timerDelay = 5000;

const int ledPin_clk = 15; //Output pin for LED
const int ledPin_data = 33; //Output pin for LED
const int resolutionAnalogRead = 12;
const int maxAnalogvalue = pow(2,resolutionAnalogRead);
ChainableLED leds(ledPin_clk, ledPin_data, NUM_LEDS);//defines the pin used on arduino.
float hue = 0.0;
HTTPServer myServer = HTTPServer();

int capteurLum = 34;

int ind1; 
int ind2;
int ind3;
int ind4;
int ind5;
int ind6;
String red = "";
String green = "";
String blue = "";

void setup() {
  Serial.begin(9600);

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
 
  Serial.println("Timer set to 5 seconds (timerDelay variable), it will take 5 seconds before publishing the first reading.");

  
  ResourceNode * nodeRoute1 = new ResourceNode("/changeColor", "POST", [](HTTPRequest * req, HTTPResponse * res){
   byte buffer[256];
   String myString = "";
   while(!(req->requestComplete())) {
     size_t s = req->readBytes(buffer, 256);
     res->write(buffer, s);
     //strcpy((char *)buffer,"0123");
     myString = String((char *)buffer);
   }
   JSONVar doc = JSON.parse(myString);

    if (JSON.typeof(doc) == "undefined") {
      Serial.println("Parsing input failed!");
      return;
    }

    Serial.print("JSON.typeof(myObject) = ");
    Serial.println(JSON.typeof(doc)); // prints: object
  
    if (doc.hasOwnProperty("red")) {
      Serial.print("doc[\"red\"] = ");
      Serial.println((bool) doc["red"]);
    }
    if (doc.hasOwnProperty("green")) {
      Serial.print("doc[\"green\"] = ");
      Serial.println((bool) doc["green"]);
    }
    if (doc.hasOwnProperty("blue")) {
      Serial.print("doc[\"blue\"] = ");
      Serial.println((bool) doc["blue"]);
    }
    String docString = JSON.stringify(doc);
    Serial.print("JSON en string = ");
    Serial.println(docString);
  
    ind1 = docString.indexOf(':');  //finds location of first :
    ind2 = docString.indexOf(',', ind1+1 ); //finds location of first ,
    String redz = docString.substring(ind1+1, ind2);
    ind3 = docString.indexOf(':', ind1+1 ); //finds location of second :
    ind4 = docString.indexOf(',', ind2+1 ); //finds location of second :
    String greenz = docString.substring(ind3+1, ind4);
    ind5 = docString.indexOf(':', ind3+1 ); //finds location of second :
    ind6 = docString.indexOf('}', ind4+1 ); //finds location of second :
    String bluez = docString.substring(ind5+1, ind6);
    Serial.print("redz = ");
    Serial.println(redz);
    Serial.print("greenz = ");
    Serial.println(greenz);
    Serial.print("bluez = ");
    Serial.println(bluez);
  
    red = redz;
    green = greenz;
    blue = bluez;
  
    leds.setColorRGB(0, redz.toInt(), greenz.toInt(), bluez.toInt());
  
  });
 
  ResourceNode * nodeRoute2 = new ResourceNode("/getColor", "GET", [](HTTPRequest * req, HTTPResponse * res){
    String response = "{\"red\":" + red + ",\"green\":" + green + ",\"blue\":" + blue + "}";
    res->println(response);
  });
 
  ResourceNode * nodeRoute3 = new ResourceNode("/getLuminosity", "GET", [](HTTPRequest * req, HTTPResponse * res){
    String luminosity = String(analogRead(capteurLum));
    String response = "{\"luminosity\":" + luminosity + "}";
    res->println(response);
  });
  
  myServer.registerNode(nodeRoute1);
  myServer.registerNode(nodeRoute2);
  myServer.registerNode(nodeRoute3);
  
  
  myServer.start();
    
  if (myServer.isRunning()) {
    Serial.println("Server ready.");
  }else{
    Serial.println("Server could not be started.");
  }

}

void loop() {

  myServer.loop();

  
  
//  if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
//  
//   HTTPClient http;   
//  
//   http.begin("http://172.20.10.2:8081/lamp/color");  //Specify destination for HTTP request
//   http.addHeader("Content-Type", "application/json");             //Specify content-type header
//
//   int httpResponseCode = http.POST("{\"red\":\"0\",\"green\":\"0\",\"blue\":\"255\"}");   //Send the actual POST request
//  
//   if(httpResponseCode>0){
  
//    String response = http.getString();                       //Get the response to the request
//  
//    Serial.println(httpResponseCode);   //Print return code
//    Serial.println(response);           //Print request answer
//  
//   }else{
//  
//    Serial.print("Error on sending POST: ");
//    Serial.println(httpResponseCode);
//  
//   }
//  
//   http.end();  //Free resources
//  
// }else{
//  
//    Serial.println("Error in WiFi connection");   
//  
// }
}
