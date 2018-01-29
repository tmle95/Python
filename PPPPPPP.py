Python 3.5.2 (v3.5.2:4def2a2901a5, Jun 25 2016, 22:01:18) [MSC v.1900 32 bit (Intel)] on win32
Type "copyright", "credits" or "license()" for more information.
>>> Date = input("Enter a date in mm/dd/yy format: ")
Enter a date in mm/dd/yy format: 11/27/16
>>> File = input("Enter your input file: ")
Enter your input file: friends.txt
>>> Month, Day, Year = Date.split("/")
>>> Year = int(Year)
>>> File = open(File)
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17 :
		Year += 100
	Birthyear = int(Birthdate[6:])
	if Year - Birthyear > 21
	
SyntaxError: invalid syntax
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17 :
		Year += 100
	Birthyear = int(Birthdate[6:])
	if Year - Birthyear > 21:
		print(Firstname, Lastname)

		
Zoila Reyna
Jose Perez
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 and < 50:
		
SyntaxError: invalid syntax
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 or < 50:
		
SyntaxError: invalid syntax
>>> 
KeyboardInterrupt
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21:
		and Year - Birthyear < 50:
			
SyntaxError: invalid syntax
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21:
		or < 50
		
SyntaxError: invalid syntax
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 and < 50:
		
SyntaxError: invalid syntax
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 or Year - Birthyear > 50:
		print(Firstname, Lastname)

		
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 or Year - Birthyear < 50:
		print(Firstname, Lastname)

		
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21:
		print(Firstname, Lastname)

		
>>> File = open(File)
Traceback (most recent call last):
  File "<pyshell#41>", line 1, in <module>
    File = open(File)
TypeError: invalid file: <_io.TextIOWrapper name='friends.txt' mode='r' encoding='cp1252'>
>>> File = open("friends.txt")
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthmonth, Birthday, Birthyear = Birthdate.split()
	Birthyear = int(Birthyear)
	if Year - Birthyear >= 21 and Year - Birthyear < 50:
		print(Firstname, Lastname)

		
Traceback (most recent call last):
  File "<pyshell#53>", line 5, in <module>
    Birthmonth, Birthday, Birthyear = Birthdate.split()
ValueError: not enough values to unpack (expected 3, got 1)
>>> File = open("friends.txt")
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthdate = int(Birthdate[6:])
	if Year - Birthdate >= 21 and Year - Birthdate < 50:
		print(Firstname, Lastname)

		
Zoila Reyna
Sandra Parker
>>> File.read()
''
>>> File = open("friends.txt")
>>> File.read()
'Zoila Reyna 713-555-3436 03/21/90\nSandra Parker 713-555-3234 10/27/95\nPeter Brown 281-555-4247 11/08/99\nJose Perez 832-555-3141 09/15/01\nAliya Smith 281-555-7364 06/30/99'
>>> File = open("friends.txt
	    
SyntaxError: EOL while scanning string literal
>>> File = open("friends.txt")
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthyear = int(Birthdate[6:])
	Friends 21 = []
	
SyntaxError: invalid syntax
>>> File = open("friends.txt")
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthyear = int(Birthdate[6:])
	Friends21 = []
	for friends in Friends 21:
		
SyntaxError: invalid syntax
>>> File = open("friends.txt")
>>> for word in File:
	Firstname, Lastname, Phonenumber, Birthdate = word.split()
	if Year < 17:
		Year += 100
	Birthyear = int(Birthdate[6:])
	Friends21 = []
	for friends in Friends21:
		if Year - Birthyear >= 21 and Year - Birthyear < 50:
			Friends21 += Firstname, Lastname
	print("Of your 5 friends, 2 are over 21. These are: ", Friends21)

	
Of your 5 friends, 2 are over 21. These are:  []
Of your 5 friends, 2 are over 21. These are:  []
Of your 5 friends, 2 are over 21. These are:  []
Of your 5 friends, 2 are over 21. These are:  []
Of your 5 friends, 2 are over 21. These are:  []
>>> 
