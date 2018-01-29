"""Your own title() function"""
def tony(title):
    newString = " "
    ch = 0
    while ch<len(title):
        if title[ch-1].isalpha()==False or ch==0:
            newString += title[ch].upper()
            mustCapitalize = False
        else:
            newString += title[ch].lower()
        ch+=1
    return newString

print(tony("visit HOUSTON"))
print(tony("the univeristy of Houston"))
print(tony("guns 'n roses"))
print(tony("ALL IN BIG LETTERS"))
print(tony("8megabytes"))
print(tony("the camelBackNotation"))
k = input("press close to exit")


    
