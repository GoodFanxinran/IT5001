d1 = {'D': 'W', '1': 'W', 'Z': 'W', 'C': 'T', '3': 'T', 'F': 'T', '0': '.', '2': '.', '4': '.', 'B': '^', '+': '^', ';': '^', 'Q': 'E', '7': 'E', '8': 'E', 'X': 'M', 'P': 'M', '!': 'M', '(': ':', ')': ':', '9': ':', '*': ' ', '|': ' ', '#': ' '}
d2 = {'C': 'W', '3': 'W', 'F': 'W', '0': 'T', '2': 'T', '4': 'T', 'B': '.', '+': '.', ';': '.', 'Q': '^', '7': '^', '8': '^', 'D': 'E', '1': 'E', 'Z': 'E', '(': 'M', ')': 'M', '9': 'M', '*': ':', '|': ':', '#': ':', 'X': ' ', 'P': ' ', '!': ' '}
        
def decode_map(mapfile, ddict, outfile):
    with open(mapfile, 'r') as f1:
        list1 = f1.readlines()
    for i in range(0, len(list1)):
        list1[i] = list1[i].rstrip('\n')
    f2 = open(outfile, 'w')
    for item in list1:
        length = len(item)
        ans_list = list()
        for i in range(0, length):
            if item[i] in ddict:
                new = ddict.get(item[i])
                ans_list.append(new)
            else:
                ans_list.append(item[i])
        ans = ''.join(ans_list)
        ans += '\n'
        # print(ans)
        # f2 = open(outfile, 'w')
        f2.write(ans)
    # print(list1)



def find_treasure(mapfile):
    with open(mapfile, 'r') as f1:
        list = f1.readlines()
    for i in range(0, len(list)):
        list[i] = list[i].rstrip('\n')
    print(list)
    rows = len(list)
    columns = len(list[0])
    martrix = [[0 for i in range(columns)] for i in range(rows)]
    for i in range(0, rows):
        for j in range(0, columns):
            martrix[i][j] = list[i][j]

    for i in range(0, rows):
        for j in range(0, columns):
            if martrix[i][j] == 'T' and martrix[i-1][j] == 'T' and martrix[i+1][j] == 'T' and martrix[i][j-1] == 'T' and martrix[i][j+1] == 'T':
                return (i,j)
    # print(martrix)





print("Map 1")
decode_map('encoded_map.txt', d1, 'decoded_map.txt')
print(find_treasure('decoded_map.txt'))

# Uncomment the following for your test cases
'''
print("Map 2")
decode_map('encoded_map2.txt',d1,'decoded_map2.txt')
print(find_treasure('decoded_map2.txt'))

print("Map 3")
decode_map('encoded_map3.txt',d1,'decoded_map3.txt')
print(find_treasure('decoded_map3.txt'))

print("Map 5")
decode_map('encoded_map5.txt',d1,'decoded_map5.txt')
print(find_treasure('decoded_map5.txt'))

print("Map 1 B")
decode_map('encoded_mapB.txt',d2,'decoded_mapB.txt')
print(find_treasure('decoded_mapB.txt'))
'''
