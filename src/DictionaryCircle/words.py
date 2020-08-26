import turkishnlp
from turkishnlp import detector
obj = detector.TurkishNLP()
obj.download()
obj.create_word_set()

file1 = open('words.txt','r')
Lines = file1.readlines()
count = 0
for line in Lines:
    if obj.is_turkish(line):
        file2 = open('turkishWords.txt','a')
        file2.write(line)
        count+= 1
