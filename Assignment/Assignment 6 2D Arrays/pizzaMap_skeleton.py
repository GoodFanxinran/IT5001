import math
from collections import Counter
def createZeroMatrix(n,m):
    return [[0 for i in range(m)] for j in range(n)]

def mTightPrint(m):
    for i in range(len(m)):
        line = ''
        for j in range(len(m[0])):
            line += str(m[i][j])
        print(line)

def cal_dis(a,b): # two coordinate two tuples
    x_2 = (b[0] - a[0]) ** 2
    y_2 = (b[1] - a[1]) ** 2
    dis = math.sqrt(x_2 + y_2)
    return dis

def PDMap(r,c,sites):
    map = createZeroMatrix(r, c)
    for i in range(r):
        for j in range(c):
            dis = []
            for k in range(len(sites)):
                disi = cal_dis((i, j), sites[k])
                dis.append(disi)
            # print(dis)
            dis_count = dict(Counter(dis))
            # print(dis_count)
            min_key = min(dis)
            for key, value in dis_count.items():
                if (value == 1) and (key == min_key):
                    ind = dis.index(min(dis))
                    map[i][j] = ind
                elif (value > 1) and (key == min_key):
                    map[i][j] = 'X'
    return map

#mTightPrint(PDMap(50,80,[[20,10], [30,30],[40,20],[45,55],[10,55],[35,70],[35,60]]))
mTightPrint(PDMap(10,10,[[2,3],[4,9],[7,2]]))

# mTightPrint(PDMap(60,70,[[10,20],[30,20],[40,50]]))
# ex = PDMap(60,70,[[10,20],[30,20],[40,50]])
    
