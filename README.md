# FIX Parser a.k.a. FIXer [![Generic badge](https://img.shields.io/badge/Version-1.0-Green.svg)](https://github.com/ntewari29/FIXer) [![Build Status](https://travis-ci.org/ntewari29/FIXer.svg?branch=master)](https://travis-ci.org/ntewari29/FIXer) [![GitHub forks](https://img.shields.io/github/forks/Naereen/StrapDown.js.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/ntewari29/FIXer/network)
FIXer is a simple FIX Utility that is aimed at simplifying FIX comparisons and FIX translations which is focused but not limited to enabling greater sense of readability on raw FIX messages and reliability in a way that minimizes the error prone method of eyeballing & FIX dictionary navigations.

### Introduction
The Financial Information eXchange (FIX) protocol is an electronic communications protocol initiated in 1992 for international real-time exchange of information related to financial security transactions and markets. 

### Problem Statement

<details>
  <summary>Raw FIX, click to expand</summary>
  
  ```javascript
    8=FIX.4.2 ; 9=176 ; 35=8 ; 49=PHLX ; 56=PERS ; 52=20071123-05:30:00.000 ; 11=ATOMNOCCC9990900 ; 20=3 ; 150=E ; 39=E ; 55=MSFT ; 167=CS ; 54=1 ; 38=15 ; 40=2 ; 44=15 ; 58=PHLX EQUITY TESTING ; 59=0 ; 47=C ; 32=0 ; 31=0 ; 151=15 ; 14=0 ; 6=0 ; 10=128 ;
  ```
</details>

* Understanding a FIX message is a learning curve in itself and usually takes years of experience/exposure before raw FIX actually starts making sense.
* It often becomes challenging for new commers to adapt and understand the usage of FIX tags thereby testing these key value pairs become even more challenging.

#### FIXer is aimed at simplifying what has seemed tedious so far. 

### Features Offered 
* Translating raw FIX into human readable format supported delimiter ';'.

### Upcoming Features
* Translating values in constant tags like 20, 21, 35, etc..
* FIX comparison.
* Support more delimiters.

### Usage
* What you would simply want to do is to invoke ```translate``` method in ```Fixer```, the method accepts raw FIX as a parameter and returns the translated message as a String.

```
Fixer.translate(<Your RAW FIX msg>)
```

## Bugs and Feedback
For bugs, feedbacks, questions and discussions do use [Github Issues](https://github.com/ntewari29/FIXer/issues).

## Core Team
* [Nishant Tewari](https://github.com/ntewari29)
