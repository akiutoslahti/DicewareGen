# DicewareGen

DicewareGen is a secure passphrase generator utilizing Diceware wordlists (http://world.std.com/~reinhold/diceware.html).
DicewareGen utilizes Javas SecureRandom class for choosing words from wordlist (https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html). 
Javas SecureRandom provides a cryptographically strong random number generator. In this application it uses /dev/urandom on Linux systems.

DicewareGen provides four distinctive wordlists for passphrase generation. Included are three English wordlists and one Finnish wordlist.

English wordlists include:
Original Diceware wordlist by Arnold Reinhold,
Diceware wordlist 8k version by Arnold Reinhold and
Diceware wordlist which "contains fewer Americanisms and obscure words" by Alan Beale
http://world.std.com/~reinhold/diceware.html

Finnish wordlist, also known as Noppaware, is a work of Kai Puolamäki.
http://users.ics.aalto.fi/kaip/noppaware/

# Usage

To use DicewareGen you should clone the repository by:
```git clone git://github.com/akiutoslahti/DicewareGen.git```
After changing to DicewareGen directory, you can create secure passphrases with command:
```java DicewareGen```
To print help, which instructs of using arguments, use command:
```java DicewareGen help```
Contents of help included also here:
```
DicewareGen 1.0, wordlist based passphrase generator
Usage:
    java DicewareGen [wordlist]
    java DicewareGen [wordlist] [pp_length]
    java DicewareGen [wordlist] [pp_length] [pp_count]

Arguments:
    wordlist,	possible wordlists:
        en -> Diceware wordlist, by Arnold Reinhold
        en_8k -> Diceware wordlist 8k, by Arnold Reinhold
        en_beale -> Diceware wordlist, edited by Alan Beale
        fi -> Finnish Diceware(Noppaware) wordlist, by Kai Puolamäki
    pp_length,	passphrase length as wordcount
    pp_count,	number of passphrases to be generated

    Default (no arguments):
    wordlist: en
    pp_length: 6
    pp_count: 10
```
