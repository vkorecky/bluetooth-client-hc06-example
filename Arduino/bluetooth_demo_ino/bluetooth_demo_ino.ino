// **************************************************
// Bluetooth demo
// --------------
// This code only sends on Bluetooth device text:
// "Hi, I'm your Arduino"
//
// Please change PINs by your current configuration
// Default configuration
// RX = 12, TX = 13
// **************************************************

// Includes
// ---------
#include <Arduino.h>
#include <SoftwareSerial.h>

// PINs
int rx = 12;
int tx = 13;

// Communication object
SoftwareSerial serialBT(rx, tx);

// Variables
String data = "";
String message = "Hi ";

// Setup
void setup()
{
  data = "";
  message = "Hi ";
  // initialization of serial port 9600 baud
  serialBT.begin(9600);
  // waits on initialization
  delay(500);
}

// Main code
void loop()
{
  // Check if bluetooth is available
  while (serialBT.available())
  {
    // Read char from bluetooth
    char character = serialBT.read();
    // Check message termination character
    if (character == '\n')
    {
      // Received termination character

      // Concate answer
      message.concat(data);
      message.concat(", I'm your Arduino.");
      serialBT.println(message);   

      // Clear variables
      data = "";
      message = "Hi ";
    } else {
      // Continue read message (concate message)
      data.concat(character);
    }
  }       
}

