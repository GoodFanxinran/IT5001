msg1e = "esbtr dgh abzqg! vhe ghz yzqtcjxx qx qt btgesjz cbxepj!"
msg2e = "qx qe mht?"
msg3e = "fyj frjxhrj qx yqlljs qs mqej frjjx brrbsujl qs b argxx"


d1 = {'a': 'm', 'b': 'a', 'c': 'c', 'd': 'y', 'e': 't', 'f': 'v', 'g': 'o', 'h': 'u', 'i': 'x', 'j': 'e', 'k': 'j', 'l': 'w', 'm': 'f', 'n': 'z', 'o': 'd', 'p': 'l', 'q': 'i', 'r': 'k', 's': 'h', 't': 'n', 'u': 'g', 'v': 'b', 'w': 'q', 'x': 's', 'y': 'p', 'z': 'r'}
d2 = {'a': 'c', 'b': 'a', 'c': 'm', 'd': 'y', 'e': 'v', 'f': 't', 'g': 'o', 'h': 'u', 'i': 'z', 'j': 'e', 'k': 'l', 'l': 'd', 'm': 'f', 'n': 'x', 'o': 'w', 'p': 'j', 'q': 'i', 'r': 'r', 's': 'n', 't': 'b', 'u': 'g', 'v': 'p', 'w': 'q', 'x': 's', 'y': 'h', 'z': 'k'}



def decipher_message(msg,guide):
    length = len(msg)
    ans_list = list()
    for i in range(0, length):
        if msg[i] in guide:
            new = guide.get(msg[i])
            ans_list.append(new)
        else:
            ans_list.append(msg[i])
    ans = ''.join(ans_list)
    return ans

print(decipher_message(msg1e,d1))
print(decipher_message(msg2e,d1))
print(decipher_message(msg3e,d1))
print(decipher_message(msg3e,d2))
