#include <Arduino.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Servo.h>
#include <Keypad.h>

//PINES ----------------
const int LED_VERDE = 2;
const int LED_ROJO  = 4;
const int BUZZER    = 3;
const int SERVO_PIN = 5;

//LCD ----------------
LiquidCrystal_I2C lcd(0x27, 16, 2);

//SERVO ----------------
Servo servo;
const int SERVO_CERRADO = 0;
const int SERVO_ABIERTO = 90;

//KEYPAD ----------------
const byte ROWS = 4;
const byte COLS = 4;
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};
byte rowPins[ROWS] = {6,7,8,9};
byte colPins[COLS] = {10,11,12,13};
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

//VARIABLES ----------------
char buffer[10];
byte index = 0;
bool bloqueado = false;

//BUZZER ----------------
void beepSuccess() {
  tone(BUZZER, 1200, 150);
  delay(180);
  tone(BUZZER, 1800, 150);
}

void beepError() {
  tone(BUZZER, 400, 400);
}
void beepLock() {
  tone(BUZZER, 250, 700);
}

//LCD ----------------
void mostrar(const char* l1, const char* l2 = "") {
  lcd.clear();
  lcd.setCursor(0,0); lcd.print(l1);
  lcd.setCursor(0,1); lcd.print(l2);
}

void setup() {
  Serial.begin(9600);

  pinMode(LED_VERDE, OUTPUT);
  pinMode(LED_ROJO, OUTPUT);
  pinMode(BUZZER, OUTPUT);

  servo.attach(SERVO_PIN);
  servo.write(SERVO_CERRADO);

  lcd.init();
  lcd.backlight();

  mostrar("Sistema listo", "Ingrese clave");
}

void loop() {

  // COMANDOS DESDE JAVA
  if (Serial.available()) {
    String cmd = Serial.readStringUntil('\n');
    cmd.trim();

    if (cmd.startsWith("OK:")) {
      long tiempoMs = cmd.substring(3).toInt();
      if (tiempoMs <= 0) return;

      digitalWrite(LED_VERDE, HIGH);
      digitalWrite(LED_ROJO, LOW);
      beepSuccess();
      servo.write(SERVO_ABIERTO);

      long tiempoSeg = (tiempoMs + 999) / 1000;
      for (long i = tiempoSeg; i > 0; i--) {
        char linea2[16];
        snprintf(linea2, sizeof(linea2), "Tiempo: %lds", i);
        mostrar("Puerta Abierta", linea2);
        delay(1000);
      }
    }

    else if (cmd.startsWith("ERR:")) {
      int intentos = cmd.substring(4).toInt();

      digitalWrite(LED_ROJO, HIGH);
      digitalWrite(LED_VERDE, LOW);
      beepError();

      char linea2[16];
      sprintf(linea2, "Quedan %d", intentos);

      mostrar("Clave Incorrecta", linea2);
    }

    else if (cmd.startsWith("LOCK:")) {
      int tiempo = cmd.substring(5).toInt();
      bloqueado = true;

      for (int i = tiempo; i > 0; i--) {
        mostrar("BLOQUEADO", ("Espere " + String(i) + "s").c_str());
        digitalWrite(LED_ROJO, HIGH);
        delay(1000);
      }

      bloqueado = false;
      digitalWrite(LED_ROJO, LOW);
      mostrar("Sistema listo", "Ingrese clave");
    }

    else if (cmd == "RESET") {
      servo.write(SERVO_CERRADO);
      digitalWrite(LED_VERDE, LOW);
      mostrar("Sistema listo", "Ingrese clave");
    }
  }

  if (bloqueado) return;

  // LECTURA KEYPAD
  char k = keypad.getKey();
  if (!k) return;

  if (k == '*') {
    index = 0;
    mostrar("Borrado");
    return;
  }

  if (k == '#') {
    buffer[index] = '\0';

    Serial.print("CODE:");
    Serial.println(buffer);

    index = 0;
    mostrar("Procesando", "...");
    return;
  }

  if (index < 9) {
    buffer[index++] = k;
    mostrar("Ingresando", buffer);
  }
}