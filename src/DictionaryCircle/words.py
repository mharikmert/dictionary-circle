import turkishnlp
from turkishnlp import detector
obj = detector.TurkishNLP()
obj.download()
obj.create_word_set()
print(obj.is_turkish("merhaba"))
