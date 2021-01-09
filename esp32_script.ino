#include "ChainableLED.h"
#include "WiFi.h"
#include "HTTPClient.h"
#include "ArduinoJson.h"
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


int sensorPin = 34;    // select the input pin for the potentiometer
int sensorValue = 0;  // variable to store the value coming from the sensor
const int ledPin_clk = 15; //Output pin for LED
const int ledPin_data = 33; //Output pin for LED
const int resolutionAnalogRead = 12;
const int maxAnalogvalue = pow(2,resolutionAnalogRead);
ChainableLED leds(ledPin_clk, ledPin_data, NUM_LEDS);//defines the pin used on arduino.
float hue = 0.0;
HTTPServer myServer = HTTPServer();

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
   StaticJsonDocument<300> doc;  //Memory pool
   deserializeJson(doc, res);  //Parse message
   
    leds.setColorRGB(0, doc["red"], doc["green"], doc["blue"]);
    res->println("Color changed");
  });
 
  ResourceNode * nodeRoute2 = new ResourceNode("/route2", "GET", [](HTTPRequest * req, HTTPResponse * res){
    res->println("Route 2");
  });
 
  ResourceNode * nodeRoute3 = new ResourceNode("/route3", "GET", [](HTTPRequest * req, HTTPResponse * res){
    res->println("Route 3");
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
  if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
  
   HTTPClient http;   
  
   http.begin("http://172.20.10.2:8081/lamp/color");  //Specify destination for HTTP request
   http.addHeader("Content-Type", "application/json");             //Specify content-type header

   int httpResponseCode = http.POST("{\"red\":\"0\",\"green\":\"0\",\"blue\":\"255\"}");   //Send the actual POST request
  
   if(httpResponseCode>0){
  
    String response = http.getString();                       //Get the response to the request
  
    Serial.println(httpResponseCode);   //Print return code
    Serial.println(response);           //Print request answer
  
   }else{
  
    Serial.print("Error on sending POST: ");
    Serial.println(httpResponseCode);
  
   }
  
   http.end();  //Free resources
  
 }else{
  
    Serial.println("Error in WiFi connection");   
  
 }
  
  delay(10000);
  sensorValue = analogRead(sensorPin);
  hue = float(sensorValue)/float(maxAnalogvalue);
  
    
    
  delay(50);
}
