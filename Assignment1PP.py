"""Tony-Trong Le Assignment #1 COSC 1306 MW 2:30-4:00 Fall 2016 Computes guests shares of a bill"""
bill = float(input("Enter the total check amount: "))
tiprate = float(input("Enter tip percentage: "))
nguests = float(input("Enter the number of guets: "))
tip = bill * (tiprate/100)
print("the tip is %.2f." %tip)
total = bill + tip
print("Our total after is %.2f" %total)
share = total/nguests
print("Each of us must pay $ %.2f" %share)
k=input("press close to exit")
