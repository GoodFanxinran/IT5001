# # Question 1
# def describe_data(L):
#     for item in L:
#         print('The type of the element', item, 'is', type(item))
# input_list = [3.1415, True, 42, '88', (1,2), [1,[2]]]
# describe_data(input_list)

# Question 2
# def howManyInt(l):
#     int_num = 0
#     for item in l:
#         if isinstance(item, int):
#             int_num += 1
#     return int_num
# print(howManyInt([1,2,2.3,3,10.0,[2,3,4],'ab',3,'1']))

# # Question 3
# import matplotlib.pyplot as plt
# #This is not a good habit to declare global variables like this
# #But just for our class assignments, let's do this at the moment
# original_wave_sample = [0, 3, 7, 14, 18, 24, 23, 29, 28, 30, 32, 35, 31, 34, 32, 30, 25, 25, 24, 23, 18, 14, 15, 14, 12, 12, 7, 8, 10, 9, 5, 8, 8, 8, 8, 5, 6, 4, 2, 2, 3, -1, -5, -4, -9, -9, -14, -16, -17, -18, -23, -24, -25, -25, -23, -20, -20, -16, -17, -11, -7, -7, 0, 3, 6, 8, 15, 18, 19, 24, 27, 24, 28, 25, 29, 27, 26, 22, 20, 16, 13, 13, 11, 7, 4, 0, 0, 0, 0, -3, -6, -6, -7, -6, -5, -7, -6, -6, -6, -6, -7, -9, -13, -11, -17, -16, -22, -24, -23, -27, -29, -30, -34, -33, -34, -37, -34, -32, -33, -28, -28, -23, -18, -13, -10, -8, 0, 3, 10, 12, 15, 22, 22, 27, 29, 31, 31, 29, 31, 27, 26, 27, 24, 20, 17, 17, 14, 11, 12, 8, 6, 5, 8, 6, 3, 6, 7, 4, 7, 6, 7, 6, 5, 4, 2, 0, -2, -3, -6, -7, -12, -14, -16, -15, -18, -21, -22, -23, -26, -26, -22, -23, -21, -18, -13, -9, -8, -3, -1, 6, 10, 12, 17, 20, 23, 25, 28, 30, 30, 30, 27, 25, 26, 24, 19, 18, 17, 12, 12, 8, 7, 4, 0, -2, -2, -1, -1, -6, -4, -4, -3, -5, -7, -8, -5, -5, -7, -10, -10, -12, -17, -17, -22, -21, -25, -29, -29, -32, -35, -34, -32, -33, -33, -33, -33, -28, -24, -22, -18, -15, -9, -6, 0, 6, 9, 11, 16, 22, 22, 24, 25, 29, 30, 31, 28, 29, 27, 22, 22, 20, 16, 17, 15, 14, 10, 10, 6, 8, 4, 4, 7, 4, 7, 7, 6, 6, 3, 7, 2, 2, 4, 1, 0, -2, -3, -7, -8, -13, -14, -16]
#
# # Just enlarge the numbers
# for i in range(len(original_wave_sample)):
#     original_wave_sample[i]*=1000
#
#
# # For this question, you only need to submit this function
# def filter_wave(wave, times):
#     old_wave = wave
#     L = len(old_wave)
#     for time in range(0, times):
#         new_wave = [0] * L
#         for i in range(0, L):
#             if i == 0:
#                 new_wave[i] = int(old_wave[i] * 0.6 + old_wave[i + 1] * 0.2)
#             elif i == len(old_wave) - 1:
#                 new_wave[i] = int(old_wave[i - 1] * 0.2 + old_wave[i] * 0.6)
#             else:
#                 new_wave[i] = int(old_wave[i - 1] * 0.2 + old_wave[i] * 0.6 + old_wave[i + 1] * 0.2)
#         old_wave = new_wave
#     return old_wave
#
#
# # plt.plot(original_wave_sample)
# # plt.show()
# #
# # new_wave = filter_wave(original_wave_sample,1)
# # plt.plot(new_wave)
# # plt.show()
#
# new_wave = filter_wave(original_wave_sample,200)
# print(new_wave)
# # plt.plot(new_wave)
# # plt.show()

# Question 4

# an example resistor list for testing
# resistor_list = (75, 80, 90, 77, 88, 91, 60, 74, 73, 70, 55, 93, 59)
resistor_list = (10,12,40,12,10)
def matchResistors(R, n):
    R_sort = sorted(R)
    print(R_sort)
    ans = []
    for i in range(len(R_sort)-1):
        for j in range(i+1, len(R_sort)):
            if R_sort[i] + R_sort[j] == n:
                ans.append((R_sort[i], R_sort[j]))
                R_sort[i] = 0
                R_sort[j] = 0
    return ans
print(matchResistors(resistor_list, 22))
