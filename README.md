# TvDialog

[![](https://jitpack.io/v/coderJohnZhang/TvDialog.svg)](https://jitpack.io/#coderJohnZhang/TvDialog)

## Introduction

Android TV Common Dialog Library

## Include

- EditDialog--- AlertDialog with EditText

- PasswordDialog -- Pin box for TV Parental Control and Program Lock

- ToastDialog -- Toast with icon, also can be used as Tips Box

## Features

- Use DataBinding

-  For TV App

- Easy expansion


## ScreenRecord

EditDialog<br><br>
<img src="https://github.com/coderJohnZhang/TvDialog/blob/master/art/EditDialog.png" width="600"><br><br><br>

PasswordDialog<br><br>
<img src="https://github.com/coderJohnZhang/TvDialog/blob/master/art/PasswordDialog.png" width="600"><br><br><br>

ToastDialog<br><br>
<img src="https://github.com/coderJohnZhang/TvDialog/blob/master/art/ToastDialog.png" width="600"><br><br><br>

UI operation<br><br>
<img src="https://github.com/coderJohnZhang/TvDialog/blob/master/art/demo.gif" width="600"><br><br><br>

## How to use

Step 1. Add the JitPack repository to your build file

### gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
Step 2. Add the dependency

### gradle

	dependencies {
	        compile 'com.github.coderJohnZhang:TvDialog:v1.0.1'
	}
	
### maven

	<dependency>
	    <groupId>com.github.coderJohnZhang</groupId>
	    <artifactId>TvDialog</artifactId>
	    <version>v1.0.1</version>
	</dependency>


## About me

Email: coder.john.cheung@gmail.com<br><br>
CSDN blog: http://blog.csdn.net/johnwcheung<br><br>
Github: https://github.com/coderJohnZhang

## License

		Copyright 2017 John Cheung

		Licensed under the Apache License, Version 2.0 (the "License");
		you may not use this file except in compliance with the License.
		You may obtain a copy of the License at

			http://www.apache.org/licenses/LICENSE-2.0

		Unless required by applicable law or agreed to in writing, software
		distributed under the License is distributed on an "AS IS" BASIS,
		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		See the License for the specific language governing permissions and
		limitations under the License.
